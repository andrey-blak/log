package blak.test.log.log;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.android.LogcatAppender;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.FileAppender;
import org.slf4j.LoggerFactory;

public class AppLogger {
    private static final String PATTERN_FULL = "%date{HH:mm:ss.SSS} %-5level %logger{36} [%thread] class.%method:%line - %message%n";
    private static final String PATTERN_THREAD_MESSAGE = "[%thread] %message%n";
    private static final String PATTERN_CLASS_METHOD = "%class{0}.%method:%line - %message%n";

    private AppLogger() {
    }

    public static void init() {
        configureLogback();
    }

    private static void configureLogback() {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();

        // reset the default context (which may already have been initialized)
        // since we want to reconfigure it
        //loggerContext.reset();

        Appender<ILoggingEvent> logcatAppender = createLogcatAppender(loggerContext);
        Appender<ILoggingEvent> logcatDebugAppender = createLogcatDebugAppender(loggerContext);
        Appender<ILoggingEvent> fileAppender = getFileAppender(loggerContext);

        // add the newly created appenders to the root logger;
        ch.qos.logback.classic.Logger rootLogger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);
        rootLogger.addAppender(logcatAppender);
        rootLogger.addAppender(logcatDebugAppender);
        rootLogger.addAppender(fileAppender);
    }

    private static Appender<ILoggingEvent> createLogcatAppender(LoggerContext loggerContext) {
        PatternLayoutEncoder encoderShort = new PatternLayoutEncoder();
        encoderShort.setContext(loggerContext);
        encoderShort.setPattern(PATTERN_THREAD_MESSAGE);
        encoderShort.start();

        LogcatAppender logcatAppender = new LogcatAppender();
        logcatAppender.setContext(loggerContext);
        logcatAppender.setEncoder(encoderShort);
        logcatAppender.start();
        return logcatAppender;
    }

    private static Appender<ILoggingEvent> createLogcatDebugAppender(LoggerContext loggerContext) {
        PatternLayoutEncoder encoderShort = new PatternLayoutEncoder();
        encoderShort.setContext(loggerContext);
        encoderShort.setPattern(PATTERN_CLASS_METHOD);
        encoderShort.start();

        LogcatAppender logcatAppender = new LogcatAppender();
        logcatAppender.setContext(loggerContext);
        logcatAppender.setEncoder(encoderShort);
        logcatAppender.start();
        return logcatAppender;
    }

    // todo temp
    private static Appender<ILoggingEvent> getFileAppender(LoggerContext loggerContext) {
        PatternLayoutEncoder encoderFull = new PatternLayoutEncoder();
        encoderFull.setContext(loggerContext);
        encoderFull.setPattern(PATTERN_FULL);
        encoderFull.start();

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
