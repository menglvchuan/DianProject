package com.bawei.dian.model.find;

public interface IFindAddressModel {
    void getFindAddrData(String userId, String sessionId, FindModelInterface findModelInterface);
    interface FindModelInterface{
        void findSuccess(Object o);
        void findFaile(Throwable throwable);
    }
}
