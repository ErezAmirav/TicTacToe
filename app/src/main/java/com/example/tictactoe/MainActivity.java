package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons = new Button[3][3];
    private boolean turnP1 = true;
    private int roundCount;
    private int pointP1;
    private int pointP2;
    private TextView textViewP1;
    private TextView textViewP2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewP1 = findViewById(R.id.text_view_player1);
        textViewP2 = findViewById(R.id.text_view_player2);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonId = "btn_" + i + j;
                int resId = getResources().getIdentifier(buttonId, "id", getPackageName());
                buttons[i][j] = findViewById(resId);
                buttons[i][j].setOnClickListener(this);

            }
        }
        Button btnReset = findViewById(R.id.btn_reset);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetG();

            }
        });

    }

    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }
        if (turnP1) {
            ((Button) v).setText("X");
        } else
            ((Button) v).setText("o");
        roundCount++;
        if (WinnerCheck()) {
            if (turnP1)
                winnerP1();
         else
            winnerP2();
        }
       else if (roundCount == 9) {
            draw();
        } else {
            turnP1 = !turnP1;
        }
    }

    private boolean WinnerCheck() {
        String[][] field = new String[3][3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    field[i][j] = buttons[i][j].getText().toString();
                }
            }
            for (int i = 0; i < 3; i++)// Check for high
                if (field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2]) && !field[i][0].equals(""))
                    return true;
            for (int i = 0; i < 3; i++) // Check for width
                if (field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i]) && !field[0][i].equals(""))
                    return true;
            if (field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2]) && !field[0][0].equals(""))
                return true;
            if (field[0][2].equals(field[1][1]) && field[0][2].equals(field[2][0]) && !field[0][2].equals(""))
                return true;
            return false;

    }
    private void winnerP1() {
        pointP1++;
        Toast.makeText(this,"Player 1 won !",Toast.LENGTH_SHORT).show();
        updatePointText();
        resetGame();
    }
    private void winnerP2() {
        pointP2++;
        Toast.makeText(this,"Player 2 won !",Toast.LENGTH_SHORT).show();
        updatePointText();
        resetGame();
    }
    private void draw() {
        Toast.makeText(this,"Draw",Toast.LENGTH_SHORT).show();
        resetGame();
    }
    private void resetGame() {
        for(int i=0; i<3; i++){
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        roundCount =0 ;
        turnP1 = true;
    }
    private void updatePointText() {
        textViewP1.setText("Player 1: " + pointP1);
        textViewP2.setText("Player 2: " + pointP2);

    }
    private void resetG(){
        pointP1 = 0;
        pointP2 = 0;
        updatePointText();
        resetGame();
    }
}
