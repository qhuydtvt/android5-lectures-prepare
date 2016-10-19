package techkids.vn.a5tumblelog.jsonmodels;

import com.google.gson.annotations.SerializedName;

/**
 * Created by apple on 10/20/16.
 */

public class PostJSONModel {
    @SerializedName("title")
    private String title;

    @SerializedName("content")
    private String content;

    public PostJSONModel(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
