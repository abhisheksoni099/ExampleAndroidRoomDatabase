package com.example.sheky.utilities;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;

public class Toaster {
    public static void showToast(String message, View view) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG)
                .setAction("CLOSE", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    }
                }).show();
    }
}
