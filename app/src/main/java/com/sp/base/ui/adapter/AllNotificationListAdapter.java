package com.sp.base.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.paging.ContiguousPagedList;
import androidx.paging.PagedList;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.sp.base.R;
import com.sp.base.ui.model.DummyResponse;
import com.sp.base.ui.model.ListData;

import java.util.ArrayList;

public class AllNotificationListAdapter extends PagedListAdapter<ListData, AllNotificationListAdapter.ViewHolder> {
    private Context mContext;
    private OnClickListener mOnClickListener;
    PagedList<ListData> listDataPagedList;


    public AllNotificationListAdapter(Context context) {
        super(DIFF_CALLBACk);
        this.mContext = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_list, null);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ListData notificationData = getItem(position);// mArrayList.get(position);
//        Glide.with(mContext)
//                .load(notificationData.getIcon())// or URI/path
//                .into(holder.imgIcon);
        if (notificationData != null) holder.txtessage.setText(notificationData.getName());
//        holder.txtTime.setText(notificationData.getNotification_date() + " " + notificationData.getNotification_time());

//        holder.layRow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mOnClickListener.onClick(notificationData);
//            }
//        });
    }

    private static DiffUtil.ItemCallback<ListData> DIFF_CALLBACk =
            new DiffUtil.ItemCallback<ListData>() {
                @Override
                public boolean areItemsTheSame(@NonNull ListData oldItem, @NonNull ListData newItem) {
                    return oldItem.getName() == newItem.getName();
                }

                @Override
                public boolean areContentsTheSame(@NonNull ListData oldItem, @NonNull ListData newItem) {
                    return oldItem.equals(newItem);
                }
            };

    public void submitList(ArrayList<ListData> results) {
        for (ListData listData : results) {
            listDataPagedList.add(listData);
        }
        submitList(listDataPagedList);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        AppCompatImageView imgIcon;
        AppCompatTextView txtessage;
        AppCompatTextView txtTime;
        RelativeLayout layRow;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
//            imgIcon = itemView.findViewById(R.id.imgIcon);
            txtessage = itemView.findViewById(R.id.txtMessage);
//            layRow = itemView.findViewById(R.id.layRow);
//            txtTime = itemView.findViewById(R.id.txtTime);
        }
    }

    public void setOnclicklistener(OnClickListener onclicklistener) {
        this.mOnClickListener = onclicklistener;
    }

    public interface OnClickListener {
        void onClick(AllNotificationData notificationData);

    }


}
