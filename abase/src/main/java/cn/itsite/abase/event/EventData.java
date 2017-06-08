package cn.itsite.abase.event;

import cn.itsite.abase.common.RxManager;

/**
 * Author：leguang on 2016/10/9 0009 15:49
 * Email：langmanleguang@qq.com
 */
public class EventData {
    private final String TAG = EventData.class.getSimpleName();
    public int position;

    public EventData(int position) {
        this.position = position;
    }
}
