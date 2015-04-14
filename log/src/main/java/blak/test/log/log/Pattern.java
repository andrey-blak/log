package blak.test.log.log;

enum Pattern {
    FULL("%date{HH:mm:ss.SSS} %-5level %logger{36} [%thread] class.%method:%line - %message%n%exception"),
    SIMPLE("%message%n%exception"),
    THREAD_MESSAGE("[%thread] %message%n%exception"),
    CLASS_METHOD("%class{0}.%method:%line - %message%n%exception");

    private String mValue;

    Pattern(String value) {
        mValue = value;
    }

    public String getValue() {
        return mValue;
    }
}
