package vn.com.codedao.facecook.presenter.login;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import vn.com.codedao.facecook.apiservice.ApiConnect;
import vn.com.codedao.facecook.model.login.MLoginFB;
import vn.com.codedao.facecook.model.login.Mlogin;
import vn.com.codedao.facecook.utils.Constant;
import vn.com.codedao.facecook.utils.MessageEvent;
import vn.com.codedao.facecook.view.login.ILoginView;

/**
 * Created by Bruce Wayne on 10/04/2018.
 */

public class PresenterLogicHandleLogin implements IPresenterHandleLogin {
    private final String TAG = this.getClass().getSimpleName();
    private ILoginView mILoginView;
    private Activity mActivity;
    private EventBus mEventBus;

    public PresenterLogicHandleLogin(ILoginView mILoginView, Activity mActivity) {
        this.mILoginView = mILoginView;
        this.mActivity = mActivity;
        mEventBus = EventBus.getDefault();
        mEventBus.register(this);
    }

    //    login with user
    @Override
    public void checkLogin(String userName, String passWord) {
        Log.d(TAG, "checkLogin() called with: userName = [" + userName + "], passWord = [" + passWord + "]");
        ApiConnect apiConnect = new ApiConnect();
        apiConnect.login(userName, passWord);
//        if (userName.equals("locdx3@gmail.com") && passWord.equals("1234")) {
//            mILoginView.loginSuccess();
//        } else {
//            mILoginView.loginFail("username fail");
//        }

    }

    //    end with user

    //    start login with facebook
    @Override
    public void loginWithFB() {
        MLoginFB mLoginFB = new MLoginFB();
        mLoginFB.loginWithFB();
    }

    //    end login with facebook

    //    start login with google
    @Override
    public void loginWithGG() {

    }
    //    end login with google

    //   Start hanle messageEvent

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onMessageEvent(MessageEvent event) {
        switch (event.getmEvent()) {
            case Constant.DATALOGIN:
                Mlogin mlogin = (Mlogin) event.getmMlogin();
                if (mlogin.getStatus().equals("200")) {
                    SharedPreferences sharedPref = mActivity.getSharedPreferences(Constant.MyPREFERENCES, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString(Constant.ID, mlogin.getId());
                    editor.putString(Constant.TOKEN, mlogin.getToken());
                    editor.commit();
                    mILoginView.loginSuccess();
                } else if (mlogin.getStatus().equals("204")) {
                    mILoginView.loginFail("Username incorrect");
                } else if (mlogin.getStatus().equals("205")) {
                    mILoginView.loginFail("Password incorrect");
                } else {
                    mILoginView.loginFail("Login Fail");
                }
                break;
            case Constant.DATALOGINFB:
                Mlogin mloginfb = (Mlogin) event.getmMlogin();
                if (mloginfb.getStatus().equals("200")) {
                    SharedPreferences sharedPref = mActivity.getSharedPreferences(Constant.MyPREFERENCES, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString(Constant.ID, mloginfb.getId());
                    editor.putString(Constant.TOKEN, mloginfb.getToken());
                    editor.commit();
                    mILoginView.loginSuccess();
                } else if (mloginfb.getStatus().equals("204")) {
                    mILoginView.loginFail("Username incorrect");
                } else if (mloginfb.getStatus().equals("205")) {
                    mILoginView.loginFail("Password incorrect");
                } else {
                    mILoginView.loginFail("Login Fail");
                }
                break;
            case Constant.DATALOGINGG:
                break;
            default:
                break;
        }
    }
    //  end  hanle messageEvent
}
