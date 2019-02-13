package com.hellochengkai.github.okhttptest.test;

import android.util.Log;

import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.HttpUrl;

public class HttpUrlTest extends BaseTest {

    private static final String TAG = "HttpUrlTest";
    @Override
    public void call() {
        test1();
        test2();
    }

    private void test1(){
        HttpUrl url = new HttpUrl.Builder()
                .scheme("https")
                .host("tools.ietf.org")
                .addPathSegment("html")
                .addPathSegment("rfc3986")
                .port(999)
//                .addEncodedQueryParameter("name","chengkai")
//                .addEncodedQueryParameter("tel","123456789")
//                .addQueryParameter("nameEx","成凯")
//                .addEncodedQueryParameter("nameEx","成凯")
                .fragment("page+1")
                .encodedFragment()
                .build();
        Log.d(TAG, "call: " +  url.toString());


        HttpUrl httpUrl = HttpUrl.parse(" https://tools.ietf.org:999/html/rfc3986#page+2");
        Log.d(TAG, "call2: " +  httpUrl.encodedFragment());

        //https://tools.ietf.org/html/rfc3986#page-4
        //https://tools.ietf.org:999/html/rfc3986?name=chengkai&tel=123456789#sasfdasdf
    }

    private void test2(){
        URL url = null;
        try {
            url = new URL("http","192.168.10.176","Videos/7560958.ts");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
//http://192.168.10.176/Videos/7560958.ts
        Log.d(TAG, "call: " + url);
    }
}
