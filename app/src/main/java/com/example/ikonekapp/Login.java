package com.example.ikonekapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    Button SignUp;
    TextInputLayout signInEmail, signInPassword;
    FirebaseAuth authenticator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        signInEmail = findViewById(R.id.email);
        signInPassword = findViewById(R.id.password);

        SignUp = findViewById(R.id.sign_up_btn);

        SignUp.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (Login.this, com.example.ikonekapp.SignUp.class);
                startActivity(intent);
            }
        });

        authenticator = FirebaseAuth.getInstance();

    }

    public void login_btn(View view) {

        String email = signInEmail.getEditText().getText().toString().trim();
        String password = signInPassword.getEditText().getText().toString().trim();

        authenticator.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            Intent intent = new Intent(getApplicationContext(),Dashboard.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(Login.this,"Invalid Email or Password!", Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }
}