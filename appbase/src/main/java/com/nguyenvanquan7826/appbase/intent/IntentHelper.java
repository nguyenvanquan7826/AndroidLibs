package com.nguyenvanquan7826.appbase.intent;

import android.os.Bundle;

public interface IntentHelper {
    Bundle sendObject(String key, Object obj);
    Object receiveObject(Bundle bundle, String key);
}
