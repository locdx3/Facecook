package vn.com.codedao.facecook.presenter.newfeed;

import vn.com.codedao.facecook.model.newfeed.Comment;
import vn.com.codedao.facecook.model.newfeed.Like;
import vn.com.codedao.facecook.model.newfeed.Post;
import vn.com.codedao.facecook.model.newfeed.PostList;

/**
 * Created by utnam on 4/10/2018.
 */

public interface IPresenterHandleNewFeed {
    void getListPost();
    void getListComment(int p);
    void addComment(Comment comment);
    void addLike(Like like);
    void addPost(PostList post);
}
