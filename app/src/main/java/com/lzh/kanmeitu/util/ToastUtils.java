package com.lzh.kanmeitu.util;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {

    private static Toast toast = null;

    public static void simpleToast (Context context, CharSequence text) {

        if (toast != null) {
            toast.cancel();
        }

        toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        toast.show();
    }
}
