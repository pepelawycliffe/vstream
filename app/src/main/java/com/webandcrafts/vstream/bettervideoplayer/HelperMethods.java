package com.webandcrafts.vstream.bettervideoplayer;

import android.net.Uri;

/**
 * @author Halil Ozercan
 */

public class HelperMethods {

    public static boolean isRemotePath(Uri path){
        return (path.getScheme().equals("http") || path.getScheme().equals("https"));
    }

    public static String secondsToDuration(int seconds){
        return String.format("%02d:%02d:%02d",
            seconds / 3600,
            (seconds % 3600) / 60,
            (seconds % 60)
        );
    }
}
