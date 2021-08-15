package com.example.hack4cause;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class myAdapter extends FirebaseRecyclerAdapter<model,myAdapter.myviewholder>
{

    public myAdapter(@NonNull FirebaseRecyclerOptions<model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int i, @NonNull model model) {
            holder.name.setText(model.getStudent_name());
            holder.reg.setText(model.getReg_no());
            holder.email.setText(model.getVit_mail());
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reg_listview,parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder
 {
     TextView name;
     TextView reg;
     TextView email;
     Button remove;
     public myviewholder(@NonNull View itemView) {
         super(itemView);
         name = (TextView)itemView.findViewById(R.id.view_name);
         reg = (TextView)itemView.findViewById(R.id.view_reg);
         email = (TextView)itemView.findViewById(R.id.view_email);
         remove =(Button)itemView.findViewById(R.id.remove_btn);
     }
 }
}
