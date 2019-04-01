package com.bawei.dian.presenter.create;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Handler;

public interface ICreationPresenter {
    void getPresenterData(HashMap<String, String> maps, HashMap<String, String> pramrs);
    void getDiZhiData(HashMap<String, String> maps, HashMap<String, String> pramrs);
    void getShouHuo(HashMap<String, String> maps);
}
