package id.ac.umn.uas_mobileapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;

public class ExpenseFragment extends Fragment {
    private EditText inputNominal;
    private Spinner saldoSpinner, kategori;
    private String tipeTransaksi, kategoriInput, saldoInput, username;
    private FloatingActionButton add;
    private static Button dateBtn;
    private static String date;
    private TransaksiData transaksiData;
    int nominal = 0;
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users");
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_expense, container, false);

        inputNominal = view.findViewById(R.id.nominal);

        dateBtn = view.findViewById(R.id.date);

        add = view.findViewById(R.id.addExpense);

        tipeTransaksi = "Expense";

        kategori = view.findViewById(R.id.kategori);
        ArrayAdapter<CharSequence> kategoriAdapter = ArrayAdapter.createFromResource(getContext(), R.array.kategori, android.R.layout.simple_spinner_item);
        kategori.setAdapter(kategoriAdapter);
        kategori.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                kategoriInput = kategori.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        saldoSpinner = view.findViewById(R.id.saldo);
        ArrayAdapter<CharSequence> saldoAdapter = ArrayAdapter.createFromResource(getContext(), R.array.saldo, android.R.layout.simple_spinner_item);
        saldoSpinner.setAdapter(saldoAdapter);
        saldoSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                saldoInput = saldoSpinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        transaksiData = new TransaksiData();


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nominal = Integer.parseInt(inputNominal.getText().toString());

                transaksiData.setTipeTransaksi(tipeTransaksi);
                transaksiData.setKategori(kategoriInput);
                transaksiData.setTipeSaldo(saldoInput);
                transaksiData.setNominal(nominal);
                transaksiData.setTanggal(date);

                int img = 0;

                if(kategoriInput.equals("Transportasi"))
                    img = R.drawable.akomodasi;
                if(kategoriInput.equals("Tempat Tinggal"))
                    img = R.drawable.rumah;
                if(kategoriInput.equals("Makanan"))
                    img = R.drawable.makanan;
                if(kategoriInput.equals("Tagihan"))
                    img = R.drawable.tagihan;

                transaksiData.setImage(img);

                ref.child(username).child("transaksi").child("expense").push().setValue(transaksiData);


//                addData(username, transaksiData);


                Intent intent = new Intent(getContext(), MainActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("tipeTransaksi", tipeTransaksi);
//                bundle.putInt("nominal",nominal);
//                bundle.putString("kategori",kategoriInput);
//                bundle.putString("saldo", saldoInput);
                intent.putExtra("user", username);
                startActivity(intent);
            }
        });

        dateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(view);
            }
        });

        return view;
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new ExpenseFragment.DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(requireContext(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            date = day + "/" + (month+1) + "/" + year;
            dateBtn.setText(date);
        }
    }
}
