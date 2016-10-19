package techkids.vn.demonetworking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.toString();
    public final static String URL = "https://a5-server.herokuapp.com";

    private PostAdapter postAdapter;

    @BindView(R.id.rv_main)
    public RecyclerView rvMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        postAdapter = new PostAdapter(Post.list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        rvMain.setLayoutManager(linearLayoutManager);
        rvMain.setHasFixedSize(true);
        rvMain.setAdapter(postAdapter);

        sendGET();
    }

    private void sendGET() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(URL)
                .build();

        Log.d(TAG, "Sending GET request");
        client.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.d(TAG, String.format("onFailure: %s", e));
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String body = response.body().string();
                        Post.list.clear();
                        for (Post post : (new Gson()).fromJson(body, Post[].class)) {
                            Post.list.add(post);
                        }
                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                postAdapter.notifyDataSetChanged();
                            }
                        });
                    }
                });
    }

    @OnClick(R.id.bt_send_post)
    public void sendPOST() {
        OkHttpClient client = new OkHttpClient();
        final Post post = new Post("She goes to work",
                "Yeah, she goes to work and I feel lonely. Nothing that I can do now");

        Log.d(TAG, String.format("Post %s", (new Gson()).toJson(post)));
        RequestBody requestBody = RequestBody.create(
                MediaType.parse("application/json"),
                new Gson().toJson(
                        new Post("She goes to work", "Yeah, she goes to work and I feel lonely. Nothing that I can do now")));

        Request request = new Request.Builder()
                .url(URL)
                .post(requestBody)
                .build();

        client.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.d(TAG, String.format("Post failed: %s", e));
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String body = response.body().string();
                        Log.d(TAG, body);
                        ResponseJSONModel responseJSONModel = (new Gson()).fromJson(body, ResponseJSONModel.class);
                        if(responseJSONModel.getCode() == 1) {
                            Post.list.add(post);
                            MainActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    postAdapter.notifyDataSetChanged();
                                }
                            });
                        }
                    }
                });

    }
}
