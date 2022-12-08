package id.ac.umn.uas_mobileapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class HariFragment extends Fragment {
    FloatingActionButton fab;
    RecyclerView recyclerView;
    MyAdapter myAdapter;
    ArrayList<TransaksiData> items;
    String username;
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users");
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hari, container, false);
        fab = view.findViewById(R.id.floatingActionButton);
        recyclerView = view.findViewById(R.id.recyclerview);
        items = new ArrayList<>();
        myAdapter = new MyAdapter(getContext(), items);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(myAdapter);

        ref.child(username).child("transaksi").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    TransaksiData transaksiData = dataSnapshot.getValue(TransaksiData.class);
                    items.add(transaksiData);
                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//        initData();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), AddTransaction.class);
                i.putExtra("user", username);
                startActivity(i);
            }
        });
        return view;
    }

//    public void initData(){
//        items.add(new Item("Transportasi","-200.000",R.drawable.akomodasi));
//        items.add(new Item("Tempat Tinggal","-1.000.000",R.drawable.rumah));
//        items.add(new Item("Makanan","-200.000",R.drawable.makanan));
//        items.add(new Item("Tagihan","-400.000",R.drawable.tagihan));
//    }

//    public void initData(){
//        items.add(new Item("Transportasi","-200.000",R.drawable.minus));
//        items.add(new Item("Tempat Tinggal","-1.000.000",R.drawable.minus));
//        items.add(new Item("Makanan","-200.000",R.drawable.minus));
//        items.add(new Item("Tagihan","-400.000",R.drawable.minus));
//        items.add(new Item("Hadiah","200.000",R.drawable.plus));
//
//    }

    public void setUsername(String user){
        username = user;
    }

    public void getData(String username){
        ref.child(username).child("transaksi").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    TransaksiData transaksiData = dataSnapshot.getValue(TransaksiData.class);
                    items.add(transaksiData);
                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}