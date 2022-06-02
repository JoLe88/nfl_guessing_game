package com.example.nflgame;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    static final String DB_NAME = "allSeasonsDB";
    static final String DB_TABLE_ALLSEASONS = "allSeasonsTable";
    static final String DB_TABLE_SAVEGAME = "savegameTable";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        dropTableSaveGame();
//        creatSavegameTable();

        Intent intent = new Intent(this, SeasonPickerActivity.class);
        startActivity(intent);
    }

    public void creatSavegameTable() {
        SQLiteDatabase database = openOrCreateDatabase("nfl_database", MODE_PRIVATE, null);
        database.execSQL("CREATE TABLE IF NOT EXISTS 'savegameTable' ('season' INTEGER PRIMARY KEY, 'allGamesFromSelectedSeasonJSON' TEXT, 'correct' INTEGER, 'incorrect' INTEGER, 'gamesToPlayTotal' TEXT, 'gamesToPlayCounter' TEXT)");

        Cursor cursor = database.rawQuery("SELECT season FROM 'allDetails' GROUP BY season", null);
        cursor.moveToFirst();

        while (cursor.moveToNext()) {
            database.execSQL("INSERT INTO 'savegameTable' ('season', 'correct', 'incorrect', 'gamesToPlayTotal', 'gamesToPlayCounter') VALUES ('" + (cursor.getString(0)) + "', '0', '0', '0', '0');");
        }
        cursor.close();

        // fill gamesToPlayTotal and gamesToPlayCounter
        database = openOrCreateDatabase("nfl_database", MODE_PRIVATE, null);
        for (int i = 1970; i <= 2021; i++) {
            cursor = database.rawQuery("SELECT COUNT(season) FROM allDetails WHERE season = " + i + " GROUP BY season;", null);
            cursor.moveToFirst();

            database.execSQL("UPDATE savegameTable SET gamesToPlayTotal = \"" + cursor.getString(0) + "\" WHERE season = \"" + i + "\";");
            database.execSQL("UPDATE savegameTable SET gamesToPlayCounter = 1;");

//        database.close();
        }
    }

    public void dropTableSaveGame() {
        SQLiteDatabase database = openOrCreateDatabase("nfl_database", MODE_PRIVATE, null);
        database.execSQL("DROP TABLE 'savegameTable'");
//        database.close();
    }


}