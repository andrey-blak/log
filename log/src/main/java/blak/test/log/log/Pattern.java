package blak.test.log.log;

/*
 * See conversion words here:
 * http://logback.qos.ch/manual/layouts.html#conversionWord
 */
enum Pattern {
    SIMPLE("%message"),
    TRACE("[%thread] %class{0}.%method:%line - %message");

    private static final String POSTFIX = "%n%exception";

    private final String mValue;

    Pattern(String value) {
        mValue = value + POSTFIX;
    }

    public String getValue() {
        return mValue;
    }
}
