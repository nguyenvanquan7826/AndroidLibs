package com.kimcy929.textviewtwoline;

import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;

import androidx.core.content.ContextCompat;


public class TextViewTwoLineProgress {
    private View root;
    private TextViewTwoLine tv;
    private ProgressBar progressBar;

    private String currentDescription = "";
    private int currentDescriptionColor = 0;
    private final int TIME_DELAY_SHOW_MESSAGE = 3 * 1000;

    public TextViewTwoLineProgress(View root, int idTv, int idProgress) {
        this.root = root;
        this.tv = root.findViewById(idTv);
        this.progressBar = root.findViewById(idProgress);
        this.progressBar.setVisibility(View.GONE);
    }

    public View getRoot() {
        return root;
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public TextViewTwoLine getTv() {
        return tv;
    }

    public void setTitle(String title) {
        tv.setTextTitle(title);
    }

    public void setDescription(String description) {
        tv.setTextDescription(description);
        currentDescription = description;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        tv.setOnClickListener(onClickListener);
    }

    public void onLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void onLoaded() {
        onLoaded(null);
    }

    public void onLoaded(String message) {
        progressBar.setVisibility(View.GONE);

        if (!TextUtils.isEmpty(message)) {
            currentDescriptionColor = tv.getDescriptionColor();
            if (!TextUtils.isEmpty(tv.getTextDescription())) {
                currentDescription = tv.getTextDescription().toString();
            }

            tv.setDescriptionColor(ContextCompat.getColor(root.getContext(), R.color.colorError));
            tv.setTextDescription(message);

            new Handler().postDelayed(() -> {
                if (tv != null) {
                    tv.setTextDescription(currentDescription);
                    tv.setDescriptionColor(currentDescriptionColor);
                }
            }, TIME_DELAY_SHOW_MESSAGE);
        }
    }
}
