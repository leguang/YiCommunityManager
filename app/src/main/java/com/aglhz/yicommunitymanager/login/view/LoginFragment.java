package com.aglhz.yicommunitymanager.login.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.aglhz.yicommunitymanager.R;
import com.aglhz.yicommunitymanager.common.Params;
import com.aglhz.yicommunitymanager.common.UserHelper;
import com.aglhz.yicommunitymanager.login.contract.LoginContract;
import com.aglhz.yicommunitymanager.login.presenter.LoginPresenter;

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
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
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

        initStateBar(toolbar);
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
                params.user = etUsername.getText().toString().trim();
                params.pwd = etPassword.getText().toString().trim();
                mPresenter.start(params);
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
//        EventBus.getDefault().post(new EventData(Constants.login));
        _mActivity.finish();
    }
}


