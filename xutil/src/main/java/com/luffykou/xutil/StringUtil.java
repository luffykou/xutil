package com.luffykou.xutil;

import android.text.TextUtils;

/**
 * Created by luffy on 18/3/13.
 */

public class StringUtil {

    public static boolean isEmpty(CharSequence str) {
        return TextUtils.isEmpty(str);
    }

    public static boolean isNotEmpty(CharSequence str) {
        return !isEmpty(str);
    }
}
