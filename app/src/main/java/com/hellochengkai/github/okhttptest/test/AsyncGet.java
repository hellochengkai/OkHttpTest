package com.hellochengkai.github.okhttptest.test;

import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AsyncGet extends BaseTest{

    private static final String TAG = "AsyncGet";

    @Override
    public void call() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = null;

        Log.d(TAG, "call: " +  HTTP_URL_BAIDU.toString());

        request = new Request.Builder()
                .url(HTTP_URL_BAIDU)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: ");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG, "onResponse: " + response.body().string());
            }
        });
    }
}
