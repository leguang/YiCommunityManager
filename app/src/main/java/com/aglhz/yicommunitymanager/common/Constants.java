package com.aglhz.yicommunitymanager.common;


import com.aglhz.yicommunitymanager.BuildConfig;

/**
 * Author：leguang on 2016/10/9 0009 15:49
 * Email：langmanleguang@qq.com
 */
public class Constants {
    private final String TAG = Constants.class.getSimpleName();

    //不允许new
    private Constants() {
        throw new Error("Do not need instantiate!");
    }

    //——————————————以下是区分debug版和非debug版的baseurl——————————————————————
    public static String BASE_USER = "";

    static {
        if (BuildConfig.DEBUG) {
            //调试可以改这里的地址。
            BASE_USER = "http://www.aglhz.com:8090/sub_property_ysq";//物业
        } else {
            //这里的是正式版的基础地址，永远不要动。
            BASE_USER = "http://www.aglhz.com:8090/sub_property_ysq";//物业
        }
    }
    //——————————————以上是区分debug版和非debug版的baseurl——————————————————————


    public static final String PRESS_AGAIN = "再按一次退出";
    public static final int PAGE_SIZE = 20;
    //网络部分
    public static final int RESPONSE_CODE_NOMAL = 200;
    public static final int RESPONSE_CODE_LOGOUT = 123;
}
