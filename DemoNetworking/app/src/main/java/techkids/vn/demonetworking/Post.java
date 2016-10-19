package techkids.vn.demonetworking;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by apple on 10/18/16.
 */

public class Post {
    @SerializedName("title")
    private String title;

    @SerializedName("content")
    private String content;

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static final ArrayList<Post> list = new ArrayList<>();
}
