package com.anonymous.attendandsystem;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

    TextView tv_hint ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_hint = (TextView)findViewById(R.id.labelText);
        Intent intent = getIntent();
        tv_hint.setText(" "+intent.getIntExtra("id" , -1 ));

    }

}
