package id.ac.umn.uas_mobileapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

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
    String username;
    Button expense, income;
    float nominal;

    ExpenseFragment expenseFragment = new ExpenseFragment();
    IncomeFragment incomeFragment = new IncomeFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);

        username = getUser();

        expenseFragment.setUsername(username);

        incomeFragment.setUsername(username);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Tambah Transaksi");
        actionBar.setDisplayHomeAsUpEnabled(true);
        ColorDrawable colorDrawable
                = new ColorDrawable(getResources().getColor(R.color.light_blue));

        actionBar.setBackgroundDrawable(colorDrawable);

//        String tipe;
//        Bundle bundle = getIntent().getExtras();
//        tipe = bundle.getString("tipe");

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

    public String getUser(){
        Bundle data = getIntent().getExtras();
        username = data.getString("user");
        return username;
    }
}
