package com.nguyenvanquan7826.gsonutil;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

public class GsonUtil {
    public static final String TAG = GsonUtil.class.getSimpleName();

    public static Gson getGson() {
        return new GsonBuilder().serializeNulls().create();
    }

    public static JSONObject createJSONCmd(String[] arrKey, Object[] arrValue) throws JSONException {
        JSONObject jsonObject = new JSONObject();

        if (arrKey.length != arrValue.length) {
            throw new JSONException("Error array length key and value");
        }

        for (int i = 0; i < arrKey.length; i++) jsonObject.put(arrKey[i], arrValue[i]);

        return jsonObject;
    }

    public static String createCmd(String[] arrKey, Object[] arrValue) {
        String cmd = "";
        try {
            cmd = createJSONCmd(arrKey, arrValue).toString();
        } catch (JSONException e) {
            Log.e(TAG, "Error JSON: " + e.getMessage());
        }
        return cmd;
    }
}
