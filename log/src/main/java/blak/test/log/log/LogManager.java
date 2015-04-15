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

public class LogManager {
    private LogManager() {
    }

    public static Logger getLogger(LoggerType loggerType) {
        return LoggerFactory.getLogger(loggerType.name());
    }

    public static void exception(Throwable e) {
        getLogger(LoggerType.FILE).error("", e);
    }

    private static void initLoggers() {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        loggerContext.reset();
        for (LoggerType loggerType : LoggerType.values()) {
            ch.qos.logback.classic.Logger logger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(loggerType.name());
            initLogger(loggerContext, logger, loggerType);
        }
    }

    private static void initLogger(Context loggerContext, ch.qos.logback.classic.Logger logger, LoggerType type) {
        int libraryFlags = type.getLibraryFlags();
        if (isFlagSet(libraryFlags, Library.LOGCAT)) {
            LogcatAppender logcatAppender = createLogcatAppender(loggerContext, type);
            logger.addAppender(logcatAppender);
        }
        if (isFlagSet(libraryFlags, Library.FILE)) {
            FileAppender<ILoggingEvent> appender = createFileAppender(loggerContext, type);
            logger.addAppender(appender);
        }
        logger.setLevel(Level.ALL);
    }

    private static LogcatAppender createLogcatAppender(Context loggerContext, LoggerType type) {
        PatternLayoutEncoder encoder = createPatternLayoutEncoder(loggerContext, type.getPattern());
        LogcatAppender logcatAppender = new LogcatAppender();
        logcatAppender.setContext(loggerContext);
        logcatAppender.setEncoder(encoder);
        logcatAppender.start();
        return logcatAppender;
    }

    private static FileAppender<ILoggingEvent> createFileAppender(Context loggerContext, LoggerType type) {
        PatternLayoutEncoder encoderFull = createPatternLayoutEncoder(loggerContext, type.getPattern());

        FileAppender<ILoggingEvent> fileAppender = new FileAppender<>();
        fileAppender.setContext(loggerContext);
        String file = "/mnt/sdcard/logtest.log";
        fileAppender.setFile(file);
        fileAppender.setEncoder(encoderFull);
        fileAppender.start();
        return fileAppender;
    }

    private static PatternLayoutEncoder createPatternLayoutEncoder(Context loggerContext, Pattern pattern) {
        PatternLayoutEncoder encoder = new PatternLayoutEncoder();
        encoder.setContext(loggerContext);
        encoder.setPattern(pattern.getValue());
        encoder.start();
        return encoder;
    }

    private static boolean isFlagSet(int bitField, int flag) {
        return (bitField & flag) != 0;
    }
}
