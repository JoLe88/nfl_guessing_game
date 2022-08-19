package com.example.nflgame;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WeekMapActivity extends AppCompatActivity {

    public DatabaseHelper dbHelper = new DatabaseHelper(this);

    ArrayList<WeekItem> weekItemList = new ArrayList<>();

    private RecyclerView mRecyclerView;
    private ExampleAdapterWeek mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_map);

        // create the Season List
        weekItemList = dbHelper.createWeekList();

        // generate RecyclerView of Week Items
        mRecyclerView = findViewById(R.id.recyclerViewWeek);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ExampleAdapterWeek(weekItemList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }
}