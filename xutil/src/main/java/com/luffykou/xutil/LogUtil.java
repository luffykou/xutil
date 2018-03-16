package com.luffykou.xutil;

import android.util.Log;

/**
 * Log工具，类似android.util.Log。
 * TAG自动产生，格式: customTagPrefix:className.methodName(L:lineNumber),
 * 切记在application的onCreate中设置打印log开关: X.Ext.setDebug(BuildConfig.DEBUG);
 * Created by luffy on 17/5/30.
 */

public class LogUtil {

    private static String customTagPrefix = LogUtil.class.getSimpleName();

    private LogUtil() {
    }

    public static String generateTag() {
        StackTraceElement caller = new Throwable().getStackTrace()[2];

        String callerClazzName = caller.getClassName();
        callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);

        String TAG = "%s:%s.%s(L:%d)";
        TAG = String.format(TAG, customTagPrefix, callerClazzName, caller.getMethodName(), caller.getLineNumber());
        return TAG;
    }

    public static void d(String content) {
        if (!X.isDebug()) return;
        String tag = generateTag();

        Log.d(tag, content);
    }

    public static void d(String content, Throwable tr) {
        if (!X.isDebug()) return;
        String tag = generateTag();

        Log.d(tag, content, tr);
    }

    public static void e(String content) {
        if (!X.isDebug()) return;
        String tag = generateTag();

        Log.e(tag, content);
    }

    public static void e(String content, Throwable tr) {
        if (!X.isDebug()) return;
        String tag = generateTag();

        Log.e(tag, content, tr);
    }

    public static void i(String content) {
        if (!X.isDebug()) return;
        String tag = generateTag();

        Log.i(tag, content);
    }

    public static void i(String content, Throwable tr) {
        if (!X.isDebug()) return;
        String tag = generateTag();

        Log.i(tag, content, tr);
    }

    public static void v(String content) {
        if (!X.isDebug()) return;
        String tag = generateTag();

        Log.v(tag, content);
    }

    public static void v(String content, Throwable tr) {
        if (!X.isDebug()) return;
        String tag = generateTag();

        Log.v(tag, content, tr);
    }

    public static void w(String content) {
        if (!X.isDebug()) return;
        String tag = generateTag();

        Log.w(tag, content);
    }

    public static void w(String content, Throwable tr) {
        if (!X.isDebug()) return;
        String tag = generateTag();

        Log.w(tag, content, tr);
    }

    public static void w(Throwable tr) {
        if (!X.isDebug()) return;
        String tag = generateTag();

        Log.w(tag, tr);
    }


    public static void wtf(String content) {
        if (!X.isDebug()) return;
        String tag = generateTag();

        Log.wtf(tag, content);
    }

    public static void wtf(String content, Throwable tr) {
        if (!X.isDebug()) return;
        String tag = generateTag();

        Log.wtf(tag, content, tr);
    }

    public static void wtf(Throwable tr) {
        if (!X.isDebug()) return;
        String tag = generateTag();

        Log.wtf(tag, tr);
    }

    /**
     * 过长文本，log打印不全时使用此方法
     *
     * @param content
     */
    public static void l(String content) {
        if (!X.isDebug()) return;
        String tag = generateTag();

        //因为String的length是字符数量不是字节数量所以为了防止中文字符过多，把4*1024的MAX字节打印长度改为2001字符数
        int max_str_length = 2001 - tag.length();
        //大于4000时
        while (content.length() > max_str_length) {
            Log.d(tag, content.substring(0, max_str_length));
            content = content.substring(max_str_length);
        }
        //剩余部分
        Log.d(tag, content);
    }
}
