package com.nguyenvanquan7826.appbase.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.appbar.AppBarLayout;
import com.nguyenvanquan7826.appbase.R;
import com.nguyenvanquan7826.appbase.util.ViewUtil;

@SuppressLint("Registered")
public class BaseContainFragmentActivity extends BaseActivity implements ActivityWithFragment {


    private Toolbar toolbar;
    private AppBarLayout appBarLayout;

    /**
     * override this method to set your custom view
     */
    protected void setCustomView() {
        setContentView(R.layout.activity_all_fragment);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView();

        toolbar = findViewById(R.id.toolbar);
        appBarLayout = findViewById(R.id.appBarLayout);
        ViewUtil.setUpToolbar(this, toolbar);
    }

    public void showFragment(Fragment fragment, boolean isAddStack) {
        ViewUtil.addFragment(getSupportFragmentManager(), R.id.layoutContent, fragment, isAddStack);
    }

    public AppBarLayout getAppBarLayout() {
        return appBarLayout;
    }

    public Toolbar getToolbar() {
        return toolbar;
    }
}
