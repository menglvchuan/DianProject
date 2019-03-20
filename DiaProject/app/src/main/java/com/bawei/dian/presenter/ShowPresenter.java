package com.bawei.dian.presenter;

import com.bawei.dian.Bean.Show;
import com.bawei.dian.model.ShowModel;
import com.bawei.dian.view.ShowView;

import java.util.List;

/**
 * Time:2019/3/19
 * <p>
 * Author:肖佳莹
 * <p>
 * Description:
 */
public class ShowPresenter {

    private final ShowModel showModel;
    private final ShowView showView;

    public ShowPresenter(ShowView view){
        showModel = new ShowModel();
        showView = view;
    }
    public void show() {
        showModel.show();
        showModel.setOnShowListener(new ShowModel.OnShowListener() {
            @Override
            public void onResult(Show.ResultBean result) {
                showView.getViewData(result);
            }
        });
    }
}
