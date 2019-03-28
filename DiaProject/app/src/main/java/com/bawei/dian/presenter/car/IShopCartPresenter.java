package com.bawei.dian.presenter.car;

import java.util.HashMap;
import java.util.Map;

public interface IShopCartPresenter {
    void getPresenterData(int uid, String sid);
    void getQueryData(int uid, String sid, HashMap<String, String> maps);
}
