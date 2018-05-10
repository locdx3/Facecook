package vn.com.codedao.facecook.apiservice;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.com.codedao.facecook.model.login.MReponse;
import vn.com.codedao.facecook.model.login.MUserProfile;
import vn.com.codedao.facecook.model.newfeed.PostList;
import vn.com.codedao.facecook.model.newfeed.PostResponse;
import vn.com.codedao.facecook.utils.Constant;
import vn.com.codedao.facecook.utils.MessageEvent;

/**
 * Created by Bruce Wayne on 12/04/2018.
 */

public class ApiConnect {
    private final String TAG = this.getClass().getSimpleName();
    ApiInterface mApi =
            ApiClient.getClient().create(ApiInterface.class);
    MessageEvent mMessageEvent = new MessageEvent();
    public void login(final String username, final String password) {
        Log.d(TAG, "login() called with: username = [" + username + "], password = [" + password + "]");
        Call<MReponse> call = mApi.login(username, password);
        call.enqueue(new Callback<MReponse>() {
            @Override
            public void onResponse(@NonNull Call<MReponse> call, @NonNull Response<MReponse> response) {
                Log.d(TAG, "onResponse 00: ");
                if (response.isSuccessful()) {
                    MReponse reponse = response.body();
                    Log.d(TAG, "onResponse 01: " + reponse.getId());
                    mMessageEvent.setmEvent(Constant.DATALOGIN);
                    mMessageEvent.setmMRepone(reponse);
                    EventBus.getDefault().post(mMessageEvent);
                } else {
                    login(username, password);
                }
            }

            @Override
            public void onFailure(Call<MReponse> call, Throwable t) {
                Log.d(TAG, "onFailure() called with: call = [" + call + "], t = [" + t + "]");
            }
        });
    }

