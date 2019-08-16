package com.nguyenvanquan7826.appbase.log;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

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

    @Override
    public void hind() {
        tv.setVisibility(View.GONE);
    }

    @Override
    public void show(String message) {
        tv.setVisibility(View.VISIBLE);
        tv.setText(message);
    }

    @Override
    public void show(int message) {
        show(context.getString(message));
    }

    @Override
    public void setLogTextColor(int color) {
        tv.setTextColor(color);
    }
}
