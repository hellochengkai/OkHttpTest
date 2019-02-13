package com.hellochengkai.github.okhttptest;

import android.support.annotation.NonNull;
import android.util.Log;

import com.hellochengkai.github.okhttptest.test.BaseTest;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.ResponseBody;

public class DownLoadTest extends BaseTest {
    private static final String TAG = "DownLoadTest";
    @Override
    public void call() {
        OkHttpUtils.getResponseBody("http://192.168.10.176/Videos/7560958.ts", new OkHttpUtils.HttpCallback<ResponseBody>() {

            @Override
            public void onResponse(ResponseBody responseBody) {
                Log.d(TAG, "onResponse: save 7560958.ts " + FileUtil.newFile(new File("/sdcard/7560958.ts"), new InputStream() {
                    long readLens = 0;
                    long contentLength = responseBody.contentLength();
                    @Override
                    public int read() throws IOException {
                        return 0;
                    }

                    @Override
                    public int read(@NonNull byte[] b, int off, int len) throws IOException {
                        int readLen = responseBody.byteStream().read(b, off, len);
                        readLens += readLen;
                        Log.d(TAG, "read: download progress " + (readLens * 100) / contentLength + "%" + String.format("(%d/%d)", readLens,contentLength));
                        return readLen;
                    }
                }));
            }

            @Override
            public void onFailure(String msg) {
                Log.d(TAG, "onFailure: ");
            }
        });
    }


    void callEx()
    {
        OkHttpUtils.getStr("http://192.168.10.176/minicom.log", new OkHttpUtils.HttpCallback<String>() {
            @Override
            public void onResponse(String s) {
                Log.d(TAG, "onResponse: " + s);
            }

            @Override
            public void onFailure(String msg) {
                Log.d(TAG, "onFailure: ");
            }
        });
    }
}
