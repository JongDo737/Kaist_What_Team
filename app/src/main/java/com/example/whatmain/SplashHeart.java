package com.example.whatmain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.widget.VideoView;

public class SplashHeart extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_heart);
        System.out.println("123123123");
        VideoView videoView = findViewById(R.id.heartVideo);
        // sample.mp4 설정
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/raw/heartjump");
        videoView.setVideoURI(uri);
        // 리스너 등록
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });
        Handler hand = new Handler();

        hand.postDelayed(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                Intent get_i=getIntent();
                String userid=get_i.getStringExtra("userId");
                Intent i = new Intent(SplashHeart.this, HeartList.class);
                i.putExtra("userId",userid);
                startActivity(i);
                finish();

            }
        }, 2500);
    }
}