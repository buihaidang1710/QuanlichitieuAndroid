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

import com.example.hoc10.R;
import com.example.hoc10.UpdateDeleteActivity;
import com.example.hoc10.AddNoticeActivity;
import com.example.hoc10.adapter.RecycleViewAdapter;
import com.example.hoc10.adapter.RecycleViewAdapter1;
import com.example.hoc10.data.SQLiteHelp;
import com.example.hoc10.data.SQLiteHelper;
import com.example.hoc10.model.Item;
import com.example.hoc10.model.Notice;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FragmentHome extends Fragment implements RecycleViewAdapter.ItemListener,RecycleViewAdapter1.NoticeListener {
    private RecyclerView recyclerView;
    private RecyclerView recyclerView1;
    private RecycleViewAdapter adapter;
    private RecycleViewAdapter1 adapter1;
    private SQLiteHelper db;
    private SQLiteHelp db1;
    private TextView tvTong,tvTong1,tvtTong,tvtTong1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.recycleView);
        recyclerView1=view.findViewById(R.id.recycleView1);
        tvTong=view.findViewById(R.id.tvTong);
        tvTong1=view.findViewById(R.id.tvTong1);
        tvtTong=view.findViewById(R.id.tvtTong);
        tvtTong1=view.findViewById(R.id.tvtTong1);
        adapter=new RecycleViewAdapter();
        adapter1=new RecycleViewAdapter1();
        db=new SQLiteHelper(getContext());
        db1=new SQLiteHelp(getContext());
        Date d= new Date();
        SimpleDateFormat f= new SimpleDateFormat("dd/MM/yyyy");
        List<Item> list= db.getByDate(f.format(d));
        List<Notice> list1=db1.getByDate(f.format(d));
        adapter.setList(list);
        adapter1.setList(list1);
        tvTong.setText("So chi tieu: "+list.size());
        tvTong1.setText("Tong tien chi tieu: "+tong(list));
        tvtTong.setText("So no: "+list1.size());
        tvtTong1.setText("Tong no: "+tong1(list1));
        LinearLayoutManager manager= new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        LinearLayoutManager manager1= new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView1.setLayoutManager(manager1);
        recyclerView.setAdapter(adapter);
        recyclerView1.setAdapter(adapter1);
        adapter.setItemListener(this);
        adapter1.setNoticeListener(this);
    }

    private int tong(List<Item> list) {
        int t=0;
        for (Item i:list) {
            t+= Integer.parseInt(i.getPrice());
        }
        return t ;
    }
    private int tong1(List<Notice> list) {
        int t=0 ;
        for (Notice n:list) {
            t+=Integer.parseInt(n.getPrice());
        }
        return t ;
    }
    @Override
    public void onItemClick(View view, int position) {
        Item item = adapter.getItem(position);
        Intent intent= new Intent(getActivity(), UpdateDeleteActivity.class);
        intent.putExtra("item",item);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        Date d= new Date();
        SimpleDateFormat f= new SimpleDateFormat("dd/MM/yyyy");
        List<Item> list= db.getByDate(f.format(d));
        List<Notice> list1=db1.getByDate(f.format(d));
        adapter.setList(list);
        adapter1.setList(list1);
        tvTong.setText("So chi tieu: "+list.size());
        tvTong1.setText("Tong tien chi tieu: "+tong(list));
        tvtTong.setText("So no: "+list1.size());
        tvtTong1.setText("Tong no: "+tong1(list1));
    }

    @Override
    public void onNoticeClick(View view, int position) {
        Notice notice=adapter1.getNotice(position);
        Intent intent=new Intent(getActivity(), AddNoticeActivity.class);
        intent.putExtra("notice",notice);
        startActivity(intent);
    }
}
