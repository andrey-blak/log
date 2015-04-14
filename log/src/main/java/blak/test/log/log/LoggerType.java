package blak.test.log.log;

/**
 * Configuration for loggers
 */
public enum LoggerType {
    LOGCAT,
    IMAGE(Pattern.CLASS_METHOD),
    LOGENTRIES(false, true),
    ALL(Pattern.FULL, true, true);

    private final boolean mShouldUseLogcat;
    private final boolean mShouldUseLogentries;
    private final Pattern mPattern;

    LoggerType() {
        this(Pattern.SIMPLE, true, false);
    }

    LoggerType(Pattern pattern) {
        this(pattern, true, false);
    }

    LoggerType(boolean shouldUseLogcat, boolean shouldUseLogentries) {
        this(Pattern.SIMPLE, shouldUseLogcat, shouldUseLogentries);
    }

    LoggerType(Pattern pattern, boolean useLogcat, boolean useLogentries) {
        mShouldUseLogcat = useLogcat;
        mShouldUseLogentries = useLogentries;
        mPattern = pattern;
    }

    public boolean shouldUseLogcat() {
        return mShouldUseLogcat;
    }

    public boolean shouldUseLogentries() {
        return mShouldUseLogentries;
    }

    Pattern getPattern() {
        return mPattern;
    }
}
