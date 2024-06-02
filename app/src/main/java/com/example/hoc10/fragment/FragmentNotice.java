package com.example.hoc10.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hoc10.DeleteActivity;
import com.example.hoc10.R;
import com.example.hoc10.adapter.RecycleViewAdapter1;
import com.example.hoc10.data.SQLiteHelp;
import com.example.hoc10.model.Notice;

import java.util.List;

public class FragmentNotice extends Fragment implements RecycleViewAdapter1.NoticeListener{
    private RecycleViewAdapter1 adapter;
    private RecyclerView recyclerView;
    private SQLiteHelp db;
    private TextView eTong,eTong1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notice,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        eTong=view.findViewById(R.id.tvTong);
        eTong1=view.findViewById(R.id.tvTong1);
        recyclerView=view.findViewById(R.id.recycleView);
        adapter = new RecycleViewAdapter1();
        db= new SQLiteHelp(getContext());
        List<Notice> list=db.getAll1();
        adapter.setList(list);
        LinearLayoutManager manager= new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setNoticeListener(this);
        eTong.setText("Tong so thong bao: "+list.size());
        eTong1.setText("Tong so thong bao: "+tong(list));

    }
    private int tong(List<Notice> list) {
        int t=0;
        for (Notice i:list) {
            t+= Integer.parseInt(i.getPrice());
        }
        return t ;
    }
    @Override
    public void onNoticeClick(View view, int position) {
        Notice notice=adapter.getNotice(position);
        Intent intent1=new Intent(getActivity(), DeleteActivity.class);
        intent1.putExtra("notice1",notice);
        startActivity(intent1);
    }

    @Override
    public void onResume() {
        super.onResume();
        List<Notice> list=db.getAll1();
        adapter.setList(list);
        eTong.setText("Tong so thong bao:"+list.size());
        eTong1.setText("Tong so tien no:"+tong(list));
    }

}
