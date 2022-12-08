package id.ac.umn.uas_mobileapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Transaksi extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    HariFragment hariFragment = new HariFragment();
    MingguFragment mingguFragment = new MingguFragment();
    BulanFragment bulanFragment = new BulanFragment();

    int nominal;
    String kategori, saldo, tipeTransaksi;
    String username;
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi);

        username = getUser();

        hariFragment.setUsername(username);

        ActionBar actionBar;
        actionBar = getSupportActionBar();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Transaksi");
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#40e0d0"));

        actionBar.setBackgroundDrawable(colorDrawable);

        bottomNavigationView  = findViewById(R.id.bottom_navigation);

        getSupportFragmentManager().beginTransaction().replace(R.id.container,hariFragment).commit();

        BadgeDrawable badgeDrawable = bottomNavigationView.getOrCreateBadge(R.id.hari);
        badgeDrawable.setVisible(true);
        badgeDrawable.setNumber(1);

        BadgeDrawable badge2Drawable = bottomNavigationView.getOrCreateBadge(R.id.minggu);
        badge2Drawable.setVisible(true);
        badge2Drawable.setNumber(7);

        BadgeDrawable badge3Drawable = bottomNavigationView.getOrCreateBadge(R.id.bulan);
        badge3Drawable.setVisible(true);
        badge3Drawable.setNumber(30);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.hari:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,hariFragment).commit();
                        return true;
                    case R.id.minggu:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,mingguFragment).commit();
                        return true;
                    case R.id.bulan:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,bulanFragment).commit();
                        return true;
                }

                return false;
            }
        });

//        Bundle data = getIntent().getExtras();
//        if(data!=null){
//            nominal = data.getInt("nominal");
//            tipeTransaksi = data.getString("tipeTransaksi");
//            kategori = data.getString("kategori");
//            saldo = data.getString("saldo");
//
//            int img = 0;
//
////            if(kategori.equals("Transportasi"))
////                img = R.drawable.akomodasi;
////            if(kategori.equals("Tempat Tinggal"))
////                img = R.drawable.rumah;
////            if(kategori.equals("Makanan"))
////                img = R.drawable.makanan;
////            if(kategori.equals("Tagihan"))
////                img = R.drawable.tagihan;
//
//            img = R.drawable.minus;
//
//            String angka = "";
//
//            if(tipeTransaksi.equals("Expense"))
//                angka = "-" + String.valueOf(nominal);
//            if(tipeTransaksi.equals("Income"))
//                angka = "+" + String.valueOf(nominal);
//
//            hariFragment.items.add(new Item(kategori, angka, img));
//        }

    }

//    @Override
//    protected void onPostResume() {
//        super.onPostResume();
//        Bundle data = getIntent().getExtras();
//        if(data!=null){
//            nominal = data.getInt("nominal");
//            tipeTransaksi = data.getString("tipeTransaksi");
//            kategori = data.getString("kategori");
//            saldo = data.getString("saldo");
//
//            int img = 0;
//
//            if(kategori.equals("Transportasi"))
//                img = R.drawable.akomodasi;
//            if(kategori.equals("Tempat Tinggal"))
//                img = R.drawable.rumah;
//            if(kategori.equals("Makanan"))
//                img = R.drawable.makanan;
//            if(kategori.equals("Tagihan"))
//                img = R.drawable.tagihan;
//
////            img = R.drawable.minus;
//
//            String angka = "";
//
//            if(tipeTransaksi.equals("Expense"))
//                angka = "-" + String.valueOf(nominal);
//            if(tipeTransaksi.equals("Income"))
//                angka = "+" + String.valueOf(nominal);
//
//            hariFragment.items.add(new Item(kategori, angka, img));
//        }
//    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public String getUser(){
        Bundle data = getIntent().getExtras();
        return data.getString("user");
    }

//    public void getData(String username){
//        ref.child(username).child("transaksi").child("expense").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
//                    TransaksiData transaksiData = dataSnapshot.getValue(TransaksiData.class);
//                    hariFragment.items.add(transaksiData);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        })
//    }
}