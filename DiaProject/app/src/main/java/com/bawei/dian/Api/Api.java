package com.bawei.dian.Api;

/**
 * Time:2019/3/19
 * <p>
 * Author:肖佳莹
 * <p>
 * Description:
 */
public class Api {
    public static final String BASE_URL = "http://mobile.bwstudent.com";
    //http://172.17.8.100/small/commodity/v1/commodityList  首页
    public static String show_url="http://172.17.8.100/small/commodity/v1/";
    //搜索  http://172.17.8.100/small/commodity/v1/findCommodityByKeyword
    public static  String GOODS_SOU = "http://172.17.8.100/";
    //http://172.17.8.100/small/user/v1/register  登录
    //注册
    public static final String REGIST ="http://172.17.8.100/small/user/v1/register";
    //登录
    public static final String LOGIN = "http://172.17.8.100/small/user/v1/login";
    //设置默认地址的接口
    public static final String SHOW_DEFAULT_ADDRESS_URL="small/user/verify/v1/setDefaultReceiveAddress";
    //查询收货地址的接口
    public static final String SHOW_SELECT_ADDRESS_URL="small/user/verify/v1/receiveAddressList";
    // 添加收货地址
    public static final String INSERT_ADDRESS = "/small/user/verify/v1/addReceiveAddress";
    //收货地址列表
    public static final String ADDRESS = "/small/user/verify/v1/receiveAddressList";
}
