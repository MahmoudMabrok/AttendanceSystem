package com.anonymous.attendandsystem;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;

public class Student extends Activity {

    int id;
    long status;
    final int ATTENDANCE_CODE = 0000;
    String contentQr;
    DBHelper helper;
    Button show ;
    ArrayList<String> data = new ArrayList<>();
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student);

        //Background gradient animation
        /*LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layout);
        AnimationDrawable animationDrawable = (AnimationDrawable) linearLayout.getBackground();
        animationDrawable.setEnterFadeDuration(4000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();*/

        ImageButton btnScan = (ImageButton) findViewById(R.id.btnScan);
        final Activity ACTIVITY = this;
        helper = new DBHelper(this);
        TextView tv_hint = (TextView) findViewById(R.id.labelText);
        Intent intent = getIntent();
        id = intent.getIntExtra("id", -1);
        tv_hint.setText("Welcome Student No." + id);

        // Write a message to the database
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("" + id);

        btnScan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                IntentIntegrator intentIntegrator = new IntentIntegrator(ACTIVITY);
                intentIntegrator.setDesiredBarcodeFormats(intentIntegrator.QR_CODE_TYPES);
                intentIntegrator.setPrompt("SCAN");
                intentIntegrator.setCameraId(0);
                intentIntegrator.setBeepEnabled(false);
                intentIntegrator.setBarcodeImageEnabled(false);
                intentIntegrator.initiateScan();
            }
        });
        show  = (Button)findViewById(R.id.showAll) ;

        // show all recorded code that are ready to send to database
        show.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                id++;
                //first clear data
                data.clear();
                //then add all from db
                data.addAll(helper.getAllCodes());
                //show every code in data
                for (int i = 0; i < data.size(); i++) {
                    Toast.makeText(ACTIVITY, "* " + data.get(i), Toast.LENGTH_SHORT).show();
                }
                //add child for every student , each one has child subjects , that have all attendances ;
                //myRef.child("subjects").setValue(data); //// TODO: 4/10/2018 ddd
                myRef.setValue(data);
                Toast.makeText(ACTIVITY, "Added", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Student.this, ShowData.class);
                startActivity(i);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (intentResult != null) {
            //You should send his id to the server here instead of that toast
            if (intentResult.getContents() == null) {
                Toast.makeText(this, "You cancelled the scanning", Toast.LENGTH_LONG).show();
            }

            else if(Integer.parseInt(intentResult.getContents()) == ATTENDANCE_CODE ) {
                /*the value of the child Number of attendance should be read from the firebase first and incremented as the commented
                code below, it shouldn't be static-> 0*/
                myRef.child("Number of attendance").setValue(0);
                Toast.makeText(this, "You attended the class", Toast.LENGTH_LONG).show();




/*
This was suppose to make a read the value of each child which is their attendance counter and increment it
It gives a run time error
Will be checked later

                DatabaseReference numberOfAttendance = myRef.child(String.valueOf(id)).child("Number of attendance");
                numberOfAttendance.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String numberOfAttendanceCounter = dataSnapshot.getValue(String.class);
                        if(numberOfAttendanceCounter=="null")
                            numberOfAttendanceCounter="0";
                        integerNumberOfAttendanceCounter=Integer.parseInt(numberOfAttendanceCounter)+1;
                        myRef.child(String.valueOf(id)).child("Number of attendance").setValue(integerNumberOfAttendanceCounter);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                Toast.makeText(this, "You attended the class", Toast.LENGTH_LONG).show();
*/




                /*contentQr = intentResult.getContents();
                status = helper.addCode(id, contentQr);

                if (status != -1)
                    Toast.makeText(this, contentQr, Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(this, "Error . you attend it", Toast.LENGTH_LONG).show();*/
            }
            else
                Toast.makeText(this, "Wrong QR code!", Toast.LENGTH_LONG).show();

        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
