package com.example.nflgame;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TestForMySQLiteDatabaseActivity extends AppCompatActivity {

    private TextView testausgabe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_for_my_sqlite_database);

        // open Database
        SQLiteDatabase database = openOrCreateDatabase("nfl_database", MODE_PRIVATE, null);

        testausgabe = findViewById(R.id.textView_testausgabe);

//        fillAllDetailsTable();
//        updateGamesToPlay();
        Log.d("SQL Injection Method: ", "done");

        testausgabe.setText("DONE");
        testausgabe.setTextColor(Color.GREEN);
        testausgabe.setTextSize(50);

    }

    public void creatSavegameTable() {
        SQLiteDatabase database = openOrCreateDatabase("nfl_database", MODE_PRIVATE, null);
        database.execSQL("CREATE TABLE IF NOT EXISTS 'savegameTable2' ('season' INTEGER PRIMARY KEY, 'allGamesFromSelectedSeasonJSON' TEXT, 'correct' INTEGER, 'incorrect' INTEGER, 'gamesToPlay' TEXT)");

//        for (int i = 1970; i <= 2021; i++) {
//            database.execSQL("INSERT INTO 'savegameTable' ('season', 'correct', 'incorrect', 'gamesToPlay') VALUES ('" + i + "', '0', '0', '0');");
//        }

//        database.close();
    }

    public void fillAllDetailsTable() {
        SQLiteDatabase database = openOrCreateDatabase("nfl_database", MODE_PRIVATE, null);


//        database.close();
    }

    public void updateGamesToPlay() {
        SQLiteDatabase database = openOrCreateDatabase("nfl_database", MODE_PRIVATE, null);

        // season aus allDetails auslesen
        for (int i = 1970; i <= 2021; i++) {
//            Cursor cursor = database.execSQL("SELECT COUNT(season) FROM allDetails WHERE season = " + i + " GROUP By season;");
            Cursor cursor = database.rawQuery("SELECT COUNT(season) FROM allDetails WHERE season = " + i + " GROUP BY season;", null);
            cursor.moveToFirst();

            database.execSQL("UPDATE savegameTable SET gamesToPlay = \"" + cursor.getString(0) + "\" WHERE season = \"" + i + "\";");

            Log.d("SEASON " + i, cursor.getString(0));
        }
        // savegameTable updaten


//        UPDATE savegameTable
//        SET gamestoplay = '250'
//        WHERE season = '1970';


//        database.close();
    }

}