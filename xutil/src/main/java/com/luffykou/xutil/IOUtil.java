package com.luffykou.xutil;

import android.support.annotation.NonNull;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;

/**
 * Created by luffy on 18/3/16.
 */

public class IOUtil {

    public static void close(@NonNull Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void flush(@NonNull Flushable flushable) {
        try {
            flushable.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
