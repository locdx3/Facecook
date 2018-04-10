package vn.com.codedao.facecook.model.home;

/**
 * Created by utnam on 4/10/2018.
 */

public class mPost {
    private String mName;
    private String mTime;
    private String mImgAvatar;
    private String mConten;

    public mPost() {
    }

    public mPost(String mName, String mTime, String mImgAvatar, String mConten) {
        this.mName = mName;
        this.mTime = mTime;
        this.mImgAvatar = mImgAvatar;
        this.mConten = mConten;
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
