package com.nguyenvanquan7826.appbase;

import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class PagerAdapter<T extends Fragment> extends FragmentStatePagerAdapter {
    private List<T> fragments;
    private List<String> titles;
    private List<Integer> icons;

    public PagerAdapter(FragmentManager fm) {
        super(fm);
        init();
    }

    private void init() {
        titles = new ArrayList<>();
        fragments = new ArrayList<>();
        icons = new ArrayList<>();
    }

    public void add(T fragment, String title) {
        fragments.add(fragment);
        titles.add(title);
    }

    public void add(T fragment, String title, int icon) {
        fragments.add(fragment);
        titles.add(title);
        icons.add(icon);
    }

    public void clear() {
        fragments.clear();
        titles.clear();
    }

    public String getTitle(int position) {
        return titles.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

    @Override
    public T getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    public int getIcon(int pos) {
        return icons.get(pos);
    }

    @Override
    public void restoreState(Parcelable arg0, ClassLoader arg1) {
        //do nothing here! no call to super.restoreState(arg0, arg1);
    }
}