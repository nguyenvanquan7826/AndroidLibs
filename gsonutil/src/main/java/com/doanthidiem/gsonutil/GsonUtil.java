package com.doanthidiem.gsonutil;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtil {
    public static Gson getGson() {
        return new GsonBuilder().serializeNulls().create();
    }
}
