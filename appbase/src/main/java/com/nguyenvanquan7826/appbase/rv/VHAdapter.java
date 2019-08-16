package com.nguyenvanquan7826.appbase.rv;

import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import java.util.List;

/**
 * Created by admin on 5/7/18
 */

public class VHAdapter<T, VH extends BaseViewHolder> extends VHBaseAdapter<T, VH> {

    private VH defaultVH;

    public VHAdapter(Context context, List<T> list, @NonNull VH defaultVH) {
        super(context, list);
        this.defaultVH = defaultVH;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.e(getLogTag(), "onCreateViewHolder");
        VH vh1 = (VH) defaultVH.createVH(parent);
        vh1.setOnItemLongClick(onItemLongClick);
        vh1.setOnItemClick(onItemClick);
        return vh1;
    }
}