package com.aglhz.yicommunitymanager.common;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.webkit.JavascriptInterface;

import com.aglhz.yicommunitymanager.login.LoginActivity;

import cn.itsite.abase.log.ALog;
import cn.itsite.abase.network.http.HttpHelper;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Author：leguang on 2017/4/12 0009 15:49
 * Email：langmanleguang@qq.com
 * <p>
 * JS调用。
 */
public class JavaScriptObject {
    private Activity mActivity;

    public JavaScriptObject(Activity context) {
        mActivity = context;
    }


    /**
     * 注销。
     */
    @JavascriptInterface
    public void AndroidEffect() {
        ALog.e("JavaScriptObject", "AndroidEffect-注销->");
        new AlertDialog.Builder(mActivity)
                .setTitle("提示")
                .setMessage("确定退出登录？")
                .setPositiveButton("确定", (dialog, which) -> {
                    UserHelper.clear();

                    HttpHelper.getService(ApiService.class)
                            .requestLogout(ApiService.requestLogout, UserHelper.token)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(baseBean -> {
                                ALog.e("JavaScriptObject", baseBean.getOther().getMessage());
                                go2Login();
                            });
                })
                .setNegativeButton("取消", null)
                .show();
    }

    private void go2Login() {
        mActivity.startActivity(new Intent(mActivity, LoginActivity.class));
        mActivity.finish();
    }

    /**
     * token失效。
     */
    @JavascriptInterface
    public void AndroidExit() {
        ALog.e("JavaScriptObject", "AndroidExit-token失效->");
        go2Login();
    }
}
