package com.hellochengkai.github.okhttptest.test;

import okhttp3.HttpUrl;
import okhttp3.MediaType;

public abstract class BaseTest {
    static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");
    static final HttpUrl HTTP_URL_BAIDU = new HttpUrl.Builder()
            .scheme("http")
            .host("www.baidu.com")
            .build();

    public abstract void call();
}
