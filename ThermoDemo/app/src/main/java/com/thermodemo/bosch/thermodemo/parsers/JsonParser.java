package com.thermodemo.bosch.thermodemo.parsers;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

public class JsonParser<T> {
    private final Gson mGson;
    private final Class<T[]> mClazzArray;
    private final Class<T> mClazz;

    public JsonParser(Class<T> clazz, Class<T[]> clazzArray) {
        mClazz = clazz;
        mClazzArray = clazzArray;
        mGson = new Gson();
    }

    public T fromJson(String json) {
        return mGson.fromJson(json, mClazz);
    }

    public List<T> fromJsonArray(String json) {
        return Arrays.asList(mGson.fromJson(json, mClazzArray));
    }

    public String toJson(T object) {
        return mGson.toJson(object);
    }
}
