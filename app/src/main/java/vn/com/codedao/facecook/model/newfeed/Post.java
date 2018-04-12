package vn.com.codedao.facecook.model.newfeed;

import java.util.List;

/**
 * Created by utnam on 4/10/2018.
 */

public class Post {
    private String mName;
    private String mTime;
    private String mImgAvatar;
    private String mConten;
    private int mType;
    private List<Like> mLikeList;
    private int mShareCount;
    private List<Comment> mCommentList;
    private String urlImg;

    public Post() {
    }

    public Post(String mName, String mTime, String mImgAvatar, String mConten, int mType, List<Like> mLikeList, int mShareCount, List<Comment> mCommentList, String urlImg) {
        this.mName = mName;
        this.mTime = mTime;
        this.mImgAvatar = mImgAvatar;
        this.mConten = mConten;
        this.mType = mType;
        this.mLikeList = mLikeList;
        this.mShareCount = mShareCount;
        this.mCommentList = mCommentList;
        this.urlImg = urlImg;
    }

    public List<Like> getmLikeList() {
        return mLikeList;
    }

    public void setmLikeList(List<Like> mLikeList) {
        this.mLikeList = mLikeList;
    }

    public List<Comment> getmCommentList() {
        return mCommentList;
    }

    public void setmCommentList(List<Comment> mCommentList) {
        this.mCommentList = mCommentList;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    public int getmShareCount() {
        return mShareCount;
    }

    public void setmShareCount(int mShareCount) {
        this.mShareCount = mShareCount;
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
