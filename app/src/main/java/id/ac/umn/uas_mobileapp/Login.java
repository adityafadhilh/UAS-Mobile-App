package id.ac.umn.uas_mobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class Login extends AppCompatActivity {
    EditText username, password;
    Button login, register;
    String userStr, passStr, userCheck, passCheck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        username = (EditText) findViewById(R.id.inputName);
        password = (EditText) findViewById(R.id.inputPass);

        login = findViewById(R.id.login);
        register = findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
            }
        });

        // Data untuk mengecek login

        Bundle data = getIntent().getExtras();

        if(data != null){
            userCheck = data.getString("username");
            passCheck = data.getString("pass");
        }else{
            userCheck = "john doe";
            passCheck = "test";
        }


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userStr = username.getText().toString();
                passStr = password.getText().toString();
                checkLogin(userStr, passStr);
            }
        });
    }

    public void checkLogin(String username, String password) {
        if(username.equals(userCheck)){
            if(password.equals(passCheck)){
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("user", userStr);
                bundle.putString("pass", passStr);
                i.putExtras(bundle);
                startActivity(i);
            }else{
                Toast.makeText(Login.this, "username atau password salah", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(Login.this, "username atau password salah", Toast.LENGTH_SHORT).show();
        }
    }
}