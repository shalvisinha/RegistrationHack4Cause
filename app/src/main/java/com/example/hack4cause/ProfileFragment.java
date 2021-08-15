package com.example.hack4cause;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileFragment extends Fragment {

    private String email , password ;
    private FirebaseUser user;
    private DatabaseReference userRef;
    private DatabaseReference userReference;
    private String userID;
    private ProgressBar progressBar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        progressBar = root.findViewById(R.id.profile_progress);
        progressBar.bringToFront();
        progressBar.setVisibility(View.VISIBLE);
        ImageView profile_Img = root.findViewById(R.id.dp) ;
        final TextView username = root.findViewById(R.id.username);
        final TextView userreg = root.findViewById(R.id.userreg);
        final TextView usermail  = root.findViewById(R.id.usermail);
        final TextView userphone = root.findViewById(R.id.userphone);
        final Button log_out = (Button)root.findViewById(R.id.logout);
        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent start = new Intent(getActivity(), login.class);
                startActivity(start);
            }
        });
        log_out.setVisibility(View.GONE);
        user = FirebaseAuth.getInstance().getCurrentUser();
        userRef = FirebaseDatabase.getInstance().getReference("Users");
        userReference = FirebaseDatabase.getInstance().getReference("Other_Users");
        userID = user.getUid();
        userRef.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                 User userProfile = snapshot.getValue(User.class);
                 if(userProfile != null) {
                     String name = userProfile.student_name;
                     String RegNo = userProfile.Reg_no;
                     String email = userProfile.vit_mail;
                     String phone = userProfile.phone;

                     username.setText(name);
                     userreg.setText(RegNo);
                     usermail.setText(email);
                     userphone.setText(phone);
                     log_out.setVisibility(View.VISIBLE);
                     progressBar.setVisibility(View.GONE);

                 }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(),"something went wrong",Toast.LENGTH_LONG).show();
            }
        });
        userReference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User_other userProf = snapshot.getValue(User_other.class);
                if(userProf != null) {
                   String name = userProf.student_name;
                    String RegNo = userProf.Reg_no;
                    String email = userProf.e_mail;
                    String phone = userProf.phone;

                    username.setText(name);
                    userreg.setText(RegNo);
                    usermail.setText(email);
                    userphone.setText(phone);
                    log_out.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(),"something went wrong",Toast.LENGTH_LONG).show();
            }
        });
        return root;
    }
}