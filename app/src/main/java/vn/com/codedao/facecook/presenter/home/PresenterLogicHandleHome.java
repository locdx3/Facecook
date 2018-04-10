package vn.com.codedao.facecook.presenter.home;

import java.util.ArrayList;
import java.util.List;

import vn.com.codedao.facecook.model.home.mPost;
import vn.com.codedao.facecook.view.home.IHome;

/**
 * Created by utnam on 4/10/2018.
 */

public class PresenterLogicHandleHome implements IPresenterHandleHome {

    private IHome mIHome;

    public PresenterLogicHandleHome(IHome mIHome) {
        this.mIHome = mIHome;
    }

    @Override
    public void getListPost() {
        List<mPost> mPosts = new ArrayList<>();
        for (int i = 0; i < 100 ; i++) {
            mPosts.add(new mPost("Name "+i,"1 hrs","Test","Trăm năm trong cõi người ta, Chữ tài chữ mệnh khéo là ghét nhau. Trải qua một cuộc bể dâu, Những điều trông thấy mà đau đớn lòng"));
        }
        mIHome.setApdater(mPosts);
    }
}
