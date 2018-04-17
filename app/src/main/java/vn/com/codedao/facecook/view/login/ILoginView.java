package vn.com.codedao.facecook.view.login;

/**
 * Created by Bruce Wayne on 10/04/2018.
 */

public interface ILoginView {
    void loginSuccess();
    void loginFail(String status);
    void checkRegisterSuccess();
    void checkRegisterFail(String status);
    void registerSuccess();
    void registerFail(String status);
}
