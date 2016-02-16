package com.marcelosantos.ecommerce;

import android.app.Application;

import roboguice.RoboGuice;

/**
 * Created by Marcelo on 13/02/2016.
 */
public class ECommerceApplication extends Application {

    @Override
    public void onCreate() {

        super.onCreate();
        RoboGuice.setUseAnnotationDatabases(false);
    }
}