package techkids.vn.demonetworkingredo.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import techkids.vn.demonetworkingredo.R;
import techkids.vn.demonetworkingredo.constants.Constants;
import techkids.vn.demonetworkingredo.jsonmodels.PostJSONModel;
import techkids.vn.demonetworkingredo.jsonmodels.ResponseJSONModel;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendPost();
    }

    void sendGet() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(Constants.TUMBLELOG_URL)
                .build();
        client.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String body = response.body().string();

                        Log.d(TAG, String.format("onResponse: %s", body));
                    }
                });
    }

    void sendPost() {
        OkHttpClient client = new OkHttpClient();
        PostJSONModel postJSONModel = new PostJSONModel("Yesterday", "Yesterday, Oh my trouble seemed so far away");
        String requestBodyString = (new Gson()).toJson(postJSONModel);

        RequestBody requestBody = RequestBody.create(
                MediaType.parse("application/json"),
                requestBodyString);

        Request request = new Request.Builder()
                .url(Constants.TUMBLELOG_URL)
                .post(requestBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()) {
                    String body = response.body().string();
                    ResponseJSONModel responseJSONModel = new Gson().fromJson(body, ResponseJSONModel.class);
                    Log.d(TAG, String.format("POST: onResponse: %s", body));
                    Log.d(TAG, String.format("POST: onResponse: %s", responseJSONModel));
                }
            }
        });
    }
}
