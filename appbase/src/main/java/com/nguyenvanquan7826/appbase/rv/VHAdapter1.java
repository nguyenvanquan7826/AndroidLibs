//package com.nguyenvanquan7826.appbase.rv;
//
//import android.content.Context;
//import android.util.Log;
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.nguyenvanquan7826.appbase.log.LogTag;
//import com.nguyenvanquan7826.appbase.rv.OnItemClick;
//import com.nguyenvanquan7826.appbase.rv.OnItemLongClick;
//
//import java.util.List;
//
///**
// * Created by admin on 5/7/18
// */
//
//public class VHAdapter1<T, VH extends BaseViewHolder> extends RecyclerView.Adapter<VH> implements LogTag {
//
//    protected List<T> list;
//    protected Context context;
//    private VH defaultVH;
//
//    public VHAdapter1(Context context, List<T> list, VH defaultVH) {
//        this.context = context;
//        this.list = list;
//        this.defaultVH = defaultVH;
//    }
//
//    @SuppressWarnings("unchecked")
//    @NonNull
//    @Override
//    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        Log.e(getLogTag(), "onCreateViewHolder");
//        VH vh1 = (VH) defaultVH.createVH(parent);
//        vh1.setOnItemLongClick(onItemLongClick);
//        vh1.setOnItemClick(onItemClick);
//        return vh1;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull VH holder, int position) {
//        //noinspection unchecked
//        holder.bindView(list.get(position), position);
//    }
//
//    @Override
//    public int getItemCount() {
//        return list.size();
//    }
//
//    /**
//     * handler click
//     */
//
//    private OnItemClick onItemClick;
//    private OnItemLongClick onItemLongClick;
//
//    public void setOnItemClick(OnItemClick onItemClick) {
//        this.onItemClick = onItemClick;
//    }
//
//    public void setOnItemLongClick(OnItemLongClick onItemLongClick) {
//        this.onItemLongClick = onItemLongClick;
//    }
//}