package com.example.addinfoinpsl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ScheduleList extends BaseActivity {
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference reference = firebaseDatabase.getReference();
    ArrayList<ScheduleAttr> scheduleAttrs;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_schedule_list);
        recyclerView=findViewById(R.id.schList);
        scheduleAttrs = new ArrayList<ScheduleAttr>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        reference.child("Schedule").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                scheduleAttrs.clear();
                //profiledata.clear();
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    ScheduleAttr p = dataSnapshot1.getValue(ScheduleAttr.class);
                    scheduleAttrs.add(p);
                }

                recyclerView.setAdapter(new ScheduleAdapter(scheduleAttrs ,ScheduleList.this));


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    int getContentViewId() {
        return R.layout.activity_schedule_list;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.schedulelist;
    }
}
