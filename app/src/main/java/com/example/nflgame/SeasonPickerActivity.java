package com.example.nflgame;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SeasonPickerActivity extends AppCompatActivity {

    public DatabaseEnum databaseEnum;
    public DatabaseHelper dbHelper = new DatabaseHelper(this);

    public static final String EXTRA_SEASON = "com.example.nflgame.EXTRA_SEASON";

    ArrayList<SeasonItem> seasonItemList = new ArrayList<>();

    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_season_picker);

        // create the Season List
        seasonItemList = dbHelper.createSeasonList();

        mRecyclerView = findViewById(R.id.recyclerViewId);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ExampleAdapter(seasonItemList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        // onClick for the Season List
        mAdapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (!isPlayedThrough(seasonItemList.get(position).getmSeason())) {
                    goToGuessActivity(position);
                } else {
                    Toast.makeText(SeasonPickerActivity.this, "Already played through.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void goToGuessActivity(int position) {
        String item = seasonItemList.get(position).getmSeason();
        Intent intent = new Intent(this, GuessActivity.class);
        intent.putExtra(EXTRA_SEASON, item);
        startActivity(intent);
    }


    public boolean isPlayedThrough(String season) {
        String isPlayedThroughFromDatabase = "0";

        String DB_NAME = "nfl_database";
        String DB_TABLE_NAME = "saveGameTable";
        String query = "SELECT 'playedThrough' FROM " + DB_TABLE_NAME + " WHERE 'season' LIKE " + season;

        SQLiteDatabase database = openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);
        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();

        while (cursor.moveToNext()) {
            isPlayedThroughFromDatabase = cursor.getString(0);
        }

        switch (isPlayedThroughFromDatabase) {
            case "1":
                return true;
            case "0":
                return false;
            default:
                return false;
        }
    }

}