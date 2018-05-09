package vn.com.codedao.facecook.view.newfeed;

import java.util.List;

import vn.com.codedao.facecook.model.newfeed.Comment;
import vn.com.codedao.facecook.model.newfeed.PostList;

/**
 * Created by utnam on 4/10/2018.
 */

public interface INewFeed {
    void setApdater(List<PostList> posts);

    void setAdapterComment(List<Comment> comments);

    void checkInternet(boolean iscontect);
}
