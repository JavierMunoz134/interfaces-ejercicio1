package com.example.game_pig;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button btnRollDice;
    private Button btnPass;
    private TextView tvPlayer1Score;
    private TextView tvPlayer1Threshold;
    private TextView tvPlayer2Score;
    private TextView tvPlayer2Threshold;
    private TextView tvWinner;
    private Button btnRestart;

    private int jugador1Puntuacion = 0;
    private int jugador2Puntuacion = 0;
    private int umbralJugador1 = 20;
    private int umbralJugador2 = 20;
    private int currentPlayer = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        btnRollDice = findViewById(R.id.btnRollDice);
        btnPass = findViewById(R.id.btnPass);
        tvPlayer1Score = findViewById(R.id.tvPlayer1Score);
        tvPlayer1Threshold = findViewById(R.id.tvPlayer1Threshold);
        tvPlayer2Score = findViewById(R.id.tvPlayer2Score);
        tvPlayer2Threshold = findViewById(R.id.tvPlayer2Threshold);
        tvWinner = findViewById(R.id.tvWinner);
        btnRestart = findViewById(R.id.btnRestart);


        btnRollDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollDice();
            }
        });

        btnPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passTurn();
            }
        });

        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restartGame();
            }
        });
    }

    private void rollDice() {
        Random random = new Random();
        int diceValue = random.nextInt(6) + 1;

        if (currentPlayer == 1) {
            jugador1Puntuacion += diceValue;
        } else {
            jugador2Puntuacion += diceValue;
        }

        actualizaInterfaz();


    }

    private void passTurn() {

        switchPlayer();


        actualizaInterfaz();


        compruebaGanador();
    }

    private void switchPlayer() {

        currentPlayer = (currentPlayer == 1) ? 2 : 1;
    }


    private void restartGame() {

        jugador1Puntuacion = 0;
        jugador2Puntuacion = 0;


        actualizaInterfaz();


        tvWinner.setText("");
    }

    private void actualizaInterfaz() {

        tvPlayer1Score.setText("Puntuación Jugador 1: " + jugador1Puntuacion);
        tvPlayer1Threshold.setText("Umbral Jugador 1: " + umbralJugador1);
        tvPlayer2Score.setText("Puntuación Jugador 2: " + jugador2Puntuacion);
        tvPlayer2Threshold.setText("Umbral Jugador 2: " + umbralJugador2);
    }

    private void compruebaGanador() {

        if (jugador1Puntuacion >= umbralJugador1) {
            sacaGanador("Jugador 1");
        } else if (jugador2Puntuacion >= umbralJugador2) {
            sacaGanador("Jugador 2");
        }
    }

    private void sacaGanador(String winner) {

        tvWinner.setText("¡El ganador es: " + winner + "!");
    }
}



