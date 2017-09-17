package info.infiniteloops.discuss;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.multidex.MultiDexApplication;
import android.support.v7.app.AppCompatDelegate;

import info.infiniteloops.discuss.util.Constants;

public class App extends MultiDexApplication {

/*
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }
*/

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void attachBaseContext(Context base) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(base);
        String lang = prefs.getString(Constants.PREF_APP_LANGUAGE, "");
        super.attachBaseContext(ShowChatContextWrapper.wrap(base));
    }
}
