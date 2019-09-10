package com.nguyenvanquan7826.appbase.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.nguyenvanquan7826.appbase.fragment.BaseFragment;
import com.nguyenvanquan7826.appbase.intent.SendObject;

public class AllFragmentInActivity extends BaseContainFragmentActivity {
    private static final String KEY_FRAGMENT = "fragment";

    public static void show(Context context, BaseFragment fragment) {
        Bundle arg = new SendObject().send(KEY_FRAGMENT, fragment);

        Intent i = new Intent(context, AllFragmentInActivity.class);
        i.putExtras(arg);
        context.startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arg = getIntent().getExtras();

        BaseFragment fragment = (BaseFragment) new SendObject().receive(arg, KEY_FRAGMENT);
        showFragment(fragment, false);
    }
}
