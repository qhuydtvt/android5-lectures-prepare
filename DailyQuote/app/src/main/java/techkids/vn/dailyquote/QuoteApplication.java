package techkids.vn.dailyquote;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import techkids.vn.dailyquote.fragments.FileManager;
import techkids.vn.dailyquote.managers.NetworkManager;
import techkids.vn.dailyquote.managers.Preference;

/**
 * Created by apple on 10/12/16.
 */

public class QuoteApplication extends Application {

    private static final String TAG = QuoteApplication.class.toString();

    @Override
    public void onCreate() {
        super.onCreate();

        Preference.init(this);
        NetworkManager.init(this);
        FileManager.init(this);
        initImageLoader();

        if(NetworkManager.getInstance().isConnectedToInternet()) {
            Log.d(TAG, "Connected");
        } else {
            Log.d(TAG, "Not connected");
        }
    }

    private void initImageLoader() {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);
    }
}
