package techkids.vn.dailyquote.services;

import android.app.IntentService;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;

import com.nostra13.universalimageloader.core.ImageLoader;

import techkids.vn.dailyquote.constants.Constants;
import techkids.vn.dailyquote.managers.FileManager;
import techkids.vn.dailyquote.managers.Preference;

/**
 * Created by apple on 10/23/16.
 */

public class UnplashDowloadService extends IntentService {

    private static final String TAG = UnplashDowloadService.class.toString();
    private static final int IMAGE_COUNT = 10;

    public UnplashDowloadService() {
        super("UnplashDowloadService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "onHandleIntent");
        for(int i = 0; i < IMAGE_COUNT; i++) {
            Log.d(TAG, String.format("Unplash #%s", i));
            Log.d(TAG, "Downloading image");

            // 1: Download image
            Bitmap bitmap = ImageLoader
                    .getInstance()
                    .loadImageSync(Constants.UNPLASH_API);
            Log.d(TAG, "Done downloading!");

            Log.d(TAG, "Saving downloaded image");
            // 2: Save
            FileManager.getInstance().createImage(bitmap, i);
            Log.d(TAG, "Done saving");

            // 3: Mark
            Preference.getInstance().putImageCount(i + 1);
        }

//        while(true) {
//            try {
//                Thread.sleep(500);
//                Log.d(TAG, "Blah");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
    }
}
