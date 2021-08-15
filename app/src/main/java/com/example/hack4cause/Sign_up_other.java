package com.example.hack4cause;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Sign_up_other extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_other);
        final EditText name = findViewById(R.id.student_name);
        final EditText reg_no = findViewById(R.id.reg_no);
        final EditText college = findViewById(R.id.college_name);
        final EditText phone = findViewById(R.id.Phone);
        final EditText e_mail = findViewById(R.id.e_mail);
        final EditText password = findViewById(R.id.Password);
        final EditText confirm_password = findViewById(R.id.confirm_password);
        final Button sign_up_oth = findViewById(R.id.sign_up_oth);
        sign_up_oth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String st_name = name.getText().toString();
                final String reg = reg_no.getText().toString();
                final String college_name = college.getText().toString();
                final String phone_no = phone.getText().toString();
                final String email = e_mail.getText().toString();
                final String pass = password.getText().toString();
                final String confirm_pass = confirm_password.getText().toString();
                progressBar = findViewById(R.id.progressBar);
                mAuth = FirebaseAuth.getInstance();
                if(st_name.isEmpty()){
                    name.setError("Name is Required");
                    name.requestFocus();
                }
                else if(reg.isEmpty()){
                    reg_no.setError("Registration Number is Required");
                    reg_no.requestFocus();
                }
                else if(phone_no.isEmpty()){
                    phone.setError("Phone number is Required");
                    phone.requestFocus();
                }
                else if(phone.length()!=10){
                    phone.setError("Please enter a valid phone number");
                    phone.requestFocus();
                }
                else if(email.isEmpty()){
                    e_mail.setError("email is Required");
                    e_mail.requestFocus();
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    e_mail.setError("Please enter a valid email address");
                    e_mail.requestFocus();
                }
                else if(college_name.isEmpty()){
                    college.setError("College name is Required");
                    college.requestFocus();
                }
                else if(pass.isEmpty()){
                    password.setError("Please enter a password");
                    password.requestFocus();
                }
                else if(confirm_pass.isEmpty()){
                    confirm_password.setError("Please confirm your password");
                    confirm_password.requestFocus();
                }
                else if(!pass.equals(confirm_pass)){
                    confirm_password.setError("Password doesn't match");
                    confirm_password.requestFocus();
                }
                else{
                    progressBar.setVisibility(View.VISIBLE);
                    mAuth.createUserWithEmailAndPassword(email, pass)
                            .addOnCompleteListener(com.example.hack4cause.Sign_up_other.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(Sign_up_other.this, "authenticated", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                        Intent start = new Intent(com.example.hack4cause.Sign_up_other.this, login.class);
                                        startActivity(start);
                                        User_other user_oth = new User_other(st_name,reg,college_name,email,phone_no);
                                        FirebaseDatabase.getInstance().getReference("Other_Users")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(user_oth).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if(task.isSuccessful()){
                                                    Toast.makeText(Sign_up_other.this,"Now Login",Toast.LENGTH_LONG).show();//added to database
                                                }else{
                                                    Toast.makeText(Sign_up_other.this,"failed",Toast.LENGTH_LONG).show();
                                                }
                                                progressBar.setVisibility(View.GONE);
                                            }
                                        });
                                    } else {
                                        Toast.makeText(Sign_up_other.this, "failed to register", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });
                }
            }
        });
    }
}