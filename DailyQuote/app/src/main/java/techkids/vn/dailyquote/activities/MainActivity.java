package techkids.vn.dailyquote.activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.sromku.simple.storage.SimpleStorage;
import com.sromku.simple.storage.Storage;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;
import techkids.vn.dailyquote.R;
import techkids.vn.dailyquote.fragments.LoginFragment;
import techkids.vn.dailyquote.fragments.QuoteFragment;
import techkids.vn.dailyquote.managers.Preference;
import techkids.vn.dailyquote.models.FragmentEvent;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.toString();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        EventBus.getDefault().register(this);

        if(Preference.getInstance().getUsername() == null) {
            changeFragment(new LoginFragment(), false);
        } else {
            changeFragment(new QuoteFragment(), false);
        }

        /*
        * if (isConnectedToInternet) {
        *   loadOnline();
        *   prepareOfflineData();
        * } else {
        *   loadOffLine();
        * }
        * */

//
//        Storage storage = SimpleStorage.getInternalStorage(this);
////        storage.createFile("text", "quote.txt", "No pain, no gain");
//        String content = storage.readTextFile("text", "quote.txt");
//
//        Log.d(TAG, String.format("Read done : %s", content));

    }

    @Subscribe
    public void onEvent(FragmentEvent fragmentEvent) {
        changeFragment(fragmentEvent.getFragment(), fragmentEvent.isAddToBackStack());
    }


    public void changeFragment(Fragment fragment, boolean addToBackstack) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_container, fragment);
        if(addToBackstack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }
}
