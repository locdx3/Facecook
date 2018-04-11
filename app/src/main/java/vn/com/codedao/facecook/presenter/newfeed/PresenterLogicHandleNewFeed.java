package vn.com.codedao.facecook.presenter.newfeed;

import java.util.ArrayList;
import java.util.List;

import vn.com.codedao.facecook.model.newfeed.mPost;
import vn.com.codedao.facecook.view.newfeed.INewFeed;

/**
 * Created by utnam on 4/10/2018.
 */

public class PresenterLogicHandleNewFeed implements IPresenterHandleNewFeed {

    private INewFeed mINewFeed;

    public PresenterLogicHandleNewFeed(INewFeed mINewFeed) {
        this.mINewFeed = mINewFeed;
    }

    @Override
    public void getListPost() {
        List<mPost> mPosts = new ArrayList<>();

        mPosts.add(new mPost("Hoang Nam ","1 hrs","Test","Trăm năm trong cõi người ta, Chữ tài chữ mệnh khéo là ghét nhau. Trải qua một cuộc bể dâu, Những điều trông thấy mà đau đớn lòng",2,99,2,2,"http://2sao.vietnamnetjsc.vn/images/2017/09/03/06/48/hot-girl-1.jpg"));
        mPosts.add(new mPost("QuyenNH ","1 hrs","Test","Trăm năm trong cõi người ta, Chữ tài chữ mệnh khéo là ghét nhau. Trải qua một cuộc bể dâu, Những điều trông thấy mà đau đớn lòng",2,2,4,6,"http://2sao.vietnamnetjsc.vn/images/2017/09/03/06/48/hot-girl-6.jpg"));
        mPosts.add(new mPost("Dao Xuan Loc ","1 hrs","Test","Trăm năm trong cõi người ta, Chữ tài chữ mệnh khéo là ghét nhau. Trải qua một cuộc bể dâu, Những điều trông thấy mà đau đớn lòng",2,999,10,88,"http://2sao.vietnamnetjsc.vn/images/2017/09/03/06/48/hot-girl-12.jpg"));
        mPosts.add(new mPost("Tre Trau ","1 hrs","Test","Trăm năm trong cõi người ta, Chữ tài chữ mệnh khéo là ghét nhau. Trải qua một cuộc bể dâu, Những điều trông thấy mà đau đớn lòng",1,99999,10,8));
        mPosts.add(new mPost("NamHV ","1 hrs","Test","Trăm năm trong cõi người ta, Chữ tài chữ mệnh khéo là ghét nhau. Trải qua một cuộc bể dâu, Những điều trông thấy mà đau đớn lòng",1,777,3,1));
        mPosts.add(new mPost("LocDX","1 hrs","Test","Trăm năm trong cõi người ta, Chữ tài chữ mệnh khéo là ghét nhau. Trải qua một cuộc bể dâu, Những điều trông thấy mà đau đớn lòng",2,99999,10,3,"http://2sao.vietnamnetjsc.vn/images/2017/09/03/06/48/hot-girl-1.jpg"));

        mINewFeed.setApdater(mPosts);
    }
}
