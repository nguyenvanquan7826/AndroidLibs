package com.nguyenvanquan7826.appbase.rv;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nguyenvanquan7826.appbase.log.LogTag;

import java.util.List;

/**
 * Created by admin on 5/7/18
 */

public abstract class VHBaseAdapter<T, VH extends BaseViewHolder> extends RecyclerView.Adapter<VH> implements LogTag {

    protected List<T> list;
    protected Context context;

    public VHBaseAdapter(Context context, List<T> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        //noinspection unchecked
        holder.bindView(list.get(position), position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    /**
     * handler click
     */

    protected OnItemClick onItemClick;
    protected OnItemLongClick onItemLongClick;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public void setOnItemLongClick(OnItemLongClick onItemLongClick) {
        this.onItemLongClick = onItemLongClick;
    }
}