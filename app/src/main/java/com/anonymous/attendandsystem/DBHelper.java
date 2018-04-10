package com.anonymous.attendandsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * Created by motamed on 9/4/2018.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "attends.db";
    public static final int DATA_VERSION = 1;
    public static final String COMMA = ",";
    public static final String TYPE = " TEXT ";
    public static final String CREATE_COMMAND = "CREATE TABLE  IF NOT EXISTS " + Attend.ATTEND_TABLE_NAME +
            "(" + Attend.ATTEND_COLUMN_ID + " INTEGER  " + COMMA
            + Attend.ATTEND_COLUMN_CODE + TYPE + "PRIMARY KEY  );";

    public static final String DELETE_COMMAND = "DROP TABLE IF EXISTS " + Attend.ATTEND_TABLE_NAME;

    Context context;
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATA_VERSION);
        this.context = context;
    }

    @Override

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_COMMAND);
    }

    /**
     * upgrade to the new schema version
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_COMMAND);
        onCreate(db);

    }

    /**
     * add code of subject to database
     *
     * @param id
     * @param code
     * @return
     */
    public long addCode(int id, String code) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Attend.ATTEND_COLUMN_ID, id);
        values.put(Attend.ATTEND_COLUMN_CODE, code);
        return db.insert(Attend.ATTEND_TABLE_NAME, null, values);
    }

    public ArrayList<String> getAllCodes() {
        ArrayList<String> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + Attend.ATTEND_TABLE_NAME, null);
        while (c.moveToNext()) {
            list.add(c.getString(1));
        }
        c.close();

        return list;
    }

    public void trucateAllCodes (){
    SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("DROP TABLE   " + Attend.ATTEND_TABLE_NAME);
    db.execSQL(CREATE_COMMAND);
    }

}
