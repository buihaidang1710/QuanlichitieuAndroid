package com.example.hoc10.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hoc10.AccountActivity;
import com.example.hoc10.MainActivity1;
import com.example.hoc10.R;
import com.example.hoc10.adapter.RecycleViewAdapter2;
import com.example.hoc10.data.SQLiteHelpest;
import com.example.hoc10.model.Account;

import java.util.List;

public class FragmentAccount extends Fragment implements RecycleViewAdapter2.AccountListener {
    private RecycleViewAdapter2 adapter2;
    private RecyclerView recyclerView;
    private SQLiteHelpest db;
    private TextView eTong;
    private Button btOutlogin;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_account,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.recycleView);
        eTong=view.findViewById(R.id.tvTongTK);
        btOutlogin=view.findViewById(R.id.tvOut);
        adapter2= new RecycleViewAdapter2();
        db=new SQLiteHelpest(getContext());
        List<Account> list=db.getAllAccounts();
        adapter2.setList(list);
        LinearLayoutManager manager= new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter2);
        adapter2.setNoticeListener(this);
        eTong.setText("Tai khoan hien co: "+list.size());
        btOutlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view==btOutlogin) {
                    Toast.makeText(getContext(), "Logout Succesful", Toast.LENGTH_SHORT).show();
                    Account account= new Account();
                    Intent intent1=new Intent(getActivity(), MainActivity1.class);
                    intent1.putExtra("account2",account);
                    startActivity(intent1);
                }
            }
        });
    }

    @Override
    public void onAccountClick(View view, int position) {
        Account account=adapter2.getAccount(position);
        Intent intent1=new Intent(getActivity(), AccountActivity.class);
        intent1.putExtra("account1",account);
        startActivity(intent1);
    }
    public void onResume() {
        super.onResume();
        List<Account> list = db.getAllAccounts();
        adapter2.setList(list);
        eTong.setText("Tai khoan hien co:" + list.size());
    }
}
