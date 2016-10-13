package techkids.vn.dailyquote.managers;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.sromku.simple.storage.SimpleStorage;
import com.sromku.simple.storage.Storage;

import java.io.File;

/**
 * Created by apple on 10/12/16.
 */

public class FileManager {
    private static final String TAG = FileManager.class.toString();
    private static final String IMAGE_DIR = "images";
    private Storage storage;

    private FileManager(Context context) {
        storage = SimpleStorage.getInternalStorage(context);
    }

    public void save(String fileName, Bitmap bitmap) {
        Log.d(TAG, String.format("Saving image: %s", fileName));
        storage.createFile(IMAGE_DIR, fileName, bitmap);
    }

    public File getImageFile(String fileName) {
        return storage.getFile(IMAGE_DIR, fileName);
    }

    public void saveUnplashImage(int index, Bitmap bitmap) {
        this.save(String.format("unplash_%s", index), bitmap);
    }

    public File getUnplashImage(int index) {
        return getImageFile(String.format("unplash_%s", index));
    }

    private static FileManager instance;
    public static  FileManager getInstance() {
        return instance;
    }

    public static void init(Context context) {
        instance = new FileManager(context);
    }
}
