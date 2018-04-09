package com.anonymous.attendandsystem;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;
/**
 * Created by motamed on 3/6/2018.
 */

public class Student  extends Activity  {

    WifiManager wifi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student);

        Button btnJoin = (Button)findViewById(R.id.btnJoin);

        wifi = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        btnJoin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (wifi.isWifiEnabled() == false) {
                    Toast.makeText(getApplicationContext(), "Connecting...", Toast.LENGTH_LONG).show();
                    wifi.setWifiEnabled(true);
                    }
                Toast.makeText(Student.this, wifi.getConnectionInfo().getMacAddress().toString(), Toast.LENGTH_SHORT).show();

            }
            });
        


        TextView   tv_hint = (TextView)findViewById(R.id.labelText);
        Intent intent = getIntent();
        int id   ;
        id = intent.getIntExtra("id" , -1 ) ;
        tv_hint.setText("Welcome Student No."+id );


    }


}
