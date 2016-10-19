package techkids.vn.demonetworking;

import com.google.gson.annotations.SerializedName;

/**
 * Created by apple on 10/18/16.
 */

public class ResponseJSONModel {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
