package vn.com.codedao.facecook.model.newfeed;

/**
 * Created by utnam on 4/10/2018.
 */

public class mPost {
    private String mName;
    private String mTime;
    private String mImgAvatar;
    private String mConten;
    private int mType;
    private int mLikeCount;
    private int mShareCount;

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    private String urlImg;

    public mPost(String mName, String mTime, String mImgAvatar, String mConten, int mType, int mLikeCount, int mShareCount, int mCommentCount, String urlImg) {
        this.mName = mName;
        this.mTime = mTime;
        this.mImgAvatar = mImgAvatar;
        this.mConten = mConten;
        this.mType = mType;
        this.mLikeCount = mLikeCount;
        this.mShareCount = mShareCount;
        this.urlImg = urlImg;
        this.mCommentCount = mCommentCount;
    }
    public mPost(String mName, String mTime, String mImgAvatar, String mConten, int mType, int mLikeCount, int mShareCount, int mCommentCount) {
        this.mName = mName;
        this.mTime = mTime;
        this.mImgAvatar = mImgAvatar;
        this.mConten = mConten;
        this.mType = mType;
        this.mLikeCount = mLikeCount;
        this.mShareCount = mShareCount;
        this.mCommentCount = mCommentCount;
    }

    public int getmLikeCount() {
        return mLikeCount;
    }

    public void setmLikeCount(int mLikeCount) {
        this.mLikeCount = mLikeCount;
    }

    public int getmShareCount() {
        return mShareCount;
    }

    public void setmShareCount(int mShareCount) {
        this.mShareCount = mShareCount;
    }

    public int getmCommentCount() {
        return mCommentCount;
    }

    public void setmCommentCount(int mCommentCount) {
        this.mCommentCount = mCommentCount;
    }

    private int mCommentCount;

    public mPost() {
    }

    public int getType() {
        return mType;
    }

    public void setType(int mType) {
        this.mType = mType;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String mTime) {
        this.mTime = mTime;
    }

    public String getImgAvatar() {
        return mImgAvatar;
    }

    public void setImgAvatar(String mImgAvatar) {
        this.mImgAvatar = mImgAvatar;
    }

    public String getConten() {
        return mConten;
    }

    public void setConten(String mConten) {
        this.mConten = mConten;
    }
}
