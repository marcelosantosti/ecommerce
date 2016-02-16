package com.marcelosantos.ecommerce.infrastructure.common;

import android.content.Context;

import com.marcelosantos.ecommerce.R;

/**
 * Created by Marcelo on 13/02/2016.
 */
public class LogUtil {

    public static void catchException(Context context, Exception e) {

        AlertUtil.showOkAlert(context, context.getString(R.string.ops), context.getString(R.string.general_error));

        //// TODO: 16/02/2016 think create local log database to sync with server log database
    }
}
