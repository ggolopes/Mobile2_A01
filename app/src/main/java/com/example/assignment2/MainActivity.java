package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView textTitle;
    private TextView textViewResult;
    private TextView textViewWinner;
    private TextView textSelectPlayer;
    private TextView textSelectComputer;
    private TextView textViewStatsPlayer;
    private TextView textViewStatsPlayerNumber;
    private TextView textViewStatsPlayerPercentage;
    private TextView textViewStatsComputer;
    private TextView textViewStatsComputerNumber;
    private TextView textViewStatsComputerPercentage;
    private TextView textViewStatsTieNumber;
    private TextView textViewStatsTiePercentage;
    private ImageView imgPlayerRock;
    private ImageView imgPlayerPaper;
    private ImageView imgPlayerScissors;
    private ImageView imgComputerRock;
    private ImageView imgComputerPaper;
    private ImageView imgComputerScissors;
    private ImageView imgRestart;
    private SharedPreferences sharedPreferences;
    private int playerOpt;
    private int computerOpt;
    private int round;
    private int tieResults;
    private int playerResults;
    private int computerResults;
    public String playerName;
    public int matchNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textTitle = findViewById(R.id.textTitle);
        textViewResult = findViewById(R.id.textViewResult);
        textViewWinner = findViewById(R.id.textViewWinner);
        textSelectPlayer = findViewById(R.id.textSelectPlayer);
        textSelectComputer = findViewById(R.id.textSelectComputer);
        textViewStatsPlayer = findViewById(R.id.textViewStatsPlayer);
        textViewStatsPlayerNumber = findViewById(R.id.textViewStatsPlayerNumber);
        textViewStatsPlayerPercentage = findViewById(R.id.textViewStatsPlayerPercentage);
        textViewStatsComputer = findViewById(R.id.textViewStatsComputer);
        textViewStatsComputerNumber = findViewById(R.id.textViewStatsComputerNumber);
        textViewStatsComputerPercentage = findViewById(R.id.textViewStatsComputerPercentage);
        textViewStatsTieNumber = findViewById(R.id.textViewStatsTieNumber);
        textViewStatsTiePercentage = findViewById(R.id.textViewStatsTiePercentage);
        imgPlayerRock = findViewById(R.id.imgPlayerRock);
        imgPlayerPaper = findViewById(R.id.imgPlayerPaper);
        imgPlayerScissors = findViewById(R.id.imgPlayerScissors);
        imgComputerRock = findViewById(R.id.imgComputerRock);
        imgComputerPaper = findViewById(R.id.imgComputerPaper);
        imgComputerScissors = findViewById(R.id.imgComputerScissors);
        imgRestart = findViewById(R.id.imgRestart);
        playerName = getIntent().getStringExtra("PlayerName");
        matchNumber = Integer.valueOf(getIntent().getStringExtra("MatchNumber"));
        textViewStatsPlayer.setText(playerName);
        // Defining the initial game status
        sharedPreferences = getSharedPreferences("LastInput", MODE_PRIVATE);
        ResetStatistics();
    }

    @Override
    protected void onPause() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("statsPlayerNum", textViewStatsPlayerNumber.getText().toString());
        editor.putString("statsPlayerPerc", textViewStatsPlayerPercentage.getText().toString());
        editor.putString("statsComputerNum", textViewStatsComputerNumber.getText().toString());
        editor.putString("statsComputerPerc", textViewStatsComputerPercentage.getText().toString());
        editor.putString("statsTieNum", textViewStatsTieNumber.getText().toString());
        editor.putString("statsTiePerc", textViewStatsTiePercentage.getText().toString());
        editor.commit();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        textViewStatsPlayerNumber.setText(sharedPreferences.getString("statsPlayerNum", ""));
        textViewStatsPlayerPercentage.setText(sharedPreferences.getString("statsPlayerPerc", ""));
        textViewStatsComputerNumber.setText(sharedPreferences.getString("statsComputerNum", ""));
        textViewStatsComputerPercentage.setText(sharedPreferences.getString("statsComputerPerc", ""));
        textViewStatsTieNumber.setText(sharedPreferences.getString("statsTieNum", ""));
        textViewStatsTiePercentage.setText(sharedPreferences.getString("statsTiePerc", ""));
    }

    public void NewRound(){
        textViewResult.setVisibility(View.INVISIBLE);
        textViewWinner.setVisibility(View.INVISIBLE);
        textSelectComputer.setVisibility(View.INVISIBLE);
        imgPlayerRock.setVisibility(View.VISIBLE);
        imgPlayerPaper.setVisibility(View.VISIBLE);
        imgPlayerScissors.setVisibility(View.VISIBLE);
        imgComputerRock.setVisibility(View.INVISIBLE);
        imgComputerPaper.setVisibility(View.INVISIBLE);
        imgComputerScissors.setVisibility(View.INVISIBLE);
        imgRestart.setVisibility(View.INVISIBLE);
        playerOpt = 0;
        computerOpt = 0;
        round ++;
    }
    public void ResetStatistics(){
        textViewStatsPlayerNumber.setText("0");
        textViewStatsPlayerPercentage.setText("0");
        textViewStatsComputerNumber.setText("0");
        textViewStatsComputerPercentage.setText("0");
        textViewStatsTieNumber.setText("0");
        textViewStatsTiePercentage.setText("0");
        round = 0;
        playerResults = 0;
        computerResults = 0;
        tieResults = 0;
        NewRound();
    }

    public void PlayerGetRock(View view) {
        if (playerOpt == 0) {
            playerOpt = 1;
            imgPlayerPaper.setVisibility(View.GONE);
            imgPlayerScissors.setVisibility(View.GONE);
            ComputerPlays();
        }
    }

    public void PlayerGetPaper(View view) {
        if (playerOpt == 0) {
            playerOpt = 2;
            imgPlayerRock.setVisibility(View.GONE);
            imgPlayerScissors.setVisibility(View.GONE);
            ComputerPlays();
        }
    }

    public void PlayerGetScissors(View view) {
        if (playerOpt == 0) {
            playerOpt = 3;
            imgPlayerRock.setVisibility(View.GONE);
            imgPlayerPaper.setVisibility(View.GONE);
            ComputerPlays();
        }
    }

    private void ComputerPlays(){
        Random random = new Random();
        computerOpt = random.nextInt(3) + 1;
        switch (computerOpt){
            case 1:
                imgComputerRock.setVisibility(View.VISIBLE);
                break;
            case 2:
                imgComputerPaper.setVisibility(View.VISIBLE);
                break;
            case 3:
                imgComputerScissors.setVisibility(View.VISIBLE);
                break;
        }
        textSelectComputer.setVisibility(View.VISIBLE);
        CheckResult();
    }

    private void CheckResult() {
        if (playerOpt == 1) { // Player is Rock
            if (computerOpt == 1) { // Computer is Rock. Result => TIE
                textViewResult.setText("Nobody wins this round.");
                textViewWinner.setText("Tie");
                tieResults ++;
            } else if (computerOpt == 2) { // Computer is Paper. Result => COMPUTER
                textViewResult.setText("Sorry, you lost. The winner is:");
                textViewWinner.setText("Computer");
                computerResults ++;
            } else { // Computer is Scissors. Result => PLAYER
                textViewResult.setText("Congratulations! The winner is:");
                textViewWinner.setText(playerName);
                playerResults ++;
            }
        } else if (playerOpt == 2) { // Player is Paper
            if (computerOpt == 1) { // Computer is Rock. Result => PLAYER
                textViewResult.setText("Congratulations! The winner is:");
                textViewWinner.setText(playerName);
                playerResults ++;
            } else if (computerOpt == 2) { // Computer is Paper. Result => TIE
                textViewResult.setText("Nobody wins this round.");
                textViewWinner.setText("Tie");
                tieResults ++;
            } else { // Computer is Scissors. Result => COMPUTER
                textViewResult.setText("Sorry, you lost. The winner is:");
                textViewWinner.setText("Computer");
                computerResults ++;
            }
        } else { // player is Scissors
            if (computerOpt == 1) { // Computer is Rock. Result => COMPUTER
                textViewResult.setText("Sorry, you lost. The winner is:");
                textViewWinner.setText("Computer");
                computerResults ++;
            } else if (computerOpt == 2) { // Computer is Paper. Result => PLAYER
                textViewResult.setText("Congratulations! The winner is:");
                textViewWinner.setText(playerName);
                playerResults ++;
            } else { // Computer is Scissors. Result => TIE
                textViewResult.setText("Nobody wins this round.");
                textViewWinner.setText("Tie");
                tieResults ++;
            }
        }
        textViewResult.setVisibility(View.VISIBLE);
        textViewWinner.setVisibility(View.VISIBLE);
        imgRestart.setVisibility(View.VISIBLE);

        textViewStatsPlayerNumber.setText("" + playerResults);
        textViewStatsPlayerPercentage.setText(String.format ("%.2f",(((float)playerResults/(float)round)*100)));
        textViewStatsComputerNumber.setText("" + computerResults);
        textViewStatsComputerPercentage.setText(String.format("%.2f",(((float)computerResults/(float)round)*100)));
        textViewStatsTieNumber.setText("" + tieResults);
        textViewStatsTiePercentage.setText(String.format("%.2f",(((float)tieResults/(float)round)*100)));
    }

    public void RestartRound(View view)
    {
        NewRound();
    }
    public void ResetStats(View view)
    {
        ResetStatistics();
    }
}