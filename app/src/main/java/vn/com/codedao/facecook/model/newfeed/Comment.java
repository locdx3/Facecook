package vn.com.codedao.facecook.model.newfeed;

/**
 * Created by utnam on 4/12/2018.
 */

public class Comment {
    private int mIdUser;
    private String mComment;
    private String mDateCreate;
    private String mDataUpdate;

    public Comment(int mIdUser, String mComment, String mDateCreate, String mDataUpdate) {
        this.mIdUser = mIdUser;
        this.mComment = mComment;
        this.mDateCreate = mDateCreate;
        this.mDataUpdate = mDataUpdate;
    }

    public int getIdUser() {
        return mIdUser;
    }

    public void setIdUser(int mIdUser) {
        this.mIdUser = mIdUser;
    }

    public String getComment() {
        return mComment;
    }

    public void setComment(String mComment) {
        this.mComment = mComment;
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
