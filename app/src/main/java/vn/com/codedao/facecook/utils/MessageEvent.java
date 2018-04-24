package vn.com.codedao.facecook.utils;

/**
 * Created by Bruce Wayne on 12/04/2018.
 */

public class MessageEvent {
    public  static  final String CONNECT_INTERNET_OK ="CONNECT_INTERNET_OK";
    public  static  final String CONNECT_INTERNET_FAIL ="CONNECT_INTERNET_FAIL";
    private String mEvent;
    private Object mMRepone;

    public String getmEvent() {
        return mEvent;
    }

    public void setmEvent(String mEvent) {
        this.mEvent = mEvent;
    }

    public Object getmMRepone() {
        return mMRepone;
    }

    public void setmMRepone(Object mMRepone) {
        this.mMRepone = mMRepone;
    }
}
