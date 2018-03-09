package com.anonymous.attendandsystem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by motamed on 3/6/2018.
 */

public class Student  extends Activity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student);


        TextView   tv_hint = (TextView)findViewById(R.id.labelText);
        Intent intent = getIntent();
        int id   ;
        id = intent.getIntExtra("id" , -1 ) ;
        tv_hint.setText("Welcome Student No."+id );

    }
}
