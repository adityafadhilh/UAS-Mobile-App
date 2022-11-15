package id.ac.umn.uas_mobileapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AddTransaction extends AppCompatActivity {
    EditText inputNominal, date;
    Spinner saldoSpinner, kategori;
    String tanggal, kategoriInput, saldoInput;
    Button expense, income;
    float nominal;

    ExpenseFragment expenseFragment = new ExpenseFragment();
    IncomeFragment incomeFragment = new IncomeFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);

//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getSupportActionBar().hide();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Tambah Transaksi");
        actionBar.setDisplayHomeAsUpEnabled(true);
        ColorDrawable colorDrawable
                = new ColorDrawable(getResources().getColor(R.color.green));

        actionBar.setBackgroundDrawable(colorDrawable);

//        inputNominal = findViewById(R.id.nominal);
//        date = findViewById(R.id.date);
//
//        kategori = findViewById(R.id.kategori);
//        ArrayAdapter<CharSequence> kategoriAdapter = ArrayAdapter.createFromResource(this, R.array.kategori, android.R.layout.simple_spinner_item);
//        kategori.setAdapter(kategoriAdapter);
//
//        saldoSpinner = findViewById(R.id.saldo);
//        ArrayAdapter<CharSequence> saldoAdapter = ArrayAdapter.createFromResource(this, R.array.saldo, android.R.layout.simple_spinner_item);
//        saldoSpinner.setAdapter(saldoAdapter);

        getSupportFragmentManager().beginTransaction().replace(R.id.contain, expenseFragment).commit();

        expense = findViewById(R.id.btnExpense);
        expense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expense.setBackgroundColor(getResources().getColor(R.color.red));
                income.setBackgroundColor(getResources().getColor(R.color.grey));
                getSupportFragmentManager().beginTransaction().replace(R.id.contain, expenseFragment).commit();
            }
        });

        income = findViewById(R.id.btnIncome);
        income.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expense.setBackgroundColor(getResources().getColor(R.color.grey));
                income.setBackgroundColor(getResources().getColor(R.color.blue));
                getSupportFragmentManager().beginTransaction().replace(R.id.contain, incomeFragment).commit();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}