package id.ac.umn.uas_mobileapp;

import android.content.Intent;
import android.icu.util.BuddhistCalendar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShowIncome extends Fragment {
    FloatingActionButton fab;
    RecyclerView recyclerView;
    MyAdapter myAdapter;
    ArrayList<TransaksiData> items;
    String username;
    int total;
    TextView totalIncome;
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users");

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.show_income_fragment, container, false);
        fab = view.findViewById(R.id.floatingActionButton);
        recyclerView = view.findViewById(R.id.recyclerview);
        items = new ArrayList<>();
        myAdapter = new MyAdapter(getContext(), items);

        total = 0;

        totalIncome = view.findViewById(R.id.totalIncome);

        ref.child(username).child("transaksi").child("income").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    TransaksiData transaksiData = dataSnapshot.getValue(TransaksiData.class);
                    total += transaksiData.getNominal();
                }
                totalIncome.setText("Rp." + String.valueOf(total));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(myAdapter);

        ref.child(username).child("transaksi").child("income").addValueEventListener(new ValueEventListener() {
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

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), AddTransaction.class);
                Bundle bundle = new Bundle();
                bundle.putString("user", username);
                bundle.putString("tipe", "income");
                i.putExtras(bundle);
                startActivity(i);
            }
        });
        return view;
    }

    public void setUsername(String user){
        username = user;
    }
}
