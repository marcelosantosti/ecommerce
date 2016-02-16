package com.marcelosantos.ecommerce.userinterface.activity;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.marcelosantos.ecommerce.R;
import com.marcelosantos.ecommerce.infrastructure.common.AlertUtil;
import com.marcelosantos.ecommerce.infrastructure.common.LogUtil;
import com.marcelosantos.ecommerce.infrastructure.common.ProgressDialogUtil;
import com.marcelosantos.ecommerce.infrastructure.common.ValidationException;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;

/**
 * Created by Marcelo on 13/02/2016.
 */
@EActivity
public class BaseActivity extends AppCompatActivity {

    @Bean
    public ProgressDialogUtil progressDialogUtil;

    @UiThread
    public void catchException(Exception e) {

        this.removeProgress();

        if (e instanceof ValidationException)
            AlertUtil.showOkAlert(this, "Erro", e.getMessage());
        else
            LogUtil.catchException(this, e);
    }

    protected void configureActionBar() {

        Toolbar toolbar = (Toolbar)super.findViewById(R.id.toolbar);
        super.setSupportActionBar(toolbar);
    }

    public void showProgress() {

        this.showProgress("Carregando");
    }

    public void showProgress(String label) {

        this.progressDialogUtil.showProgress(label);
    }

    public void removeProgress() {

        this.progressDialogUtil.removeProgress();
    }
}
