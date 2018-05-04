package com.anonymous.attendandsystem;

/**
 * Created by motamed on 3/5/2018.
 */


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import static android.R.id.message;


public class LogIn extends Activity {
    private  int id  = -1 ;
    final int ADMIN_ID =0000000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);
        getData();
        Log.v("LogIn"  ,"before if  " + id) ;
        if (id != -1) {
            startNewActivity();
        }
        Log.v("LogIn"  ,"After if " + id) ;
    }

    public void saveData (View view ){
        SharedPreferences shared = getSharedPreferences("UserData" , Context.MODE_PRIVATE) ;
        SharedPreferences.Editor editor = shared.edit() ;
        EditText input = (EditText)findViewById(R.id.password) ;


        int n  = 0 ;
        try {
            if (input.getText().toString().length() < 7) {
                Toast.makeText(this , "Enter A Valid Academic Number (7 Numbers)" ,Toast.LENGTH_LONG ).show();
            }
            else {
                n = Integer.parseInt(input.getText().toString());
                id = n;
                editor.putInt("ID", n);
                editor.apply();
                //start activity
                startNewActivity();
            }

        }catch (NullPointerException e ){
            Toast.makeText(this , "Enter A Valid Academic Number (7 Numbers)" ,Toast.LENGTH_LONG ).show();
        }
        catch (NumberFormatException s){
            Toast.makeText(this , "Enter A Valid Academic Number (7 Numbers)" ,Toast.LENGTH_LONG ).show();
        }
        // Toast.makeText(this , "inSave " ,Toast.LENGTH_LONG ).show();
    }
    public void getData (){
        try {
            Log.v("LogIn"  ," from get id is " + id) ;
            SharedPreferences shared = getSharedPreferences("UserData", Context.MODE_PRIVATE);
            id = shared.getInt("ID", -1);
            Log.v("LogIn"  ," after get id is " + id) ;
        }catch (Exception e){
            id = -1 ;
        }
    }
    public void startNewActivity(){
        Intent intent ;
        if (id == ADMIN_ID) {
            intent = new Intent(this, ShowData.class);
        } else {
            intent = new Intent(this, Student.class);
            intent.putExtra("id", id);
        }
        startActivity(intent);
        finish();
    }
}
