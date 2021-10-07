package com.adit.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeScreenActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_sp, btn_mp;
    public static boolean isSinglePlayer = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        btn_mp = (Button) findViewById(R.id.ws_btn_mp);
        btn_sp = (Button) findViewById(R.id.ws_btn_sp);
        btn_sp.setOnClickListener(this);
        btn_mp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ws_btn_sp:
                isSinglePlayer = true;
                Intent intent_sp = new Intent(this,SinglePlayerActivity.class);
                startActivity(intent_sp);
                break;
            case R.id.ws_btn_mp:
                Intent intent_mp = new Intent(this,MainActivity.class);
                startActivity(intent_mp);
                break;
        }
    }
}