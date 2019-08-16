package com.nguyenvanquan7826.appbase.fragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.nguyenvanquan7826.appbase.activity.ActivityWithFragment;

public class InAcFragment extends BaseFragment {

    protected void showFragment(Fragment fragment, boolean isAddStack) {
        FragmentActivity activity = getActivity();
        if (activity instanceof ActivityWithFragment) {
            ((ActivityWithFragment) activity).showFragment(fragment, isAddStack);
        } else {
            throw new RuntimeException("Activity must be " + ActivityWithFragment.class.getSimpleName());
        }
    }
}
