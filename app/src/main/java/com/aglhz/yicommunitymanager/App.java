package com.aglhz.yicommunitymanager;

import android.app.Activity;
import android.app.Application;
import android.app.Notification;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.aglhz.yicommunitymanager.common.ApiService;
import com.aglhz.yicommunitymanager.common.UserHelper;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.MsgConstant;
import com.umeng.message.PushAgent;
import com.umeng.message.UTrack;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.entity.UMessage;

import cn.itsite.abase.BaseApplication;
import cn.itsite.abase.log.ALog;
import cn.itsite.abase.network.http.HttpHelper;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Author：leguang on 2016/10/9 0009 15:49
 * Email：langmanleguang@qq.com
 */
public class App extends BaseApplication implements Application.ActivityLifecycleCallbacks {
    private static final String TAG = App.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        initData();//数据的初始化要在友盟推送之前，因为要注册别名时，用到用户名。
        initPush();//初始化友盟推送。
    }

    private void initData() {
        UserHelper.init();
        registerActivityLifecycleCallbacks(this);
    }


    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        ALog.e("onActivityCreated");
        PushAgent.getInstance(mContext).onAppStart();
    }

    @Override
    public void onActivityStarted(Activity activity) {
        ALog.e("onActivityStarted");
    }

    @Override
    public void onActivityResumed(Activity activity) {
        ALog.e("onActivityResumed");
    }

    @Override
    public void onActivityPaused(Activity activity) {
        ALog.e("onActivityPaused");
    }

    @Override
    public void onActivityStopped(Activity activity) {
        ALog.e("onActivityStopped");
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        ALog.e("onActivitySaveInstanceState");
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        ALog.e("onActivityDestroyed");
    }

    //初始化友盟推送
    private void initPush() {
        PushAgent mPushAgent = PushAgent.getInstance(this);
        mPushAgent.setDebugMode(true);

        //sdk开启通知声音
        mPushAgent.setNotificationPlaySound(MsgConstant.NOTIFICATION_PLAY_SDK_ENABLE);

        ALog.e(TAG, "UserHelper.account-->" + UserHelper.account);

        mPushAgent.addExclusiveAlias(UserHelper.account, "userType", new UTrack.ICallBack() {
            @Override
            public void onMessage(boolean b, String s) {
                ALog.e(TAG, "addAlias-->" + b + "……" + s);
            }
        });

        //注册推送服务 每次调用register都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {
            @Override
            public void onSuccess(String deviceToken) {

                ALog.e(TAG, "deviceToken-->" + deviceToken);

                HttpHelper.getService(ApiService.class)
                        .requestRegisterPush(ApiService.requestRegisterPush, UserHelper.token, UserHelper.account, "app_user", "and_" + deviceToken)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(baseBean -> ALog.e(TAG, baseBean.getOther().getMessage()));
            }

            @Override
            public void onFailure(String s, String s1) {
                ALog.e(TAG, "register failed: " + s + " ---  " + s1);
            }
        });

        UmengMessageHandler messageHandler = new UmengMessageHandler() {
//            @Override
//            public void dealWithCustomMessage(final Context context, final UMessage msg) {
//                ALog.e(TAG, msg.getRaw().toString());//未来考虑把这个写入本地日志系统，当然要考虑异步形式。
//
//                new Handler().post(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        // TODO Auto-generated method stub
//                        // 对自定义消息的处理方式，点击或者忽略
//                        boolean isClickOrDismissed = true;
//                        if (isClickOrDismissed) {
////统计自定义消息的打开
//                            UTrack.getInstance(getApplicationContext()).trackMsgClick(msg);
//                        } else {
////统计自定义消息的忽略
//                            UTrack.getInstance(getApplicationContext()).trackMsgDismissed(msg);
//                        }
//                        Toast.makeText(context, msg.custom, Toast.LENGTH_LONG).show();
//
//                    }
//                });
//            }

            //自定义通知样式
            @Override
            public Notification getNotification(Context context, UMessage msg) {
                //每当有通知送达时，均会回调getNotification方法，因此可以通过监听此方法来判断通知是否送达。
                ALog.e(TAG, msg.getRaw().toString());
                ALog.e(TAG, msg.custom);

                switch (msg.builder_id) {
                    //自定义通知样式编号
                    case 1:
                        ALog.e(TAG, msg.builder_id);

//                        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
//                        RemoteViews myNotificationView = new RemoteViews(context.getPackageName(), R.layout.notification_view);
//                        myNotificationView.setTextViewText(R.id.notification_title, msg.title);
//                        myNotificationView.setTextViewText(R.id.notification_text, msg.text);
//                        myNotificationView.setImageViewBitmap(R.id.notification_large_icon, getLargeIcon(context, msg));
//                        myNotificationView.setImageViewResource(R.id.notification_small_icon, getSmallIconId(context, msg));
//                        builder.setContent(myNotificationView);
//                        builder.setAutoCancel(true);
//                        Notification mNotification = builder.build();
//                        //由于Android v4包的bug，在2.3及以下系统，Builder创建出来的Notification，并没有设置RemoteView，故需要添加此代码
//                        mNotification.contentView = myNotificationView;
//                        return mNotification;
                    default:
                        //默认为0，若填写的builder_id并不存在，也使用默认。
                        return super.getNotification(context, msg);
                }
            }
        };

        mPushAgent.setMessageHandler(messageHandler);

        /**
         * 该Handler是在BroadcastReceiver中被调用，故
         * 如果需启动Activity，需添加Intent.FLAG_ACTIVITY_NEW_TASK
         * 参考集成文档的1.6.2
         * [url=http://dev.umeng.com/push/android/integration#1_6_2]http://dev.umeng.com/push/android/integration#1_6_2[/url]
         * */
        UmengNotificationClickHandler notificationClickHandler = new UmengNotificationClickHandler() {
            //点击通知的自定义行为
            @Override
            public void dealWithCustomAction(Context context, UMessage msg) {
                Toast.makeText(context, msg.custom, Toast.LENGTH_LONG).show();
                ALog.e(TAG, msg.getRaw().toString());//未来考虑把这个写入本地日志系统，当然要考虑异步形式。
                ALog.e(TAG, msg.custom);
            }
        };

        mPushAgent.setNotificationClickHandler(notificationClickHandler);
    }
}
