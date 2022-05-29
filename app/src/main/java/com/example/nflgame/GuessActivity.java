package com.example.nflgame;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Random;

public class GuessActivity extends AppCompatActivity implements View.OnClickListener {

    public DatabaseHelper dbHelper = new DatabaseHelper(this);

    static String SEASON;
    ArrayList<Datensatz> listOfAllGamesFromSelectedSeason = new ArrayList<>();
    Datensatz currentGame = new Datensatz();
    int randomGameId;


    // Views
    TextView textViewSeason, textViewGameType, textViewWeek, textViewWeekday, textViewAwayTeam, textViewAwayScore, textViewHomeTeam, textViewHomeScore;
    ImageView imageViewBackToSeasonList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess);

        Intent intent = getIntent();
        SEASON = intent.getStringExtra(SeasonPickerActivity.EXTRA_SEASON);

        textViewSeason = findViewById(R.id.textViewSeason);
        textViewGameType = findViewById(R.id.textViewGameType);
        textViewWeek = findViewById(R.id.textViewWeek);
        textViewWeekday = findViewById(R.id.textViewWeekday);
        textViewAwayTeam = findViewById(R.id.textViewAwayTeam);
        textViewAwayScore = findViewById(R.id.textViewAwayScore);
        textViewAwayTeam = findViewById(R.id.textViewAwayTeam);
        textViewHomeTeam = findViewById(R.id.textViewHomeTeam);
        textViewHomeScore = findViewById(R.id.textViewHomeScore);
        imageViewBackToSeasonList = findViewById(R.id.imageViewBackToSeasonList);

        textViewAwayTeam.setOnClickListener(this);
        textViewHomeTeam.setOnClickListener(this);
        imageViewBackToSeasonList.setOnClickListener(this);

        // allGamesFromSelectedSeason for Savegame
        if (dbHelper.isAllGamesFromSelectedSeasonJSONempty(SEASON)) {
            listOfAllGamesFromSelectedSeason = dbHelper.makeAllGamesFromSelectedSeasonIfNotInSaveGame(SEASON);
            dbHelper.writeListOfAllGamesFromSelectedSeasonToDatabase(fromListToJSONString(listOfAllGamesFromSelectedSeason), SEASON);
        } else {
            try {
                listOfAllGamesFromSelectedSeason = fromJSONStringToList(dbHelper.loadListOfAllGamesFromSelectedSeasonFromDatabase(SEASON));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        setCurrentGame();


    }

    private void setCurrentGame() {
        randomGameId = createRandomGameId(listOfAllGamesFromSelectedSeason.size());
        currentGame = listOfAllGamesFromSelectedSeason.get(randomGameId);
        setTextViews();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textViewAwayTeam:
                listOfAllGamesFromSelectedSeason.remove(randomGameId);
                setCurrentGame();
                break;

            case R.id.textViewHomeTeam:
                listOfAllGamesFromSelectedSeason.remove(randomGameId);
                setCurrentGame();
                break;

            case R.id.imageViewBackToSeasonList:
                break;
        }
    }


    public void setTextViews() {

        textViewSeason.setText(currentGame.getSeason());
        textViewGameType.setText(currentGame.getGame_type());
        textViewWeek.setText(currentGame.getWeek());
        textViewWeekday.setText(currentGame.getWeekday());
        textViewAwayTeam.setText(currentGame.getAway_team());
        textViewAwayScore.setText(currentGame.getAway_score());
        textViewHomeTeam.setText(currentGame.getHome_team());
        textViewHomeScore.setText(currentGame.getHome_score());

    }

    public int createRandomGameId(int size) {
        Random r = new Random();
//        int low = 0;
//        int high = size;
//        return r.nextInt(size - low) + low;
        return r.nextInt(size) + 1;
    }

    public void buildNewLevel() {
//        setCurrentGame();
        setTextViews();
        Toast.makeText(this, "Noch " + listOfAllGamesFromSelectedSeason.size() + " Spiele in dieser Season.", Toast.LENGTH_SHORT).show();
    }

    public boolean ceckIfCorrectOrIncorrect(String choosen, String notChoosen) {
        return Integer.parseInt(choosen) > Integer.parseInt(notChoosen);
    }

    public String fromListToJSONString(ArrayList listOfAllGamesFromSelectedSeason) {
        Gson gson = new Gson();
        return gson.toJson(listOfAllGamesFromSelectedSeason);
    }

    public ArrayList fromJSONStringToList(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, new TypeReference<ArrayList<Datensatz>>() {
        });
    }


    public void saveGameIntoDatabase(boolean correctOrIncorrect, boolean increase) {
        SQLiteDatabase database = openOrCreateDatabase("allSeasonsDB", MODE_PRIVATE, null);
        Cursor cursor = database.rawQuery("SELECT * FROM savegameTable WHERE season LIKE " + SEASON + ";", null);
        cursor.moveToFirst();

        int correct = Integer.parseInt(cursor.getString(2));
        int incorrect = Integer.parseInt(cursor.getString(3));

        if (increase) {
            if (correctOrIncorrect) {
                incorrect++;
            } else {
                correct++;
            }
        }
        cursor.close();

        database.execSQL("UPDATE 'savegameTable' SET 'allGamesFromSelectedSeasonJSON' = '" + fromListToJSONString(listOfAllGamesFromSelectedSeason) + "', 'correct' = '" + correct + "', 'incorrect' = '" + incorrect + "', 'gamesToPlay' = '" + listOfAllGamesFromSelectedSeason.size() + "' WHERE season = " + SEASON + ";");
//        database.close();
    }

    public void dropTableSaveGame() {
        SQLiteDatabase database = openOrCreateDatabase("allSeasonsDB", MODE_PRIVATE, null);
        database.execSQL("DELETE FROM 'savegameTable'");
//        database.close();
    }

}