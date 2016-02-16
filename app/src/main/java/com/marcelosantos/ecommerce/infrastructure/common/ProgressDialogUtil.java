package com.marcelosantos.ecommerce.infrastructure.common;

import android.app.ProgressDialog;
import android.content.Context;

import com.marcelosantos.ecommerce.R;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

/**
 * Created by Marcelo on 14/02/2016.
 */
@EBean
public class ProgressDialogUtil {

    private ProgressDialog progressDialog;

    @RootContext
    public Context context;

    public void showProgress(String label) {

        if (this.progressDialog == null)
            this.progressDialog = ProgressDialog.show(this.context, null, label, true);
    }

    public void showProgress() {

        this.showProgress(this.context.getString(R.string.loading));
    }

    public void removeProgress() {

        if (this.progressDialog != null) {

            this.progressDialog.dismiss();
            this.progressDialog = null;
        }
    }
}
