package com.example.addinfoinpsl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class UpdatePlayer extends BaseActivity {
    EditText inning, totalScore ,currentPslScore, strike, bestScore, averageScore, thirty, fifty, hundred, tWicket, cWicket, bestBowlScore, bestBowlWicket;
    private Button addPlayer;
    Spinner typeSpinner, handSpinner;
    String type, hand, id = "", team , name;
    ProgressDialog pd;
    StorageReference StorageRef;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_update_player);
        final Intent i = getIntent();
        id = i.getStringExtra("id");
        team = i.getStringExtra("team");
        name = i.getStringExtra("name");
        inning = (EditText) findViewById(R.id.edtInnings);
        strike = (EditText) findViewById(R.id.edtStrike);
        bestScore = (EditText) findViewById(R.id.edtBestScore);
        averageScore = (EditText) findViewById(R.id.edtAvgScore);
        thirty = (EditText) findViewById(R.id.edt30);
        fifty = (EditText) findViewById(R.id.edt50);
        hundred = (EditText) findViewById(R.id.edt100);
        tWicket = (EditText) findViewById(R.id.edtTWicket);
        cWicket = (EditText) findViewById(R.id.edtCWicket);
        bestBowlScore = (EditText) findViewById(R.id.edtBScore);
        bestBowlWicket = (EditText) findViewById(R.id.edtBWicket);
        totalScore = (EditText) findViewById(R.id.edtTotalScore);
        currentPslScore = (EditText) findViewById(R.id.edtCurrentPSLScore);
        addPlayer = (Button) findViewById(R.id.btnAddPlayer);
        typeSpinner = findViewById(R.id.spinnerType);
        handSpinner = findViewById(R.id.spinnerHand);
        StorageRef = FirebaseStorage.getInstance().getReference();
        reference = FirebaseDatabase.getInstance().getReference();

        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                type = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        handSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                hand = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        pd = new ProgressDialog(this);
        pd.setMessage("Updating Player..... ");
        if (!id.equals("")) {
            reference.child("Players").child(team).child(id).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    inning.setText(dataSnapshot.child("innings").getValue().toString());
                    averageScore.setText(dataSnapshot.child("avgScore").getValue().toString());
                    strike.setText(dataSnapshot.child("strike").getValue().toString());
                    bestScore.setText(dataSnapshot.child("bestScore").getValue().toString());
                    bestBowlScore.setText(dataSnapshot.child("bestBScore").getValue().toString());
                    bestBowlWicket.setText(dataSnapshot.child("bestBWicket").getValue().toString());
                    cWicket.setText(dataSnapshot.child("cwickets").getValue().toString());
                    fifty.setText(dataSnapshot.child("fifty").getValue().toString());
                    hundred.setText(dataSnapshot.child("hundred").getValue().toString());
                    thirty.setText(dataSnapshot.child("thirty").getValue().toString());
                    totalScore.setText(dataSnapshot.child("totalScore").getValue().toString());
                    currentPslScore.setText(dataSnapshot.child("currentPSLScore").getValue().toString());
                    tWicket.setText(dataSnapshot.child("twickets").getValue().toString());
                    if (dataSnapshot.child("type").getValue().toString().equals("Batsman"))
                        typeSpinner.setSelection(0);
                    if (dataSnapshot.child("type").getValue().toString().equals("Bowler"))
                        typeSpinner.setSelection(1);
                    if (dataSnapshot.child("type").getValue().toString().equals("All Rounder"))
                        typeSpinner.setSelection(2);
                    if (dataSnapshot.child("hand").getValue().toString().equals("Left Hand"))
                        handSpinner.setSelection(0);
                    if (dataSnapshot.child("hand").getValue().toString().equals("Right Hand"))
                        handSpinner.setSelection(1);


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

        addPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.show();
                reference.child("Runs").child(id).setValue(null);
                reference.child("Wickets").child(id).setValue(null);
                reference.child("Players").child(team).child(id).child("innings").setValue(inning.getText().toString());
                reference.child("Players").child(team).child(id).child("strike").setValue(strike.getText().toString());
                reference.child("Players").child(team).child(id).child("bestScore").setValue(Integer.parseInt(bestScore.getText().toString()));
                reference.child("Players").child(team).child(id).child("avgScore").setValue(averageScore.getText().toString());
                reference.child("Players").child(team).child(id).child("thirty").setValue(Integer.parseInt(thirty.getText().toString()));
                reference.child("Players").child(team).child(id).child("fifty").setValue(Integer.parseInt(fifty.getText().toString()));
                reference.child("Players").child(team).child(id).child("hundred").setValue(Integer.parseInt(hundred.getText().toString()));
                reference.child("Players").child(team).child(id).child("twickets").setValue(Integer.parseInt(tWicket.getText().toString()));
                reference.child("Players").child(team).child(id).child("cwickets").setValue(Integer.parseInt(cWicket.getText().toString()));
                reference.child("Players").child(team).child(id).child("bestBScore").setValue(Integer.parseInt(bestBowlScore.getText().toString()));
                reference.child("Players").child(team).child(id).child("bestBWicket").setValue(Integer.parseInt(bestBowlWicket.getText().toString()));
                reference.child("Players").child(team).child(id).child("totalScore").setValue(Integer.parseInt(totalScore.getText().toString()));
                reference.child("Players").child(team).child(id).child("currentPSLScore").setValue(Integer.parseInt(currentPslScore.getText().toString()));



//                reference.child("Players").child(team).child(push)
//                        .setValue(addPlayerAttr);
                if (!type.equals("Bowler")) {
                    reference.child("Runs").child(id).child("name").setValue(name);
                    reference.child("Runs").child(id).child("team").setValue(team);
                    if (currentPslScore.getText().toString().isEmpty()) {
                        reference.child("Runs").child(id).child("score").setValue(0);
                    } else
                        reference.child("Runs").child(id).child("score").setValue(Integer.valueOf(currentPslScore.getText().toString()));

                }
                if (!type.equals("Batsman")) {
                    reference.child("Wickets").child(id).child("name").setValue(name);
                    reference.child("Wickets").child(id).child("team").setValue(team);
                    if (cWicket.getText().toString().isEmpty()) {
                        reference.child("Wickets").child(id).child("wickets").setValue(0);
                    } else
                        reference.child("Wickets").child(id).child("wickets").setValue(Integer.parseInt(cWicket.getText().toString()));

                }
                pd.dismiss();
                Toast.makeText(getApplicationContext(), "Player Updated", Toast.LENGTH_LONG).show();
                startActivity(new Intent(UpdatePlayer.this , PlayersList.class));


            }
        });
    }

    @Override
    int getContentViewId() {
        return R.layout.activity_update_player;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.playerlist;
    }
}
