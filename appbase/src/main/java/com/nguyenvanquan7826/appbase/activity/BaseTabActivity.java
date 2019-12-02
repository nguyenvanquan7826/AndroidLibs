package com.nguyenvanquan7826.appbase.activity;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.nguyenvanquan7826.appbase.PagerAdapter;
import com.nguyenvanquan7826.appbase.R;
import com.nguyenvanquan7826.appbase.util.ViewUtil;

public class BaseTabActivity extends BaseActivity {

    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_tab_fragment);

        Toolbar toolbar = findViewById(R.id.toolbar);
        ViewUtil.setUpToolbar(this, toolbar);

        final ViewPager pager = findViewById(R.id.pager);
        TabLayout tabLayout = findViewById(R.id.tab);

        pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);
        pager.setOffscreenPageLimit(pagerAdapter.getCount());
        tabLayout.setupWithViewPager(pager);
    }

    protected void addTab(Fragment fragment, String title) {
        pagerAdapter.add(fragment, title);
    }

    @Deprecated
    protected void build() {
        pagerAdapter.notifyDataSetChanged();
    }

    protected void buildPage() {
        pagerAdapter.notifyDataSetChanged();
    }

}
