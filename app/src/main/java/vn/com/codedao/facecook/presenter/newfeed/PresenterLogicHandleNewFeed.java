package vn.com.codedao.facecook.presenter.newfeed;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import vn.com.codedao.facecook.apiservice.ApiConnect;
import vn.com.codedao.facecook.model.newfeed.Comment;
import vn.com.codedao.facecook.model.newfeed.PostResponse;
import vn.com.codedao.facecook.utils.Constant;
import vn.com.codedao.facecook.utils.MessageEvent;
import vn.com.codedao.facecook.view.newfeed.INewFeed;

/**
 * Created by utnam on 4/10/2018.
 */

public class PresenterLogicHandleNewFeed implements IPresenterHandleNewFeed {

    private INewFeed mINewFeed;
    PostResponse mPost;

    public PresenterLogicHandleNewFeed(INewFeed mINewFeed) {
        EventBus.getDefault().register(this);
        this.mINewFeed = mINewFeed;
    }

    @Override
    public void getListPost() {
        ApiConnect apiConnect = new ApiConnect();
        apiConnect.getPost(5,0);
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onMessageEvent(MessageEvent event) {
        switch (event.getmEvent()) {
            case MessageEvent.CONNECT_INTERNET_OK:
                mINewFeed.checkInternet(true);
                break;
            case MessageEvent.CONNECT_INTERNET_FAIL:
                mINewFeed.checkInternet(false);
                break;
            case Constant.HANDLE_GET_POST_FINISH:
                mPost = (PostResponse) event.getmMRepone();
                mINewFeed.setApdater(mPost.getPostLists());
                break;
            default:
                break;
        }
    }

    @Override
    public void getListComment(int p) {
        List<Comment> comment = mPost.getPostLists().get(p).getComment();
        mINewFeed.setAdapterComment(comment);
    }

}
