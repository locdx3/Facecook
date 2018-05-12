package vn.com.codedao.facecook.view.Comment;

import vn.com.codedao.facecook.model.newfeed.PostList;

/**
 * Created by utnam on 4/12/2018.
 */

public interface ICommentActivity {
    void setAdapterComemnt(PostList postList);
    void notifyData();
}
