package com.example.nflgame;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "nfl_database";
    private static final String ALL_DETAILS_TABLE = "allDetails";
    private static final String SAVEGAME_TABLE = "savegameTable";
    private static final String COLUMN_SEASON = "season";


    private SQLiteDatabase db;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ArrayList<SeasonItem> createSeasonList() {
        ArrayList<SeasonItem> seasonItemList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + SAVEGAME_TABLE + " GROUP BY " + COLUMN_SEASON, null);
        cursor.moveToFirst();

        do {
            seasonItemList.add(new SeasonItem(R.drawable.ic_circle, cursor.getString(0), cursor.getString(2), cursor.getString(3)));
        } while (cursor.moveToNext());

        cursor.close();
        db.close();
        return seasonItemList;
    }

    public boolean isPlayedThrough(String season) {
        //TODO aus SeasonPickerActivity kopieren
        return true;
    }

}