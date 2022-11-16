package id.ac.umn.uas_mobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ExpenseFragment extends Fragment {
    private EditText inputNominal, date;
    private Spinner saldoSpinner, kategori;
    private String tipeTransaksi, kategoriInput, saldoInput;
    private FloatingActionButton add;
    int nominal = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_expense, container, false);
        inputNominal = view.findViewById(R.id.nominal);

        date = view.findViewById(R.id.date);

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


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nominal = Integer.parseInt(inputNominal.getText().toString());
                Intent intent = new Intent(getContext(), Transaksi.class);
                Bundle bundle = new Bundle();
                bundle.putString("tipeTransaksi", tipeTransaksi);
                bundle.putInt("nominal",nominal);
                bundle.putString("kategori",kategoriInput);
                bundle.putString("saldo", saldoInput);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        return view;
    }
}
