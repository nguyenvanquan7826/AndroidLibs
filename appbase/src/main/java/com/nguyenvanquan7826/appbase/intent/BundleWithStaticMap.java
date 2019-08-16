package com.nguyenvanquan7826.appbase.intent;

import android.os.Bundle;

import java.util.Hashtable;

public class BundleWithStaticMap implements IntentHelper {
    @Override
    public Bundle sendObject(String key, Object obj) {
        Bundle bundle = new Bundle();
        SendWithStaticMap.addObjectForKey(obj, key);
        return bundle;
    }

    @Override
    public Object receiveObject(Bundle bundle, String key) {
        return SendWithStaticMap.getObjectForKey(key);
    }


    static class SendWithStaticMap {

        private static SendWithStaticMap _instance;
        private Hashtable<String, Object> _hash;

        private SendWithStaticMap() {
            _hash = new Hashtable<>();
        }

        private static SendWithStaticMap getInstance() {
            if(_instance==null) {
                _instance = new SendWithStaticMap();
            }
            return _instance;
        }

        public static void addObjectForKey(Object object, String key) {
            getInstance()._hash.put(key, object);
        }

        public static Object getObjectForKey(String key) {
            SendWithStaticMap helper = getInstance();
            Object data = helper._hash.get(key);
            helper._hash.remove(key);
            helper = null;
            return data;
        }
    }
}