    public void getPost(int numItem, int startNum) {
        Call<PostResponse> call = mApi.getPosst(numItem, startNum);
        call.enqueue(new Callback<PostResponse>() {
            @Override
            public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                Log.d(TAG, "onResponse() called with: call = [" + call + "], response = [" + response + "]");
                PostResponse postResponse = response.body();
                PostList postList = new PostList();
                postList.setHeader(true);
                postResponse.getPostLists().add(0, postList);
                mMessageEvent.setmEvent(Constant.HANDLE_GET_POST_FINISH);
                mMessageEvent.setmMRepone(postResponse);
                EventBus.getDefault().post(mMessageEvent);
            }

            @Override
            public void onFailure(Call<PostResponse> call, Throwable t) {
                mMessageEvent.setmEvent(Constant.HANDLE_GET_POST_FAIL);
                EventBus.getDefault().post(mMessageEvent);
            }
        });
    }

    public void loginWithFB(MUserProfile mUserProfile) {
        Log.d(TAG, "loginWithFB() called");
        Call<MReponse> call = mApi.loginWithFB(mUserProfile.getFbid(), mUserProfile.getName(),
                mUserProfile.getUsername(), mUserProfile.getPassword(), mUserProfile.getFirstname(),
                mUserProfile.getLastname(), mUserProfile.getUrlavatar(), mUserProfile.getSex(),
                mUserProfile.getAddress(), mUserProfile.getHometown(), mUserProfile.getPhone(),
                mUserProfile.getEmail(), mUserProfile.getBirthday(), mUserProfile.getDescripton());
        call.enqueue(new Callback<MReponse>() {
            @Override
            public void onResponse(Call<MReponse> call, Response<MReponse> response) {
                MReponse reponse = response.body();
                Log.d(TAG, "onResponse 01: " + reponse.getId());
                mMessageEvent.setmEvent(Constant.DATALOGINFB);
                mMessageEvent.setmMRepone(reponse);
                EventBus.getDefault().post(mMessageEvent);
            }

            @Override
            public void onFailure(Call<MReponse> call, Throwable t) {
                String errorType, errorDesc;
                if (t instanceof IOException) {
                    errorType = "Timeout";
                    errorDesc = String.valueOf(t.getCause());
                } else if (t instanceof IllegalStateException) {
                    errorType = "ConversionError";
                    errorDesc = String.valueOf(t.getCause());
                } else {
                    errorType = "Other Error";
                    errorDesc = String.valueOf(t.getLocalizedMessage());
                }
            }
        });
    }

    public void checkRegister(String username, String password) {
        Call<MReponse> call = mApi.checkRegister(username, password);
        call.enqueue(new Callback<MReponse>() {
            @Override
            public void onResponse(Call<MReponse> call, Response<MReponse> response) {
                MReponse reponse = response.body();
                Log.d(TAG, "onResponse status : " + reponse.getStatus());
                mMessageEvent.setmEvent(Constant.CHECKDATAREGISTER);
                mMessageEvent.setmMRepone(reponse);
                EventBus.getDefault().post(mMessageEvent);
            }

            @Override
            public void onFailure(Call<MReponse> call, Throwable t) {
                Log.d(TAG, "onFailure() called with: call = [" + call + "], t = [" + t + "]");
            }
        });
    }

    public void register(String username, String password, String name) {
        Call<MReponse> call = mApi.register(username, password, name, "");
        call.enqueue(new Callback<MReponse>() {
            @Override
            public void onResponse(Call<MReponse> call, Response<MReponse> response) {
                MReponse reponse = response.body();
                Log.d(TAG, "onResponse iduser : " + reponse.getId());
                mMessageEvent.setmEvent(Constant.DATAREGISTER);
                mMessageEvent.setmMRepone(reponse);
                EventBus.getDefault().post(mMessageEvent);
            }

            @Override
            public void onFailure(Call<MReponse> call, Throwable t) {
                Log.d(TAG, "onFailure() called with: call = [" + call + "], t = [" + t + "]");
            }
        });
    }

    public void getUser(String id) {
        Log.d(TAG, "getUser() called with: id = [" + id + "]");
        Call<MUserProfile> call = mApi.getUser(id);
        call.enqueue(new Callback<MUserProfile>() {
            @Override
            public void onResponse(Call<MUserProfile> call, Response<MUserProfile> response) {
                Log.d(TAG, "onResponse() called with: call = [" + call + "], response = [" + response + "]");
                MUserProfile mUserProfile = response.body();
                Log.d(TAG, "onResponse() called with: name = [" + mUserProfile.getName() + "]");
                Log.d(TAG, "onResponse() called with: avatar = [" + mUserProfile.getUrlavatar() + "]");
                mMessageEvent.setmEvent(Constant.DATAGETUSER);
                mMessageEvent.setmMRepone(mUserProfile);
                EventBus.getDefault().post(mMessageEvent);
            }

            @Override
            public void onFailure(Call<MUserProfile> call, Throwable t) {
                Log.d(TAG, "onFailure() called with: call = [" + call + "], t = [" + t + "]");
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void UpdateUser(MUserProfile mUserProfile, File file, RequestBody requestBody) {
        RequestBody userid = RequestBody.create(MediaType.parse("text/plain"), mUserProfile.getUserid());
        RequestBody name = RequestBody.create(MediaType.parse("text/plain"), mUserProfile.getName());
        RequestBody username = RequestBody.create(MediaType.parse("text/plain"), mUserProfile.getUsername());
        RequestBody firstname = RequestBody.create(MediaType.parse("text/plain"), mUserProfile.getFirstname());
        RequestBody lastname = RequestBody.create(MediaType.parse("text/plain"), mUserProfile.getLastname());
        RequestBody sex = RequestBody.create(MediaType.parse("text/plain"), mUserProfile.getSex());
        RequestBody address = RequestBody.create(MediaType.parse("text/plain"), mUserProfile.getAddress());
        RequestBody hometown = RequestBody.create(MediaType.parse("text/plain"), mUserProfile.getHometown());
        RequestBody phone = RequestBody.create(MediaType.parse("text/plain"), mUserProfile.getPhone());
        RequestBody email = RequestBody.create(MediaType.parse("text/plain"), mUserProfile.getEmail());
        RequestBody birthday = RequestBody.create(MediaType.parse("text/plain"), mUserProfile.getBirthday());
        RequestBody descripton = RequestBody.create(MediaType.parse("text/plain"), mUserProfile.getDescripton());
        RequestBody urlavatar = RequestBody.create(MediaType.parse("text/plain"), mUserProfile.getUrlavatar());
        RequestBody dateupdate = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(LocalDate.now()));
        // Trong retrofit 2 để upload file ta sử dụng Multipart, khai báo 1 MultipartBody.Part
        // uploaded_file là key mà mình đã định nghĩa trong khi khởi tạo server
        MultipartBody.Part filePart = null;
        if (file != null) {
            filePart = MultipartBody.Part.createFormData("image", file.getName(), requestBody);
        }
        Log.d(TAG, "uploadFiles() called with: filePart = [" + filePart + "]");
        Call<MReponse> call = mApi.updateUser(filePart, userid, urlavatar, name, username, firstname, lastname, sex, address,
                hometown, phone, email, birthday, descripton, dateupdate);
        call.enqueue(new Callback<MReponse>() {
            @Override
            public void onResponse(Call<MReponse> call, Response<MReponse> response) {
                Log.d(TAG, "onResponse() called with: call = [" + call + "], response = [" + response + "]");
                MReponse mReponse = response.body();
                mMessageEvent.setmEvent(Constant.DATAUPDATEUSER);
                mMessageEvent.setmMRepone(mReponse);
                EventBus.getDefault().post(mMessageEvent);
            }

            @Override
            public void onFailure(Call<MReponse> call, Throwable t) {
                Log.d(TAG, "onFailure() called with: call = [" + call + "], t = [" + t + "]");
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void AddPost(int idUser,String conten ,String avt, File file, RequestBody requestBody) {
        RequestBody userid = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(idUser));
        RequestBody groupid = RequestBody.create(MediaType.parse("text/plain"), "1");
        RequestBody title = RequestBody.create(MediaType.parse("text/plain"), "");
        RequestBody content = RequestBody.create(MediaType.parse("text/plain"), conten);
        RequestBody priority = RequestBody.create(MediaType.parse("text/plain"), "1");
        RequestBody name = RequestBody.create(MediaType.parse("text/plain"), "");
        RequestBody description = RequestBody.create(MediaType.parse("text/plain"), "");
        RequestBody urlavatar = RequestBody.create(MediaType.parse("text/plain"), avt);
        // Trong retrofit 2 để upload file ta sử dụng Multipart, khai báo 1 MultipartBody.Part
        // uploaded_file là key mà mình đã định nghĩa trong khi khởi tạo server
        MultipartBody.Part filePart = null;
        if (file != null) {
            filePart = MultipartBody.Part.createFormData("image", file.getName(), requestBody);
        }
        Log.d(TAG, "uploadFiles() called with: filePart = [" + filePart + "]");
        Call<MReponse> call = mApi.addPost(filePart, userid, groupid, title, content, priority, name, description, urlavatar);
        call.enqueue(new Callback<MReponse>() {
            @Override
            public void onResponse(Call<MReponse> call, Response<MReponse> response) {
                Log.d(TAG, "onResponse() called with: call = [" + call + "], response = [" + response + "]");
                MReponse mReponse = response.body();
                mMessageEvent.setmEvent(Constant.ADD_POST_FINISH);
                mMessageEvent.setmMRepone(mReponse);
                EventBus.getDefault().post(mMessageEvent);
            }

            @Override
            public void onFailure(Call<MReponse> call, Throwable t) {
                Log.d(TAG, "onFailure() called with: call = [" + call + "], t = [" + t + "]");
                mMessageEvent.setmEvent(Constant.HANDLE_GET_POST_FAIL);
                EventBus.getDefault().post(mMessageEvent);
            }
        });
    }
}
