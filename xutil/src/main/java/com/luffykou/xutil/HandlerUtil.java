package com.luffykou.xutil;

import android.os.Handler;
import android.os.Looper;

public class HandlerUtil {

    public static final Handler handler = new Handler(Looper.getMainLooper());

    public static void runOnUiThread(Runnable runnable) {
        handler.post(runnable);
    }

    public static void runOnUiThreadDelay(Runnable runnable, long delayMillis) {
        handler.postDelayed(runnable, delayMillis);
    }

    public static void removeRunable(Runnable runnable) {
        handler.removeCallbacks(runnable);
    }
}
