package com.example.hack4cause;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private ProgressBar reset_progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        mAuth = FirebaseAuth.getInstance();
        reset_progressBar = findViewById(R.id.progressBar_reset);
        reset_progressBar.bringToFront();
        final EditText e_reset = findViewById(R.id.email_for_reset);
        Button reset_password_btn = findViewById(R.id.reset_pass);
        reset_password_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email_for_reset = e_reset.getText().toString();
                if(email_for_reset.isEmpty()){
                    e_reset.setError("email is Required");
                    e_reset.requestFocus();
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(email_for_reset).matches()){
                    e_reset.setError("Please enter a valid email address");
                    e_reset.requestFocus();
                }
                else{
                    reset_progressBar.setVisibility(View.VISIBLE);
                    mAuth.sendPasswordResetEmail(email_for_reset)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(ForgotPassword.this,"Check your email",Toast.LENGTH_SHORT).show();
                                        reset_progressBar.setVisibility(View.GONE);
                                    }
                                    else{
                                        Toast.makeText(ForgotPassword.this,"Some error occurred ! try again later",Toast.LENGTH_SHORT).show();
                                        reset_progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });

                }
            }
        });
    }
}