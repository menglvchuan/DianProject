package com.bawei.dian.presenter.find;


import com.bawei.dian.activity.AddressActivity;
import com.bawei.dian.model.find.FindAddressModel;
import com.bawei.dian.model.find.IFindAddressModel;

public class FindAddressPresenter implements IFindAddressPresenter {
    AddressActivity addressActivity;
    private final FindAddressModel findAddressModel;

    public FindAddressPresenter(AddressActivity addressActivity) {
        this.addressActivity = addressActivity;
        findAddressModel = new FindAddressModel();
    }

    @Override
    public void getFindPresenter(String userId, String sessionId) {
        findAddressModel.getFindAddrData(userId, sessionId, new IFindAddressModel.FindModelInterface() {
            @Override
            public void findSuccess(Object o) {
                addressActivity.getFindView(o);
            }

            @Override
            public void findFaile(Throwable throwable) {

            }
        });
    }
}
