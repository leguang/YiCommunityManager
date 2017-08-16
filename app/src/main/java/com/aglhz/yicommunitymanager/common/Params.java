package com.aglhz.yicommunitymanager.common;


/**
 * Created by leguang on 2017/5/6 0006.
 * Email：langmanleguang@qq.com
 */

public class Params {
    private static final String TAG = Params.class.getSimpleName();
    public static String token;
    public static String cmnt_c = "";
    public String account = "";
    public String password = "";

    private Params() {
    }

    public static Params getInstance() {
        Params params = new Params();
        params.token = UserHelper.token;
        return params;
    }

}