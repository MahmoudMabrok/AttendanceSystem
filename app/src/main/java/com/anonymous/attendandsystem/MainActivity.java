package com.anonymous.attendandsystem;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    private  int id =0 ;
    Button btn_enter ;
    TextView tv_hint ;
    EditText input ;
   ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




/*

        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(getString(R.string.saved_high_score_key), newHighScore);
        editor.commit();*/



        btn_enter = (Button)findViewById(R.id.btnEnter);
        tv_hint = (TextView)findViewById(R.id.labelText);
        input = (EditText)findViewById(R.id.input);

        btn_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String in = input.getText().toString() ;

            }
        });

    }
}
