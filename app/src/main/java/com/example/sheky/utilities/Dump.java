package com.example.sheky.utilities;

import android.util.Log;

public class Dump {
    private static String tag = "moonstag";

    public static void l(String msg) {
        Log.i(tag, msg);
    }

    public static void l(int msg) {
        Log.i(tag, String.valueOf(msg));
    }

    public static void l(long msg) {
        Log.i(tag, String.valueOf(msg));
    }
}
