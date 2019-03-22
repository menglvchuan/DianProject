package com.bawei.dian.model.regist;


import java.util.Map;

public interface IRegistModel {
    void regist(String url, Map<String, String> map, IRegistCallBack callBack);
    //定义接口
    interface IRegistCallBack{
        void onStatus(Object o);
        void onFailed(Throwable throwable);
    }
}
