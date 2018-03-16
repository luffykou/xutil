package com.luffykou.xutil;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by luffy on 17/6/1.
 */

public class ToastUtil {

    public static void showShort(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    public static void showLong(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }
}
