package com.anonymous.attendandsystem;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by motamed on 3/6/2018.
 */

public class Admin  extends Activity  {

    WifiManager  wifiManager ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin);

        wifiManager = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if ( !wifiManager.isWifiEnabled() ){
            wifiManager.setWifiEnabled(true) ;

        }
        for ( ScanResult scanResult :wifiManager.getScanResults() ){
            Toast.makeText(this,scanResult.BSSID.toString(), Toast.LENGTH_SHORT).show();
        }
        TextView   tv_hint = (TextView)findViewById(R.id.labelText);
        Intent intent = getIntent();
        int id   ;
        id = intent.getIntExtra("id" , -1 ) ;
        tv_hint.setText("Welcome Admin");

    }
    public void list (View view){
        for ( ScanResult scanResult :wifiManager.getScanResults() ){
            Toast.makeText(this,scanResult.BSSID.toString(), Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(this, "ss" + wifiManager.getConnectionInfo().getSSID(), Toast.LENGTH_SHORT).show();
    }
}
