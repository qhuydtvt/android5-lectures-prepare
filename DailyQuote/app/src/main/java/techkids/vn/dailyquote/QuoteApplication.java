package techkids.vn.dailyquote;

import android.app.Application;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.sromku.simple.storage.SimpleStorage;
import com.sromku.simple.storage.Storage;

import techkids.vn.dailyquote.constants.Constants;
import techkids.vn.dailyquote.managers.FileManager;
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
        FileManager.init(this);
        initImageLoader();

//        final Storage internalStorage = SimpleStorage.getInternalStorage(this);
//
//        ImageLoader.getInstance().loadImage(Constants.UNPLASH_API, new ImageLoadingListener() {
//            @Override
//            public void onLoadingStarted(String imageUri, View view) {
//
//            }
//
//            @Override
//            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
//
//            }
//
//            @Override
//            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
//                FileManager.getInstance().save("unplash", loadedImage);
//                Log.d(TAG, "Loading image done");
//            }
//
//            @Override
//            public void onLoadingCancelled(String imageUri, View view) {
//
//            }
//        });
    }

    private void initImageLoader() {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);
    }
}
