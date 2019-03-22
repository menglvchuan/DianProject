package com.bawei.dian.model.address;

/**
 * Time:2019/3/22
 * <p>
 * Author:肖佳莹
 * <p>
 * Description:
 */
public interface IAddrModel {
    void getAddData(String userId, String sessionId, String realName, String phone, String address, String zipCode, AddModelInterface addModelInterface);
    interface AddModelInterface{
        void loadAddSuccess(Object o);
        void loadAddFailed(Throwable throwable);
    }
}
