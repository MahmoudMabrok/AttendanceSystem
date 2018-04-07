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

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class Student  extends Activity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student);

        Button btnScan = (Button)findViewById(R.id.btnScan);
        final Activity ACTIVITY = this;

        TextView   tv_hint = (TextView)findViewById(R.id.labelText);
        Intent intent = getIntent();
        int id   ;
        id = intent.getIntExtra("id" , -1 ) ;
        tv_hint.setText("Welcome Student No."+id );

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
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(intentResult != null){
            //You should send his id to the server here instead of that toast
            if(intentResult.getContents()==null){
                Toast.makeText(this,"You cancelled the scanning",Toast.LENGTH_LONG).show();

            }
            else{
                Toast.makeText(this, intentResult.getContents(), Toast.LENGTH_LONG).show();
            }

        }
        else{
            super.onActivityResult(requestCode,resultCode,data);
        }
    }


}
