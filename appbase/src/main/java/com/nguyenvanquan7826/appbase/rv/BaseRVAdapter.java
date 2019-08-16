package com.nguyenvanquan7826.appbase.rv;


import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseRVAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    private OnItemClick onItemClick;
    private OnItemLongClick onItemLongClick;

    public <T extends BaseRVAdapter> T setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
        return (T) this;
    }

    public <T extends BaseRVAdapter> T setOnItemLongClick(OnItemLongClick onItemLongClick) {
        this.onItemLongClick = onItemLongClick;
        return (T) this;
    }

    protected void clickItem(int position, View view, String tag) {
        if (onItemClick != null) onItemClick.onItemClick(position, view, tag);
    }

    protected void longClickItem(int position, View view, String tag) {
        if (onItemLongClick != null) onItemLongClick.onItemLongClick(position, view, tag);
    }
}