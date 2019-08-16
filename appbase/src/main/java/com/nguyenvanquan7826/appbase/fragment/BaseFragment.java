package com.nguyenvanquan7826.appbase.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.nguyenvanquan7826.appbase.log.LogTag;

public class BaseFragment extends Fragment implements FragmentListener, LogTag {
    protected static final String TAG = BaseFragment.class.getSimpleName();

    protected FragmentCallBack fragmentCallBack;
    protected Context context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = getContext();
    }

    public BaseFragment setFragmentCallBack(FragmentCallBack fragmentCallBack) {
        this.fragmentCallBack = fragmentCallBack;
        return this;
    }

    public void onBackPressed() {
        if (getActivity() != null) {
            getActivity().onBackPressed();
        } else {
            Log.e(getLogTag(), "activity null");
        }
    }

    protected void finish() {
        if (getActivity() != null) {
            getActivity().finish();
        } else {
            Log.e(getLogTag(), "activity null");
        }
    }

    protected void setTitle(String title) {
        if (getActivity() != null) {
            getActivity().setTitle(title);
        } else {
            Log.e(getLogTag(), "setTitle Activity was null");
        }
    }

    protected void setTitle(int id) {
        setTitle(getString(id));
    }

    protected void setSubTitle(int id) {
        setSubTitle(getString(id));
    }

    protected void setSubTitle(String subTitle) {
        if (getActivity() != null && getActivity() instanceof AppCompatActivity) {
            ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
            if (actionBar != null) {
                actionBar.setSubtitle(subTitle);
            } else {
                Log.e(getLogTag(), "setSubTitle actionbar was null");
            }
        } else {
            Log.e(getLogTag(), "setSubTitle Activity was null");
        }
    }

    protected <T extends View> T findViewById(int id) {
        if (getView() != null) {
            return getView().findViewById(id);
        }
        Log.e(getLogTag(), "root view is null");
        return null;
    }

    @Override
    public void onActionFromContainer(String tag, Object... data) {
        if (!isAdded()) {
            return;
        }
    }

    public void onFragmentReturnData(String tag, Object... data) {
        if (fragmentCallBack != null) {
            fragmentCallBack.onFragmentDataSent(tag, data);
        } else {
            Log.e(getLogTag(), "fragmentCallBack null");
        }
    }
}
