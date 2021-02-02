package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int activePlayer = 0;
    int[] cellsPlayed = {2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
    boolean gameActive = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void show(View view) {
        ImageView img = (ImageView) view;
        int tappedPos = Integer.parseInt(img.getTag().toString());
        if(cellsPlayed[tappedPos]==2 && gameActive) {
            if (activePlayer == 0) {
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
                cellsPlayed[tappedPos] = 0;

            } else {
                img.setImageResource(R.drawable.o);
                activePlayer = 0;
                cellsPlayed[tappedPos] = 1;
            }
        }

        for (int[] winningPosition : winningPositions) {

            if (cellsPlayed[winningPosition[0]] == cellsPlayed[winningPosition[1]] &&
                    cellsPlayed[winningPosition[1]] == cellsPlayed[winningPosition[2]] &&
                    cellsPlayed[winningPosition[0]] != 2) {
                gameActive = false;

                String winner = "o";

                if (cellsPlayed[winningPosition[0]] == 0) {

                    winner = "X";

                }

                TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);

                winnerMessage.setText(winner + " has won!");

                LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);

                layout.setVisibility(View.VISIBLE);
                break;
            }
            else {
                boolean gameIsOver = true;

                for (int counterState : cellsPlayed) {

                    if (counterState == 2) gameIsOver = false;

                }

                if (gameIsOver) {

                    TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);

                    winnerMessage.setText("It's a draw");

                    LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);

                    layout.setVisibility(View.VISIBLE);


                }
            }
        }
    }

    public void playAgain(View view) {
        gameActive= true;

        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);

        layout.setVisibility(View.INVISIBLE);

        activePlayer = 0;

        for (int i = 0; i < cellsPlayed.length; i++) {

            cellsPlayed[i] = 2;

        }

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridlayout);

        for (int i = 0; i< gridLayout.getChildCount(); i++) {

            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);

        }
    }
}
