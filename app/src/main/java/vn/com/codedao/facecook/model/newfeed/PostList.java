package vn.com.codedao.facecook.model.newfeed;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class PostList implements Serializable {
    @SerializedName("userid")
    private String userid;
    @SerializedName("postid")
    private String postid;
    @SerializedName("groupid")
    private String groupid;
    @SerializedName("title")
    private String title;
    @SerializedName("content")
    private String content;
    @SerializedName("priority")
    private String priority;
    @SerializedName("datecreate")
    private String datecreate;
    @SerializedName("dateupdate")
    private String dateupdate;
    @SerializedName("image")
    private List<MImage> image;
    @SerializedName("comment")
    private List<Comment> comment;
    @SerializedName("name")
    private String name;
    @SerializedName("fell")
    private List<Like> likeList;
    @SerializedName("urlavatar")
    private String urlavatar;
    private boolean isHeader = false;
    private boolean isNewAdd = false;

    public boolean isNewAdd() {
        return isNewAdd;
    }

    public void setNewAdd(boolean newAdd) {
        isNewAdd = newAdd;
    }

    public boolean isHeader() {
        return isHeader;
    }

    public void setHeader(boolean header) {
        isHeader = header;
    }

    public String getUrlavatar() {
        return urlavatar;
    }

    public void setUrlavatar(String urlavatar) {
        this.urlavatar = urlavatar;
    }

    public List<Like> getLikeList() {
        return likeList;
    }

    public void setLikeList(List<Like> likeList) {
        this.likeList = likeList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int type;

    public int getType() {
        if (isHeader) {
            return 0;
        } else {
            if (getImage() != null && getImage().size() > 0) {
                return 2;
            } else {
                return 1;
            }
        }
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDatecreate() {
        return datecreate;
    }

    public void setDatecreate(String datecreate) {
        this.datecreate = datecreate;
    }

    public String getDateupdate() {
        return dateupdate;
    }

    public void setDateupdate(String dateupdate) {
        this.dateupdate = dateupdate;
    }

    public List<MImage> getImage() {
        return image;
    }

    public void setImage(List<MImage> image) {
        this.image = image;
    }

    public List<Comment> getComment() {
        return comment;
    }

    public void setComment(List<Comment> comment) {
        this.comment = comment;
    }
}
