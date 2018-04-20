package vn.com.codedao.facecook.apiservice;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import vn.com.codedao.facecook.model.login.MReponse;
import vn.com.codedao.facecook.model.login.MUserProfile;

/**
 * Created by Bruce Wayne on 12/04/2018.
 */

public interface ApiInterface {
    @FormUrlEncoded
    @Headers({
            "Client-Service: locdx3",
            "Auth-Key: chandoi812"
    })
    @POST("User/login")
    Call<MReponse> login(@Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @Headers({
            "Client-Service: locdx3",
            "Auth-Key: chandoi812"
    })
    @POST("User/register")
    Call<MReponse> loginWithFB(
            @Field("fbid") String fbid,
            @Field("name") String name,
            @Field("username") String username,
            @Field("password") String password,
            @Field("firstname") String firstname,
            @Field("lastname") String lastname,
            @Field("urlavatar") String urlavatar,
            @Field("sex") String sex,
            @Field("address") String address,
            @Field("hometown") String hometown,
            @Field("phone") String phone,
            @Field("email") String email,
            @Field("birthday") String birthday,
            @Field("descripton") String descripton);

    @FormUrlEncoded
    @Headers({
            "Client-Service: locdx3",
            "Auth-Key: chandoi812"
    })
    @POST("User/register")
    Call<MReponse> register(@Field("username") String username,
                            @Field("password") String password,
                            @Field("name") String name,
                            @Field("fbid") String fbid);

    @FormUrlEncoded
    @Headers({
            "Client-Service: locdx3",
            "Auth-Key: chandoi812"
    })
    @POST("User/checkRegister")
    Call<MReponse> checkRegister(@Field("username") String username,
                                 @Field("password") String password);


    @FormUrlEncoded
    @Headers({
            "Client-Service: locdx3",
            "Auth-Key: chandoi812"
    })
    @POST("User/getUser")
    Call<MUserProfile> getUser(@Field("userid") String userid);

    @Headers({
            "Client-Service: locdx3",
            "Auth-Key: chandoi812"
    })
    @Multipart
    @POST("User/updateUser")
    Call<MReponse> updateUser(
            @Part MultipartBody.Part filePart,
            @Part("userid") RequestBody userid,
            @Part("name") RequestBody name,
            @Part("username") RequestBody username,
            @Part("firstname") RequestBody firstname,
            @Part("lastname") RequestBody lastname,
            @Part("sex") RequestBody sex,
            @Part("address") RequestBody address,
            @Part("hometown") RequestBody hometown,
            @Part("phone") RequestBody phone,
            @Part("email") RequestBody email,
            @Part("birthday") RequestBody birthday,
            @Part("descripton") RequestBody descripton,
            @Part("dateupdate") RequestBody dateupdate);
}
