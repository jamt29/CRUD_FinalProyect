package com.itca.crud_finalproyect;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Timer;
import java.util.TimerTask;


public class SplashScreen extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);



        String urlGif = "https://jamt29.000webhostapp.com/img/80578-scary-cat.gif";
        ImageView imageView = (ImageView)findViewById(R.id.imageView);
        Uri uri = Uri.parse(urlGif);
        Glide.with(getApplicationContext()).load(uri).into(imageView);


        TimerTask time = new TimerTask() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        };

        Timer t = new Timer();
        t.schedule(time, 3700);

    }

}