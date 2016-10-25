package techkids.vn.dailyquote.managers;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by apple on 10/12/16.
 */

public class Preference {
    private static final String KEY = "Quote";
    private static final String USER_NAME_KEY = "Username";

    private static final String OFFLINE_IMAGE_COUNT_KEY = "ImageCount";

    private SharedPreferences sharedPreferences;
    
    public Preference(Context context) {
        // Context can be Actiivty or Application
        sharedPreferences = context.getSharedPreferences(KEY, Context.MODE_PRIVATE);
    }

    public String getUsername() {
        return sharedPreferences.getString(USER_NAME_KEY, null);
    }

    public void putUsername(String username) {
        sharedPreferences
                .edit()
                .putString(USER_NAME_KEY, username)
                .commit();
    }

    public int getImageCount() {
        return sharedPreferences
                .getInt(OFFLINE_IMAGE_COUNT_KEY, -1);
    }

    public void putImageCount(int imageCount) {
        sharedPreferences
                .edit()
                .putInt(OFFLINE_IMAGE_COUNT_KEY, imageCount)
                .commit();
    }

    private static Preference instance;
    public static Preference getInstance() {
        return instance;
    }

    public static void init(Context context) {
        instance = new Preference(context);
    }
}
