package vn.com.codedao.facecook.apiservice;

import android.support.annotation.NonNull;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.com.codedao.facecook.model.login.MUserProfile;
import vn.com.codedao.facecook.model.login.Mlogin;
import vn.com.codedao.facecook.utils.Constant;
import vn.com.codedao.facecook.utils.MessageEvent;

/**
 * Created by Bruce Wayne on 12/04/2018.
 */

public class ApiConnect {
    private final String TAG = this.getClass().getSimpleName();
    ApiInterface mApi =
            ApiClient.getClient().create(ApiInterface.class);

    public void login(final String username, final String password) {
        Log.d(TAG, "login() called with: username = [" + username + "], password = [" + password + "]");
        Call<Mlogin> call = mApi.login(username, password);
        call.enqueue(new Callback<Mlogin>() {
            @Override
            public void onResponse(@NonNull Call<Mlogin> call, @NonNull Response<Mlogin> response) {
                Log.d(TAG, "onResponse 00: ");
                if (response.isSuccessful()) {
                    Mlogin reponse = response.body();
                    Log.d(TAG, "onResponse 01: " + reponse.getId());
                    MessageEvent messageEvent = new MessageEvent();
                    messageEvent.setmEvent(Constant.DATALOGIN);
                    messageEvent.setmMlogin(reponse);
                    EventBus.getDefault().post(messageEvent);
                } else {
                    login(username, password);
                }
            }

            @Override
            public void onFailure(Call<Mlogin> call, Throwable t) {
                Log.d(TAG, "onFailure() called with: call = [" + call + "], t = [" + t + "]");
            }
        });
    }

    public void loginWithFB(MUserProfile mUserProfile) {
        Log.d(TAG, "loginWithFB() called");
        Call<Mlogin> call = mApi.loginWithFB(mUserProfile.getFbid(), mUserProfile.getName(),
                mUserProfile.getUsername(), mUserProfile.getPassword(), mUserProfile.getFirstname(),
                mUserProfile.getLastname(), mUserProfile.getUrlavatar(), mUserProfile.getSex(),
                mUserProfile.getAddress(), mUserProfile.getHometown(), mUserProfile.getPhone(),
                mUserProfile.getEmail(), mUserProfile.getBirthday(), mUserProfile.getDescripton());
        call.enqueue(new Callback<Mlogin>() {
            @Override
            public void onResponse(Call<Mlogin> call, Response<Mlogin> response) {
                Mlogin reponse = response.body();
                Log.d(TAG, "onResponse 01: " + reponse.getId());
                MessageEvent messageEvent = new MessageEvent();
                messageEvent.setmEvent(Constant.DATALOGINFB);
                messageEvent.setmMlogin(reponse);
                EventBus.getDefault().post(messageEvent);
            }

            @Override
            public void onFailure(Call<Mlogin> call, Throwable t) {

            }
        });
    }
    public void checkRegister(String username, String password){
        Call<Mlogin> call = mApi.checkRegister(username, password);
        call.enqueue(new Callback<Mlogin>() {
            @Override
            public void onResponse(Call<Mlogin> call, Response<Mlogin> response) {
                Mlogin reponse = response.body();
                Log.d(TAG, "onResponse status : " + reponse.getStatus());
                MessageEvent messageEvent = new MessageEvent();
                messageEvent.setmEvent(Constant.CHECKDATAREGISTER);
                messageEvent.setmMlogin(reponse);
                EventBus.getDefault().post(messageEvent);
            }

            @Override
            public void onFailure(Call<Mlogin> call, Throwable t) {
                Log.d(TAG, "onFailure() called with: call = [" + call + "], t = [" + t + "]");
            }
        });
    }
    public void register(String username, String password, String name) {
        Call<Mlogin> call = mApi.register(username, password, name, "");
        call.enqueue(new Callback<Mlogin>() {
            @Override
            public void onResponse(Call<Mlogin> call, Response<Mlogin> response) {
                Mlogin reponse = response.body();
                Log.d(TAG, "onResponse iduser : " + reponse.getId());
                MessageEvent messageEvent = new MessageEvent();
                messageEvent.setmEvent(Constant.DATAREGISTER);
                messageEvent.setmMlogin(reponse);
                EventBus.getDefault().post(messageEvent);
            }

            @Override
            public void onFailure(Call<Mlogin> call, Throwable t) {
                Log.d(TAG, "onFailure() called with: call = [" + call + "], t = [" + t + "]");
            }
        });
    }
}
