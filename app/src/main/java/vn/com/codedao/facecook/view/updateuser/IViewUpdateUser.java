package vn.com.codedao.facecook.view.updateuser;

import vn.com.codedao.facecook.model.login.MUserProfile;

/**
 * Created by Bruce Wayne on 18/04/2018.
 */

public interface IViewUpdateUser {
    void updateUserSuccess(String message);

    void updateUserFail(String message);

    void updategetUser(MUserProfile mUserProfile);
}
