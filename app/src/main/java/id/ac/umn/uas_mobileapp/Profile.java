package id.ac.umn.uas_mobileapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Profile extends AppCompatActivity {
    Button logout;
    String username;
    String fName, lName;
    TextView nama;
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getData();

        nama = findViewById(R.id.nama);

        nama.setText(fName + " " + lName);

        Toast.makeText(this, fName + " " + lName, Toast.LENGTH_SHORT).show();

        ActionBar actionBar;
        actionBar = getSupportActionBar();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Profil");
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#40e0d0"));

        actionBar.setBackgroundDrawable(colorDrawable);

        logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
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

    public void getData(){
        Bundle data = getIntent().getExtras();
        username = data.getString("username");
        fName = data.getString("firstName");
        lName = data.getString("lastName");
    }

}
