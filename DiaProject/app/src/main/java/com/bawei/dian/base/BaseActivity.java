package com.bawei.dian.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.bawei.dian.network.NetWorkUtils;


public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayout());
        NetWorkUtils.getConnectivityManager(getActivitys());
        initView(savedInstanceState);
        initData();
        initTitle();
        initListener();
    }

    public abstract void initView(Bundle savedInstanceState);

    public abstract Context getActivitys();

    public abstract void initListener();

    public abstract void initTitle();

    public abstract int initLayout();

    public abstract void initData();


}
