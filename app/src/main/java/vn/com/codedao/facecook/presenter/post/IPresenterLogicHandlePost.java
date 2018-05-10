package vn.com.codedao.facecook.presenter.post;

import android.net.Uri;

public interface IPresenterLogicHandlePost {
    void AddPhoto();

    void pickImg();

    void uploadFiles(Uri uri);

    void postOnService(int idUser, String conten);

    void cancel();
}
