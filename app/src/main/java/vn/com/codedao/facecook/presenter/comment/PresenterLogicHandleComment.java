package vn.com.codedao.facecook.presenter.comment;

import android.annotation.SuppressLint;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import vn.com.codedao.facecook.apiservice.ApiConnect;
import vn.com.codedao.facecook.model.newfeed.Comment;
import vn.com.codedao.facecook.model.newfeed.PostList;
import vn.com.codedao.facecook.utils.Constant;
import vn.com.codedao.facecook.utils.MessageEvent;
import vn.com.codedao.facecook.view.Comment.ICommentActivity;

/**
 * Created by utnam on 4/12/2018.
 */

public class PresenterLogicHandleComment implements IPresenterHandleComment {
    private static final String TAG = "PresenterLogicHandleComment";
    private ICommentActivity mICommentActivity;

    public PresenterLogicHandleComment(ICommentActivity mICommentActivity) {
        this.mICommentActivity = mICommentActivity;
        EventBus.getDefault().register(this);
    }

    @Override
    public void getListComment(PostList comments) {
        mICommentActivity.setAdapterComemnt(comments);
    }


    @SuppressLint("NewApi")
    @Override
    public void addCommentToSevice(Comment comment) {
        ApiConnect apiConnect = new ApiConnect();
        apiConnect.AddCommet(comment);
    }

    @SuppressLint("LongLogTag")
    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onMessageEvent(MessageEvent event) {
        switch (event.getmEvent()) {
            case MessageEvent.CONNECT_INTERNET_OK:

                break;
            case MessageEvent.CONNECT_INTERNET_FAIL:

                break;
            case Constant.SEND_LIST_COMMENT:
                break;
            case Constant.HANDLE_ADD_COMMENT_FINISH:
                Log.d(TAG, "onMessageEvent() called with: event = HANDLE_ADD_COMMENT_FINISH");
                break;
            default:
                break;
        }
    }

}
