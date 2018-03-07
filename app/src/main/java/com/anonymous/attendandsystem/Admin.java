package com.anonymous.attendandsystem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by motamed on 3/6/2018.
 */

public class Admin  extends Activity  {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin);

      TextView   tv_hint = (TextView)findViewById(R.id.labelText);
        Intent intent = getIntent();
        int id   ;
        String name ;
        id = intent.getIntExtra("id" , -1 ) ;
        name = intent.getStringExtra("name");
        tv_hint.setText("id is " + id  + "name :: " + name );

    }
}
