package blak.test.log.activities;

import blak.test.log.log.LoggerManager;
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
        LoggerManager.getLogger(LoggerType.LOGCAT).error("Terrible error");
        LoggerManager.getLogger(LoggerType.LOGCAT).warn("Using default config");
        LoggerManager.getLogger(LoggerType.LOGCAT).info("Database updated");
        LoggerManager.getLogger(LoggerType.LOGCAT).debug("Response : {some_json}");
        LoggerManager.getLogger(LoggerType.IMAGE).trace("testAppLog() called");

        LoggerManager.getLogger(LoggerType.LOGENTRIES).error("[Logentries] Terrible error");
        LoggerManager.getLogger(LoggerType.LOGENTRIES).warn("[Logentries] Using default config");
        LoggerManager.getLogger(LoggerType.LOGENTRIES).info("[Logentries] Database updated");
        LoggerManager.getLogger(LoggerType.LOGENTRIES).debug("[Logentries] Response : {some_json}");
        LoggerManager.getLogger(LoggerType.LOGENTRIES).trace("[Logentries] testAppLog() called");

        logException();
    }

    private void logException() {
        try {
            throw new IllegalStateException("Test exception");
        } catch (IllegalStateException e) {
            LoggerManager.getLogger(LoggerType.LOGCAT).error("", e);
        }
    }
}
