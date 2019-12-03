package com.nguyenvanquan7826.appbase.log;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.nguyenvanquan7826.appbase.R;

public class LogTextView implements LogView {

    private TextView tv;
    private Context context;

    public LogTextView(View rootView, int tvLogId) {
        this.context = rootView.getContext();
        tv = rootView.findViewById(tvLogId);
    }

    public LogTextView(TextView tv) {
        this.context = tv.getContext();
        this.tv = tv;
    }

    private int getTextColor(int logLevel) {
        switch (logLevel) {
            case LOG_LEVEL_WARNING:
                return R.color.colorTextWarning;
            case LOG_LEVEL_ERROR:
                return R.color.colorTextError;
            default:
                return R.color.colorTextNomal;
        }
    }

    private int getBgColor(int logLevel) {
        switch (logLevel) {
            case LOG_LEVEL_WARNING:
                return R.color.colorBgWarning;
            case LOG_LEVEL_ERROR:
                return R.color.colorBgError;
            default:
                return R.color.colorBgNomal;
        }
    }

    @Override
    public void hind() {
        tv.setVisibility(View.GONE);
    }


    @Override
    public void show(int message) {
        show(context.getString(message), LOG_LEVEL_NONE);
    }

    @Override
    public void show(String message) {
        show(message, LOG_LEVEL_NONE);
    }

    @Override
    public void show(int message, int logLevel) {
        show(context.getString(message), logLevel);
    }

    @Override
    public void show(String message, int logLevel) {
        tv.setVisibility(View.VISIBLE);
        tv.setText(message);

        if (logLevel != LOG_LEVEL_NONE) {
            tv.setBackgroundColor(ContextCompat.getColor(context, getBgColor(logLevel)));
            tv.setTextColor(ContextCompat.getColor(context, getTextColor(logLevel)));
        }
    }

    @Override
    public void setLogTextColor(int color) {
        tv.setTextColor(color);
    }
}
