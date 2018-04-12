package vn.com.codedao.facecook.presenter.login;

import vn.com.codedao.facecook.view.login.iLoginView;

/**
 * Created by Bruce Wayne on 10/04/2018.
 */

public class PresenterLogicHandleLogin implements IPresenterHandleLogin {
    private iLoginView mILoginView;

    public PresenterLogicHandleLogin(iLoginView mILoginView) {
        this.mILoginView = mILoginView;
    }

    @Override
    public void checkLogin(String userName, String passWord) {
        if (userName.equals("locdx3@gmail.com") && passWord.equals("1234")) {
            mILoginView.loginSuccess();
        } else {
            mILoginView.loginFail("username fail");
        }
    }
}
