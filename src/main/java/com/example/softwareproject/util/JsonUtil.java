package com.example.softwareproject.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class JsonUtil {
    private static final Gson gson = new GsonBuilder().create();

    // 将对象转换为 JSON 字符串
    public static String toJson(Object object) {
        return gson.toJson(object);
    }

    // 将 JSON 字符串解析为对象
    public static <T> T fromJson(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }
    // 使用Type将JSON字符串解析为对象数组
    public static List<String> fromJsonToImages(String json) {
        try {
            return gson.fromJson(json, new TypeToken<List<String>>() {}.getType());
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }
}
