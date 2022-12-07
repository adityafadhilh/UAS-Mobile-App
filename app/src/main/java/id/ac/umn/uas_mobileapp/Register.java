package id.ac.umn.uas_mobileapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Register extends AppCompatActivity {
    EditText username, firstName, lastName, password, confirmPass;
    Button batal, register;
    String userStr, fNameStr, lNameStr, passStr,confirmPassStr;
    User user;
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users");
    long id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                    id = snapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        username = findViewById(R.id.nama_pengguna);
        firstName = findViewById(R.id.firstname);
        lastName = findViewById(R.id.lastname);
        password = findViewById(R.id.register_pass);
        confirmPass = findViewById(R.id.confirm_pass);

        batal = findViewById(R.id.batal);
        register = findViewById(R.id.daftar);

        user = new User();

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
                fNameStr = firstName.getText().toString();
                lNameStr = lastName.getText().toString();
                passStr = password.getText().toString();
                confirmPassStr = confirmPass.getText().toString();
//                Toast.makeText(getApplicationContext(), FirebaseDatabase.getInstance().getReference().toString(), Toast.LENGTH_LONG).show();


                if(!confirmPassStr.equals(passStr)){
                    Toast.makeText(Register.this, "Konfirmasi sandi harus sama dengan sandi", Toast.LENGTH_SHORT).show();
                }else{
                    user.setUsername(userStr);
                    user.setFirstName(fNameStr);
                    user.setLastName(lNameStr);
                    user.setPass(passStr);

                    ref.child(user.getUsername()).setValue(user);

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