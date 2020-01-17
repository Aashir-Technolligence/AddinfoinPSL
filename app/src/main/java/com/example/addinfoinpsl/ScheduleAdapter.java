package com.example.addinfoinpsl;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {
    ArrayList<ScheduleAttr> scheduleAttrs;
    Activity activity;

    public ScheduleAdapter(ArrayList<ScheduleAttr> scheduleAttrs, Activity activity) {
        this.scheduleAttrs = scheduleAttrs;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.schedule, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.team1.setText(scheduleAttrs.get(position).getTeamOne());
        holder.team2.setText(scheduleAttrs.get(position).getTeamTwo());
        holder.live.setText(scheduleAttrs.get(position).getStatus());
        final String id = scheduleAttrs.get(position).getId();
        final String status = scheduleAttrs.get(position).getStatus();
        holder.live.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (status.equals("Live")) {
                    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                    DatabaseReference databaseReference = firebaseDatabase.getReference();
                    databaseReference.child("Schedule").child(id).child("status").setValue("Not Live");
                }
                else {
                    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                    DatabaseReference databaseReference = firebaseDatabase.getReference();
                    databaseReference.child("Schedule").child(id).child("status").setValue("Live");
                }
            }
        });
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);
                alertDialogBuilder.setMessage("Are you sure to delete match?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                        DatabaseReference databaseReference = firebaseDatabase.getReference();
                        databaseReference.child("Schedule").child(id).setValue(null);
                        dialog.dismiss();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return scheduleAttrs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView team1, team2, live;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.imgDelete);
            team1 = (TextView) itemView.findViewById(R.id.txtTeam1);
            team2 = (TextView) itemView.findViewById(R.id.txtTeam2);
            live = (TextView) itemView.findViewById(R.id.txtLive);


        }
    }
}
