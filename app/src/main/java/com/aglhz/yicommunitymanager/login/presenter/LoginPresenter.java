package com.aglhz.yicommunitymanager.login.presenter;

import android.support.annotation.NonNull;

import com.aglhz.yicommunitymanager.common.Constants;
import com.aglhz.yicommunitymanager.common.Params;
import com.aglhz.yicommunitymanager.common.UserHelper;
import com.aglhz.yicommunitymanager.login.contract.LoginContract;
import com.aglhz.yicommunitymanager.login.model.LoginModel;

import cn.itsite.abase.mvp.presenter.base.BasePresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Author：leguang on 2017/4/12 0009 14:23
 * Email：langmanleguang@qq.com
 */

public class LoginPresenter extends BasePresenter<LoginContract.View, LoginContract.Model> implements LoginContract.Presenter {
    private static final String TAG = LoginPresenter.class.getSimpleName();

    public LoginPresenter(LoginContract.View mView) {
        super(mView);
    }

    @NonNull
    @Override
    protected LoginContract.Model createModel() {
        return new LoginModel();
    }


    @Override
    public void requestLogin(Params params) {
        mRxManager.add(mModel.requestLogin(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userBean -> {
                    if (userBean.getOther().getCode() == Constants.RESPONSE_CODE_NOMAL) {
                        //注册友盟
//                        mModel.requestUMeng(params.user);
                        //保存用户信息
                        UserHelper.setAccount(params.user, params.pwd);//setAccount要先于setUserInfo调用，不然无法切换SP文件。
                        UserHelper.setUserInfo(userBean.getData().getMemberInfo());
                        //注册Sip到全视通服务器
//                        requestSip(Params.getInstance());
                        getView().start(null);
                    } else {
                        getView().error(userBean.getOther().getMessage());
                    }
                }, this::error, this::complete, disposable -> start(null)));
    }
}