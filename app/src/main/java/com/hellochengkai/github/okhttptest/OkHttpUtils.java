package com.hellochengkai.github.okhttptest;


import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.stream.Stream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class OkHttpUtils {

    private static final String TAG = "OkHttpUtils";
    private static OkHttpClient okHttpClient = new OkHttpClient();
    {
        okHttpClient.newBuilder().build();
    }


    interface HttpCallback<T>{
        void onResponse(T t);
        void onFailure(String msg);
    }


    private static void doOnFailure(HttpCallback callback,IOException e)
    {
        if(callback == null)
            return;
        callback.onFailure(e.getMessage());
    }

    private static void doOnResponse(HttpCallback<String> callback,String s)
    {
        if(callback == null)
            return;
        callback.onResponse(s);
    }


    private static void doOnResponse(HttpCallback<ResponseBody> callback,ResponseBody body)
    {
        if(callback == null)
            return;
        callback.onResponse(body);
    }


    public static void getStr(String url, final HttpCallback<String> callback)
    {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = okHttpClient.newBuilder().build().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: ");
                doOnFailure(callback,e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
//                Log.d(TAG, "onResponse: " + response.body().string());
                doOnResponse(callback,response.body().string());
            }
        });
    }


    public static void getResponseBody(String url, final HttpCallback<ResponseBody> callback)
    {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = okHttpClient.newBuilder().build().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: ");
                doOnFailure(callback,e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
//                Log.d(TAG, "onResponse: " + response.body().string());
                doOnResponse(callback,response.body());
            }
        });
    }
}
