package com.nguyenvanquan7826.appbase.activity;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.nguyenvanquan7826.appbase.log.LogTag;

public class BaseActivity extends AppCompatActivity implements LogTag {

    protected Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
    }
}
