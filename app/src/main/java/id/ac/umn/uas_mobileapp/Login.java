package id.ac.umn.uas_mobileapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.Objects;

public class Login extends AppCompatActivity {
    EditText username, password;
    Button login, register;
    String userStr, passStr, userCheck, passCheck;
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
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

//        // Data untuk mengecek login
//
//        Bundle data = getIntent().getExtras();
//
//        if(data != null){
//            userCheck = data.getString("username");
//            passCheck = data.getString("pass");
//        }else{
//            userCheck = "john doe";
//            passCheck = "test";
//        }


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userStr = username.getText().toString();
                passStr = password.getText().toString();
                checkData(userStr, passStr);
            }
        });
    }

//    public void checkLogin(String username, String password) {
//        if(username.equals(userCheck)){
//            if(password.equals(passCheck)){
//                Intent i = new Intent(getApplicationContext(), MainActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("user", userStr);
//                bundle.putString("pass", passStr);
//                i.putExtras(bundle);
//                startActivity(i);
//            }else{
//                Toast.makeText(Login.this, "username atau password salah", Toast.LENGTH_SHORT).show();
//            }
//        }else{
//            Toast.makeText(Login.this, "username atau password salah", Toast.LENGTH_SHORT).show();
//        }
//    }

    public void checkData(String username, String password){
        mDatabase.child(username).child("pass").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                    Toast.makeText(Login.this, "username atau password salah", Toast.LENGTH_SHORT).show();
                }
                else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                    if(password.equals(String.valueOf(task.getResult().getValue()))){
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("user", username);
                        bundle.putString("pass", password);
                        i.putExtras(bundle);
                        startActivity(i);
                    }else{
                        Toast.makeText(Login.this, "username atau password salah", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}