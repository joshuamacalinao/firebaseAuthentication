package com.example.ikonekapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    Button goBackButton;
    TextInputLayout regStudentID, regFirstName, regMiddleName, regLastName, regBirthDate, regAddress, regEmail, regPassword;
    FirebaseAuth authenticator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);

        regStudentID = findViewById(R.id.student_id);
        regFirstName = findViewById(R.id.firstname);
        regMiddleName = findViewById(R.id.middle_initial);
        regLastName = findViewById(R.id.surname);
        regBirthDate = findViewById(R.id.birth_date);
        regAddress = findViewById(R.id.address);
        regEmail = findViewById(R.id.email);
        regPassword = findViewById(R.id.password);

        goBackButton = findViewById(R.id.go_back_btn);

        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (SignUp.this, com.example.ikonekapp.Login.class);
                startActivity(intent);
            }
        });

        authenticator = FirebaseAuth.getInstance();

    }

    public void signup_btn(View view) {

        String studentID = regStudentID.getEditText().getText().toString().trim();
        String firstName = regFirstName.getEditText().getText().toString().trim();
        String middleName = regMiddleName.getEditText().getText().toString().trim();
        String lastName = regLastName.getEditText().getText().toString().trim();
        String birthDate = regBirthDate.getEditText().getText().toString().trim();
        String address = regAddress.getEditText().getText().toString().trim();
        String email = regEmail.getEditText().getText().toString().trim();
        String password = regPassword.getEditText().getText().toString().trim();

        authenticator.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            UserHelper userHelperClass = new UserHelper(studentID,firstName,middleName,lastName,birthDate,address,email,password);

                            FirebaseDatabase.getInstance().getReference("users")
                                    .child(studentID)
                                    .setValue(userHelperClass).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Intent intent = new Intent(SignUp.this, com.example.ikonekapp.Login.class);
                                        startActivity(intent);
                                        Toast.makeText(SignUp.this, "Successfully Registered!", Toast.LENGTH_LONG).show();
                                    }else{
                                        Toast.makeText(SignUp.this, "Failed to Register!", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }else{
                            Toast.makeText(SignUp.this, "Error! Please try again later", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}