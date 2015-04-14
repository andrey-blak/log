package blak.test.log;

import blak.test.log.log.LogManager;

import android.app.Application;

public class LogApplication extends Application {
    private static LogApplication sInstance;

    private LogManager mLogManager;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;

        initLogManager();
    }

    public static LogApplication getInstance() {
        return sInstance;
    }

    public static LogManager getLogManager() {
        return sInstance.mLogManager;
    }

    private void initLogManager() {
        mLogManager = new LogManager();
    }
}
