package vn.com.codedao.facecook.apiservice;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import vn.com.codedao.facecook.model.login.Mlogin;

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
    Call<Mlogin> login(@Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @Headers({
            "Client-Service: locdx3",
            "Auth-Key: chandoi812"
    })
    @POST("User/register")
    Call<Mlogin> loginWithFB(
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
    Call<Mlogin> register(@Field("username") String username,
                          @Field("password") String password,
                          @Field("name") String name,
                          @Field("fbid") String fbid);

    @FormUrlEncoded
    @Headers({
            "Client-Service: locdx3",
            "Auth-Key: chandoi812"
    })
    @POST("User/checkRegister")
    Call<Mlogin> checkRegister(@Field("username") String username,
                          @Field("password") String password);


}
