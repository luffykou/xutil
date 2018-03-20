package com.luffykou.xutil.hardware;

import android.annotation.TargetApi;
import android.app.KeyguardManager;
import android.content.Context;
import android.os.Build;

/**
 * require <uses-permission android:name="android.permission.DISABLE_KEYGUARD"/>
 */
public class KeyguardLock {
    private static KeyguardManager keyguardManager;
    private static KeyguardManager.KeyguardLock keyguardLock;

    public static void init(Context context) {
        if (null == keyguardManager || null == keyguardLock) {
            keyguardManager = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
            keyguardLock = keyguardManager.newKeyguardLock(KeyguardLock.class.getSimpleName());
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static boolean isKeyguardLocked(Context context) {
        init(context);
        return keyguardManager.isKeyguardLocked();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public boolean isKeyguardSecure(Context context) {
        init(context);
        return keyguardManager.isKeyguardSecure();
    }

    public boolean inKeyguardRestrictedInputMode(Context context) {
        init(context);
        return keyguardManager.inKeyguardRestrictedInputMode();
    }

    public void disableKeyguard(Context context) {
        init(context);
        keyguardLock.disableKeyguard();
    }

    public void reenableKeyguard(Context context) {
        init(context);
        keyguardLock.reenableKeyguard();
    }
}
