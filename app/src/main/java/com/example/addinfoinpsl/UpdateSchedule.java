package com.example.addinfoinpsl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateSchedule extends BaseActivity {
    Spinner team1Spinner, team2Spinner;
    String team1, team2;
    ProgressDialog pd;
    CalendarView calendarView;
    EditText time;
    DatabaseReference reference;
    Button addSchedule;
    String curDate = "";
    long i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_update_schedule);
        Intent i = getIntent();
        final String id = i.getStringExtra("id");
        pd = new ProgressDialog(this);
        pd.setMessage("Updating Schedule..... ");
        reference = FirebaseDatabase.getInstance().getReference();
        team1Spinner = findViewById(R.id.spinnerTeam1);
        team2Spinner = findViewById(R.id.spinnerTeam2);
        time = findViewById(R.id.edtTime);
        calendarView = findViewById(R.id.calender);
        addSchedule = findViewById(R.id.btnAddSchedule);
        reference.child("Schedule").child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                time.setText(dataSnapshot.child("time").getValue().toString());
                //     calendarView.setDate(Long.parseLong(dataSnapshot.child("date").getValue().toString()));

                if (dataSnapshot.child("teamOne").getValue().toString().equals("Lahore Qalandars"))
                    team1Spinner.setSelection(0);
                if (dataSnapshot.child("teamOne").getValue().toString().equals("Karachi Kings"))
                    team1Spinner.setSelection(1);
                if (dataSnapshot.child("teamOne").getValue().toString().equals("Islamabad United"))
                    team1Spinner.setSelection(2);
                if (dataSnapshot.child("teamOne").getValue().toString().equals("Quetta Gladiators"))
                    team1Spinner.setSelection(3);
                if (dataSnapshot.child("teamOne").getValue().toString().equals("Multan Sultan"))
                    team1Spinner.setSelection(4);
                if (dataSnapshot.child("teamOne").getValue().toString().equals("Peshawar Zalmi"))
                    team1Spinner.setSelection(5);
                if (dataSnapshot.child("teamTwo").getValue().toString().equals("Lahore Qalandars"))
                    team2Spinner.setSelection(0);
                if (dataSnapshot.child("teamTwo").getValue().toString().equals("Karachi Kings"))
                    team2Spinner.setSelection(1);
                if (dataSnapshot.child("teamTwo").getValue().toString().equals("Islamabad United"))
                    team2Spinner.setSelection(2);
                if (dataSnapshot.child("teamTwo").getValue().toString().equals("Quetta Gladiators"))
                    team2Spinner.setSelection(3);
                if (dataSnapshot.child("teamTwo").getValue().toString().equals("Multan Sultan"))
                    team2Spinner.setSelection(4);
                if (dataSnapshot.child("teamTwo").getValue().toString().equals("Peshawar Zalmi"))
                    team2Spinner.setSelection(5);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                curDate = String.valueOf(dayOfMonth + "/" + month + 1 + "/" + year);
                Toast.makeText(getApplicationContext(), curDate, Toast.LENGTH_LONG).show();
            }
        });
        team1Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                team1 = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        team2Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                team2 = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        Toast.makeText(getApplicationContext(), team1, Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(), team2, Toast.LENGTH_LONG).show();
        addSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (team1.equals(team2)) {
                    Toast.makeText(getApplicationContext(), "Teams must be different!", Toast.LENGTH_LONG).show();
                } else {
                    if (!time.getText().toString().isEmpty() && !curDate.equals("")) {
                        pd.show();
                        ScheduleAttr addSchedule1 = new ScheduleAttr();
                        addSchedule1.setId(id);
                        addSchedule1.setTeamOne(team1);
                        addSchedule1.setTeamTwo(team2);
                        addSchedule1.setTime(time.getText().toString());
                        addSchedule1.setDate(curDate);
                        addSchedule1.setStatus("Not Live");

                        reference.child("Schedule").child(id).setValue(addSchedule1);
                        Toast.makeText(getApplicationContext(), "Schedule updated.", Toast.LENGTH_LONG).show();
                        pd.dismiss();
                        startActivity(new Intent(UpdateSchedule.this, ScheduleList.class));
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Please insert all information.", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    @Override
    int getContentViewId() {
        return R.layout.activity_update_schedule;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.schedulelist;
    }
}
