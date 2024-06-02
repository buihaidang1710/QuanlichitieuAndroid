package com.example.hoc10.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hoc10.R;
import com.example.hoc10.model.Account;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewAdapter2 extends RecyclerView.Adapter<RecycleViewAdapter2.AccountViewHolder>{
    private List<Account> list;
    private AccountListener accountListener;

    public RecycleViewAdapter2() {
        list= new ArrayList<>();
    }

    public void setNoticeListener(AccountListener accountListener) {
        this.accountListener = accountListener;
    }

    public void setList(List<Account> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    public Account getAccount(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public AccountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item2,parent,false);
        return new RecycleViewAdapter2.AccountViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountViewHolder holder, int position) {
        Account account = list.get(position);
        holder.username.setText(account.getUsername());
        holder.pass.setText(account.getPassword());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class AccountViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView username,pass;
        public AccountViewHolder(@NonNull View view) {
            super(view);
            username=view.findViewById(R.id.tvUsername);
            pass=view.findViewById(R.id.tvPassword);
            view.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            if(accountListener!=null) {
                accountListener.onAccountClick(view,getAdapterPosition());
            }
        }
    }

    public interface AccountListener{
        void onAccountClick(View view,int position);
    }
}
