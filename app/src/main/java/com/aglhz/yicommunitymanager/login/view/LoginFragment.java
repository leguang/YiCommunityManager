package com.aglhz.yicommunitymanager.login.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.aglhz.yicommunitymanager.R;
import com.aglhz.yicommunitymanager.common.ApiService;
import com.aglhz.yicommunitymanager.common.Constants;
import com.aglhz.yicommunitymanager.common.Params;
import com.aglhz.yicommunitymanager.common.UserHelper;
import com.aglhz.yicommunitymanager.login.contract.LoginContract;
import com.aglhz.yicommunitymanager.login.presenter.LoginPresenter;
import com.aglhz.yicommunitymanager.web.WebActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.itsite.abase.mvp.view.base.BaseFragment;
import me.yokeyword.fragmentation.SupportFragment;


/**
 * Author：leguang on 2016/10/9 0009 15:49
 * Email：langmanleguang@qq.com
 */
public class LoginFragment extends BaseFragment<LoginContract.Presenter> implements LoginContract.View, TextWatcher {
    public static final String TAG = LoginFragment.class.getSimpleName();
    public static final int LOGIN_REQUEST = 101;
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.cb_remember)
    CheckBox cbRemember;
    @BindView(R.id.bt_login)
    Button btLogin;
    Unbinder unbinder;
    private Params params = Params.getInstance();

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @NonNull
    @Override
    protected LoginContract.Presenter createPresenter() {
        return new LoginPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }

    private void initData() {
        etUsername.addTextChangedListener(this);//先监听，这样在下面设置账号密码的时候，
        etPassword.addTextChangedListener(this);//如果账号密码是空或者不为空就能起到作用，省掉了回复按钮可点击的代码。
        if (UserHelper.isRemember()) {
            cbRemember.setChecked(UserHelper.isRemember());
            etUsername.setText(UserHelper.getAccount());
            etPassword.setText(UserHelper.getPassword());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.cb_remember, R.id.bt_login,})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cb_remember:
                break;
            case R.id.bt_login:
                params.account = etUsername.getText().toString().trim();
                params.password = etPassword.getText().toString().trim();
                mPresenter.requestLogin(params);
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        boolean enabled = TextUtils.isEmpty(etUsername.getText().toString())
                || TextUtils.isEmpty(etPassword.getText().toString());
        btLogin.setEnabled(!enabled);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    protected void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }

        if (requestCode == LOGIN_REQUEST && resultCode == SupportFragment.RESULT_OK) {
            etUsername.setText(data.getString(UserHelper.ACCOUNT, ""));
            etPassword.setText(data.getString(UserHelper.PASSWORD, ""));
        }
    }

    @Override
    public void responseLogin(Params params) {
        UserHelper.setRemember(cbRemember.isChecked());
        go2Main();
    }

    private void go2Main() {
        Intent intent = new Intent(_mActivity, WebActivity.class);
        intent.putExtra("link", ApiService.URL.replace("%1", UserHelper.token));
        startActivity(intent);
        _mActivity.overridePendingTransition(0, 0);
        //此处之所以延迟退出是因为立即退出在小米手机上会有一个退出跳转动画，而我不想要这个垂直退出的跳转动画。
        new Handler().postDelayed(() -> _mActivity.finish(), 1000);
    }
}


