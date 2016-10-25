package techkids.vn.a5tumblelog.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 10/23/16.
 */

public class Post {
    private String title;
    private String content;

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public static final List<Post> list = new ArrayList<>();
}
