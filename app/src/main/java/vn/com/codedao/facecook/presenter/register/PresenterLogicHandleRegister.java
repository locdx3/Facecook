package vn.com.codedao.facecook.presenter.register;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import vn.com.codedao.facecook.apiservice.ApiConnect;
import vn.com.codedao.facecook.model.login.Mlogin;
import vn.com.codedao.facecook.utils.Constant;
import vn.com.codedao.facecook.utils.MessageEvent;
import vn.com.codedao.facecook.view.login.ILoginView;

/**
 * Created by Bruce Wayne on 10/04/2018.
 */

public class PresenterLogicHandleRegister implements IPresenterHandleRegister {
    private final String TAG = this.getClass().getSimpleName();
    private ILoginView mILoginView;
    private Activity mActivity;
    private EventBus mEventBus;

    public PresenterLogicHandleRegister(ILoginView mILoginView, Activity mActivity) {
        this.mILoginView = mILoginView;
        this.mActivity = mActivity;
        mEventBus = EventBus.getDefault();
        mEventBus.register(this);
    }

    @Override
    public void checkValidateRegister(String userName, String passWord) {
        ApiConnect apiConnect = new ApiConnect();
        apiConnect.checkRegister(userName, passWord);
    }

    //   start register with user
    @Override
    public void register(String userName, String passWord, String name) {
        ApiConnect apiConnect = new ApiConnect();
        apiConnect.register(userName, passWord, name);
    }
    // end  register with user

    //   Start hanle messageEvent

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onMessageEvent(MessageEvent event) {
        switch (event.getmEvent()) {
            case Constant.DATAREGISTER:
                Mlogin mlogin = (Mlogin) event.getmMlogin();
                if (mlogin.getStatus().equals("200")) {
                    SharedPreferences sharedPref = mActivity.getSharedPreferences(Constant.MyPREFERENCES, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString(Constant.ID, mlogin.getId());
                    editor.putString(Constant.TOKEN, mlogin.getToken());
                    editor.commit();
                    mILoginView.loginSuccess();
                } else {
                    mILoginView.loginFail("Register Fail");
                }
                break;
            case Constant.CHECKDATAREGISTER:
                Mlogin mregister = (Mlogin) event.getmMlogin();
                if (mregister.getStatus().equals("200")) {
                    Log.d(TAG, "onMessageEvent() called with: checkRegisterSuccess");
                    mILoginView.checkRegisterSuccess();
                } else {
                    Log.d(TAG, "onMessageEvent() called with: checkRegisterFail");
                    mILoginView.checkRegisterFail(mregister.getMessage());
                }
                break;
            default:
                break;
        }
    }

    //  end  hanle messageEvent
}
