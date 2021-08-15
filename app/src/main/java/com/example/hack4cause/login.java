package com.example.hack4cause;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class login extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        final EditText log_email = findViewById(R.id.login_email);
        final EditText log_password= findViewById(R.id.login_password);
        progressBar = findViewById(R.id.log_progress);
        progressBar.bringToFront();
        Button signIn = findViewById(R.id.log);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email_login = log_email.getText().toString();
                final String pass_login = log_password.getText().toString();
                if(email_login.isEmpty()){
                    log_email.setError("email is Required");
                    log_email.requestFocus();
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(email_login).matches()){
                    log_email.setError("Please enter a valid email address");
                    log_email.requestFocus();
                }
                else if(pass_login.isEmpty()){
                    log_password.setError("Please enter a password");
                    log_password.requestFocus();
                }
                else {
                    progressBar.setVisibility(View.VISIBLE);
                    mAuth.signInWithEmailAndPassword(email_login, pass_login)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(login.this, "welcome", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                        Intent start = new Intent(login.this, com.example.hack4cause.Nav.class);
                                        startActivity(start);
                                    }
                                    else{
                                        Toast.makeText(login.this, "Please check your credentials and try again", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }

                                }
                            });
                }

            }
        });


        TextView signUp = findViewById(R.id.sign_up_text);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this,sign_up.class));
            }
        });
        TextView click_here = findViewById(R.id.click);
        click_here.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent start = new Intent(login.this,Sign_up_other.class);
                startActivity(start);
            }
        });
        TextView forgotPassword = findViewById(R.id.forgot_p);
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this,ForgotPassword.class));
            }
        });

    }
}