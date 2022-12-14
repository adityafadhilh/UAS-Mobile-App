package id.ac.umn.uas_mobileapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class ProfileEdit extends AppCompatActivity {
    String username, fName, lName;
    EditText userEdit, fNameEdit, lNameEdit;
    Button save, delete;
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users");
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);

        ActionBar actionBar;
        actionBar = getSupportActionBar();

        actionBar.setTitle("Edit Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#40e0d0"));
        actionBar.setBackgroundDrawable(colorDrawable);

        getData();

        userEdit = findViewById(R.id.usernameEdit);
        fNameEdit = findViewById(R.id.namaDepanEdit);
        lNameEdit = findViewById(R.id.namaBelakangEdit);
        save = findViewById(R.id.saveBtn);
        delete = findViewById(R.id.deleteBtn);

        userEdit.setText(username);
        fNameEdit.setText(fName);
        lNameEdit.setText(lName);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                username = userEdit.getText().toString();
                fName = fNameEdit.getText().toString();
                lName = lNameEdit.getText().toString();



                ref.child(username).child("username").setValue(username);
                ref.child(username).child("firstName").setValue(fName);
                ref.child(username).child("lastName").setValue(lName);

                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivityIfNeeded(i, 0);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialog dialog = new BottomSheetDialog(view.getContext());
                dialog.setContentView(R.layout.warning);

                dialog.show();

                Button yesBtn = dialog.findViewById(R.id.yesBtn);
                Button noBtn = dialog.findViewById(R.id.noBtn);

                yesBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ref.child(username).removeValue();
                        dialog.hide();
                        Intent i = new Intent(getApplicationContext(), Login.class);
                        startActivity(i);
                    }
                });

                noBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.hide();
                    }
                });
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void getData(){
        Bundle bundle = getIntent().getExtras();
        username = bundle.getString("username");
        fName = bundle.getString("firstName");
        lName = bundle.getString("lastName");
    }

}
