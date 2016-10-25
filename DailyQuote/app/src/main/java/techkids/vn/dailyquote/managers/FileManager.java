package techkids.vn.dailyquote.managers;

import android.content.Context;
import android.graphics.Bitmap;

import com.sromku.simple.storage.SimpleStorage;
import com.sromku.simple.storage.Storage;

import java.io.File;

/**
 * Created by apple on 10/13/16.
 */

public class FileManager {
    private Storage storage;
    private final static String IMAGE_DIR = "images";
    private final static String IMAGE_FILE_FORMAT = "unplash_%s.png";

    private FileManager(Context context) {
        storage = SimpleStorage.getInternalStorage(context);
    }

    public void createImage(Bitmap bitmap, String fileName) {
        storage.createFile(IMAGE_DIR, fileName, bitmap);
    }

    public File loadImageFile(String fileName) {
        return storage.getFile(IMAGE_DIR, fileName);
    }

    public void createImage(Bitmap bitmap, int index) {
        createImage(bitmap, String.format(IMAGE_FILE_FORMAT, index));
    }

    public File loadImageFile(int index) {
        return loadImageFile(String.format(IMAGE_FILE_FORMAT, index));
    }

    private static FileManager instance;
    public static FileManager getInstance() {
        return instance;
    }

    public static void init(Context context) {
        instance = new FileManager(context);
    }
}
