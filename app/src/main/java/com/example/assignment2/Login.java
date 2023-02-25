package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private EditText editTextPlayerName;
    private EditText editTextMatchNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextMatchNumber = findViewById(R.id.EditTextMachNumber);
        editTextPlayerName = findViewById(R.id.editTextPlayerName);
        editTextPlayerName.requestFocus();

    }

    public void PlayGame(View view) {
        String player = editTextPlayerName.getText().toString();
        String match = editTextMatchNumber.getText().toString();
        if ( player.trim().length() == 0){
            Toast.makeText(this, "Please enter the Player name.", Toast.LENGTH_SHORT).show();
            editTextPlayerName.requestFocus();
        } else {
            // Getting the first name
            if (!(player.indexOf(" ") < 0)) {
                player = player.substring(0,player.indexOf(" "));
                if (player.length() > 9) {
                    player = player.substring(0, 9);
                }
            }

            if ( match.length() == 0){
                Toast.makeText(this, "The number of Matches is 10", Toast.LENGTH_SHORT).show();
                editTextMatchNumber.setText("10");
            }
            Intent intentMain = new Intent(this, MainActivity.class);
            intentMain.putExtra("PlayerName", player);
            intentMain.putExtra("MatchNumber", editTextMatchNumber.getText().toString());
            startActivity(intentMain);
        }
    }
}