package com.nguyenvanquan7826.appbase.log;

public interface LogTag {
    default String getLogTag() {
        return this.getClass().getSimpleName();
    }
}
