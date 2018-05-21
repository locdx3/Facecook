package vn.com.codedao.facecook.presenter.newfeed;

import android.annotation.SuppressLint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import vn.com.codedao.facecook.apiservice.ApiConnect;
import vn.com.codedao.facecook.model.newfeed.Comment;
import vn.com.codedao.facecook.model.newfeed.Like;
import vn.com.codedao.facecook.model.newfeed.PostList;
import vn.com.codedao.facecook.model.newfeed.PostResponse;
import vn.com.codedao.facecook.utils.Constant;
import vn.com.codedao.facecook.utils.MessageEvent;
import vn.com.codedao.facecook.view.newfeed.INewFeed;

/**
 * Created by utnam on 4/10/2018.
 */

public class PresenterLogicHandleNewFeed implements IPresenterHandleNewFeed {

    private static final String TAG = "PresenterLogicHandleNewFeed" ;
    private INewFeed mINewFeed;
    PostResponse mPost;
    int mPotision = 0;

    public PresenterLogicHandleNewFeed(INewFeed mINewFeed) {
        EventBus.getDefault().register(this);
        this.mINewFeed = mINewFeed;
    }

    @Override
    public void getListPost() {
        ApiConnect apiConnect = new ApiConnect();
        apiConnect.getPost(10,0);
    }

    @SuppressLint("LongLogTag")
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
            case Constant.HANDLE_ADD_POST_FINISH:
                Log.d(TAG, "onMessageEvent() called with: event = HANDLE_ADD_POST_FINISH");
                mINewFeed.updateProgessbar();
                break;
            default:
                break;
        }
    }

    @Override
    public void getListComment(int p) {
        List<Comment> comment = mPost.getPostLists().get(p).getComment();
        mINewFeed.setAdapterComment(comment);
        mPotision = p;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void addComment(Comment comment) {
        ApiConnect apiConnect = new ApiConnect();
        apiConnect.AddCommet(comment);
    }

    @Override
    public void addLike(Like like) {
        ApiConnect apiConnect = new ApiConnect();
        apiConnect.AddFell(like);
    }

    @Override
    public void addPost(PostList post) {
        mINewFeed.addPost(post);
    }

}
