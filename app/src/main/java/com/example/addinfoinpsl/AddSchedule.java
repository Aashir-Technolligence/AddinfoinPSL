package com.example.addinfoinpsl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class AddSchedule extends BaseActivity {
    Spinner team1Spinner, team2Spinner;
    String team1, team2;
    ProgressDialog pd;
    CalendarView calendarView;
    EditText time;
    DatabaseReference reference;
    Button addSchedule;
    String curDate="";
    long i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_add_schedule);
        pd = new ProgressDialog(this);
        pd.setMessage("Adding Schedule..... ");
        reference = FirebaseDatabase.getInstance().getReference();
        team1Spinner = findViewById(R.id.spinnerTeam1);
        team2Spinner = findViewById(R.id.spinnerTeam2);
        time = findViewById(R.id.edtTime);
        calendarView = findViewById(R.id.calender);
        addSchedule = findViewById(R.id.btnAddSchedule);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                curDate = String.valueOf(dayOfMonth+"/"+month+1+"/"+year) ;
                Toast.makeText(getApplicationContext() , curDate , Toast.LENGTH_LONG).show();
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

        addSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(team1.equals(team2)){
                    Toast.makeText(getApplicationContext(),"Teams must be different!", Toast.LENGTH_LONG).show();
                }
                else {
                    if (!time.getText().toString().isEmpty() && !curDate.equals("")) {
                        pd.show();
                        reference.child("Schedule").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    i = dataSnapshot.getChildrenCount();
                                    ScheduleAttr addSchedule1 = new ScheduleAttr();
                                    addSchedule1.setId(String.valueOf(i));
                                    addSchedule1.setTeamOne(team1);
                                    addSchedule1.setTeamTwo(team2);
                                    addSchedule1.setTime(time.getText().toString());
                                    addSchedule1.setDate(curDate);
                                    addSchedule1.setStatus("Not Live");

                                    reference.child("Schedule").child(String.valueOf(i)).setValue(addSchedule1);
                                    Toast.makeText(getApplicationContext(),"Schedule created.", Toast.LENGTH_LONG).show();
                                    pd.dismiss();
                                    recreate();
                                } else {
                                    i = 0;
                                    ScheduleAttr addSchedule1 = new ScheduleAttr();
                                    addSchedule1.setId(String.valueOf(i));
                                    addSchedule1.setTeamOne(team1);
                                    addSchedule1.setTeamTwo(team2);
                                    addSchedule1.setTime(time.getText().toString());
                                    addSchedule1.setDate(curDate);
                                    addSchedule1.setStatus("Not Live");

                                    reference.child("Schedule").child(String.valueOf(i)).setValue(addSchedule1);
                                    Toast.makeText(getApplicationContext(),"Schedule created.", Toast.LENGTH_LONG).show();
                                    pd.dismiss();
                                    recreate();
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Please insert all information.", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    @Override
    int getContentViewId() {
        return R.layout.activity_add_schedule;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.addschedule;
    }
}
