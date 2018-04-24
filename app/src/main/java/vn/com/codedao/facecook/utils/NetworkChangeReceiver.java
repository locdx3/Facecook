package vn.com.codedao.facecook.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Bruce Wayne on 21/04/2018.
 */

public class NetworkChangeReceiver extends BroadcastReceiver {
    private final String TAG = this.getClass().getSimpleName();
    MessageEvent messageEvent = new MessageEvent();
    @Override
    public void onReceive(Context context, Intent intent)
    {
        try
        {
            if (isOnline(context)) {
                messageEvent.setmEvent(MessageEvent.CONNECT_INTERNET_OK);
                EventBus.getDefault().post(messageEvent);
                Log.e(TAG, "Online Connect Intenet ");
            } else {
                messageEvent.setmEvent(MessageEvent.CONNECT_INTERNET_FAIL);
                EventBus.getDefault().post(messageEvent);
                Log.e(TAG, "Conectivity Failure !!! ");
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private boolean isOnline(Context context) {
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            //should check null because in airplane mode it will be null
            return (netInfo != null && netInfo.isConnected());
        } catch (NullPointerException e) {
            e.printStackTrace();
            return false;
        }
    }

}
