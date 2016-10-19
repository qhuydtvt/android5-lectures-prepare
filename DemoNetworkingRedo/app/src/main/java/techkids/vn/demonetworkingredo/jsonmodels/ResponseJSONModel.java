package techkids.vn.demonetworkingredo.jsonmodels;

import com.google.gson.annotations.SerializedName;

/**
 * Created by apple on 10/19/16.
 */

public class ResponseJSONModel {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @Override
    public String toString() {
        return code == 1?"OK":"NOT OK";
    }
}
