package com.nguyenvanquan7826.appbase.log;

import android.view.View;

public interface LogView {
    int LOG_LEVEL_NONE = 0;
    int LOG_LEVEL_NOMAL = 1;
    int LOG_LEVEL_WARNING = 2;
    int LOG_LEVEL_ERROR = 3;

    void hind();

    void show(int message);

    void show(String message);

    void show(int message, int logLevel);

    void show(String message, int logLevel);

    void setLogTextColor(int color);

    void setOnClick(View.OnClickListener onClickListener);
}
