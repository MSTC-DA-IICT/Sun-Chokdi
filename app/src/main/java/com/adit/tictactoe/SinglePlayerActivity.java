package com.adit.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.adit.tictactoe.dialogs.SelectPlayerDialog;
import com.adit.tictactoe.utils.SinglePlayerUtil;

import java.util.ArrayList;
import java.util.Arrays;

public class SinglePlayerActivity extends AppCompatActivity {

    SinglePlayerUtil spUtil;
    public static int activePlayerSp=-1;
    public static char initChoose;
    private boolean compChance = false;
    SelectPlayerDialog dialog;
    // Player Representation:
    //0 - X
    //1 - O
    boolean gameActive=true;
    int[] gameState={2,2,2,2,2,2,2,2,2};
    // State meanings:
    // 0-X      1-O      2-Null
    int[][] winPositions={{0,1,2},{3,4,5},{6,7,8},
                          {0,3,6},{1,4,7},{2,5,8},
                          {0,4,8},{2,4,6}};


    char[][] board = new char[3][3];


    public void playerTapSp(View view){
        spUtil = new SinglePlayerUtil(initChoose);
        if(activePlayerSp == -1) {
            dialog.show();
        }
        ImageView img;
        img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
        if(!gameActive){
            gameReset();
        }else {
            if(gameState[tappedImage]==2 && gameActive) {
                if (compChance) {
                    ArrayList<Integer> coord = new ArrayList<>();
                    coord = spUtil.findBestMove(board);
                    int row=coord.get(0), col = coord.get(1);
                    int tag = 3*row+col;
                    String sTag = Integer.toString(tag);
                    Log.i("Board", "row: " + row + " col: " + col);
                    gameState[tag]=activePlayerSp;
                    compChance=false;
                    ImageView img1 = (ImageView) findViewById(R.id.activity_single_player).findViewWithTag(sTag);
                    if(activePlayerSp==0){
                        board[row][col]='x';
                        img1.setImageResource(R.drawable.x);
                        activePlayerSp=1;
                        TextView status = findViewById(R.id.status);
                        status.setText("O's Turn-Tap on appropriate box");
                    }else{
                        board[row][col]='x';
                        img1.setImageResource(R.drawable.o);
                        activePlayerSp=0;
                        TextView status = findViewById(R.id.status);
                        status.setText("O's Turn-Tap on appropriate box");
                    }
                } else {
                    gameState[tappedImage]=activePlayerSp;
                    int i=tappedImage/3, j=tappedImage%3;
                    compChance=true;
                    if (activePlayerSp == 0) {
                        board[i][j]='x';
                        img.setImageResource(R.drawable.x);
                        activePlayerSp = 1;
                        TextView status = findViewById(R.id.status);
                        status.setText("O's Turn-Tap on appropriate box");
                    } else {
                        board[i][j]='o';
                        img.setImageResource(R.drawable.o);
                        activePlayerSp = 0;
                        TextView status = findViewById(R.id.status);
                        status.setText("X's Turn-Tap on appropriate box");
                    }
                }
            }

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

        activePlayerSp=-1;
        Arrays.fill(gameState,2);
        compChance=false;

        board = new char[][]{{'_', '_', '_'},
                {'_', '_', '_'},
                {'_', '_', '_'}};

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
        setContentView(R.layout.activity_single_player);
        board = new char[][]{{'_', '_', '_'},
                {'_', '_', '_'},
                {'_', '_', '_'}};
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
            case R.id.menu_game_select_screen:
                Intent intent = new Intent(this,WelcomeScreenActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}