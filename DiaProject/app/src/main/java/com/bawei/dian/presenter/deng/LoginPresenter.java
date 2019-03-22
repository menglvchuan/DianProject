package com.bawei.dian.presenter.deng;

import com.bawei.dian.Api.Api;
import com.bawei.dian.activity.DengActivity;
import com.bawei.dian.model.deng.ILoginModel;
import com.bawei.dian.model.deng.LoginModel;
import com.bawei.dian.view.LoginView;

import java.util.HashMap;
import java.util.Map;

/**
 * Time:2019/3/22
 * <p>
 * Author:肖佳莹
 * <p>
 * Description:
 */
public class LoginPresenter implements ILoginPresenter{
    DengActivity dengActivity;
    LoginModel loginModel;
    public LoginPresenter(DengActivity dengActivity) {
        this.dengActivity = dengActivity;
        loginModel = new LoginModel();
    }

    @Override
    public void loginPre(Map<String, String> map) {
        loginModel.login(Api.LOGIN, map, new ILoginModel.ILoginCallBack() {
            @Override
            public void onStatus(Object o) {
                dengActivity.showMsg(o);
            }

            @Override
            public void onFailed() {

            }
        });
    }
}
