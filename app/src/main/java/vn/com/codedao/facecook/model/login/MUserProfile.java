package vn.com.codedao.facecook.model.login;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Bruce Wayne on 10/04/2018.
 */

public class MUserProfile {
    @SerializedName("userid")
    private int userid;

    @SerializedName("username")
    private String username;

    @SerializedName("password")
    private String password;

    @SerializedName("firstname")
    private String firstname;

    @SerializedName("lastname")
    private String lastname;

    @SerializedName("urlavatar")
    private String urlavatar;

    @SerializedName("age")
    private int age;

    @SerializedName("sex")
    private int sex;

    @SerializedName("address")
    private String address;

    @SerializedName("phone")
    private String phone;

    @SerializedName("gmail")
    private String gmail;

    @SerializedName("birthday")
    private Date birthday;

    @SerializedName("descripton")
    private String descripton;

    @SerializedName("last_login")
    private Date last_login;

    @SerializedName("datecreate")
    private Date datecreate;

    @SerializedName("dateupdate")
    private Date dateupdate;

    public MUserProfile() {
    }

    public MUserProfile(int userid, String username, String password, String firstname,
                        String lastname, String urlavatar, int age, int sex, String address,
                        String phone, String gmail, Date birthday, String descripton,
                        Date last_login, Date datecreate, Date dateupdate) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.urlavatar = urlavatar;
        this.age = age;
        this.sex = sex;
        this.address = address;
        this.phone = phone;
        this.gmail = gmail;
        this.birthday = birthday;
        this.descripton = descripton;
        this.last_login = last_login;
        this.datecreate = datecreate;
        this.dateupdate = dateupdate;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUrlavatar() {
        return urlavatar;
    }

    public void setUrlavatar(String urlavatar) {
        this.urlavatar = urlavatar;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getDescripton() {
        return descripton;
    }

    public void setDescripton(String descripton) {
        this.descripton = descripton;
    }

    public Date getLast_login() {
        return last_login;
    }

    public void setLast_login(Date last_login) {
        this.last_login = last_login;
    }

    public Date getDatecreate() {
        return datecreate;
    }

    public void setDatecreate(Date datecreate) {
        this.datecreate = datecreate;
    }

    public Date getDateupdate() {
        return dateupdate;
    }

    public void setDateupdate(Date dateupdate) {
        this.dateupdate = dateupdate;
    }
}
