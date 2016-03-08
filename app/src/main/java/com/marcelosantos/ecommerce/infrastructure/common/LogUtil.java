package com.marcelosantos.ecommerce.infrastructure.common;

import android.content.Context;

import com.crashlytics.android.Crashlytics;
import com.marcelosantos.ecommerce.R;

/**
 * Created by Marcelo on 13/02/2016.
 */
public class LogUtil {

    public static void catchException(Context context, Exception e) {

        Crashlytics.logException(e);

        AlertUtil.showOkAlert(context, context.getString(R.string.ops), context.getString(R.string.general_error));
    }
}
