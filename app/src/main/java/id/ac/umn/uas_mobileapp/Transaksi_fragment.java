package id.ac.umn.uas_mobileapp;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Transaksi_fragment extends Fragment {
    BottomNavigationView bottomNavigationView;

    ShowExpense showExpense = new ShowExpense();
    ShowIncome showIncome = new ShowIncome();

    int nominal;
    String kategori, saldo, tipeTransaksi;
    String username;

    public void setUsername(String username) {
        this.username = username;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.transaksi_fragment, container, false);

        showExpense.setUsername(username);

        showIncome.setUsername(username);

        bottomNavigationView  = view.findViewById(R.id.bottom_navigation);

        getChildFragmentManager().beginTransaction().replace(R.id.container,showExpense).commit();

//        BadgeDrawable badgeDrawable = bottomNavigationView.getOrCreateBadge(R.id.expense);
//        badgeDrawable.setVisible(true);
//        badgeDrawable.setNumber(1);

//        BadgeDrawable badge2Drawable = bottomNavigationView.getOrCreateBadge(R.id.income);
//        badge2Drawable.setVisible(true);
//        badge2Drawable.setNumber(7);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.expense:
                        getChildFragmentManager().beginTransaction().replace(R.id.container,showExpense).commit();
                        return true;
                    case R.id.income:
                        getChildFragmentManager().beginTransaction().replace(R.id.container,showIncome).commit();
                        return true;
                }

                return false;
            }
        });

        return view;
    }
}
