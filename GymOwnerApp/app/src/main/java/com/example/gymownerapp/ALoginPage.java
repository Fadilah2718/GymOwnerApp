package com.example.gymownerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
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

public class ALoginPage extends AppCompatActivity {
    EditText loginEmail,loginPassword;
    TextView forgotpassword,signup;
    Button loginBTN;
    FirebaseAuth mAuth;
    ProgressBar progressBar;

    /*@Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent1 = new Intent(getApplicationContext(),HomePage.class);
            startActivity(intent1);
            finish();
        }
    }*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alogin_page);

        loginEmail=findViewById(R.id.loginemail);
        loginPassword=findViewById(R.id.loginpassword);
        loginBTN=findViewById(R.id.loginbtn);
        forgotpassword=findViewById(R.id.loginforgotpass);
        signup=findViewById(R.id.logintoregister);
        progressBar=findViewById(R.id.loginprogressbar);
        mAuth=FirebaseAuth.getInstance();

        loginBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                //String email, password;
                //email = String.valueOf(loginEmail.getText());
                //password = String.valueOf(loginPassword.getText());
                String myemail = loginEmail.getText().toString();
                String mypassword = loginPassword.getText().toString();
                if (TextUtils.isEmpty(myemail)) {
                    Toast.makeText(ALoginPage.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(mypassword)) {
                    Toast.makeText(ALoginPage.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(myemail,mypassword)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    Toast.makeText(ALoginPage.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                    Intent intent1 = new Intent(getApplicationContext(),HomePage.class);
                                    startActivity(intent1);
                                } else {
                                    Toast.makeText(ALoginPage.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
        /*loginBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String myemail=loginEmail.getText().toString();
                String mypass=loginPassword.getText().toString();
                if(myemail.equals("") || mypass.isEmpty()){
                    Toast.makeText(ALoginPage.this, "Please Fill All Details", Toast.LENGTH_LONG).show();
                } else {
                    mAuth.signInWithEmailAndPassword(myemail,mypass)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        if(mAuth.getCurrentUser().isEmailVerified()){
                                            Toast.makeText(ALoginPage.this, "WELCOME!", Toast.LENGTH_SHORT).show();
                                            Intent move = new Intent(getApplicationContext(),HomePage.class);
                                            startActivity(move);
                                        }else {
                                            Toast.makeText(ALoginPage.this, "Please Verify Email", Toast.LENGTH_LONG).show();
                                        }
                                    }else {
                                        Toast.makeText(ALoginPage.this, "Enter Registered Email Id", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });*/

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AOwnerRegister.class);
                startActivity(intent);
            }
        });

        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ALoginPage.this);
                View dialogView = getLayoutInflater().inflate(R.layout.forgotpassworddialog,null);
                EditText emailBox=dialogView.findViewById(R.id.emailBox);

                builder.setView(dialogView);
                AlertDialog dialog = builder.create();
                dialogView.findViewById(R.id.btnReset).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String userEmail= emailBox.getText().toString();
                        if (TextUtils.isEmpty(userEmail)&& !Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){
                            Toast.makeText(ALoginPage.this,"Enter Your Registered EmailID",Toast.LENGTH_SHORT).show();
                            return;
                        }

                        mAuth.sendPasswordResetEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(ALoginPage.this,"Check your Spam Email",Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }else {
                                    Toast.makeText(ALoginPage.this,"Unable to send Email",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
                dialogView.findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                if ( dialog.getWindow() != null){
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                }
                dialog.show();
            }
        });
    }
}