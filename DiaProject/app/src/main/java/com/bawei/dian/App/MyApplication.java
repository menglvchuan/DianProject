package com.bawei.dian.App;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Time:2019/3/20
 * <p>
 * Author:肖佳莹
 * <p>
 * Description:
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
