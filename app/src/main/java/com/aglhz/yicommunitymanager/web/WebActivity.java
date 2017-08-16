package com.aglhz.yicommunitymanager.web;

import android.os.Bundle;

import com.aglhz.yicommunitymanager.R;

import cn.itsite.abase.log.ALog;
import me.yokeyword.fragmentation.SupportActivity;


/**
 * Author：leguang on 2017/4/12 0009 15:49
 * Email：langmanleguang@qq.com
 * <p>
 * 负责项目中的web部分。
 */

public class WebActivity extends SupportActivity {
    private static final String TAG = WebActivity.class.getSimpleName();
    private String link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        if (savedInstanceState == null) {
            loadRootFragment(R.id.fl_main_activity, WebFragment.newInstance(link));
        }
    }

    private void initData() {
        link = getIntent().getStringExtra("link");
        ALog.e(link);
    }
}
