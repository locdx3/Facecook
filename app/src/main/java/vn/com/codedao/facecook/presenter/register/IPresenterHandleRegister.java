package vn.com.codedao.facecook.presenter.register;

/**
 * Created by Bruce Wayne on 10/04/2018.
 */

public interface IPresenterHandleRegister {
    void checkValidateRegister(String userName, String passWord);
    void register(String userName, String passWord,String name);
}
