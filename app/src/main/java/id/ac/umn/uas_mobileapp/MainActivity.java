package id.ac.umn.uas_mobileapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    String username, fName, lName;

    Home_fragment homeFragment = new Home_fragment();
    Transaksi_fragment transaksiFragment = new Transaksi_fragment();
    Profile_fragment profileFragment = new Profile_fragment();
    Camera_fragment cameraFragment = new Camera_fragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = getUser();
        getData(username);
        homeFragment.setUsername(username);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getSupportActionBar().hide();

        ActionBar actionBar;
        actionBar = getSupportActionBar();

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setTitle("Home");
        actionBar.setTitle("Home");
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#40e0d0"));
        actionBar.setBackgroundDrawable(colorDrawable);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        actionBar.setTitle("Home");
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();
                        return (true);
                    case R.id.transaksi:
                        actionBar.setTitle("Transaksi");
                        transaksiFragment.setUsername(username);
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,transaksiFragment).commit();
//                        startActivity(new Intent(getApplicationContext(),Transaksi.class).putExtra("user", username));
                        return true;
                    case R.id.kamera:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,cameraFragment).commit();
//                        useCamera();
                        return true;
                    case R.id.profile:
                        profileFragment.setUsername(username);
                        profileFragment.setfName(fName);
                        profileFragment.setlName(lName);
                        actionBar.setTitle("Profile");
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,profileFragment).commit();
//                        Bundle data = new Bundle();
//                        data.putString("firstName",fName);
//                        data.putString("lastName", lName);
//                        data.putString("username", username);
//                        startActivity(new Intent(getApplicationContext(),Profile.class).putExtras(data));
//                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

    }

    public void useCamera(){
        final int REQUEST_IMAGE_CAPTURE = 1;
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try{
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }catch(Exception e){
            Toast.makeText(getApplicationContext(), e.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        getData(username);
    }

    public void getData(String username){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users");
        ref.child(username).child("firstName").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                } else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                    fName = String.valueOf(task.getResult().getValue());
                }
            }
        });

        ref.child(username).child("lastName").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                } else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                    lName = String.valueOf(task.getResult().getValue());
                }
            }
        });

    }

    public String getUser(){
        Bundle data = getIntent().getExtras();
        return data.getString("user");
    }
}