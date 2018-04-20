package vn.com.codedao.facecook.presenter.updateuser;

import android.os.Build;
import android.support.annotation.RequiresApi;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;

import okhttp3.RequestBody;
import vn.com.codedao.facecook.apiservice.ApiConnect;
import vn.com.codedao.facecook.model.login.MReponse;
import vn.com.codedao.facecook.model.login.MUserProfile;
import vn.com.codedao.facecook.utils.Constant;
import vn.com.codedao.facecook.utils.MessageEvent;
import vn.com.codedao.facecook.view.updateuser.IViewUpdateUser;

/**
 * Created by Bruce Wayne on 18/04/2018.
 */

public class PresenterLogicHandleUpdateUser implements IPresenterHandleUpdateUser {
    private IViewUpdateUser mIViewUpdateUser;
    private EventBus mEventBus;

    public PresenterLogicHandleUpdateUser(IViewUpdateUser mIViewUpdateUser) {
        this.mIViewUpdateUser = mIViewUpdateUser;
        mEventBus = EventBus.getDefault();
        mEventBus.register(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void saveUpdateUser(MUserProfile mUserProfile, File file, RequestBody requestBody) {
        ApiConnect apiConnect = new ApiConnect();
        apiConnect.UpdateUser(mUserProfile, file,requestBody);
    }

    @Override
    public void getUser(String id) {
        ApiConnect apiConnect = new ApiConnect();
        apiConnect.getUser(id);
    }
    //   Start hanle messageEvent

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onMessageEvent(MessageEvent event) {
        switch (event.getmEvent()) {
            case Constant.DATAUPDATEUSER:
                MReponse mlogin = (MReponse) event.getmMRepone();
                if (mlogin.getStatus().equals("200")) {
                    mIViewUpdateUser.updateUserSuccess("Update Success");
                } else {
                    mIViewUpdateUser.updateUserFail("Update Fail");
                }
                break;
            case Constant.DATAGETUSER:
                MUserProfile mUserProfile = (MUserProfile) event.getmMRepone();
                mIViewUpdateUser.updategetUser(mUserProfile);
                break;
            default:
                break;
        }
    }

    //  end  hanle messageEvent
}
