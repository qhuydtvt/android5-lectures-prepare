package techkids.vn.dailyquote.services;

import android.app.IntentService;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.util.Log;
import android.view.View;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import techkids.vn.dailyquote.constants.Constants;
import techkids.vn.dailyquote.managers.FileManager;
import techkids.vn.dailyquote.managers.Preference;

/**
 * Created by apple on 10/12/16.
 */

public class UnplashDownloadService extends IntentService {

    private static final String TAG = UnplashDownloadService.class.toString();
    private static final int IMAGE_NUM = 10;

    public UnplashDownloadService() {
        this(null);
    }

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public UnplashDownloadService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "onHandleIntent");

        for(int imageIndex = 0; imageIndex < IMAGE_NUM; imageIndex++) {
            Bitmap bitmap = ImageLoader.getInstance().loadImageSync(Constants.UNPLASH_API);
            FileManager.getInstance().saveUnplashImage(imageIndex, bitmap);
            Preference.getInstance().putImageCount(imageIndex + 1);
        }
    }

    private class IncrementImageLoadingListener implements ImageLoadingListener {

        private FileManager fileManager;
        private int imageIndex;

        public IncrementImageLoadingListener() {
            fileManager = FileManager.getInstance();
            imageIndex = 0;
        }

        @Override
        public void onLoadingStarted(String imageUri, View view) {

        }

        @Override
        public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

        }

        @Override
        public synchronized void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
            fileManager.saveUnplashImage(imageIndex, loadedImage);
            imageIndex++;
        }

        @Override
        public void onLoadingCancelled(String imageUri, View view) {

        }

        public int getImageIndex() {
            return imageIndex;
        }
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "Destroyed");
        super.onDestroy();
    }
}
