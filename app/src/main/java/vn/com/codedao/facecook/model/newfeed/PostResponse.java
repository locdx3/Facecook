package vn.com.codedao.facecook.model.newfeed;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PostResponse {
    @SerializedName("ListPost")
    private List<PostList> postLists;

    public List<PostList> getPostLists() {
        return postLists;
    }

    public void setPostLists(List<PostList> postLists) {
        this.postLists = postLists;
    }
}
