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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class sign_up extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        final EditText name = findViewById(R.id.st_name);
        final EditText reg_no = findViewById(R.id.reg);
        final EditText phone = findViewById(R.id.Phone_no);
        final EditText vit_mail = findViewById(R.id.vit_mail);
        final EditText h_add = findViewById(R.id.h_add);
        final EditText password = findViewById(R.id.Pass);
        final EditText con_password = findViewById(R.id.confirm_pass);
        final Button signup = findViewById(R.id.sign_up);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String st_name = name.getText().toString();
                final String reg = reg_no.getText().toString();
                final String phone_no = phone.getText().toString();
                final String email_vit = vit_mail.getText().toString().trim();
                final String h_address = h_add.getText().toString();
                final String pass = password.getText().toString().trim();
                final String confirm_pass = con_password.getText().toString();
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
                else if(email_vit.isEmpty()){
                    vit_mail.setError("email is Required");
                    vit_mail.requestFocus();
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(email_vit).matches()){
                    vit_mail.setError("Please enter a valid email address");
                    vit_mail.requestFocus();
                }
                else if(h_address.isEmpty()){
                    h_add.setError("hostel Address is Required");
                    h_add.requestFocus();
                }
                else if(pass.isEmpty()){
                    password.setError("Please enter a password");
                    password.requestFocus();
                }
                else if(confirm_pass.isEmpty()){
                    con_password.setError("Please confirm your password");
                    con_password.requestFocus();
                }
                else if(!pass.equals(confirm_pass)){
                    con_password.setError("Password doesn't match");
                    con_password.requestFocus();
                }
                else{
                    progressBar.setVisibility(View.VISIBLE);
                    mAuth.createUserWithEmailAndPassword(email_vit, pass)
                            .addOnCompleteListener(com.example.hack4cause.sign_up.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                            Toast.makeText(sign_up.this, "authenticated", Toast.LENGTH_LONG).show();
                                            progressBar.setVisibility(View.GONE);
                                            Intent start = new Intent(com.example.hack4cause.sign_up.this, com.example.hack4cause.reg.class);
                                            startActivity(start);
                                    User user = new User(st_name,reg,email_vit,phone_no,h_address);
                                    FirebaseDatabase.getInstance().getReference("Users")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                Toast.makeText(sign_up.this,"added",Toast.LENGTH_LONG).show();
                                            }else{
                                                Toast.makeText(sign_up.this,"failed",Toast.LENGTH_LONG).show();
                                            }
                                            progressBar.setVisibility(View.GONE);
                                        }
                                    });
                                    } else {
                                        Toast.makeText(sign_up.this, "failed to register", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });
                }
            }
        });


    }




}