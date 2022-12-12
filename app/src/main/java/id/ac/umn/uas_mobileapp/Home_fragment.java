package id.ac.umn.uas_mobileapp;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.ArrayList;

public class Home_fragment extends Fragment {
    PieChart pieChart;
    TextView tempatTinggalV, makananV, tagihanV, transportasiV, jumlahPengeluranView;
    String username;
    float persenTempat, persenMakanan, persenTagihan, persenTransportasi;
    int jumlahPengeluaran;
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users");

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        pieChart = view.findViewById(R.id.piechart);

        tempatTinggalV = view.findViewById(R.id.tempatTinggal);
        makananV = view.findViewById(R.id.makanan);
        tagihanV = view.findViewById(R.id.tagihan);
        transportasiV = view.findViewById(R.id.transportasi);
        jumlahPengeluranView = view.findViewById(R.id.jumlahPengeluaran);

        ref.child(username).child("transaksi").child("expense").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int tempatTinggal = 0, makanan = 0, tagihan = 0, transportasi = 0, totalData = 0;
                String kategori;
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    TransaksiData transaksiData = dataSnapshot.getValue(TransaksiData.class);
                    if(transaksiData.getKategori().equals("Tempat Tinggal"))
                        tempatTinggal += transaksiData.getNominal();
                    if(transaksiData.getKategori().equals("Makanan"))
                        makanan += transaksiData.getNominal();
                    if(transaksiData.getKategori().equals("Tagihan"))
                        tagihan += transaksiData.getNominal();
                    if(transaksiData.getKategori().equals("Transportasi"))
                        transportasi += transaksiData.getNominal();
                    totalData = totalData + transaksiData.getNominal();
                }
                if(totalData == 0){
                    persenTempat = 0;
                    persenMakanan = 0;
                    persenTagihan = 0;
                    persenTransportasi = 0;
                }else{
                    persenTempat = (float)tempatTinggal / (float)totalData * 100;
                    persenMakanan = (float)makanan / (float)totalData * 100;
                    persenTagihan = (float)tagihan / (float)totalData * 100;
                    persenTransportasi = (float)transportasi / (float)totalData * 100;
                    jumlahPengeluaran = totalData;

                    jumlahPengeluranView.setText("Jumlah Pengeluaran \nRp."+jumlahPengeluaran);
                }

                tempatTinggalV.setText(String.format("%.2f", persenTempat) + "%");
                makananV.setText(String.format("%.2f", persenMakanan) + "%");
                tagihanV.setText(String.format("%.2f", persenTagihan) + "%");
                transportasiV.setText(String.format("%.2f", persenTransportasi) + "%");

                setData(persenTempat, persenMakanan, persenTagihan, persenTransportasi);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return view;
    }

    private void setData(float persenTempat, float persenMakanan, float persenTagihan, float persenTransportasi) {
        pieChart.addPieSlice(
                new PieModel(
                        "Tempat Tinggal",
                        (int) persenTempat,
                        Color.parseColor("#FFA726")));
        pieChart.addPieSlice(
                new PieModel(
                        "Makanan",
                        (int) persenMakanan,
                        Color.parseColor("#66BB6A")));
        pieChart.addPieSlice(
                new PieModel(
                        "Tagihan",
                        (int) persenTagihan,
                        Color.parseColor("#EF5350")));
        pieChart.addPieSlice(
                new PieModel(
                        "Transportasi",
                        (int) persenTransportasi,
                        Color.parseColor("#29B6F6")));

        pieChart.startAnimation();
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
