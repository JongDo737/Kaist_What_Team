package com.example.whatmain;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class weatherDislayTest extends AppCompatActivity {

    String latitude, longitude;
    final ArrayList<Weather> data = new ArrayList<>();
    String finalBase_time;
    String finalDate;
    public static Context mContext;
    static final int PERMISSIONS_REQUEST = 0x0000001;
    String abc[] = new String[6];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_dislay_test);
        WeatherData weatherdata = new WeatherData();
        //부산
        int is_daytime;
        if(latitude==null || longitude==null){
            Toast.makeText(this,"위치 정보 가져오기 실패",Toast.LENGTH_LONG).show();
        }
        System.out.println("###Callback");

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat _date = new SimpleDateFormat("yyyyMMdd");
        String date = _date.format(calendar.getTime());
        SimpleDateFormat _time = new SimpleDateFormat("HHmm");
        String time = _time.format(calendar.getTime());

        if (time.compareTo("0600") >= 0 && time.compareTo("1800") < 0) {
            is_daytime = 1;
        }else{
            is_daytime = 0;
        }
        String base_time = "0200";

        // basetime 조정
        if (time.compareTo("0000") >= 0 && time.compareTo("0211") < 0) {
            date = Integer.toString(Integer.parseInt(date) - 1);
            base_time = "2300";
        } else if (time.compareTo("0211") >= 0 && time.compareTo("0511") < 0) {
            base_time = "0200";
        } else if (time.compareTo("0511") >= 0 && time.compareTo("0811") < 0) {
            base_time = "0500";
        } else if (time.compareTo("0811") >= 0 && time.compareTo("1111") < 0) {
            base_time = "0800";
        } else if (time.compareTo("1111") >= 0 && time.compareTo("1411") < 0) {
            base_time = "1100";
        } else if (time.compareTo("1411") >= 0 && time.compareTo("1711") < 0) {
            base_time = "1400";
        } else if (time.compareTo("1711") >= 0 && time.compareTo("2011") < 0) {
            base_time = "1700";
        } else if (time.compareTo("2011") >= 0 && time.compareTo("2311") < 0) {
            base_time = "2000";
        } else {
            base_time = "2300";
        }

        finalBase_time = base_time;
        finalDate = date;
        System.out.println("### Final DATE: " + finalDate);
        System.out.println("### Final TIME: " + finalBase_time);
        System.out.println("### is_daytime : " + is_daytime);
        try {
            abc = weatherdata.getWeather("98","76", finalDate,finalBase_time,"json",is_daytime);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for(int i=0; i<abc.length;i++){
            System.out.println("여기여기");
            System.out.println(abc[i]);
        }
    }

}