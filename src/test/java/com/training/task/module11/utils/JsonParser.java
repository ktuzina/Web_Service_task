package com.training.task.module11.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.training.task.module11.models.User;

import java.lang.reflect.Type;
import java.util.List;

public class JsonParser {

    public static List<User> getUserList(String json) {
        Type itemsListType = new TypeToken<List<User>>() {}.getType();
        List<User> userList = new Gson().fromJson(json, itemsListType);
        return userList;
    }

}
