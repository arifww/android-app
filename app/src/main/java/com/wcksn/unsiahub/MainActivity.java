package com.wcksn.unsiahub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {


    private EditText txtNIM, txtPassword;
    private Button btnReg, btnLogin;
    private DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnReg = findViewById(R.id.btnReg);
        btnLogin = findViewById(R.id.btnLogin);
        txtNIM = findViewById(R.id.txtNIM);
        txtPassword = findViewById(R.id.txtPassword);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(register);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nim = txtNIM.getText().toString();
                String password = txtPassword.getText().toString();

                db = FirebaseDatabase.getInstance().getReference("users");

                if (nim.isEmpty() || password.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Email Atau Password Salah",Toast.LENGTH_SHORT).show();
                }else{
                    db.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.child(nim).exists()){
                                if (snapshot.child(nim).child("password").getValue(String.class).equals(password)){
                                    Toast.makeText(getApplicationContext(), "Login Berhasil",Toast.LENGTH_SHORT).show();
                                    Intent login = new Intent(getApplicationContext(), ProfileActivity.class);
                                    startActivity(login);
                                }
                            }else{
                                Toast.makeText(getApplicationContext(), "Data Belum Terdaftar",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });
    }
}