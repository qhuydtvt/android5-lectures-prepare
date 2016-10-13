package techkids.vn.dailyquote.activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;
import techkids.vn.dailyquote.R;
import techkids.vn.dailyquote.fragments.LoginFragment;
import techkids.vn.dailyquote.fragments.QuoteFragment;
import techkids.vn.dailyquote.managers.Preference;
import techkids.vn.dailyquote.models.FragmentEvent;
import techkids.vn.dailyquote.models.ServiceEvent;
import techkids.vn.dailyquote.services.UnplashDownloadService;

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
    }

    @Subscribe
    public void onEvent(FragmentEvent fragmentEvent) {
        changeFragment(fragmentEvent.getFragment(), fragmentEvent.isAddToBackStack());
    }

    @Subscribe
    public void OnServiceEvent(ServiceEvent serviceEvent) {
        Log.d(TAG, "OnServiceEvent");

        Intent intent = new Intent(this, UnplashDownloadService.class);

        startService(intent);
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
