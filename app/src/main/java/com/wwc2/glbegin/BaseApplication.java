package com.wwc2.glbegin;

import android.app.Application;

import com.wwc2.glbegin.core.AppCore;


/**
 * @anchor: andy
 * @date: 2018-11-07
 * @description:
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppCore.getInstance().init(this);
    }
}
