package com.nguyenvanquan7826.appbase.intent;

import android.os.Binder;
import android.os.Bundle;

public class BundleWithBinder implements IntentHelper {
    @Override
    public Bundle sendObject(String key, Object obj) {
        Bundle bundle = new Bundle();
        bundle.putBinder(key, new SendWithBinder(obj));
        return bundle;
    }

    @Override
    public Object receiveObject(Bundle bundle, String key) {
        return ((SendWithBinder) bundle.getBinder(key)).getData();
    }

    class SendWithBinder extends Binder {

        private final Object mData;

        public SendWithBinder(Object data) {
            mData = data;
        }

        public Object getData() {
            return mData;
        }
    }
}
