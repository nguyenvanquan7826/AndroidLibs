package com.nguyenvanquan7826.appbase.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtil {
    public static boolean isNetworkOnline(Context context) {
        boolean status = false;
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            if (netInfo != null && netInfo.getState() == NetworkInfo.State.CONNECTED) {
                status = true;
            }
//            else {
//                netInfo = cm.getNetworkInfo(1);
//                if (netInfo != null && netInfo.getState() == NetworkInfo.State.CONNECTED)
//                    status = true;
//            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return status;
    }
}
