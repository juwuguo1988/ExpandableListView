package com.example.administrator.myapplication.modle;


public class BaseResponseBean<T> {
    public String status;  //注意当status为success和fail时，都是在请求响应成功的回调中
    public T data;

    public boolean isSuccess() {
        return "success".equals(status);
    }
}
