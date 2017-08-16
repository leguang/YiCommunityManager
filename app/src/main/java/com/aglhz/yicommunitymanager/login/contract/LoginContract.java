package com.aglhz.yicommunitymanager.login.contract;


import com.aglhz.yicommunitymanager.common.Params;
import com.aglhz.yicommunitymanager.entity.bean.BaseBean;
import com.aglhz.yicommunitymanager.entity.bean.UserBean;

import cn.itsite.abase.mvp.contract.base.BaseContract;
import io.reactivex.Observable;

/**
 * Author：leguang on 2017/4/12 0009 14:23
 * Email：langmanleguang@qq.com
 */
public interface LoginContract {

    interface View extends BaseContract.View {

        void responseLogin(Params params);
    }

    interface Presenter extends BaseContract.Presenter {
        void requestLogin(Params params);
    }

    interface Model extends BaseContract.Model {
        Observable<UserBean> requestLogin(Params params);

        void requestUMeng(String alias);
    }
}