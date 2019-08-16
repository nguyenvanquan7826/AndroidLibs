package com.nguyenvanquan7826.appbase.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.nguyenvanquan7826.appbase.R;
import com.nguyenvanquan7826.appbase.util.ViewUtil;

public class FMDialog extends DialogFragment {
    private Fragment fragment;
    private String title;

    public FMDialog(Fragment fragment) {
        this.fragment = fragment;
    }

    public FMDialog setTitle(String title) {
        this.title = title;
        return this;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogFragmentStyle);
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setTitle(title);
        return dialog;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ViewUtil.addFragment(getChildFragmentManager(), R.id.layoutContent, fragment, false);
    }
}