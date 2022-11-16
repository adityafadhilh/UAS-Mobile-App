package id.ac.umn.uas_mobileapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class HariFragment extends Fragment {
    FloatingActionButton fab;
    RecyclerView recyclerView;
    MyAdapter myAdapter;
    ArrayList<Item> items = new ArrayList<Item>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hari, container, false);
        fab = view.findViewById(R.id.floatingActionButton);
        recyclerView = view.findViewById(R.id.recyclerview);
        myAdapter = new MyAdapter(getContext(), items);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(myAdapter);

        initData();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), AddTransaction.class);
                startActivity(i);
            }
        });
        return view;
    }

    public void initData(){
        items.add(new Item("Transportasi","-200.000",R.drawable.akomodasi));
        items.add(new Item("Tempat Tinggal","-1.000.000",R.drawable.rumah));
        items.add(new Item("Makanan","-200.000",R.drawable.makanan));
        items.add(new Item("Tagihan","-400.000",R.drawable.tagihan));
    }
}