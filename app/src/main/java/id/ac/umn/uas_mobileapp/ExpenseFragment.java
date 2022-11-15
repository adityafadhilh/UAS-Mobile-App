package id.ac.umn.uas_mobileapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

public class ExpenseFragment extends Fragment {
    EditText inputNominal, date;
    Spinner saldoSpinner, kategori;
    String tanggal, kategoriInput, saldoInput;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_expense, container, false);
        inputNominal = view.findViewById(R.id.nominal);
        date = view.findViewById(R.id.date);

        kategori = view.findViewById(R.id.kategori);
        ArrayAdapter<CharSequence> kategoriAdapter = ArrayAdapter.createFromResource(getContext(), R.array.kategori, android.R.layout.simple_spinner_item);
        kategori.setAdapter(kategoriAdapter);

        saldoSpinner = view.findViewById(R.id.saldo);
        ArrayAdapter<CharSequence> saldoAdapter = ArrayAdapter.createFromResource(getContext(), R.array.saldo, android.R.layout.simple_spinner_item);
        saldoSpinner.setAdapter(saldoAdapter);
        return view;
    }
}
