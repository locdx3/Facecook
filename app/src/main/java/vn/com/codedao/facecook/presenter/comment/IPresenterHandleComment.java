package vn.com.codedao.facecook.presenter.comment;

import vn.com.codedao.facecook.model.newfeed.Comment;
import vn.com.codedao.facecook.model.newfeed.PostList;

/**
 * Created by utnam on 4/12/2018.
 */

public interface IPresenterHandleComment {
    void getListComment(PostList comments);
    void addCommentToSevice(Comment comment);

}
