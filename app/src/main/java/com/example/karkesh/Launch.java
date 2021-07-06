package com.example.karkesh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Launch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        ImageView logo;
        TextView legend;

        logo= findViewById(R.id.logo);
        legend= findViewById(R.id.legend);

        Animation animation= AnimationUtils.loadAnimation(this,R.anim.logoanimation);
        logo.startAnimation(animation);

        Animation titleAnimation =AnimationUtils.loadAnimation(this,R.anim.titleanimation);
        legend.startAnimation(titleAnimation);

        new Handler().postDelayed(new Runnable() {
                                      @Override
                                      public void run() {
                                          Intent i = new Intent(Launch.this, Scan.class);
                                          startActivity(i);
                                          finish();
                                      }},
                5000)
        ;
    }
}
