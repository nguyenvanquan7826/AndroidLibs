package com.nguyenvanquan7826.appbase.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.nguyenvanquan7826.appbase.util.ViewUtil;

@SuppressLint("Registered")
public class BaseContainFragmentActivity extends BaseActivity implements ActivityWithFragment {

    /**
     * override this method to set your custom view
     */
    protected void setCustomView() {
        setContentView(com.nguyenvanquan7826.appbase.R.layout.activity_all_fragment);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView();

        Toolbar toolbar = findViewById(com.nguyenvanquan7826.appbase.R.id.toolbar);
        ViewUtil.setUpToolbar(this, toolbar);
    }

    public void showFragment(Fragment fragment, boolean isAddStack) {
        ViewUtil.addFragment(getSupportFragmentManager(), com.nguyenvanquan7826.appbase.R.id.layoutContent, fragment, isAddStack);
    }
}
