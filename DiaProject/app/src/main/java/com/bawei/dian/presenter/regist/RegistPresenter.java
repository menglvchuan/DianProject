package com.bawei.dian.presenter.regist;

import com.bawei.dian.Api.Api;
import com.bawei.dian.activity.RegistActivity;
import com.bawei.dian.model.regist.IRegistModel;
import com.bawei.dian.model.regist.RegistModel;

import java.util.Map;

/**
 * Time:2019/3/21
 * <p>
 * Author:肖佳莹
 * <p>
 * Description:
 */
public class RegistPresenter implements IRegistPresenter{

    private  RegistModel registModel;
    RegistActivity registActivity;

    public RegistPresenter(RegistActivity registActivity){
        this.registActivity=registActivity;
        registModel = new RegistModel();
    }
    @Override
    public void registPre(Map<String, String> map) {
        registModel.regist(Api.REGIST, map, new IRegistModel.IRegistCallBack() {
            @Override
            public void onStatus(Object o) {
                registActivity.showMsg(o);
            }

            @Override
            public void onFailed(Throwable throwable) {

            }
        });
    }
}
