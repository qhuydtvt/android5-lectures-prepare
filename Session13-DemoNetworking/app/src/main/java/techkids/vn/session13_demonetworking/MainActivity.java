package techkids.vn.session13_demonetworking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendGETRequest();
    }


    void sendGETRequest() {
        String URL = "https://a5-web.herokuapp.com/";

        // 1
        OkHttpClient client = new OkHttpClient();

        // 2: Build request
        final Request request = new Request.Builder()
                .url(URL)
                .build();

        // 3: Send request
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // 4
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // 5
                String body = response.body().string();
                Log.d(TAG, String.format("onResponse: body - %s", body));
            }
        });
        // 6
    }

    void sendAddPost(String title, String content) {
        String url = String.format("https://a5-web.herokuapp.com/addpost?title=%s&content=%s", title, content);
    }


}
