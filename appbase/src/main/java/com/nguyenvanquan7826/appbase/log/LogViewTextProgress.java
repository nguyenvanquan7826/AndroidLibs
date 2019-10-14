package com.nguyenvanquan7826.appbase.log;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class LogViewTextProgress extends LogTextView implements ILogTextProgress {

    private ProgressBar progressBar;

    public LogViewTextProgress(TextView tv, ProgressBar progressBar) {
        super(tv);
        this.progressBar = progressBar;
    }

    private void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hindProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void startLoadWithMessage(String message) {
        showProgress();
        show(message);
    }

    @Override
    public void startLoad() {
        showProgress();
        hind();
    }

    @Override
    public void finishLoadWithMessage(String message) {
        hindProgress();
        show(message);
    }

    @Override
    public void finishLoad() {
        hindProgress();
        hind();
    }
}
