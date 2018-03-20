package com.luffykou.xutil;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.os.storage.StorageManager;
import android.support.v4.os.EnvironmentCompat;

import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Get SD card info.
 *
 * @author MaTianyu
 * @date 2015-04-19
 */
public class SdCardUtil {
    private static final String TAG = SdCardUtil.class.getSimpleName();

    /**
     * is sd card available.
     *
     * @return true if available
     */
    public boolean isSdCardAvailable() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    /**
     * Get {@link android.os.StatFs}.
     */
    public static StatFs getStatFs(String path) {
        return new StatFs(path);
    }

    public static String getSDCardPath() {
        String sdcard = Environment.getExternalStorageDirectory().getPath() + File.separator;
        return sdcard;
    }

    /**
     * Get phone data path.
     */
    public static String getDataPath() {
        return Environment.getDataDirectory().getPath();

    }

    /**
     * 获取外接存储路径
     *
     * @param context
     * @return
     */
    public static ArrayList<String> getStoragePath(Context context) {
        final StorageManager storageManager = (StorageManager) context.getSystemService(Context.STORAGE_SERVICE);
        try {
            //得到StorageManager中的getVolumeList()方法的对象
            final Method getVolumeList = storageManager.getClass().getMethod("getVolumeList");

            //得到StorageVolume类的对象
            final Class<?> storageVolumeClazz = Class.forName("android.os.storage.StorageVolume");
            //获得StorageVolume中的一些方法
            final Method getPath = storageVolumeClazz.getMethod("getPath");
            Method isRemovable = storageVolumeClazz.getMethod("isRemovable");

            Method mGetState = null;
            //getState 方法是在4.4_r1之后的版本加的，之前版本（含4.4_r1）没有
            // （http://grepcode.com/file/repository.grepcode.com/java/ext/com.google.android/android/4.4_r1/android/os/Environment.java/）
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
                mGetState = storageVolumeClazz.getMethod("getState");
            }

            //调用getVolumeList方法，参数为：“谁”中调用这个方法
            final Object invokeVolumeList = getVolumeList.invoke(storageManager);
            final int length = Array.getLength(invokeVolumeList);
            ArrayList<String> list = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                final Object storageVolume = Array.get(invokeVolumeList, i);//得到StorageVolume对象
                final String path = (String) getPath.invoke(storageVolume);
                final boolean removable = (Boolean) isRemovable.invoke(storageVolume);
                String state;
                if (mGetState != null) {
                    state = (String) mGetState.invoke(storageVolume);
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        state = Environment.getStorageState(new File(path));
                    } else {
                        if (removable) {
                            state = EnvironmentCompat.getStorageState(new File(path));
                        } else {
                            //不能移除的存储介质，一直是mounted
                            state = Environment.MEDIA_MOUNTED;
                        }
                    }
                }
                if (Environment.MEDIA_MOUNTED.equals(state)) {
                    LogUtil.i(String.format("path = %S", path));
                    list.add(path);
                }
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static long getTotalSize(String path) {
        final StatFs statFs = new StatFs(path);
        long blockSize;
        long blockCountLong;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            blockSize = statFs.getBlockSizeLong();
            blockCountLong = statFs.getBlockCountLong();
        } else {
            blockSize = statFs.getBlockSize();
            blockCountLong = statFs.getBlockCount();
        }
        return blockSize * blockCountLong;
    }

    public static long getAvailableSize(String path) {
        final StatFs statFs = new StatFs(path);
        long blockSize;
        long availableBlocks;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            blockSize = statFs.getBlockSizeLong();
            availableBlocks = statFs.getAvailableBlocksLong();
        } else {
            blockSize = statFs.getBlockSize();
            availableBlocks = statFs.getAvailableBlocks();
        }
        return availableBlocks * blockSize;
    }
}
