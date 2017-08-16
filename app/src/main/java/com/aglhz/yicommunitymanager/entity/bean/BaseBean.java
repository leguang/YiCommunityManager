package com.aglhz.yicommunitymanager.entity.bean;

public class BaseBean {

    /**
     * other : {"currpage":0,"message":"用户已经登录！","time":"","forward":"","next":"","code":200,"first":"","refresh":""}
     */

    private OtherBean other;

    public OtherBean getOther() {
        return other;
    }

    public void setOther(OtherBean other) {
        this.other = other;
    }

    public static class OtherBean {
        /**
         * currpage : 0
         * message : 用户已经登录！
         * time :
         * forward :
         * next :
         * code : 200
         * first :
         * refresh :
         */

        private int currpage;
        private String message;
        private String time;
        private String forward;
        private String next;
        private int code;
        private String first;
        private String refresh;

        public int getCurrpage() {
            return currpage;
        }

        public void setCurrpage(int currpage) {
            this.currpage = currpage;
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

        public String getForward() {
            return forward;
        }

        public void setForward(String forward) {
            this.forward = forward;
        }

        public String getNext() {
            return next;
        }

        public void setNext(String next) {
            this.next = next;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getFirst() {
            return first;
        }

        public void setFirst(String first) {
            this.first = first;
        }

        public String getRefresh() {
            return refresh;
        }

        public void setRefresh(String refresh) {
            this.refresh = refresh;
        }
    }
}
