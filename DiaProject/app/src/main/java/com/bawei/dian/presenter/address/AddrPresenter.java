package com.bawei.dian.presenter.address;

import com.bawei.dian.activity.AddAdressActivity;
import com.bawei.dian.activity.AddressActivity;
import com.bawei.dian.model.address.AddrModel;
import com.bawei.dian.model.address.IAddrModel;

/**
 * Time:2019/3/22
 * <p>
 * Author:肖佳莹
 * <p>
 * Description:
 */
public class AddrPresenter implements IAddrPresenter{
    AddAdressActivity addressActivity;
    private final AddrModel addrModel;

    public AddrPresenter(AddAdressActivity addressActivity) {
        this.addressActivity = addressActivity;
        addrModel = new AddrModel();
    }

    @Override
    public void getAddPresenter(String userId, String sessionId, String realName, String phone, String address, String zipCode) {
        addrModel.getAddData(userId, sessionId, realName, phone, address, zipCode, new IAddrModel.AddModelInterface() {
            @Override
            public void loadAddSuccess(Object o) {
                addressActivity.getAddrViewData(o);
            }

            @Override
            public void loadAddFailed(Throwable throwable) {

            }
        });
    }
}
