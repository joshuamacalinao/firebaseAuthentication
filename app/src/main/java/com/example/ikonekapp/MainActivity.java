package com.example.ikonekapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN = 3500;

    //Variables
    Animation topAnimation,rotatingSunAnimation, bottomAnimation;
    ImageView logobody,sunlogo,text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

    //Animations
    topAnimation = AnimationUtils.loadAnimation(this,R.anim.top_animation);
    rotatingSunAnimation = AnimationUtils.loadAnimation(this,R.anim.top_rotating_sun_animation);
    bottomAnimation = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

    //Hooks
    logobody = findViewById(R.id.logobody);
    sunlogo = findViewById(R.id.sunlogo);
    text = findViewById(R.id.textlogo);

    logobody.setAnimation(topAnimation);
    sunlogo.setAnimation(rotatingSunAnimation);
    text.setAnimation(bottomAnimation);

    new Handler().postDelayed(new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(MainActivity.this,Login.class);
            startActivity(intent);
            finish();
        }
    },SPLASH_SCREEN);

    }
}