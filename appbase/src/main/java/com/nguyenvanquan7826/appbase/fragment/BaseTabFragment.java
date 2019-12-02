package com.nguyenvanquan7826.appbase.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.nguyenvanquan7826.appbase.PagerAdapter;
import com.nguyenvanquan7826.appbase.R;
import com.nguyenvanquan7826.appbase.activity.BaseContainFragmentActivity;

import java.util.Objects;

public class BaseTabFragment extends InAcFragment {

    private PagerAdapter<Fragment> pagerAdapter;
    private ViewPager pager;
    private TabLayout tabLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.base_tab_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findView();
        setupPageAndTab();
    }

    @Override
    public void onResume() {
        super.onResume();
        hindElevationAppBar();
    }

    @Override
    public void onPause() {
        super.onPause();
        showElevationAppBar();
    }

    protected void findView() {
        pager = findViewById(R.id.pager);
        tabLayout = findViewById(R.id.tab);
    }

    protected void setupPageAndTab() {
        pagerAdapter = new PagerAdapter<>(getChildFragmentManager());
        pager.setAdapter(pagerAdapter);
        pager.setOffscreenPageLimit(pagerAdapter.getCount());
        tabLayout.setupWithViewPager(pager);
        tabLayout.setElevation(8);
    }

    protected AppBarLayout getAppBarLayout() {
        return ((BaseContainFragmentActivity) Objects.requireNonNull(getActivity())).getAppBarLayout();
    }

    protected void hindElevationAppBar() {
        getAppBarLayout().setElevation(0);
    }

    protected void showElevationAppBar() {
        getAppBarLayout().setElevation(8);
    }

    protected void addTab(Fragment fragment, String title) {
        pagerAdapter.add(fragment, title);
    }

    protected void build() {
        pagerAdapter.notifyDataSetChanged();
    }

    public ViewPager getPager() {
        return pager;
    }

    public TabLayout getTabLayout() {
        return tabLayout;
    }
}
