package com.aglhz.yicommunitymanager.entity.bean;

/**
 * Created by leguang on 2017/8/15 0015.
 * Email：langmanleguang@qq.com
 */

public class UserBean {

    /**
     * data : {"token":"ac3ae0853d9b0e9a238ba91b75aaccd4","aliasType":"app_user"}
     * other : {"code":200,"message":"登录成功","time":"","currpage":0,"next":"","forward":"","refresh":"","first":""}
     */

    private DataBean data;
    private OtherBean other;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public OtherBean getOther() {
        return other;
    }

    public void setOther(OtherBean other) {
        this.other = other;
    }

    public static class DataBean {
        /**
         * token : ac3ae0853d9b0e9a238ba91b75aaccd4
         * aliasType : app_user
         */

        private String token;
        private String aliasType;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getAliasType() {
            return aliasType;
        }

        public void setAliasType(String aliasType) {
            this.aliasType = aliasType;
        }
    }

    public static class OtherBean {
        /**
         * code : 200
         * message : 登录成功
         * time :
         * currpage : 0
         * next :
         * forward :
         * refresh :
         * first :
         */

        private int code;
        private String message;
        private String time;
        private int currpage;
        private String next;
        private String forward;
        private String refresh;
        private String first;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public int getCurrpage() {
            return currpage;
        }

        public void setCurrpage(int currpage) {
            this.currpage = currpage;
        }

        public String getNext() {
            return next;
        }

        public void setNext(String next) {
            this.next = next;
        }

        public String getForward() {
            return forward;
        }

        public void setForward(String forward) {
            this.forward = forward;
        }

        public String getRefresh() {
            return refresh;
        }

        public void setRefresh(String refresh) {
            this.refresh = refresh;
        }

        public String getFirst() {
            return first;
        }

        public void setFirst(String first) {
            this.first = first;
        }
    }
}
