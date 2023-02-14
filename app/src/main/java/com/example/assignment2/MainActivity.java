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
    private TextView textViewStatsEvenNumber;
    private TextView textViewStatsEvenPercentage;
    private ImageView imgPlayerRock;
    private ImageView imgPlayerPaper;
    private ImageView imgPlayerScissors;
    private ImageView imgComputerRock;
    private ImageView imgComputerPaper;
    private ImageView imgComputerScissors;
    private ImageView imgRestart;
    private int playerOpt;
    private int computerOpt;
    private int round;
    private int evenResults;
    private int playerResults;
    private int computerResults;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        round = 0;
        playerResults = 0;
        computerResults = 0;
        evenResults = 0;
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
        textViewStatsEvenNumber = findViewById(R.id.textViewStatsEvenNumber);
        textViewStatsEvenPercentage = findViewById(R.id.textViewStatsEvenPercentage);
        imgPlayerRock = findViewById(R.id.imgPlayerRock);
        imgPlayerPaper = findViewById(R.id.imgPlayerPaper);
        imgPlayerScissors = findViewById(R.id.imgPlayerScissors);
        imgComputerRock = findViewById(R.id.imgComputerRock);
        imgComputerPaper = findViewById(R.id.imgComputerPaper);
        imgComputerScissors = findViewById(R.id.imgComputerScissors);
        imgRestart = findViewById(R.id.imgRestart);

        // Defining the initial game status
        NewRound();
        textViewStatsPlayerNumber.setText("0");
        textViewStatsPlayerPercentage.setText("0");
        textViewStatsComputerNumber.setText("0");
        textViewStatsComputerPercentage.setText("0");
        textViewStatsEvenNumber.setText("0");
        textViewStatsEvenPercentage.setText("0");
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
            if (computerOpt == 1) { // Computer is Rock. Result => EVEN
                textViewResult.setText("Nobody wins this round.");
                textViewWinner.setText("Even");
                evenResults ++;
            } else if (computerOpt == 2) { // Computer is Paper. Result => COMPUTER
                textViewResult.setText("Sorry, you lost. The winner is:");
                textViewWinner.setText("Computer");
                computerResults ++;
            } else { // Computer is Scissors. Result => PLAYER
                textViewResult.setText("Congratulations! The winner is:");
                textViewWinner.setText("Player");
                playerResults ++;
            }
        } else if (playerOpt == 2) { // Player is Paper
            if (computerOpt == 1) { // Computer is Rock. Result => PLAYER
                textViewResult.setText("Congratulations! The winner is:");
                textViewWinner.setText("Player");
                playerResults ++;
            } else if (computerOpt == 2) { // Computer is Paper. Result => EVEN
                textViewResult.setText("Nobody wins this round.");
                textViewWinner.setText("Even");
                evenResults ++;
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
                textViewWinner.setText("Player");
                playerResults ++;
            } else { // Computer is Scissors. Result => EVEN
                textViewResult.setText("Nobody wins this round.");
                textViewWinner.setText("Even");
                evenResults ++;
            }
        }
        textViewResult.setVisibility(View.VISIBLE);
        textViewWinner.setVisibility(View.VISIBLE);
        imgRestart.setVisibility(View.VISIBLE);

        textViewStatsPlayerNumber.setText("" + playerResults);
        textViewStatsPlayerPercentage.setText(String.format ("%.2f",(((float)playerResults/(float)round)*100)));
        textViewStatsComputerNumber.setText("" + computerResults);
        textViewStatsComputerPercentage.setText(String.format("%.2f",(((float)computerResults/(float)round)*100)));
        textViewStatsEvenNumber.setText("" + evenResults);
        textViewStatsEvenPercentage.setText(String.format("%.2f",(((float)evenResults/(float)round)*100)));
    }

    public void RestartRound(View view){
        NewRound();
    }
}