package com.aglhz.yicommunitymanager.launch;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aglhz.yicommunitymanager.R;
import com.aglhz.yicommunitymanager.common.ApiService;
import com.aglhz.yicommunitymanager.common.Constants;
import com.aglhz.yicommunitymanager.common.UserHelper;
import com.aglhz.yicommunitymanager.web.WebActivity;

import cn.itsite.abase.common.RxManager;
import cn.itsite.abase.log.ALog;
import cn.itsite.abase.mvp.view.base.BaseFragment;
import cn.itsite.abase.network.http.HttpHelper;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Author：leguang on 2016/10/9 0009 15:49
 * Email：langmanleguang@qq.com
 */
public class SplashFragment extends BaseFragment {
    private static final String TAG = SplashFragment.class.getSimpleName();
    private RxManager mRxManager = new RxManager();

    public static SplashFragment newInstance() {
        return new SplashFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_splash, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        checkLogin();
    }

    private void checkLogin() {
        mRxManager.add(HttpHelper.getService(ApiService.class)
                .requestCheckToken(ApiService.requestCheckToken, UserHelper.token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBean -> {
                    ALog.e("1111111111");
                    if (baseBean.getOther().getCode() == Constants.RESPONSE_CODE_LOGOUT) {
                        UserHelper.clear();
                    }
                    go2Main();
                }, throwable -> {
                    ALog.e("1111111111");

                    ALog.e(throwable);
                    go2Main();
                })
        );
    }

    private void go2Main() {
        Intent intent = new Intent(_mActivity, WebActivity.class);
        intent.putExtra("link", "https://github.com/leguang/YiCommunityManager");
        startActivity(intent);
        _mActivity.overridePendingTransition(0, 0);
        //此处之所以延迟退出是因为立即退出在小米手机上会有一个退出跳转动画，而我不想要这个垂直退出的跳转动画。
        new Handler().postDelayed(() -> _mActivity.finish(), 1000);
    }

    @Override
    public void onDestroyView() {
        mRxManager.clear();
        super.onDestroyView();
    }
}
