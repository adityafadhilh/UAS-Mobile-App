package id.ac.umn.uas_mobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    EditText username, password, confirmPass;
    Button batal, register;
    String userStr, passStr,confirmPassStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        username = findViewById(R.id.nama_pengguna);
        password = findViewById(R.id.register_pass);
        confirmPass = findViewById(R.id.confirm_pass);

        batal = findViewById(R.id.batal);
        register = findViewById(R.id.daftar);

        batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userStr = username.getText().toString();
                passStr = password.getText().toString();
                confirmPassStr = confirmPass.getText().toString();

                if(!confirmPassStr.equals(passStr)){
                    Toast.makeText(Register.this, "Konfirmasi sandi harus sama dengan sandi", Toast.LENGTH_SHORT).show();
                }else{
                    Intent i = new Intent(getApplicationContext(), Login.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("username", userStr);
                    bundle.putString("pass", passStr);
                    i.putExtras(bundle);
                    startActivity(i);
                }


            }
        });
    }
}