package com.bawei.dian.presenter.regist;

import com.bawei.dian.model.regist.RegistModel;
import com.bawei.dian.view.DengView;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Handler;

/**
 * Time:2019/3/21
 * <p>
 * Author:肖佳莹
 * <p>
 * Description:
 */
public class RegistPresenter {

    private final RegistModel registModel;
    private final DengView dengView;

    public RegistPresenter(DengView view){
        registModel = new RegistModel();
        dengView = view;
    }
    public void registPre(HashMap<String, String> params) {
        registModel.getHttpData(params);
        registModel.setRegistListener(new RegistModel.onRegistListener() {
            @Override
            public void onResult(String status) {
                dengView.getViewData(status);
            }
        });
    }
}
