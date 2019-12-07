package com.nguyenvanquan7826.appbase.fragment;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
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

        pagerAdapter = new PagerAdapter<>(getChildFragmentManager());
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
        Log.e(getLogTag(), "onResume");
        hindElevationAppBar();
        // new Handler().postDelayed(this::hindElevationAppBar,50);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e(getLogTag(), "onPause");
        showElevationAppBar();
    }

    protected void findView() {
        pager = findViewById(R.id.pager);
        tabLayout = findViewById(R.id.tab);
    }

    protected void setupPageAndTab() {
        pager.setAdapter(pagerAdapter);
        pager.setOffscreenPageLimit(pagerAdapter.getCount());
        tabLayout.setupWithViewPager(pager);
        tabLayout.setElevation(8);
    }

    protected AppBarLayout getAppBarLayout() {
        return ((BaseContainFragmentActivity) Objects.requireNonNull(getActivity())).getAppBarLayout();
    }

    protected void hindElevationAppBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getAppBarLayout().setElevation(0);
            getAppBarLayout().setStateListAnimator(null);
        }
    }

    protected void showElevationAppBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getAppBarLayout().setElevation(0);
            getAppBarLayout().setStateListAnimator(null);
        }
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

    public ViewPager getPager() {
        return pager;
    }

    public TabLayout getTabLayout() {
        return tabLayout;
    }

    public PagerAdapter<Fragment> getPagerAdapter() {
        return pagerAdapter;
    }
}
