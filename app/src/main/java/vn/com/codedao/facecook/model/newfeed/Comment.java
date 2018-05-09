package vn.com.codedao.facecook.model.newfeed;

/**
 * Created by utnam on 4/12/2018.
 */

public class Comment {
    private int userid;
    private String content;
    private String mDateCreate;
    private String mDataUpdate;
    private String commentid;
    private String postid;
    private String name;
    private String urlavatar;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getmDateCreate() {
        return mDateCreate;
    }

    public void setmDateCreate(String mDateCreate) {
        this.mDateCreate = mDateCreate;
    }

    public String getmDataUpdate() {
        return mDataUpdate;
    }

    public String getCommentid() {
        return commentid;
    }

    public void setCommentid(String commentid) {
        this.commentid = commentid;
    }

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlavatar() {
        return urlavatar;
    }

    public void setUrlavatar(String urlavatar) {
        this.urlavatar = urlavatar;
    }

    public Comment(int mIdUser, String mComment, String mDateCreate, String mDataUpdate) {
        this.userid = mIdUser;
        this.content = mComment;
        this.mDateCreate = mDateCreate;
        this.mDataUpdate = mDataUpdate;
    }

    public int getIdUser() {
        return userid;
    }

    public void setIdUser(int mIdUser) {
        this.userid = mIdUser;
    }

    public String getComment() {
        return content;
    }

    public void setComment(String mComment) {
        this.content = mComment;
    }

    public String getDateCreate() {
        return mDateCreate;
    }

    public void setDateCreate(String mDateCreate) {
        this.mDateCreate = mDateCreate;
    }

    public String getDataUpdate() {
        return mDataUpdate;
    }

    public void setmDataUpdate(String mDataUpdate) {
        this.mDataUpdate = mDataUpdate;
    }
}
