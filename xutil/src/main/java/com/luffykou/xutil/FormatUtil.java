package com.luffykou.xutil;

/**
 * Created by luffy on 18/3/20.
 */

public class FormatUtil {

    public static String formatSpace(long space) {
        final float A_KB = 1024.f;
        final float A_MB = A_KB * A_KB;
        final float A_GB = A_MB * A_KB;

        if (space <= 0) {
            return "0KB";
        }

        final double gbValue = space / A_GB;
        if (gbValue >= 1) {
            return String.format("%.2fGB", gbValue);
        } else {
            final double mbValue = space / A_MB;
            if (mbValue >= 1) {
                return String.format("%.2fMB", mbValue);
            } else {
                final double kbValue = space / A_KB;
                return String.format("%.2fKB", kbValue);
            }
        }
    }
}
