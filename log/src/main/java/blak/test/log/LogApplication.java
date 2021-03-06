package blak.test.log;

import blak.test.log.log.LogManager;

import android.app.Application;

public class LogApplication extends Application {
    private static LogApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;

        initLogManager();
    }

    public static LogApplication getInstance() {
        return sInstance;
    }

    private void initLogManager() {
        LogManager.init();
    }
}
