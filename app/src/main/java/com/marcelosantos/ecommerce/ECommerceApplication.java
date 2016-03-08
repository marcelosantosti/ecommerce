package com.marcelosantos.ecommerce;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;
import roboguice.RoboGuice;

/**
 * Created by Marcelo on 13/02/2016.
 */
public class ECommerceApplication extends Application {

    @Override
    public void onCreate() {

        super.onCreate();
        Fabric.with(this, new Crashlytics());
        RoboGuice.setUseAnnotationDatabases(false);
    }
}