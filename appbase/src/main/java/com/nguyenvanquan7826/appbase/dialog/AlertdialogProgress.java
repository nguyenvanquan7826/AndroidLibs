package com.nguyenvanquan7826.appbase.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.nguyenvanquan7826.appbase.R;

public class AlertdialogProgress extends AlertDialog.Builder {
    private TextView tv;

    public AlertdialogProgress(Context context) {
        super(context);
        View v = LayoutInflater.from(context).inflate(R.layout.dialog_progress, null);
        tv = v.findViewById(R.id.tv);
        setView(v);
    }

    public AlertdialogProgress(Context context, String message) {
        this(context);
        tv.setText(message);
    }

    public AlertdialogProgress(Context context, int message) {
        this(context);
        tv.setText(message);
    }
}
