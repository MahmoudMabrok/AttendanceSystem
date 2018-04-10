package com.anonymous.attendandsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Iterator;

public class ShowData extends AppCompatActivity {

    DatabaseReference databaseReference;
    ArrayList<String> data = new ArrayList<>();
    ArrayAdapter<String> adapter;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);
        //make an adapter to be used with listview
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
        list = (ListView) findViewById(R.id.list);
        //attach adapter to listview
        list.setAdapter(adapter);

        //get reference from db
        databaseReference = FirebaseDatabase.getInstance().getReference();
        //listener to any change in db
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Iterator<DataSnapshot> i = dataSnapshot.getChildren().iterator();
                data.clear();
                while (i.hasNext()) {
                    data.add(i.next().getValue(String.class));
                }
                adapter.notifyDataSetChanged();
                Toast.makeText(ShowData.this, "child added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                //get iterator (same as for loop on strucured data)
                Iterator<DataSnapshot> i = dataSnapshot.getChildren().iterator();
                data.clear();
                while (i.hasNext()) {
                    //get value of key and add it to list to show in listview
                    data.add(i.next().getValue(String.class));
                }
                //redraw list as data changed
                adapter.notifyDataSetChanged();
                Toast.makeText(ShowData.this, "changed", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

                Iterator<DataSnapshot> i = dataSnapshot.getChildren().iterator();
                data.clear();
                while (i.hasNext()) {
                    data.add(i.next().getValue(String.class));
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
