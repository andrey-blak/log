package blak.test.log.log;

public enum LoggerType {
    LOGCAT(),
    FILE(Library.LOGCAT | Library.FILE),
    TRACE(Pattern.TRACE);

    @Library.Flags
    private final int mLibraryFlags;

    private final Pattern mPattern;

    LoggerType() {
        this(Pattern.SIMPLE, Library.LOGCAT);
    }

    LoggerType(Pattern pattern) {
        this(pattern, Library.LOGCAT);
    }

    LoggerType(@Library.Flags int libraryFlags) {
        this(Pattern.SIMPLE, libraryFlags);
    }

    LoggerType(Pattern pattern, @Library.Flags int libraryFlags) {
        mLibraryFlags = libraryFlags;
        mPattern = pattern;
    }

    @Library.Flags
    public int getLibraryFlags() {
        return mLibraryFlags;
    }

    Pattern getPattern() {
        return mPattern;
    }
}
