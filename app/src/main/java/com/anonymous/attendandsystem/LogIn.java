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
            //name = userName.getText().toString() ;
            //editor.putString("name",name);
            n = Integer.parseInt(input.getText().toString()) ;
            id = n;
            Log.v("LogIn", "n " + n);
            editor.putInt("ID", n);
            editor.apply();

            //start activity
                startNewActivity();

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
            //name = shared.getString("name" , "") ;

            Log.v("LogIn"  ," after get id is " + id) ;
        }catch (Exception e){
            id = -1 ;
            //name = "" ;
        }
    }
    public void startNewActivity(){
        Intent intent ;
        intent = new Intent(this ,Student.class) ;
        intent.putExtra("id", id);
        //intent.putExtra("name", name );
        startActivity(intent);
        finish();
    }
}
