package vn.com.codedao.facecook.presenter.updateuser;

import java.io.File;

import okhttp3.RequestBody;
import vn.com.codedao.facecook.model.login.MUserProfile;

/**
 * Created by Bruce Wayne on 18/04/2018.
 */

public interface IPresenterHandleUpdateUser {
    void saveUpdateUser(MUserProfile mUserProfile, File file, RequestBody requestBody);

    void getUser(String id);
}
