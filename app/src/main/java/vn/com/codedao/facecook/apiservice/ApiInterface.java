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
            "Client-Service: frontend-client",
            "Auth-Key: simplerestapi"
    })
    @POST("Auth/login")
    Call<Mlogin> login(@Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @Headers({
            "Client-Service: frontend-client",
            "Auth-Key: simplerestapi"
    })
    @POST("Auth/register")
    Call<Mlogin> register(@Field("username") String username, @Field("password") String password);
}
