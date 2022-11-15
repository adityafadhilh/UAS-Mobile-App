package id.ac.umn.uas_mobileapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

public class IncomeFragment extends Fragment {
    EditText inputNominal, date;
    Spinner saldoSpinner, kategoriSpinner;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_income, container, false);
        inputNominal = view.findViewById(R.id.nominalIncome);
        date = view.findViewById(R.id.dateIncome);


        kategoriSpinner = view.findViewById(R.id.kategoriIncome);
        ArrayAdapter<CharSequence> adapterKategori = ArrayAdapter.createFromResource(getContext(), R.array.kategoriIncome, android.R.layout.simple_spinner_item);
        kategoriSpinner.setAdapter(adapterKategori);

        saldoSpinner = view.findViewById(R.id.saldoIncome);
        ArrayAdapter<CharSequence> adapterSaldo = ArrayAdapter.createFromResource(getContext(), R.array.saldo, android.R.layout.simple_spinner_item);
        saldoSpinner.setAdapter(adapterSaldo);

        return view;
    }
}
