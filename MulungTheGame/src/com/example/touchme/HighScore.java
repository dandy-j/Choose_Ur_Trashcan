package com.example.touchme;

import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import java.util.List;

public class HighScore extends ListActivity
{
    private static String HIGHSCORE = "HIGH_SCORE";
    private SQLiteDatabase sampleDB = null;
    private List<String> results = new ArrayList<String>();
    private Cursor cursor = null;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.highscore);

        try
        {
            sampleDB = openOrCreateDatabase("NAME", MODE_PRIVATE, null);
            createTable();
            insertData();
            lookupData();
            this.setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,results));
        }
        catch (SQLiteException se)
        {
            Log.e(getClass().getSimpleName(), "Could not create or Open the database");
        }
        finally
        {

            if (sampleDB != null)
                sampleDB.execSQL("DELETE FROM " + HIGHSCORE);
            sampleDB.close();
        }

    }

    /**
     * Create a table if it doesn't already exist
     */
    private void createTable()
    {
       sampleDB.execSQL("CREATE TABLE IF NOT EXISTS " +
                    HIGHSCORE +
                    " (LEVEL VARCHAR, " +
                    "  NAME VARCHAR, " +
                    "  TIME VARCHAR);");
    }

    /**
     * Insert some test data, modify as you see fit
     */
    private void insertData()
    {
        sampleDB.execSQL("INSERT INTO " + HIGHSCORE + " Values ('Level 1','Miko','00:03');");    
    }

    /**
     * Run a query to get some data, then add it to a List and format as you require
     */
    private void lookupData()
    {
        cursor = sampleDB.rawQuery("SELECT LEVEL, NAME, TIME FROM " +
                HIGHSCORE +
                " where LEVEL == 'Level 1' ", null);

        if (cursor != null)
        {
            if (cursor.moveToFirst())
            {
                do
                {
                    String personName = cursor.getString(cursor.getColumnIndex("LEVEL"));
                    String country = cursor.getString(cursor.getColumnIndex("NAME"));
                    String age = cursor.getString(cursor.getColumnIndex("TIME"));
                    results.add("" + personName + ", " + country + ", " + age);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
    }
}
