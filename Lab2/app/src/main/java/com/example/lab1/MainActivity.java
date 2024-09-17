package com.example.lab1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView tvResult, tvPlayerScore, tvAIScore;
    ImageButton btnRock, btnPaper, btnScissors;
    ImageView ivPlayer, ivAI;
    int playerScore = 0;
    int aiScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivAI = findViewById(R.id.ivAI);
        ivPlayer = findViewById(R.id.ivPlayer);
        tvResult = findViewById(R.id.tvResult);
        tvPlayerScore = findViewById(R.id.tvPlayerScore);
        tvAIScore = findViewById(R.id.tvAIScore);
        btnRock = findViewById(R.id.btnRock);
        btnPaper = findViewById(R.id.btnPaper);
        btnScissors = findViewById(R.id.btnScissors);

        btnRock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playTurn("rock");
            }
        });

        btnPaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playTurn("paper");
            }
        });

        btnScissors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playTurn("scissors");
            }
        });
    }

    private void playTurn(String playerChoice) {
        String aiChoice = getAIChoice();
        String result = getResult(playerChoice, aiChoice);
        setImageResult(aiChoice, ivAI);
        setImageResult(playerChoice, ivPlayer);
        tvResult.setText(result);
        updateScore(result);
    }

    private String getAIChoice() {
        String[] choices = {"rock", "paper", "scissors"};
        Random random = new Random();
        int aiMove = random.nextInt(3);
        return choices[aiMove];
    }

    private void setImageResult(String choice, ImageView iv) {
        switch (choice) {
            case ("rock"):
                iv.setImageResource(R.drawable.rock);
                break;
            case ("paper"):
                iv.setImageResource(R.drawable.paper);
                break;
            case ("scissors"):
                iv.setImageResource(R.drawable.scissors);
                break;
            default:
                break;
        }
    }

    private String getResult(String player, String ai) {
        if (player.equals(ai)) {
            return "Ничья";
        } else if ((player.equals("rock") && ai.equals("scissors")) ||
                (player.equals("paper") && ai.equals("rock")) ||
                (player.equals("scissors") && ai.equals("paper"))) {
            return "Ты победил!";
        } else {
            return "Ты проиграл((";
        }
    }

    private void updateScore(String result) {
        if (result.equals("Ты победил!")) {
            playerScore++;
        } else if (result.equals("Ты проиграл((")) {
            aiScore++;
        }
        tvPlayerScore.setText("Игрок " + playerScore);
        tvAIScore.setText("ИИ " + aiScore);
    }
}