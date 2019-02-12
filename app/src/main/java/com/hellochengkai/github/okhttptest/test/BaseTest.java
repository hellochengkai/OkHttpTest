package com.hellochengkai.github.okhttptest.test;

import okhttp3.MediaType;

public abstract class BaseTest {
    static final String HTTP_WWW_BAIDU_COM = "http://www.baidu.com";
    static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");

    abstract void call();
}
