package com.anonymous.attendandsystem;

/**
 * Created by motamed on 3/5/2018.
 */


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

import static android.R.id.message;


public class LogIn extends Activity {

    private  int id  = -1 ;

    EditText input ;
    EditText userName ;
    ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        input = (EditText)findViewById(R.id.password) ;
        userName = (EditText)findViewById(R.id.userName) ;
        getData();
        setContentView(R.layout.log_in);
        if (id > -1)
           startNewActivity();

    }

    public void saveData (View view ){
        SharedPreferences shared = getSharedPreferences("UserData" , Context.MODE_PRIVATE) ;
        SharedPreferences.Editor editor = shared.edit() ;
        try {
            if(input != null ) {
                editor.putString("name", userName.getText().toString());
                editor.putInt("id", Integer.parseInt(input.getText().toString()));
                editor.apply();
            }
        }catch (NullPointerException e ){
            Toast.makeText(this , "Error " ,Toast.LENGTH_LONG ).show();
        }
        catch (NumberFormatException s){
            Toast.makeText(this , "Error " ,Toast.LENGTH_LONG ).show();
        }

    }
    public void getData (){
       try {
           SharedPreferences shared = getSharedPreferences("UserData", Context.MODE_PRIVATE);
           id = shared.getInt("id", -1);

       }catch (Exception e){
           id = -1 ;
       }
    }
    public void startNewActivity(){
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);

    }
}
