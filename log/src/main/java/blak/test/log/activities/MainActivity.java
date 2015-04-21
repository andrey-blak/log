package blak.test.log.activities;

import blak.test.log.log.LogManager;
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
        LogManager.getLogger(LoggerType.LOGCAT).error("Terrible error");
        LogManager.getLogger(LoggerType.LOGCAT).warn("Using default config");
        LogManager.getLogger(LoggerType.LOGCAT).info("Database updated");
        LogManager.getLogger(LoggerType.LOGCAT).debug("Response : {some_json}");
        LogManager.getLogger(LoggerType.TRACE).trace("testAppLog() called");

        LogManager.getLogger(LoggerType.FILE).error("[File] Terrible error");
        LogManager.getLogger(LoggerType.FILE).warn("[File] Using default config");
        LogManager.getLogger(LoggerType.FILE).info("[File] Database updated");
        LogManager.getLogger(LoggerType.FILE).debug("[File] Response : {some_json}");
        LogManager.getLogger(LoggerType.FILE).trace("[File] testAppLog() called");

        logException();
    }

    private void logException() {
        String json = "{json}";
        try {
            throw new IllegalStateException("Test exception");
        } catch (IllegalStateException ex) {
            LogManager.getLogger(LoggerType.LOGCAT).error("Failed to parse json message {}", json, ex);
            LogManager.exception(ex);
        }
    }
}
