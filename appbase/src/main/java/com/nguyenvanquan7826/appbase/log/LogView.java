package com.nguyenvanquan7826.appbase.log;

public interface LogView {
    int LOG_LEVEL_NOMAL = 0;
    int LOG_LEVEL_WARNING = 1;
    int LOG_LEVEL_ERROR = 2;

    void hind();

    void show(int message);

    void show(String message);

    void show(int message, int logLevel);

    void show(String message, int logLevel);

    void setLogTextColor(int color);
}
