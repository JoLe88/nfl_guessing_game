package com.example.nflgame;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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

    // DatabaseHelper methods for Queries

    public ArrayList<SeasonItem> createSeasonList() {
        ArrayList<SeasonItem> seasonItemList = new ArrayList<>();
        db = getReadableDatabase();
        String query = "SELECT * FROM " + SAVEGAME_TABLE + " GROUP BY " + COLUMN_SEASON;
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        do {
            seasonItemList.add(new SeasonItem(R.drawable.ic_circle, cursor.getString(0), cursor.getString(2), cursor.getString(3)));
        } while (cursor.moveToNext());

        cursor.close();
        db.close();
        return seasonItemList;
    }

    public boolean isPlayedThrough(String season) {
        // TODO implement isPlayedThrough somehow like
        // 1. allGamesFromSelectedSeason Array is empty?
        // 2. additional colum in savegameTable with isPlayedThrough = 0/1?
        // 3. gamesToPlay == 0?
        return false;
    }

    public ArrayList<Datensatz> makeAllGamesFromSelectedSeasonIfNotInSaveGame(String season) {
        ArrayList<Datensatz> listOfallGamesFromSelectedSeason = new ArrayList<>();
        int mapKey = 1;

        db = getReadableDatabase();
        String query = "SELECT * FROM " + ALL_DETAILS_TABLE + " WHERE season LIKE " + season;
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        do {
            Datensatz datensatz = new Datensatz();

            datensatz.setGameId(cursor.getString(cursor.getColumnIndex("gameId")));
            datensatz.setSeason(cursor.getString(cursor.getColumnIndex("season")));
            datensatz.setGame_type(cursor.getString(cursor.getColumnIndex("game_type")));
            datensatz.setWeek(cursor.getString(cursor.getColumnIndex("week")));
            datensatz.setDate(cursor.getString(cursor.getColumnIndex("date")));
            datensatz.setWritten_date(cursor.getString(cursor.getColumnIndex("written_date")));
            datensatz.setWeekday(cursor.getString(cursor.getColumnIndex("weekday")));
            datensatz.setAway_team(cursor.getString(cursor.getColumnIndex("away_team")));
            datensatz.setAway_score(cursor.getString(cursor.getColumnIndex("away_score")));
            datensatz.setHome_team(cursor.getString(cursor.getColumnIndex("home_team")));
            datensatz.setHome_score(cursor.getString(cursor.getColumnIndex("home_score")));
            datensatz.setResult(cursor.getString(cursor.getColumnIndex("result")));
            datensatz.setTotal(cursor.getString(cursor.getColumnIndex("total")));
            datensatz.setAway_win_loss_record(cursor.getString(cursor.getColumnIndex("away_win_loss_record")));
            datensatz.setHome_win_loss_record(cursor.getString(cursor.getColumnIndex("home_win_loss_record")));
            datensatz.setRoof(cursor.getString(cursor.getColumnIndex("roof")));
            datensatz.setSurface(cursor.getString(cursor.getColumnIndex("surface")));
            datensatz.setTemp(cursor.getString(cursor.getColumnIndex("temp")));
            datensatz.setWind(cursor.getString(cursor.getColumnIndex("wind")));
            datensatz.setWritten_weather(cursor.getString(cursor.getColumnIndex("written_weather")));
            datensatz.setAway_coach(cursor.getString(cursor.getColumnIndex("away_coach")));
            datensatz.setHome_coach(cursor.getString(cursor.getColumnIndex("home_coach")));
            datensatz.setStadium(cursor.getString(cursor.getColumnIndex("stadium")));


            listOfallGamesFromSelectedSeason.add(datensatz);
            mapKey++;
        } while (cursor.moveToNext());

        cursor.close();
        return listOfallGamesFromSelectedSeason;
    }

    public boolean isAllGamesFromSelectedSeasonJSONempty(String season) {
        db = getReadableDatabase();
        String query = "SELECT allGamesFromSelectedSeasonJSON FROM " + SAVEGAME_TABLE + " WHERE season LIKE " + season;
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        if (cursor.getString(cursor.getColumnIndex("allGamesFromSelectedSeasonJSON")) == null) {
            Log.d("allGamesFromSelectedSeasonJSON", "empty");
            cursor.close();
            return true;
        } else {
            Log.d("allGamesFromSelectedSeasonJSON", "Not empty");
            cursor.close();
            return false;
        }
    }
}