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

public class LoginActivity extends AppCompatActivity {

    EditText loginEmail, loginPass;
    Button bMasuk, bRegist;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null){
            Intent i = new Intent(LoginActivity.this,  MainActivity.class);
            startActivity(i);
            finish();
        }

        loginEmail = findViewById(R.id.edEmail);
        loginPass = findViewById(R.id.edPass);
        bMasuk = findViewById(R.id.bMasuk);
        bRegist = findViewById(R.id.bDaftar);

        bRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

        bMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginFirebase();
            }
        });
    }

    private void loginFirebase() {
        String email = loginEmail.getText().toString();
        String pass = loginPass.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Email masih kosong!", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(this, "Password masih kosong!", Toast.LENGTH_SHORT).show();
        }

        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "Gagal Login", Toast.LENGTH_LONG).show();
                }else{
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
