package com.example.gymownerapp;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.VerifiedInputEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AOwnerRegister extends AppCompatActivity {
    EditText registerEmail,registerPassword;
    TextView loginpage;
    Button registerBTN;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent1 = new Intent(getApplicationContext(),HomePage.class);
            startActivity(intent1);
            finish();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aowner_register);
        registerEmail=findViewById(R.id.registeremail);
        registerPassword=findViewById(R.id.registerpassword);
        registerBTN=findViewById(R.id.registerbtn);
        loginpage=findViewById(R.id.registertologin);
        progressBar=findViewById(R.id.registerprogressbar);
        mAuth=FirebaseAuth.getInstance();
        registerBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String email, password;
                email = String.valueOf(registerEmail.getText());
                password = String.valueOf(registerPassword.getText());
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(AOwnerRegister.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(AOwnerRegister.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                String myemail = registerEmail.getText().toString();
                String mypass = registerPassword.getText().toString();
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    Toast.makeText(AOwnerRegister.this, "Account Created", Toast.LENGTH_SHORT).show();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(AOwnerRegister.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        loginpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ALoginPage.class);
                startActivity(intent);
                finish();
            }
        });

    }
}