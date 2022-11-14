package id.ac.umn.uas_mobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class AddTransaction extends AppCompatActivity {
    EditText inputNominal, date;
    Spinner saldoSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        inputNominal = findViewById(R.id.nominal);
        date = findViewById(R.id.date);

        saldoSpinner = findViewById(R.id.saldo);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.saldo, android.R.layout.simple_spinner_item);
        saldoSpinner.setAdapter(adapter);


    }
}