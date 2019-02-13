package com.hellochengkai.github.okhttptest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.hellochengkai.github.okhttptest.test.HttpUrlTest;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View v) {
//        new AsyncGet().call();
//        new DownLoadTest().call();

        new HttpUrlTest().call();
    }
}
