package blak.test.log.activities;

import ch.qos.logback.classic.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class MainActivity extends FragmentActivity {

    private static final String TAG = "@@@";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        testLogLevels();

        {
            ch.qos.logback.classic.Logger log = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(TAG);
            log.setLevel(Level.ALL);
        }

        Logger log = LoggerFactory.getLogger(TAG);

        log.debug("debug.start()");
        log.info("info hello world {}", 3);
        log.warn("warn hello world {}", 3);
        log.error("erro hello world {}", 3);
        log.trace("trace hello world {}", 4);
    }

    private void testLogLevels() {
        // get a logger instance named "com.foo". Let us further assume that the
        // logger is of type  ch.qos.logback.classic.Logger so that we can
        // set its level
        ch.qos.logback.classic.Logger logger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("com.foo");

        //set its Level to INFO. The setLevel() method requires a logback logger
        logger.setLevel(Level.INFO);
        Logger barlogger = LoggerFactory.getLogger("com.foo.Bar");

        // This request is enabled, because WARN >= INFO
        logger.warn("Low fuel level.");

        // This request is disabled, because DEBUG < INFO.
        logger.debug("Starting search for nearest gas station.");

        // The logger instance barlogger, named "com.foo.Bar",
        // will inherit its level from the logger named
        // "com.foo" Thus, the following request is enabled
        // because INFO >= INFO.
        barlogger.info("Located nearest gas station.");

        // This request is disabled, because DEBUG < INFO.
        barlogger.debug("Exiting gas station search");
    }
}
