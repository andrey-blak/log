package blak.test.log.activities;

import blak.test.log.log.AppLogger;
import blak.test.log.log.LoggerType;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class MainActivity extends FragmentActivity {
    private static final String TAG = "@@@";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        testAppLog();
    }

    private void testAppLog() {
        AppLogger.getLogger(LoggerType.LOGCAT).error("Terrible error");
        AppLogger.getLogger(LoggerType.LOGCAT).warn("Using default config");
        AppLogger.getLogger(LoggerType.LOGCAT).info("Database updated");
        AppLogger.getLogger(LoggerType.LOGCAT).debug("Response : {some_json}");
        AppLogger.getLogger(LoggerType.IMAGE).trace("testAppLog() called");

        AppLogger.getLogger(LoggerType.LOGENTRIES).error("[Logentries] Terrible error");
        AppLogger.getLogger(LoggerType.LOGENTRIES).warn("[Logentries] Using default config");
        AppLogger.getLogger(LoggerType.LOGENTRIES).info("[Logentries] Database updated");
        AppLogger.getLogger(LoggerType.LOGENTRIES).debug("[Logentries] Response : {some_json}");
        AppLogger.getLogger(LoggerType.LOGENTRIES).trace("[Logentries] testAppLog() called");
    }
}
