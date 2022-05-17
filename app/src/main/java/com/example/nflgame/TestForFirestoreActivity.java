package com.example.nflgame;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.Scanner;

public class TestForFirestoreActivity extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    //DocumentReference docref = db.document("allDetails");

    private String id = "0";
    private String docPath = String.format("allDetails/%s", id);

    private RequestQueue mQueue;

    private TextView testausgabe;
    private Datensatz datensatz = new Datensatz();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_for_firestore);

        testausgabe = findViewById(R.id.textView_testausgabe);

        mQueue = Volley.newRequestQueue(this);

        // read from JSON file and write to Firestore
//        redFromJSONfile();

        // write
//        writeDataToFirestore();

        // read
//        readDataFromFirestore();


    }


    private void redFromJSONfile() {
        Resources res = getResources();
        InputStream inputStream = res.openRawResource(R.raw.json_2011_to_2021);
        Scanner scanner = new Scanner(inputStream);
        StringBuilder builder = new StringBuilder();

        while (scanner.hasNextLine()) {
            builder.append(scanner.nextLine());
        }

        parseJason(builder.toString());
    }

    private void parseJason(String json) {
        try {
            JSONObject root = new JSONObject(json);
            JSONArray allDetails = root.getJSONArray("allDetails");
            for (int i = 0; i < allDetails.length(); i++) {
                JSONObject parsedDatensatz = allDetails.getJSONObject(i);

                datensatz.setDatensatzToNull();

                datensatz.setGameId(parsedDatensatz.getString("gameId"));
                datensatz.setSeason(parsedDatensatz.getString("season"));
                datensatz.setGame_type(parsedDatensatz.getString("game_type"));
                datensatz.setWeek(parsedDatensatz.getString("week"));
                datensatz.setDate(parsedDatensatz.getString("date"));
                datensatz.setWritten_date(parsedDatensatz.getString("written_date"));
                datensatz.setWeekday(parsedDatensatz.getString("weekday"));
                datensatz.setAway_team(parsedDatensatz.getString("away_team"));
                datensatz.setAway_score(parsedDatensatz.getString("away_score"));
                datensatz.setAway_win_loss_record(parsedDatensatz.getString("away_win_loss_record"));
                datensatz.setHome_team(parsedDatensatz.getString("home_team"));
                datensatz.setHome_score(parsedDatensatz.getString("home_score"));
                datensatz.setHome_win_loss_record(parsedDatensatz.getString("home_win_loss_record"));
                datensatz.setResult(parsedDatensatz.getString("result"));
                datensatz.setTotal(parsedDatensatz.getString("total"));
                datensatz.setRoof(parsedDatensatz.getString("roof"));
                datensatz.setSurface(parsedDatensatz.getString("surface"));
                datensatz.setTemp(parsedDatensatz.getString("temp"));
                datensatz.setWind(parsedDatensatz.getString("wind"));
                datensatz.setWritten_weather(parsedDatensatz.getString("written_weather"));
                datensatz.setAway_coach(parsedDatensatz.getString("away_coach"));
                datensatz.setHome_coach(parsedDatensatz.getString("home_coach"));
                datensatz.setStadium(parsedDatensatz.getString("stadium"));

                writeDataToFirestore(datensatz.getGameId());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("======== Done...?", "done.");
    }


    private void readDataFromFirestore() {
        id = "1";
        docPath = String.format("allDetails/%s", id);

        DocumentReference docref = db.document(docPath);

        docref.get().
                addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        String season = documentSnapshot.getString("season");
                        Toast.makeText(TestForFirestoreActivity.this, season, Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(TestForFirestoreActivity.this, "FAILURE", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void writeDataToFirestore(String documentPath) {
        db.collection("allDetails").document(documentPath).set(datensatz);

        if (datensatz.getGame_type().equals("Super Bowl")) {
            Log.d(" ======= Done for Season: ", datensatz.getSeason());
        }

    }
}