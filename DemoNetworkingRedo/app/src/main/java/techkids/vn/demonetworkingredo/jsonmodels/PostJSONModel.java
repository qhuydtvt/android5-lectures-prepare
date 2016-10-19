package techkids.vn.demonetworkingredo.jsonmodels;

/**
 * Created by apple on 10/19/16.
 */

public class PostJSONModel {
    private String title;
    private String content;

    public PostJSONModel(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
