package com.adit.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreenActivity extends AppCompatActivity {

    ImageView backgroundImg;
    Animation slideAnim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //This sets the screen to fullscreen mode for splash screen
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        backgroundImg = findViewById(R.id.logo_image);
        slideAnim = AnimationUtils.loadAnimation(this,R.anim.slide_anim);
        backgroundImg.startAnimation(slideAnim);

        //This is a seperate "Thread" for handling animation and pass to MainActivity
        final Runnable r = new Runnable() {
            @Override
            public void run() {
                intentFunc();
            }
        };

        //We run the animation for 3000 milliseconds.
        Handler handler = new Handler();
        handler.postDelayed(r,3000);
    }

    private void intentFunc(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}