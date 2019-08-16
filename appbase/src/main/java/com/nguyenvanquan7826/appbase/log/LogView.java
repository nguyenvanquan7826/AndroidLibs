package com.nguyenvanquan7826.appbase.log;

public interface LogView {
    void hind();

    void show(String message);

    void show(int message);

    void setLogTextColor(int color);
}
