package com.anonymous.attendandsystem;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.InputMismatchException;

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

        tv_hint = (TextView)findViewById(R.id.labelText);
        input = (EditText)findViewById(R.id.input);


    }

    public void saveId (View view ){
        SharedPreferences shared = getSharedPreferences("id" , Context.MODE_PRIVATE) ;
        SharedPreferences.Editor editor = shared.edit() ;
        try {
            editor.putInt("id", Integer.parseInt(input.getText().toString()));
            editor.apply();
        }catch (NumberFormatException s){
            Toast.makeText(this , "Error " ,Toast.LENGTH_LONG ).show();
        }

    }
    public void getId (View view){
        SharedPreferences shared = getSharedPreferences("id" , Context.MODE_PRIVATE) ;
        id = shared.getInt("id" , 0) ;
        tv_hint.setText(""+id);

    }
}
