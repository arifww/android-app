package com.wcksn.unsiahub;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private EditText txtEmail, txtName, txtNIM, txtPassword, txtPassword2;
    private Button btnRegister;
    private DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txtEmail = findViewById(R.id.txtEmail);
        txtNIM = findViewById(R.id.txtNIM);
        txtName = findViewById(R.id.txtName);
        txtPassword = findViewById(R.id.txtPassword);
        txtPassword2 = findViewById(R.id.txtPassword2);
        btnRegister = findViewById(R.id.btnRegister);

        db = FirebaseDatabase.getInstance().getReferenceFromUrl("https://unsiahub-default-rtdb.asia-southeast1.firebasedatabase.app/");

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = txtEmail.getText().toString();
                String name = txtName.getText().toString();
                String nim = txtNIM.getText().toString();
                String password = txtPassword.getText().toString();
                String password2 = txtPassword2.getText().toString();

                if (email.isEmpty() || name.isEmpty() || nim.isEmpty() || password.isEmpty() || password2.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Data belum lengkap!", Toast.LENGTH_SHORT).show();
                //    } else if (password.equals(password2)) {
                //        Toast.makeText(getApplicationContext(),"Password tidak sama!", Toast.LENGTH_SHORT).show();
                }else {
                    db = FirebaseDatabase.getInstance().getReference("users");
                    db.child(nim).child("nim").setValue(nim);
                    db.child(nim).child("email").setValue(email);
                    db.child(nim).child("name").setValue(name);
                    db.child(nim).child("password").setValue(password);

                    Toast.makeText(getApplicationContext(),"Register berhasil!", Toast.LENGTH_SHORT).show();
                    Intent register = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(register);

                }


            }
        });
    }
}