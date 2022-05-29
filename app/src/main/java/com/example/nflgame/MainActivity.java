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
        SQLiteDatabase database = openOrCreateDatabase("allSeasonsDB", MODE_PRIVATE, null);
        database.execSQL("CREATE TABLE IF NOT EXISTS 'savegameTable' ('season' INTEGER PRIMARY KEY, 'allGamesFromSelectedSeasonJSON' TEXT, 'correct' INTEGER, 'incorrect' INTEGER, 'gamesToPlay' TEXT)");

        Cursor cursor = database.rawQuery("SELECT season FROM 'allSeasonsTable' GROUP BY season", null);
        cursor.moveToFirst();

        while (cursor.moveToNext()) {
            database.execSQL("INSERT INTO 'savegameTable' ('season', 'correct', 'incorrect', 'gamesToPlay') VALUES ('" + (cursor.getString(0)) + "', '0', '0', '0');");
        }
        cursor.close();
//        database.close();
    }

    public void dropTableSaveGame() {
        SQLiteDatabase database = openOrCreateDatabase("allSeasonsDB", MODE_PRIVATE, null);
        database.execSQL("DELETE FROM 'savegameTable'");
//        database.close();
    }


}