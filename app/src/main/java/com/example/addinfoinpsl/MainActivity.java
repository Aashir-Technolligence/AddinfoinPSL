package com.example.addinfoinpsl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

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

import java.io.IOException;

public class MainActivity extends BaseActivity {
    ImageView profile;
    EditText name, age, inning,currentPslScore, totalScore, strike, bestScore, averageScore, thirty, fifty, hundred, tWicket, cWicket, bestBowlScore, bestBowlWicket;
    private Uri filePath;
    private Button addPlayer;
    Spinner teamSpinner, typeSpinner, handSpinner;
    int count = 0;
    String team, type, hand;
    ProgressDialog pd;
    StorageReference StorageRef;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        name = (EditText) findViewById(R.id.edtName);
        age = (EditText) findViewById(R.id.edtAge);
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
        teamSpinner = findViewById(R.id.spinnerTeam);
        typeSpinner = findViewById(R.id.spinnerType);
        handSpinner = findViewById(R.id.spinnerHand);
        profile = (ImageView) findViewById(R.id.playerImage);
        StorageRef = FirebaseStorage.getInstance().getReference();
        reference = FirebaseDatabase.getInstance().getReference();
        teamSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                team = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
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
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 2);
            }

        });
        pd = new ProgressDialog(this);
        pd.setMessage("Adding Player..... ");
        addPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!name.getText().toString().isEmpty() && !age.getText().toString().isEmpty()) {
                    if (count == 1) {
                        pd.show();
                        final String push = FirebaseDatabase.getInstance().getReference().child("Players").push().getKey();
                        StorageReference fileReference = StorageRef.child("images/players/" + push);
                        fileReference.putFile(filePath)
                                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                        //Toast.makeText(getApplicationContext(), "Uploaded", Toast.LENGTH_SHORT).show();
                                        if (filePath != null) {
                                            AddPlayerAttr addPlayerAttr = new AddPlayerAttr();
                                            addPlayerAttr.setId(push);
                                            Task<Uri> urlTask = taskSnapshot.getStorage().getDownloadUrl();
                                            while (!urlTask.isSuccessful()) ;
                                            Uri downloadUrl = urlTask.getResult();
                                            addPlayerAttr.setImage_url(downloadUrl.toString());
                                            addPlayerAttr.setName(name.getText().toString());
                                            addPlayerAttr.setAge(age.getText().toString());
                                            addPlayerAttr.setTeam(team);
                                            addPlayerAttr.setType(type);
                                            addPlayerAttr.setHand(hand);
                                            if (inning.getText().toString().isEmpty()) {
                                                addPlayerAttr.setInnings("0");
                                            } else
                                                addPlayerAttr.setInnings(inning.getText().toString());

                                            if (strike.getText().toString().isEmpty()) {
                                                addPlayerAttr.setStrike("0");
                                            } else
                                                addPlayerAttr.setStrike(strike.getText().toString());

                                            if (bestScore.getText().toString().isEmpty()) {
                                                addPlayerAttr.setBestScore(0);
                                            } else
                                                addPlayerAttr.setBestScore(Integer.parseInt(bestScore.getText().toString()));

                                            if (averageScore.getText().toString().isEmpty()) {
                                                addPlayerAttr.setAvgScore("0");
                                            } else
                                                addPlayerAttr.setAvgScore(averageScore.getText().toString());
                                            if (currentPslScore.getText().toString().isEmpty()) {
                                                addPlayerAttr.setCurrentPSLScore(0);
                                            } else
                                                addPlayerAttr.setCurrentPSLScore(Integer.parseInt(currentPslScore.getText().toString()));

                                            if (thirty.getText().toString().isEmpty()) {
                                                addPlayerAttr.setThirty(0);
                                            } else
                                                addPlayerAttr.setThirty(Integer.parseInt(thirty.getText().toString()));

                                            if (fifty.getText().toString().isEmpty()) {
                                                addPlayerAttr.setFifty(0);
                                            } else
                                                addPlayerAttr.setFifty(Integer.parseInt(fifty.getText().toString()));
                                            if (hundred.getText().toString().isEmpty()) {
                                                addPlayerAttr.setHundred(0);
                                            } else
                                                addPlayerAttr.setHundred(Integer.parseInt(hundred.getText().toString()));

                                            if (cWicket.getText().toString().isEmpty()) {
                                                addPlayerAttr.setCWickets(0);
                                            } else
                                                addPlayerAttr.setCWickets(Integer.parseInt(cWicket.getText().toString()));

                                            if (tWicket.getText().toString().isEmpty()) {
                                                addPlayerAttr.setTWickets(0);
                                            } else
                                                addPlayerAttr.setTWickets(Integer.parseInt(tWicket.getText().toString()));

                                            if (bestBowlScore.getText().toString().isEmpty()) {
                                                addPlayerAttr.setBestBScore(0);
                                            } else
                                                addPlayerAttr.setBestBScore(Integer.parseInt(bestBowlScore.getText().toString()));

                                            if (bestBowlWicket.getText().toString().isEmpty()) {
                                                addPlayerAttr.setBestBWicket(0);
                                            } else
                                                addPlayerAttr.setBestBWicket(Integer.parseInt(bestBowlWicket.getText().toString()));

                                            if (totalScore.getText().toString().isEmpty()) {
                                                addPlayerAttr.setTotalScore(0);
                                            } else
                                                addPlayerAttr.setTotalScore(Integer.parseInt(totalScore.getText().toString()));


                                            reference.child("Players").child(team).child(push)
                                                    .setValue(addPlayerAttr);
                                            if (!type.equals("Bowler")) {
                                                reference.child("Runs").child(push).child("name").setValue(name.getText().toString());
                                                reference.child("Runs").child(push).child("team").setValue(team);
                                                if (currentPslScore.getText().toString().isEmpty()) {
                                                    reference.child("Runs").child(push).child("score").setValue(0);
                                                } else
                                                    reference.child("Runs").child(push).child("score").setValue(Integer.valueOf(currentPslScore.getText().toString()));

                                            }
                                            if (!type.equals("Batsman")) {
                                                reference.child("Wickets").child(push).child("name").setValue(name.getText().toString());
                                                reference.child("Wickets").child(push).child("team").setValue(team);
                                                if (cWicket.getText().toString().isEmpty()) {
                                                    reference.child("Wickets").child(push).child("wickets").setValue(0);
                                                } else
                                                    reference.child("Wickets").child(push).child("wickets").setValue(Integer.parseInt(cWicket.getText().toString()));

                                            }
                                            pd.dismiss();
                                            Toast.makeText(getApplicationContext(), "Player Added", Toast.LENGTH_LONG).show();

                                        }
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(getApplicationContext(), "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });

                    } else {
                        Toast.makeText(getApplicationContext(), "Please upload an image.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Please enter all Information", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    @Override
    int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.addplayer;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == requestCode && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getApplication().getApplicationContext().getContentResolver(), filePath);
                profile.setImageBitmap(bitmap);
                count = 1;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
