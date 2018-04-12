package vn.com.codedao.facecook.model.newfeed;

/**
 * Created by utnam on 4/12/2018.
 */

public class Like {
    private int mIdUser;
    private String mDataLike;

    public Like(int mIdUser, String mDataLike) {
        this.mIdUser = mIdUser;
        this.mDataLike = mDataLike;
    }

    public int getIdUser() {
        return mIdUser;
    }

    public void setIdUser(int mIdUser) {
        this.mIdUser = mIdUser;
    }

    public String getDataLike() {
        return mDataLike;
    }

    public void setDataLike(String mDataLike) {
        this.mDataLike = mDataLike;
    }
}
