package com.example.addinfoinpsl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PointsTable extends BaseActivity {
    DatabaseReference dref = FirebaseDatabase.getInstance().getReference();
    EditText b1, b2, b3, b4, b5, b6, b7, b8, b9, b10;
    EditText b11, b12, b13, b14, b15, b16, b17, b18, b19, b20;
    EditText b21, b22, b23, b24, b25, b26, b27, b28, b29, b30;
    EditText b31, b32, b33, b34, b35, b36;
    Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_points_table);
        update = (Button) findViewById(R.id.btnUpdate);
        b1 = (EditText) findViewById(R.id.edt1);
        b2 = (EditText) findViewById(R.id.edt2);
        b3 = (EditText) findViewById(R.id.edt3);
        b4 = (EditText) findViewById(R.id.edt4);
        b5 = (EditText) findViewById(R.id.edt5);
        b6 = (EditText) findViewById(R.id.edt6);
        b7 = (EditText) findViewById(R.id.edt7);
        b8 = (EditText) findViewById(R.id.edt8);
        b9 = (EditText) findViewById(R.id.edt9);
        b10 = (EditText) findViewById(R.id.edt10);
        b11 = (EditText) findViewById(R.id.edt11);
        b12 = (EditText) findViewById(R.id.edt12);
        b13 = (EditText) findViewById(R.id.edt13);
        b14 = (EditText) findViewById(R.id.edt14);
        b15 = (EditText) findViewById(R.id.edt15);
        b16 = (EditText) findViewById(R.id.edt16);
        b17 = (EditText) findViewById(R.id.edt17);
        b18 = (EditText) findViewById(R.id.edt18);
        b19 = (EditText) findViewById(R.id.edt19);
        b20 = (EditText) findViewById(R.id.edt20);
        b21 = (EditText) findViewById(R.id.edt21);
        b22 = (EditText) findViewById(R.id.edt22);
        b23 = (EditText) findViewById(R.id.edt23);
        b24 = (EditText) findViewById(R.id.edt24);
        b25 = (EditText) findViewById(R.id.edt25);
        b26 = (EditText) findViewById(R.id.edt26);
        b27 = (EditText) findViewById(R.id.edt27);
        b28 = (EditText) findViewById(R.id.edt28);
        b29 = (EditText) findViewById(R.id.edt29);
        b30 = (EditText) findViewById(R.id.edt30);
        b31 = (EditText) findViewById(R.id.edt31);
        b32 = (EditText) findViewById(R.id.edt32);
        b33 = (EditText) findViewById(R.id.edt33);
        b34 = (EditText) findViewById(R.id.edt34);
        b35 = (EditText) findViewById(R.id.edt35);
        b36 = (EditText) findViewById(R.id.edt36);
        dref.child("Points").child("Quetta Gladiators").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                b1.setText(dataSnapshot.child("Total").getValue().toString());
                b2.setText(dataSnapshot.child("Win").getValue().toString());
                b3.setText(dataSnapshot.child("Lose").getValue().toString());
                b4.setText(dataSnapshot.child("Draw").getValue().toString());
                b5.setText(dataSnapshot.child("Points").getValue().toString());
                b6.setText(dataSnapshot.child("NRR").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        dref.child("Points").child("Peshawar Zalmi").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                b7.setText(dataSnapshot.child("Total").getValue().toString());
                b8.setText(dataSnapshot.child("Win").getValue().toString());
                b9.setText(dataSnapshot.child("Lose").getValue().toString());
                b10.setText(dataSnapshot.child("Draw").getValue().toString());
                b11.setText(dataSnapshot.child("Points").getValue().toString());
                b12.setText(dataSnapshot.child("NRR").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        dref.child("Points").child("Islamabad United").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                b13.setText(dataSnapshot.child("Total").getValue().toString());
                b14.setText(dataSnapshot.child("Win").getValue().toString());
                b15.setText(dataSnapshot.child("Lose").getValue().toString());
                b16.setText(dataSnapshot.child("Draw").getValue().toString());
                b17.setText(dataSnapshot.child("Points").getValue().toString());
                b18.setText(dataSnapshot.child("NRR").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        dref.child("Points").child("Lahore Qalandars").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                b19.setText(dataSnapshot.child("Total").getValue().toString());
                b20.setText(dataSnapshot.child("Win").getValue().toString());
                b21.setText(dataSnapshot.child("Lose").getValue().toString());
                b22.setText(dataSnapshot.child("Draw").getValue().toString());
                b23.setText(dataSnapshot.child("Points").getValue().toString());
                b24.setText(dataSnapshot.child("NRR").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        dref.child("Points").child("Karachi Kings").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                b25.setText(dataSnapshot.child("Total").getValue().toString());
                b26.setText(dataSnapshot.child("Win").getValue().toString());
                b27.setText(dataSnapshot.child("Lose").getValue().toString());
                b28.setText(dataSnapshot.child("Draw").getValue().toString());
                b29.setText(dataSnapshot.child("Points").getValue().toString());
                b30.setText(dataSnapshot.child("NRR").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        dref.child("Points").child("Multan Sultan").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                b31.setText(dataSnapshot.child("Total").getValue().toString());
                b32.setText(dataSnapshot.child("Win").getValue().toString());
                b33.setText(dataSnapshot.child("Lose").getValue().toString());
                b34.setText(dataSnapshot.child("Draw").getValue().toString());
                b35.setText(dataSnapshot.child("Points").getValue().toString());
                b36.setText(dataSnapshot.child("NRR").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dref.child("Points").child("Quetta Gladiators").child("Total").setValue(b1.getText().toString());
                dref.child("Points").child("Quetta Gladiators").child("Win").setValue(b2.getText().toString());
                dref.child("Points").child("Quetta Gladiators").child("Lose").setValue(b3.getText().toString());
                dref.child("Points").child("Quetta Gladiators").child("Draw").setValue(b4.getText().toString());
                dref.child("Points").child("Quetta Gladiators").child("Points").setValue(b5.getText().toString());
                dref.child("Points").child("Quetta Gladiators").child("NRR").setValue(b6.getText().toString());
                dref.child("Points").child("Peshawar Zalmi").child("Total").setValue(b7.getText().toString());
                dref.child("Points").child("Peshawar Zalmi").child("Win").setValue(b8.getText().toString());
                dref.child("Points").child("Peshawar Zalmi").child("Lose").setValue(b9.getText().toString());
                dref.child("Points").child("Peshawar Zalmi").child("Draw").setValue(b10.getText().toString());
                dref.child("Points").child("Peshawar Zalmi").child("Points").setValue(b11.getText().toString());
                dref.child("Points").child("Peshawar Zalmi").child("NRR").setValue(b12.getText().toString());
                dref.child("Points").child("Islamabad United").child("Total").setValue(b13.getText().toString());
                dref.child("Points").child("Islamabad United").child("Win").setValue(b14.getText().toString());
                dref.child("Points").child("Islamabad United").child("Lose").setValue(b15.getText().toString());
                dref.child("Points").child("Islamabad United").child("Draw").setValue(b16.getText().toString());
                dref.child("Points").child("Islamabad United").child("Points").setValue(b17.getText().toString());
                dref.child("Points").child("Islamabad United").child("NRR").setValue(b18.getText().toString());
                dref.child("Points").child("Lahore Qalandars").child("Total").setValue(b19.getText().toString());
                dref.child("Points").child("Lahore Qalandars").child("Win").setValue(b20.getText().toString());
                dref.child("Points").child("Lahore Qalandars").child("Lose").setValue(b21.getText().toString());
                dref.child("Points").child("Lahore Qalandars").child("Draw").setValue(b22.getText().toString());
                dref.child("Points").child("Lahore Qalandars").child("Points").setValue(b23.getText().toString());
                dref.child("Points").child("Lahore Qalandars").child("NRR").setValue(b24.getText().toString());
                dref.child("Points").child("Karachi Kings").child("Total").setValue(b25.getText().toString());
                dref.child("Points").child("Karachi Kings").child("Win").setValue(b26.getText().toString());
                dref.child("Points").child("Karachi Kings").child("Lose").setValue(b27.getText().toString());
                dref.child("Points").child("Karachi Kings").child("Draw").setValue(b28.getText().toString());
                dref.child("Points").child("Karachi Kings").child("Points").setValue(b29.getText().toString());
                dref.child("Points").child("Karachi Kings").child("NRR").setValue(b30.getText().toString());
                dref.child("Points").child("Multan Sultan").child("Total").setValue(b31.getText().toString());
                dref.child("Points").child("Multan Sultan").child("Win").setValue(b32.getText().toString());
                dref.child("Points").child("Multan Sultan").child("Lose").setValue(b33.getText().toString());
                dref.child("Points").child("Multan Sultan").child("Draw").setValue(b34.getText().toString());
                dref.child("Points").child("Multan Sultan").child("Points").setValue(b35.getText().toString());
                dref.child("Points").child("Multan Sultan").child("NRR").setValue(b36.getText().toString());
                Toast.makeText(getApplicationContext(), "Timetable Updated", Toast.LENGTH_LONG).show();

            }
        });

    }

    @Override
    int getContentViewId() {
        return R.layout.activity_points_table;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.pointsTable;
    }
}
