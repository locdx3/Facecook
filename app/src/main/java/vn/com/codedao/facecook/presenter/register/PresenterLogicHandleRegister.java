package vn.com.codedao.facecook.presenter.register;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import vn.com.codedao.facecook.apiservice.ApiConnect;
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

    //   start register with user
    @Override
    public void register(String userName, String passWord) {
        ApiConnect apiConnect = new ApiConnect();
        apiConnect.register(userName, passWord);
    }
    // end  register with user

    //   Start hanle messageEvent

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onMessageEvent(MessageEvent event) {
        switch (event.getmEvent()) {
            case Constant.DATAREGISTER:
                if (event.getmMlogin().getStatus().equals("200")) {
                    SharedPreferences sharedPref = mActivity.getSharedPreferences(Constant.MyPREFERENCES, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString(Constant.ID, event.getmMlogin().getId());
                    editor.putString(Constant.TOKEN, event.getmMlogin().getToken());
                    editor.commit();
                    mILoginView.loginSuccess();
                } else {
                    mILoginView.loginFail("Register Fail");
                }
                break;
            default:
                break;
        }
    }

    //  end  hanle messageEvent
}
