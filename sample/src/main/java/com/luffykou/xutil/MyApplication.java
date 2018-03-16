package com.luffykou.xutil;

import android.app.Application;

/**
 * Created by luffy on 18/3/13.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.setDebug(BuildConfig.DEBUG);
    }
}
