package com.adit.tictactoe;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.adit.tictactoe.dialogs.SelectPlayerDialog;

public class MainActivity extends AppCompatActivity {
    SelectPlayerDialog dialog;
    // Player Representation:
    //0 - X
    //1 - O
    static public int activePlayer=-1;
    boolean gameActive=true;
    int[] gameState={2,2,2,2,2,2,2,2,2};
    // State meanings:
    // 0-X      1-O      2-Null


    int[][] winPositions={{0,1,2},{3,4,5},{6,7,8},
                          {0,3,6},{1,4,7},{2,5,8},
                          {0,4,8},{2,4,6}};

    public void playerTap(View view)
    {
        if(activePlayer==-1){
            dialog.show();
        }
        ImageView img;              // here ImageView is the type of variable img
        img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
        if(!gameActive){
            gameReset();
        }
        else
        {
            if (gameState[tappedImage] == 2 && gameActive) {
                gameState[tappedImage] = activePlayer;
                if (activePlayer == 0) {
                    img.setImageResource(R.drawable.x);
                    activePlayer = 1;
                    TextView status = findViewById(R.id.status);
                    status.setText("O's Turn-Tap on appropriate box");
                } else {
                    img.setImageResource(R.drawable.o);
                    activePlayer = 0;
                    TextView status = findViewById(R.id.status);
                    status.setText("X's Turn-Tap on appropriate box");
                }
            }

            // draw check
            boolean draw=true;
            for (int k:gameState)
            {
                if(k==2) {
                    draw=false;
                    break;
                }
            }
            if(draw)
            {
                TextView status = findViewById(R.id.status);
                status.setText("Game Ended in a Draw !!");
                gameActive=false;
            }

            // win check
            for (int[] winPosition : winPositions) {
                if (gameState[winPosition[0]] == gameState[winPosition[1]] && gameState[winPosition[1]] == gameState[winPosition[2]] && gameState[winPosition[0]] != 2) {
                    if (gameState[winPosition[0]] == 0) {
                        TextView status = findViewById(R.id.status);
                        status.setText("X won !! Game Over");
                    } else {
                        TextView status = findViewById(R.id.status);
                        status.setText("O won !! Game Over");
                    }
                    gameActive = false;
                }
            }
        }
    }
    public void gameReset()
    {
        dialog.show();
        gameActive=true;

        activePlayer=-1;
        Arrays.fill(gameState,2);

        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dialog = new SelectPlayerDialog(this);
        dialog.show();
    }

    //To handle the creation of menu for resetting game
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_activity,menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_reset_game:
                gameReset();
                // Toast.makeText(this, "Reset Game Confirmed", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
