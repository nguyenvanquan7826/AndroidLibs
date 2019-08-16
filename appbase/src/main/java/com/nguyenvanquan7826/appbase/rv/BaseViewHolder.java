package com.nguyenvanquan7826.appbase.rv;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nguyenvanquan7826.appbase.log.LogTag;

public abstract class BaseViewHolder<T, VH extends BaseViewHolder> extends RecyclerView.ViewHolder implements LogTag {
    protected Context context;

    public BaseViewHolder(View itemView) {
        super(itemView);
        this.context = itemView.getContext();
    }

    public abstract VH createVH(@NonNull ViewGroup parent);

    public void bindView(T t, int position) {
        itemView.setOnClickListener(v -> clickItem(position, v, null));
        itemView.setOnLongClickListener(v -> {
            longClickItem(position, v, null);
            return true;
        });
    }

    public <V extends View> V findViewById(int id) {
        return itemView.findViewById(id);
    }

    private OnItemClick onItemClick;
    private OnItemLongClick onItemLongClick;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public void setOnItemLongClick(OnItemLongClick onItemLongClick) {
        this.onItemLongClick = onItemLongClick;
    }

    protected void clickItem(int position, View view, String tag) {
        if (onItemClick != null) {
            onItemClick.onItemClick(position, view, tag);
        } else {
            Log.e(getLogTag(), "onItemClick null");
        }
    }

    protected void longClickItem(int position, View view, String tag) {
        if (onItemLongClick != null) {
            onItemLongClick.onItemLongClick(position, view, tag);
        } else {
            Log.e(getLogTag(), "onItemLongClick null");
        }
    }
}