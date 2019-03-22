package com.bawei.dian.model.deng;

import java.util.Map;

public interface ILoginModel {
    void login(String url, Map<String, String> map, ILoginCallBack iLoginCallBack);

    //定义接口
    interface ILoginCallBack{
        void onStatus(Object o);
        void onFailed();
    }
}
