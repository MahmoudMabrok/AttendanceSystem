package com.mahmoudmabrok.keepinmind.data;

import com.mahmoudmabrok.keepinmind.data.WordsContract.WordEntry;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * Created by motamed on 3/27/2018.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Words.db";
    public static final int DATA_VERSION = 1;
    public static final String COMMA = ",";
    public static final String TYPE = " TEXT ";
    public static final String CREATE_COMMAND = "CREATE TABLE  IF NOT EXISTS " + Word.WORD_TABLE_NAME +
            "(" + Word.WORD_Column_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT  " + COMMA
            + Word.WORD_Column_WORD + TYPE + COMMA +
            Word.WORD_Column_MEAN + TYPE + COMMA +
            Word.WORD_Column_CATEGORY + TYPE + COMMA + Word.WORD_Column_DESCRIPTION + TYPE + ");";

    public static final String DELETE_COMMAND = "DROP TABLE IF EXISTS " + Word.WORD_TABLE_NAME;
    public static final String Cataloge_CREATE = "CREATE TABLE  IF NOT EXISTS " + Cataloge.Cataloge_Table_NAME + "(" +
            Cataloge.Cataloge_Column_NAME + TYPE + " PRIMARY KEY );";
    public static final String DELETE_CATALOGE = "DROP TABLE IF EXISTS " + Cataloge.Cataloge_Column_NAME + ";";
    public static long count = 0;
    Context context;
    private boolean isCatalog;
    private String[] project = new String[]{Word.WORD_Column_ID, Word.WORD_Column_WORD,
            Word.WORD_Column_MEAN, Word.WORD_Column_CATEGORY};

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATA_VERSION);
        this.context = context;
    }

    /*
    public DBHelper(Context context , String name ) {
        super(context, DATABASE_NAME, null, DATA_VERSION);
        this.context = context ;
        isCatalog = true ;
    }*/

    @Override

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_COMMAND);
        db.execSQL(Cataloge_CREATE);

        //add prepared english
        for (Word w : AllData.getListOfEnglishWords()) {
            insertWord(db, w);
        }
        //add prepared linux
        for (Word w : AllData.getListOfLinux()) {
            insertWord(db, w);
        }
        //add available categories
        for (String cat : AllData.getListOfCat()) {
            insertCataloge(db, cat);
        }

    }

    /**
     * upgrade to the new schema version
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_COMMAND);
        db.execSQL(DELETE_CATALOGE);

        onCreate(db);

    }

    public long insertCataloge(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Cataloge.Cataloge_Column_NAME, name);

        return db.insert(Cataloge.Cataloge_Table_NAME, null, values);
    }

    public long insertCataloge(SQLiteDatabase db, String name) {

        ContentValues values = new ContentValues();
        values.put(Cataloge.Cataloge_Column_NAME, name);

        return db.insert(Cataloge.Cataloge_Table_NAME, null, values);
    }

    public ArrayList<String> getAllCatalog() {
        ArrayList<String> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + Cataloge.Cataloge_Table_NAME, null);

        while (c.moveToNext()) {
            list.add(c.getString(0));
        }
        c.close();
        return list;
    }

    public long insertWord(Word word) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues con = new ContentValues();
        con.put(Word.WORD_Column_WORD, word.getWord());
        con.put(Word.WORD_Column_MEAN, word.getMean());
        con.put(Word.WORD_Column_CATEGORY, word.getCategory());
        con.put(Word.WORD_Column_DESCRIPTION, word.getDescription());

        //  Toast.makeText(context, "Add from Word ", Toast.LENGTH_LONG).show();
        count++;
        return db.insert(Word.WORD_TABLE_NAME, null, con);
    }

    public long insertWord(SQLiteDatabase db, Word word) {

        ContentValues con = new ContentValues();
        con.put(Word.WORD_Column_WORD, word.getWord());
        con.put(Word.WORD_Column_MEAN, word.getMean());
        con.put(Word.WORD_Column_CATEGORY, word.getCategory());
        con.put(Word.WORD_Column_DESCRIPTION, word.getDescription());
        return db.insert(Word.WORD_TABLE_NAME, null, con);
    }

    public ArrayList<Word> getAllData() {
        ArrayList<Word> list = new ArrayList<>();
        Word word;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT *  FROM " + Word.WORD_TABLE_NAME + " order by  " +
                Word.WORD_Column_CATEGORY + " desc ; ", null); //get data organized vt cataloge
        if (c == null)
            return list;

        while (c.moveToNext()) {
            word = new Word();
            word.setId((c.getInt(0)));
            word.setWord(c.getString(1));
            word.setMean(c.getString(2));
            word.setCategory(c.getString(3));
            word.setDescription(c.getString(4));
            list.add(word);
        }
        c.close();
        return list;
    }

    public ArrayList<String> getAllWords() {
        ArrayList<String> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT *  FROM " + Word.WORD_TABLE_NAME + " order by  " +
                Word.WORD_Column_CATEGORY + " desc ; ", null); //get data organized vt cataloge
        if (c == null)
            return list;
        while (c.moveToNext()) {
            list.add(c.getString(1));
        }
        c.close();
        return list;
    }


    public ArrayList<Word> getSpecificWord(String word) {
        ArrayList<Word> list = new ArrayList<>();
        Word wordObj;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + Word.WORD_TABLE_NAME + " WHERE word=?", new String[]{word});
        if (c == null)
            return list;
        while (c.moveToNext()) {
            wordObj = new Word();
            wordObj.setId((c.getInt(0)));
            wordObj.setWord(c.getString(1));
            wordObj.setMean(c.getString(2));
            wordObj.setCategory(c.getString(3));
            wordObj.setDescription(c.getString(4));
            list.add(wordObj);
        }
        c.close();
        return list;
    }

    public ArrayList<Word> getWordByCat(String word, String cat) {
        ArrayList<Word> list = new ArrayList<>();
        Word wordObj;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c;
        if (word.trim().length() > 0)
            c = db.rawQuery("SELECT * FROM " + Word.WORD_TABLE_NAME +
                " WHERE word=? and " + Word.WORD_Column_CATEGORY + "=?", new String[]{word, cat});
        else //case to get all row of specific category
            c = db.rawQuery("SELECT * FROM " + Word.WORD_TABLE_NAME +
                    " WHERE " + Word.WORD_Column_CATEGORY + "=?", new String[]{cat});

        if (c == null)
            return list;

        while (c.moveToNext()) {
            wordObj = new Word();
            wordObj.setId((c.getInt(0)));
            wordObj.setWord(c.getString(1));
            wordObj.setMean(c.getString(2));
            wordObj.setCategory(c.getString(3));
            wordObj.setDescription(c.getString(4));
            list.add(wordObj);
        }
        c.close();
        return list;
    }
    /**
     * @param word obj for word to update with it
     * @return n. rows affected
     */
    public int update(Word word) {
        SQLiteDatabase db = this.getWritableDatabase();
        String select = Word.WORD_Column_ID + " = ?";
        String[] args = new String[]{String.valueOf(word.getId())};
        ContentValues values = new ContentValues();
        values.put(Word.WORD_Column_WORD, word.getWord());
        values.put(Word.WORD_Column_MEAN, word.getMean());
        values.put(Word.WORD_Column_CATEGORY, word.getCategory());
        values.put(Word.WORD_Column_DESCRIPTION, word.getDescription());
        int c = db.update(Word.WORD_TABLE_NAME, values, select, args);
        return c;
    }
    /**
     * @param id id of item to be deleted
     * @return n. row affected
     */
    public int delete(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String select = Word.WORD_Column_ID + "=?";
        String[] args = new String[]{String.valueOf(id)};
        int c = db.delete(Word.WORD_TABLE_NAME, select, args);
        return c;
    }

    public void trucateAllWord (){
    SQLiteDatabase db = this.getReadableDatabase();
    db.execSQL("DROP TABLE   " + Word.WORD_TABLE_NAME);
    db.execSQL(CREATE_COMMAND);

    }

}
