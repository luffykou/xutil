package com.luffykou.xutil.hardware;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.PowerManager;

public class Screen {

    private static PowerManager powerManager;
    private static PowerManager.WakeLock wakeLock;

    private Screen() {
    }

    private static void init(Context context) {
        if (null == powerManager || null == wakeLock) {
            ////获取电源的服务 声明电源管理器
            powerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            wakeLock = powerManager.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.FULL_WAKE_LOCK, Screen.class.getSimpleName());
        }
    }

    @TargetApi(Build.VERSION_CODES.ECLAIR_MR1)
    public static boolean isScreenOn(Context context) {
        init(context);
        return powerManager.isScreenOn();
    }

    /**
     * 点亮 唤醒
     *
     * @param timeout
     */
    public static void turnOn(Context context, long timeout) {
        init(context);
        if (!wakeLock.isHeld()) {
            wakeLock.acquire(timeout);
        }
    }

    /**
     * 息屏
     */
    public static void turnOff(Context context) {
        init(context);
        if (wakeLock.isHeld()) {
            wakeLock.release();
        }
    }
}
