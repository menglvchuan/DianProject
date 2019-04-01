package com.bawei.dian.model.create;

import java.util.HashMap;
import java.util.Map;

public interface ICreationModel {
    void getData(HashMap<String, String> pramrs, HashMap<String, String> maps, ModelCallBack modelCallBack);
    void gteDiZhiData(HashMap<String, String> pramrs, HashMap<String, String> maps, ModelCallBack modelCallBack);
    void getShouHuo(HashMap<String, String> maps, ModelCallBack modelCallBack);
    interface ModelCallBack{
        void loadSuccess(Object o);
        void loadFailed(Throwable throwable);
    }

}
