package vn.com.codedao.facecook.presenter.newfeed;

import vn.com.codedao.facecook.model.newfeed.Comment;

/**
 * Created by utnam on 4/10/2018.
 */

public interface IPresenterHandleNewFeed {
    void getListPost();
    void getListComment(int p);
    void addComment(Comment comment);
}
