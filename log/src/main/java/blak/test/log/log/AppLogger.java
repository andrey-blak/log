package blak.test.log.log;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.android.LogcatAppender;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Context;
import ch.qos.logback.core.FileAppender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppLogger {

    private AppLogger() {
    }

    public static void init() {
        for (LoggerType loggerType : LoggerType.values()) {
            ch.qos.logback.classic.Logger logger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(loggerType.name());
            initLogger((LoggerContext) LoggerFactory.getILoggerFactory(), logger, loggerType);
        }
    }

    public static Logger getLogger(LoggerType loggerType) {
        return LoggerFactory.getLogger(loggerType.name());
    }

    private static void initLogger(ch.qos.logback.core.Context loggerContext, ch.qos.logback.classic.Logger logger, LoggerType type) {
        if (type.shouldUseLogcat()) {
            LogcatAppender logcatAppender = createLogcatAppender(loggerContext, type);
            logger.addAppender(logcatAppender);
        }
        if (type.shouldUseLogentries()) {
            FileAppender<ILoggingEvent> fileAppender = getFileAppender(loggerContext);
            logger.addAppender(fileAppender);
        }
        logger.setLevel(Level.ALL);
    }

    private static PatternLayoutEncoder getPatternLayoutEncoder(Context loggerContext, Pattern pattern) {
        PatternLayoutEncoder encoderFull = new PatternLayoutEncoder();
        encoderFull.setContext(loggerContext);
        encoderFull.setPattern(pattern.getValue());
        encoderFull.start();
        return encoderFull;
    }

    private static LogcatAppender createLogcatAppender(Context loggerContext, LoggerType type) {
        PatternLayoutEncoder encoder = getPatternLayoutEncoder(loggerContext, type.getPattern());
        LogcatAppender logcatAppender = new LogcatAppender();
        logcatAppender.setContext(loggerContext);
        logcatAppender.setEncoder(encoder);
        logcatAppender.start();
        return logcatAppender;
    }

    // todo replace with logentries
    private static FileAppender<ILoggingEvent> getFileAppender(Context loggerContext) {
        PatternLayoutEncoder encoderFull = getPatternLayoutEncoder(loggerContext, Pattern.FULL);

        FileAppender<ILoggingEvent> fileAppender = new FileAppender<>();
        fileAppender.setContext(loggerContext);
        //String file = this.getFileStreamPath("app.log").getAbsolutePath();
        String file = "/mnt/sdcard/logtest.log";
        fileAppender.setFile(file);
        fileAppender.setEncoder(encoderFull);
        fileAppender.start();
        return fileAppender;
    }

}
