package com.nguyenvanquan7826.appbase.intent;

import android.os.Bundle;

public class SendObject {
    private IntentHelper intentHelper;

    public SendObject() {
        setIntentHelper(new BundleWithBinder());
    }

    public void setIntentHelper(IntentHelper intentHelper) {
        this.intentHelper = intentHelper;
    }

    public Bundle send(String key, Object obj) {
        return intentHelper.sendObject(key, obj);
    }

    public Object receive(Bundle bundle, String key) {
        return intentHelper.receiveObject(bundle, key);
    }
}
