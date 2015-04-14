package blak.test.log.log;

public enum LogType {
    LOGCAT,
    IMAGE,
    LOGENTRIES(false, true),
    ALL(true, true);

    private boolean mUseLogcat;
    private boolean mUseLogentries;

    LogType() {
        this(true, false);
    }

    LogType(boolean useLogcat, boolean useLogentries) {
        mUseLogcat = useLogcat;
        mUseLogentries = useLogentries;
    }
}
