package vn.com.codedao.facecook.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;

/**
 * Created by Bruce Wayne on 21/04/2018.
 */

public class PermissionManager {
    //A method that can be called from any Activity, to check for specific permission
    public static boolean isConnect(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Network[] netArray = connectivityManager.getAllNetworks();
            NetworkInfo netInfo;
            for (Network net : netArray) {
                netInfo = connectivityManager.getNetworkInfo(net);
                if ((netInfo.getTypeName().equalsIgnoreCase("WIFI") || netInfo.getTypeName().equalsIgnoreCase("MOBILE")) && netInfo.isConnected() && netInfo.isAvailable()) {
                    //if (netInfo.getState().equals(NetworkInfo.State.CONNECTED)) {
                    Log.d("Network", "NETWORKNAME: " + netInfo.getTypeName());
                    return true;
                }
            }
        } else {
            if (connectivityManager != null) {
                @SuppressWarnings("deprecation")
                NetworkInfo[] netInfoArray = connectivityManager.getAllNetworkInfo();
                if (netInfoArray != null) {
                    for (NetworkInfo netInfo : netInfoArray) {
                        if ((netInfo.getTypeName().equalsIgnoreCase("WIFI") || netInfo.getTypeName().equalsIgnoreCase("MOBILE")) && netInfo.isConnected() && netInfo.isAvailable()) {
                            //if (netInfo.getState() == NetworkInfo.State.CONNECTED) {
                            Log.d("Network", "NETWORKNAME: " + netInfo.getTypeName());
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
