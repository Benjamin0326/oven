package com.oven.oven.layout;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.oven.oven.R;

import tyrantgit.explosionfield.ExplosionField;

public class SplashActivity extends AppCompatActivity {

    private ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        logo = (ImageView)findViewById(R.id.img_splash);
        overridePendingTransition(R.anim.anim_transition_in, R.anim.anim_transition_out);
        final ExplosionField field = ExplosionField.attach2Window(this);
        Handler hd = new Handler();
        hd.postDelayed(new Runnable(){
            @Override
            public void run(){
                field.explode(logo);
            }
        },1000);
        /*
        hd.postDelayed(new Runnable(){
            @Override
            public void run(){
                field.explode(logo);
            }
        },1500);
        */
        hd.postDelayed(new Runnable(){
            @Override
            public void run(){
                finish();
            }
        },2000);
    }

}
