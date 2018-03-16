package com.luffykou.xutil;

/**
 * Created by luffy on 18/3/13.
 * 任务控制中心
 * 需要在在application的onCreate中初始化: X.Ext.init(this);
 */

public final class X {

    private X() {
    }

    public static boolean isDebug() {
        return Ext.isDebug;
    }

    public static class Ext {

        private static boolean isDebug;

        private Ext() {
        }

        public void setDebug(boolean isDebug) {
            this.isDebug = isDebug;
        }
    }
}
