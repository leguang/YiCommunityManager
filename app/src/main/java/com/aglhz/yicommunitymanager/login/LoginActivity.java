package com.aglhz.yicommunitymanager.login;

import android.os.Bundle;

import com.aglhz.yicommunitymanager.R;
import com.aglhz.yicommunitymanager.login.view.LoginFragment;

import cn.itsite.abase.mvp.view.base.BaseActivity;


public class LoginActivity extends BaseActivity {
    private static final String TAG = LoginActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            loadRootFragment(R.id.fl_main_activity, LoginFragment.newInstance());
        }
    }

    @Override
    public boolean swipeBackPriority() {
        return false;
    }
}
