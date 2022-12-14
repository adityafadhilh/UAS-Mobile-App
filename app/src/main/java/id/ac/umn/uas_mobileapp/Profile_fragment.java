package id.ac.umn.uas_mobileapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Profile_fragment extends Fragment {
    Button logout, edit;
    String username;
    String fName, lName;
    TextView namaPengguna, namaDepan, namaBelakang;
    ImageView img;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment, container, false);

        img = view.findViewById(R.id.imageView);

        namaPengguna = view.findViewById(R.id.nama_pengguna);

        namaPengguna.setText(username);

        namaDepan = view.findViewById(R.id.namaDepan);
        namaDepan.setText(fName);

        namaBelakang = view.findViewById(R.id.namaBelakang);
        namaBelakang.setText(lName);

        Toast.makeText(getContext(), fName + " " + lName, Toast.LENGTH_SHORT).show();

        edit = view.findViewById(R.id.editBtn);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("username", username);
                bundle.putString("firstName", fName);
                bundle.putString("lastName", lName);
                Intent intent = new Intent(getContext(), ProfileEdit.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

        logout = view.findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Login.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
