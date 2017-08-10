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


    //登录
    String requestLogin = BASE_USER + "/appbackend/user/from-client/login";

    @POST
    Observable<UserBean> requestLogin(@Url String url
            , @Query("user") String user
            , @Query("pwd") String pwd);

    //登出
    String requestLogout = BASE_USER + "/appbackend/user/from-client/logout";

    @POST
    Observable<BaseBean> requestLogout(
            @Url String url,
            @Query("token") String token);


    //登录验证
    String requestCheckToken = BASE_USER + "/appbackend/user/from-client/login-token";

    @POST
    Observable<BaseBean> requestCheckToken(@Url String url,
                                           @Query("token") String token);

}

