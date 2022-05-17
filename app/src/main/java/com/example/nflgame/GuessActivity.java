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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Random;

public class GuessActivity extends AppCompatActivity implements View.OnClickListener {

    public DatabaseHelper dbHelper = new DatabaseHelper(this);

    public static final String HOME_TEAM = "com.example.nflgame.EXTRA_SEASON";
    public static final String EXTRA_SEASON = "com.example.nflgame.EXTRA_SEASON";


    static String SEASON;
    ArrayList<Datensatz> listOfallGamesFromSelectedSeason = new ArrayList<>();
    GameObject currentGame = new GameObject();
    int mapKey = 1;


    // Views
    TextView textViewGameType, textViewWeek, textViewGameday, textViewAwayTeam, textViewAwayScore, textViewHomeTeam, textViewHomeScore;
    ImageView imageViewBackToSeasonList, imageViewDrop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess);

        Intent intent = getIntent();
        SEASON = intent.getStringExtra(SeasonPickerActivity.EXTRA_SEASON);

        textViewGameType = findViewById(R.id.textViewGameType);
        textViewWeek = findViewById(R.id.textViewWeek);
        textViewGameday = findViewById(R.id.textViewGameday);
        textViewAwayTeam = findViewById(R.id.textViewAwayTeam);
        textViewAwayScore = findViewById(R.id.textViewAwayScore);
        textViewAwayTeam = findViewById(R.id.textViewAwayTeam);
        textViewHomeTeam = findViewById(R.id.textViewHomeTeam);
        textViewHomeScore = findViewById(R.id.textViewHomeScore);
        imageViewBackToSeasonList = findViewById(R.id.imageViewBackToSeasonList);
        imageViewDrop = findViewById(R.id.imageViewDrop);

        textViewAwayTeam.setOnClickListener(this);
        textViewHomeTeam.setOnClickListener(this);
        imageViewBackToSeasonList.setOnClickListener(this);
        imageViewDrop.setOnClickListener(this);

        listOfallGamesFromSelectedSeason = dbHelper.makeAllGamesFromSelectedSeasonIfNotInSaveGame(SEASON);


        boolean isAllGamesFromSelectedSeasonJSONempty = dbHelper.isAllGamesFromSelectedSeasonJSONempty(SEASON);

//        makeAllGamesFromSelectedSeasonIfNotInSaveGame();
//        setCurrentGame();
//        setTextViews();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textViewAwayTeam:
                if (ceckIfCorrectOrIncorrect(currentGame.getAway_score(), currentGame.getHome_score())) {
                    saveGameIntoDatabase(true, true);
                } else {
                    saveGameIntoDatabase(false, true);
                }
//                try {
//                    fromJSONtoAllGamesFromSelectedSeason(fromAllGamesFromSelectedSeasonToJsonString(allGamesFromSelectedSeason));
//                } catch (JsonProcessingException e) {
//                    e.printStackTrace();
//                }
                buildNewLevel();
                break;

            case R.id.textViewHomeTeam:
                if (ceckIfCorrectOrIncorrect(currentGame.getHome_score(), currentGame.getAway_score())) {
                    saveGameIntoDatabase(true, true);
                } else {
                    saveGameIntoDatabase(false, true);
                }
//                try {
//                    fromJSONtoAllGamesFromSelectedSeason(fromAllGamesFromSelectedSeasonToJsonString(allGamesFromSelectedSeason));
//                } catch (JsonProcessingException e) {
//                    e.printStackTrace();
//                }
                buildNewLevel();
                break;

            case R.id.imageViewBackToSeasonList:
                saveGameIntoDatabase(true, false);
                try {
                    fromJSONtoAllGamesFromSelectedSeason(fromAllGamesFromSelectedSeasonToJsonString(listOfallGamesFromSelectedSeason));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(this, SeasonPickerActivity.class);
                startActivity(intent);
                break;

            case R.id.imageViewDrop:
//                dropTableSaveGame();
                break;
        }
    }

    public void getAllGamesFromSelectedSeasonFromSaveGame() {

    }

    public void setCurrentGame() {
        int i = createRandomNumber();

        currentGame.setGame_type(listOfallGamesFromSelectedSeason.get(i).getGame_type());
        currentGame.setWeek(listOfallGamesFromSelectedSeason.get(i).getWeek());
//        currentGame.setGameday(allGamesFromSelectedSeason.get(i).getGameday());
        currentGame.setAway_team(listOfallGamesFromSelectedSeason.get(i).getAway_team());
        currentGame.setAway_score(listOfallGamesFromSelectedSeason.get(i).getAway_score());
        currentGame.setHome_team(listOfallGamesFromSelectedSeason.get(i).getHome_team());
        currentGame.setHome_score(listOfallGamesFromSelectedSeason.get(i).getHome_score());

        listOfallGamesFromSelectedSeason.remove(i);
    }

    public void setTextViews() {

        textViewGameType.setText(currentGame.getGame_type());
        textViewWeek.setText(currentGame.getWeek());
        textViewGameday.setText(currentGame.getGameday());
        textViewAwayTeam.setText(currentGame.getAway_team());
        textViewAwayScore.setText(currentGame.getAway_score());
        textViewHomeTeam.setText(currentGame.getHome_team());
        textViewHomeScore.setText(currentGame.getHome_score());

    }

    public int createRandomNumber() {
        Random r = new Random();
        int low = 1;
        int high = listOfallGamesFromSelectedSeason.size();
        return r.nextInt(high - low) + low;
    }

    public void buildNewLevel() {
//        setCurrentGame();
        setTextViews();
        Toast.makeText(this, "Noch " + listOfallGamesFromSelectedSeason.size() + " Spiele in dieser Season.", Toast.LENGTH_SHORT).show();
    }

    public boolean ceckIfCorrectOrIncorrect(String choosen, String notChoosen) {
        return Integer.parseInt(choosen) > Integer.parseInt(notChoosen);
    }

    public String fromAllGamesFromSelectedSeasonToJsonString(ArrayList allGamesFromSelectedSeason) {
        Gson gson = new Gson();

        return gson.toJson(allGamesFromSelectedSeason);
    }

    public void fromJSONtoAllGamesFromSelectedSeason(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
//        allGamesFromSelectedSeason = mapper.readValue(json, new TypeReference<ArrayList<GameObject>>() {
//        });
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

        database.execSQL("UPDATE 'savegameTable' SET 'allGamesFromSelectedSeasonJSON' = '" + fromAllGamesFromSelectedSeasonToJsonString(listOfallGamesFromSelectedSeason) + "', 'correct' = '" + correct + "', 'incorrect' = '" + incorrect + "', 'gamesToPlay' = '" + listOfallGamesFromSelectedSeason.size() + "' WHERE season = " + SEASON + ";");
//        database.close();
    }

    public void dropTableSaveGame() {
        SQLiteDatabase database = openOrCreateDatabase("allSeasonsDB", MODE_PRIVATE, null);
        database.execSQL("DELETE FROM 'savegameTable'");
//        database.close();
    }

}