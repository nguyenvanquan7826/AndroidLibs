package com.nguyenvanquan7826.appbase.util;

import android.content.Context;
import android.content.SharedPreferences;

public class Preference {
    private String keyName;

    public Preference(String keyName) {
        this.keyName = keyName;
    }

    public SharedPreferences get(Context context) {
        return context.getSharedPreferences(keyName, Context.MODE_PRIVATE);
    }

    public void save(Context context, String key, Object value) {
        SharedPreferences.Editor edit = context.getSharedPreferences(keyName, Context.MODE_PRIVATE).edit();
        if (value == null) {
            edit.putString(key, null);
        } else {
            Class<?> fieldType = value.getClass();
            if (fieldType.equals(Integer.class) || fieldType.equals(int.class)) {
                edit.putInt(key, (Integer) value);
            } else if (fieldType.equals(Long.class) || fieldType.equals(long.class)) {
                edit.putLong(key, (Long) value);
            } else if (fieldType.equals(Float.class) || fieldType.equals(float.class)) {
                edit.putFloat(key, (Float) value);
            } else if (fieldType.equals(Boolean.class) || fieldType.equals(boolean.class)) {
                edit.putBoolean(key, (Boolean) value);
            } else if (fieldType.equals(String.class)) {
                edit.putString(key, (String) value);
            }
        }
        edit.apply();
    }
}
