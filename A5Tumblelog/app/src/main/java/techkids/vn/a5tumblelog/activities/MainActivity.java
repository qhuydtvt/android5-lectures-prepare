package techkids.vn.a5tumblelog.activities;

import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import techkids.vn.a5tumblelog.R;
import techkids.vn.a5tumblelog.adapters.PostAdapter;
import techkids.vn.a5tumblelog.constants.Constants;
import techkids.vn.a5tumblelog.jsonmodels.PostJSONModel;
import techkids.vn.a5tumblelog.models.Post;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.toString();

    @BindView(R.id.rv_post)
    public RecyclerView rvPost;

    private PostAdapter postAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        setupUI();

        sendGETRequest();
    }

    private void setupUI() {
        // 1: Setup layoutmanager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
//        StaggeredGridLayoutManager
        rvPost.setLayoutManager(layoutManager);

        // 2: Setup Adapter
        postAdapter = new PostAdapter();
        rvPost.setAdapter(postAdapter);
    }

    private void sendPOSTRequest() {
        // 1
        OkHttpClient client = new OkHttpClient();

        // 2: Create Request
        // 2.1 : Create request data (body)
//        FormBody formBody = new FormBody.Builder()
//                .add("title", "Yesterday")
//                .add("content", "Oh my trouble seemd so far away")
//                .build();

        PostJSONModel postJSONModel = new PostJSONModel("Yet another day to come", "A new day arrived and I felt great");
        String jsonBOdy = new Gson().toJson(postJSONModel);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonBOdy);

        // 2.2: Create POST request
        Request request = new Request.Builder()
                .url(Constants.TUMBLELOG_URL)
                .post(requestBody)
                .build();

        //2.3: Send request
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, String.format("onFailure %s", e));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String body = response.body().string();
                Log.d(TAG, String.format("onResponse %s", body));
            }
        });
    }

    private void sendGETRequest() {
        // 1
        OkHttpClient client = new OkHttpClient();

        // 2: Create Request based on URL
        Request request = new Request.Builder()
                .url(Constants.TUMBLELOG_URL)
                .build();

        //3: Send request
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, String.format("onFailure %s", e));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String body = response.body().string();
                    Log.d(TAG, String.format("onResponse %s", body));

                    PostJSONModel[] postJSONModels = (new Gson())
                            .fromJson(body, PostJSONModel[].class);

                    Post.list.clear();
                    for(PostJSONModel postJSONModel : postJSONModels) {
                        Post.list
                                .add(new Post(
                                        postJSONModel.getTitle(),
                                        postJSONModel.getContent()));
                    }

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
