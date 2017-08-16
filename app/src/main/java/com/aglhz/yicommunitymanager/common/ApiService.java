package com.aglhz.yicommunitymanager.common;


import com.aglhz.yicommunitymanager.entity.bean.BaseBean;
import com.aglhz.yicommunitymanager.entity.bean.UserBean;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by leguang on 2017/4/16 0016.
 * Email：langmanleguang@qq.com
 * <p>
 * 项目接口集合
 */

public interface ApiService {

    //*************以下基础路径*******************
    String BASE_USER = Constants.BASE_USER;           //用户

    //*************以上基础路径*******************


    //登录。
    String requestLogin = BASE_USER + "/appbackend/user/from-client/login";

    @POST
    Observable<UserBean> requestLogin(@Url String url
            , @Query("account") String user
            , @Query("password") String pwd);

    //登出。
    String requestLogout = BASE_USER + "/appbackend/user/from-client/logout";

    @POST
    Observable<BaseBean> requestLogout(
            @Url String url,
            @Query("token") String token);

    //登录验证。
    String requestCheckToken = BASE_USER + "/appbackend/user/from-client/check-login";

    @POST
    Observable<BaseBean> requestCheckToken(@Url String url,
                                           @Query("token") String token);

    //注册友盟推送。
    String requestRegisterPush = BASE_USER + "/appbackend/umparams/from-client/umengparams-create";

    @POST
    Observable<BaseBean> requestRegisterPush(@Url String url,
                                             @Query("token") String token,
                                             @Query("alias") String alias,
                                             @Query("aliasType") String aliasType,
                                             @Query("deviceToken") String deviceToken);

    String URL = "http://www.aglhz.com/sub_property_ysq/m/manager/index.html?appType=2&token=%1";
//    String URL = "http://192.168.7.106:8000/index.html?appType=2&token=%1";
}

