package com.example.hoc10.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hoc10.R;
import com.example.hoc10.model.Notice;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewAdapter1 extends RecyclerView.Adapter<RecycleViewAdapter1.NoticeViewHolder>{
    private List<Notice> list;
    private NoticeListener noticeListener;

    public RecycleViewAdapter1() {
        list= new ArrayList<>();
    }

    public void setNoticeListener(NoticeListener noticeListener) {
        this.noticeListener = noticeListener;
    }

    public void setList(List<Notice> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    public Notice getNotice(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public NoticeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item1,parent,false);
        return new RecycleViewAdapter1.NoticeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeViewHolder holder, int position) {
        Notice notice = list.get(position);
        holder.title.setText(notice.getTitle());
        holder.category.setText(notice.getCategory());
        holder.price.setText(notice.getPrice());
        holder.date.setText(notice.getDate());
        holder.status.setText(notice.getStatus());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class NoticeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView title,category,price,date,status;
        public NoticeViewHolder(@NonNull View view) {
            super(view);
            title=view.findViewById(R.id.tvTitle);
            category=view.findViewById(R.id.tvCategory);
            price=view.findViewById(R.id.tvPrice);
            date=view.findViewById(R.id.tvDate);
            status=view.findViewById(R.id.tvStatus);
            view.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            if(noticeListener!=null) {
                noticeListener.onNoticeClick(view,getAdapterPosition());
            }
        }
    }

    public interface NoticeListener{
        void onNoticeClick(View view,int position);
    }
}
