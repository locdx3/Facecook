package vn.com.codedao.facecook.model.login;

import android.os.Bundle;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import vn.com.codedao.facecook.apiservice.ApiConnect;

/**
 * Created by Bruce Wayne on 15/04/2018.
 */

public class MLoginFB {
    private final String TAG = this.getClass().getSimpleName();
    private AccessTokenTracker mAccessTokenTracker;
    private CallbackManager mCallbackManager;
    private AccessToken accessToken;

    public AccessToken getAccessToken() {
        mCallbackManager = CallbackManager.Factory.create();

        mAccessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(
                    AccessToken oldAccessToken,
                    AccessToken currentAccessToken) {
                // Set the access token using
                // currentAccessToken when it's loaded or set.
                if (oldAccessToken != currentAccessToken) {
                    accessToken = currentAccessToken;
                }
            }
        };
        // If the access token is available already assign it.
        return accessToken = AccessToken.getCurrentAccessToken();

    }

    public void stopTrackingFB() {
        mAccessTokenTracker.stopTracking();
    }

    public void loginWithFB() {
        AccessToken accessToken = getAccessToken();
        Log.d(TAG, "loginWithFB() called accessToken : " + accessToken.toString());
        GraphRequest request = GraphRequest.newMeRequest(
                accessToken,
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject object,
                            GraphResponse response) {
                        MUserProfile mUserProfile = getFacebookData(object);
                        ApiConnect apiConnect = new ApiConnect();
                        apiConnect.loginWithFB(mUserProfile);
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id, name,first_name, last_name, email,gender, birthday, location,hometown ");
        request.setParameters(parameters);
        request.executeAsync();
    }

    private MUserProfile getFacebookData(JSONObject object) {
        Log.d(TAG, "getFacebookData() called with: object = [" + object.toString() + "]");
        try {
            String time = DateFormat.getDateTimeInstance().format(new Date());
            MUserProfile mUserProfile = new MUserProfile();
            String id = object.getString("id");
            try {
                URL profile_pic = new URL("https://graph.facebook.com/" + id + "/picture?width=200&height=150");
                Log.d(TAG, "getFacebookData() called with: profile_pic = " + profile_pic.toString());
                mUserProfile.setUrlavatar(profile_pic.toString());

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            }

            mUserProfile.setFbid(id);
            if (object.has("name")) {
                mUserProfile.setName(object.getString("name"));
                Log.d(TAG, "getFacebookData() called with:" +
                        " name = [" + object.getString("name") + "]");
            } else {
                mUserProfile.setName("");
            }
            if (object.has("first_name")) {
                mUserProfile.setFirstname(object.getString("first_name"));
                Log.d(TAG, "getFacebookData() called with: " +
                        "first_name = [" + object.getString("first_name") + "]");
            } else {
                mUserProfile.setFirstname("");
            }
            if (object.has("last_name")) {
                mUserProfile.setLastname(object.getString("last_name"));
                Log.d(TAG, "getFacebookData() called with: " +
                        "last_name = [" + object.getString("last_name") + "]");
            } else {
                mUserProfile.setLastname("");
            }
            if (object.has("email")) {
                mUserProfile.setEmail(object.getString("email"));
                Log.d(TAG, "getFacebookData() called with: " +
                        "email = [" + object.getString("email") + "]");
            } else {
                mUserProfile.setEmail("");
            }
            if (object.has("gender")) {
                mUserProfile.setSex(object.getString("gender"));
                Log.d(TAG, "getFacebookData() called with: " +
                        "gender = [" + object.getString("gender") + "]");
            } else {
                mUserProfile.setSex("");
            }
            if (object.has("birthday")) {
                mUserProfile.setBirthday(object.getString("birthday"));
                Log.d(TAG, "getFacebookData() called with: " +
                        "birthday = [" + object.getString("birthday") + "]");
            } else {
                mUserProfile.setBirthday(time);
            }
            if (object.has("location")) {
                mUserProfile.setAddress(object.getJSONObject("location").getString("name"));
                Log.d(TAG, "getFacebookData() called with: location =" +
                        " [" + object.getJSONObject("location").getString("name") + "]");
            } else {
                mUserProfile.setAddress("");
            }
            if (object.has("hometown")) {
                mUserProfile.setHometown(object.getJSONObject("hometown").getString("name"));
                Log.d(TAG, "getFacebookData() called with: hometown  " +
                        "= [" + object.getJSONObject("hometown").getString("name") + "]");
            } else {
                mUserProfile.setHometown("");
            }
            mUserProfile.setPassword("");
            mUserProfile.setUsername("");
            mUserProfile.setPhone("");
            mUserProfile.setDescripton("");
            return mUserProfile;
        } catch (JSONException e) {
            Log.d(TAG, "Error parsing JSON");
        }
        return null;
    }

    public void disconnectFromFacebook() {

        if (AccessToken.getCurrentAccessToken() == null) {
            return; // already logged out
        }

        new GraphRequest(AccessToken.getCurrentAccessToken(), "/me/permissions/",
                null, HttpMethod.DELETE, new GraphRequest
                .Callback() {
            @Override
            public void onCompleted(GraphResponse graphResponse) {

                LoginManager.getInstance().logOut();

            }
        }).executeAsync();
    }

    public Date getdatefromString(String date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date cdate = df.parse(date);
            return cdate;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
