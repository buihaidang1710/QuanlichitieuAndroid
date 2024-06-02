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
import com.example.hoc10.adapter.RecycleViewAdapter;
import com.example.hoc10.data.SQLiteHelper;
import com.example.hoc10.model.Item;

import java.util.List;

public class FragmentHistory extends Fragment implements RecycleViewAdapter.ItemListener {
    private RecycleViewAdapter adapter;
    private RecyclerView recyclerView;
    private SQLiteHelper db;
    private TextView eTong,eTong1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_history,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.recycleView);
        eTong=view.findViewById(R.id.tvTongDC);
        eTong1=view.findViewById(R.id.tvTongDC1);
        adapter= new RecycleViewAdapter();
        db= new SQLiteHelper(getContext());
        List<Item> list=db.getAll();
        adapter.setList(list);
        eTong.setText("Tong tien chi tieu:"+tong(list));
        eTong1.setText("Tong so chi tieu:"+list.size());
        LinearLayoutManager manager= new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setItemListener(this);

    }
    private int tong(List<Item> list) {
        int t=0;
        for (Item i:list) {
            t+= Integer.parseInt(i.getPrice());
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
        List<Item> list=db.getAll();
        adapter.setList(list);
        eTong.setText("Tong tien chi tieu:"+tong(list));
        eTong1.setText("Tong so chi tieu: "+list.size());
    }
}
