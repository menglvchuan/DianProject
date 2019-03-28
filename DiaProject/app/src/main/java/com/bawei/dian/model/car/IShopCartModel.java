package com.bawei.dian.model.car;

import java.util.HashMap;
import java.util.Map;

public interface IShopCartModel {
    void getData(int uid, String sid, ModelCallBack modelCallBack);
    void queryCart(int uid, String sid, HashMap<String, String> pramrs, ModelCallBack modelCallBack);
    interface ModelCallBack{
        void loadSuccess(Object o);
        void loadFailed(Throwable throwable);
    }

}
