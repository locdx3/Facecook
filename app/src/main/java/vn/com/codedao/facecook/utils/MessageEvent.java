package vn.com.codedao.facecook.utils;

import vn.com.codedao.facecook.model.login.Mlogin;

/**
 * Created by Bruce Wayne on 12/04/2018.
 */

public class MessageEvent {
    private String mEvent;
    private Mlogin mMlogin;

    public String getmEvent() {
        return mEvent;
    }

    public void setmEvent(String mEvent) {
        this.mEvent = mEvent;
    }

    public Mlogin getmMlogin() {
        return mMlogin;
    }

    public void setmMlogin(Mlogin mMlogin) {
        this.mMlogin = mMlogin;
    }
}
