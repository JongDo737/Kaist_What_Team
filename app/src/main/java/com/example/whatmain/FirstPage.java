package com.example.whatmain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class FirstPage extends AppCompatActivity {
    FrameLayout foodFrame;
    FrameLayout enterFrame;
    FrameLayout dressFrame;
    FrameLayout festivalFrame;
    ImageView weatherImg;
    TextView degree;
    TextView rainPorb;
    ConstraintLayout weatherBackground;
    ImageView editInfo;

    String userid;
    String username;
    String userpw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);

        TextView firstPageUserName=(TextView) findViewById(R.id.firstPageUserName);

        //로그인 시 username 변경해야 함 **********************
        Intent i_login=getIntent();
        userid=i_login.getStringExtra("userId");
        username=i_login.getStringExtra("userName");
        System.out.println("FirstPage!!!!: "+username);
        //userpw="12334235";
        firstPageUserName.setText(username+" 어서온나!");

        setWeather();
        foodFrame = findViewById(R.id.foodFrame);
        enterFrame = findViewById(R.id.enterFrame);
        dressFrame = findViewById(R.id.dressFrame);
        festivalFrame = findViewById(R.id.festivalFrame);
        editInfo=findViewById(R.id.editInfo);

        foodFrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),BusanFoodStep1.class);
                intent.putExtra("userId", userid);
                startActivity(intent);
            }
        });
        festivalFrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),BusanFestivalStep1.class);
                intent.putExtra("userId", userid);
                startActivity(intent);
            }
        });
        enterFrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),BusanTodoStep1.class);
                intent.putExtra("userId", userid);
                startActivity(intent);
            }
        });
//        dressFrame.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(),BusanWearStep1.class);
//                startActivity(intent);
//            }
//        });
        //리스트 뷰 띄워주기
        dressFrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),HeartList.class);
                startActivity(intent);

            }
        });
        editInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),EditProfile.class);
                intent.putExtra("userid",userid);
                intent.putExtra("username",username);
                //intent.putExtra("pw",userpw);
                startActivity(intent);
            }
        });
    }
    public void setWeather(){
        weatherBackground = findViewById(R.id.weatherBackground);
        weatherImg = findViewById(R.id.weatherImg);
        degree = findViewById(R.id.degree);
        rainPorb = findViewById(R.id.rainPorb);

        WeatherData weatherData = new WeatherData();
        String[] weather = new String[7];
        weather = weatherData.getData();
        System.out.println("메인 !");
        for(int i=0; i<weather.length;i++){
            System.out.println(weather[i]);
        }
        //0 : 온도 1: 구름많음 2. 밤낮 3 강수 중 4: 강수확률 5: 강수없음
        degree.setText(weather[0]+"C˚");
        // 밤낮구분
        if(weather[2].equals("1")){
            weatherBackground.setBackgroundResource(R.drawable.day);

        }else {
            weatherBackground.setBackgroundResource(R.drawable.night);
        }
        if(weather[1].equals("맑음")){
            weatherImg.setImageResource(R.drawable.sun);
            rainPorb.setText("강수확률 : "+weather[4]+"%");
        }
        else if(weather[1].equals("구름많음")){
            weatherImg.setImageResource(R.drawable.cloudy);
            rainPorb.setText("강수확률 : "+weather[4]+"%");
        }
        else if(weather[1].equals("흐림")){
            weatherImg.setImageResource(R.drawable.cloud);
            rainPorb.setText("강수확률 : "+weather[4]+"%");
        }
        else if(weather[1].equals("비")){
            weatherImg.setImageResource(R.drawable.rain);
            rainPorb.setText("강수량 : "+weather[5]+"mm");
        }
        else if(weather[1].equals("눈")){
            weatherImg.setImageResource(R.drawable.snow);
            rainPorb.setText("강수량 : "+weather[5]+"mm");
        }
    }
}