package com.marcelosantos.ecommerce.infrastructure.common;

import android.content.Context;
import android.support.v7.app.AlertDialog;

import com.marcelosantos.ecommerce.R;

/**
 * Created by Marcelo on 13/02/2016.
 */
public class AlertUtil {

    public static void showOkAlert(Context context, String title, String message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setPositiveButton(context.getString(R.string.ok), null);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.create().show();
    }
}
