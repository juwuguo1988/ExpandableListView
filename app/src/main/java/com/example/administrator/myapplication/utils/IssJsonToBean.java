package com.example.administrator.myapplication.utils;

import com.google.gson.Gson;

public class IssJsonToBean<T> {
	
	public T parseToBean(String json, Class<T> clazz) {
		T t = null;
		try {
			t = new Gson().fromJson(json, clazz);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}

	public static <T> T parseBean(String json, Class<T> clazz) {
		return new Gson().fromJson(json, clazz);
	}

}
