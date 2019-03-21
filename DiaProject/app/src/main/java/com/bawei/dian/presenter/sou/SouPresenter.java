package com.bawei.dian.presenter.sou;

import com.bawei.dian.Bean.JsonBean;
import com.bawei.dian.model.sou.SouModel;
import com.bawei.dian.view.SouView;

import java.util.List;

/**
 * Time:2019/3/20
 * <p>
 * Author:肖佳莹
 * <p>
 * Description:
 */
public class SouPresenter {

    private final SouModel souModel;
    private final SouView souView;

    public SouPresenter(SouView view){
        souModel = new SouModel();
        souView = view;
    }
    public void sou(String keyword, int count, int page) {
        souModel.sou(keyword,count,page);
        souModel.setOnSouListener(new SouModel.OnSouListener() {
            @Override
            public void onSou(List<JsonBean.ResultBean> beans) {
                souView.getSouData(beans);
            }
        });
    }
}
