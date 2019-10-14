package com.nguyenvanquan7826.appbase.log;

public interface ILogTextProgress extends LogView {
    void startLoadWithMessage(String message);

    void startLoad();

    void finishLoadWithMessage(String message);

    void finishLoad();
}
