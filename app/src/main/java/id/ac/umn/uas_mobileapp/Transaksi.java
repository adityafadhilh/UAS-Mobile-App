package id.ac.umn.uas_mobileapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Transaksi extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    HariFragment hariFragment = new HariFragment();
    MingguFragment mingguFragment = new MingguFragment();
    BulanFragment bulanFragment = new BulanFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi);

        ActionBar actionBar;
        actionBar = getSupportActionBar();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Transaksi");
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#A0E418"));

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

    }
}