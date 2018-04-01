package com.example.aulia.aulia_1202151364_modul6;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    EditText registEmail, registPass;
    Button btnRegist;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registEmail = findViewById(R.id.rEmail);
        registPass = findViewById(R.id.rPass);
        btnRegist = findViewById(R.id.bDaftar);
        auth = FirebaseAuth.getInstance();

        btnRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerFirebase();


            }
        });
    }

    private void registerFirebase() {
        String email = registEmail.getText().toString();
        String pass = registPass.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Email masih kosong!", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(this, "Password masih kosong!", Toast.LENGTH_SHORT).show();
        }

        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()){
                    Toast.makeText(RegisterActivity.this, "Gagal register", Toast.LENGTH_LONG).show();
                }else{
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}