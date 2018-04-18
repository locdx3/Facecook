package vn.com.codedao.facecook.model.menu;

public class MMenu {
    private String mUrlAvatar;

    public MMenu(String mName) {
        this.mName = mName;
    }

    private String mName;

    public String getmUrlAvatar() {
        return mUrlAvatar;
    }

    public void setmUrlAvatar(String mUrlAvatar) {
        this.mUrlAvatar = mUrlAvatar;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }
}
