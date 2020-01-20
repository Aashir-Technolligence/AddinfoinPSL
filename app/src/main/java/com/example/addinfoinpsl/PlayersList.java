package com.example.addinfoinpsl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PlayersList extends BaseActivity {
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference reference = firebaseDatabase.getReference();
    ArrayList<AddPlayerAttr> addPlayerAttrs;
    RecyclerView recyclerView;
    Spinner team;
    String team1="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_players_list);
        team = findViewById(R.id.spinnerTeam);
        team.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                team1 = parent.getItemAtPosition(position).toString();
                calldata(team1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        recyclerView=findViewById(R.id.playList);
        addPlayerAttrs = new ArrayList<AddPlayerAttr>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private void calldata(String team1) {
        reference.child("Players").child(team1).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                addPlayerAttrs.clear();
                //profiledata.clear();
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    AddPlayerAttr p = dataSnapshot1.getValue(AddPlayerAttr.class);
                    addPlayerAttrs.add(p);
                }

                recyclerView.setAdapter(new PlayersAdapter(addPlayerAttrs ,PlayersList.this));


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    int getContentViewId() {
        return R.layout.activity_players_list;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.playerlist;
    }
}
