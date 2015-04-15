package blak.test.log.log;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

class Library {
    @Retention(RetentionPolicy.SOURCE)
    @IntDef(flag = true,
            value = {LOGCAT, FILE})
    public @interface Flags {}

    public static final int LOGCAT = 1;
    public static final int FILE = 2;
}
