package com.example.whatmain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.Serializable;

public class FestivalFullImage extends AppCompatActivity implements OnMapReadyCallback, Serializable {

    private GoogleMap mMap;
    Button button;

    double foodLatitude;
    double foodLongitude;
    BusanFestivalDto get_festivalDto;
    String foodUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("festivalFullImage야!!!!!!!");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_festival_full_image);

        //userName 하드코딩된 거 바꿔야 함 ***********************
        foodUserName="지나";

        TextView foodMainTitle=(TextView) findViewById(R.id.foodMainTitle);
        //TextView foodPlace1=(TextView) findViewById(R.id.foodPlace1);
        TextView festivalDate=(TextView) findViewById(R.id.festivalDate);
        TextView festivalHomePage=(TextView) findViewById(R.id.festivalHomePage);
        ImageView foodFullImg=(ImageView) findViewById(R.id.foodFullImg);
        TextView foodSubTitle=(TextView) findViewById(R.id.foodSubTitle);
        TextView foodContext=(TextView) findViewById(R.id.foodContext);
        TextView foodPlace2=(TextView) findViewById(R.id.foodPlace2);



        //intent 에 클릭된 dto 받아오기
        Intent i=getIntent();
        //intent 에 클릭된 dto 로 업데이트 해주기
        get_festivalDto=(BusanFestivalDto) i.getSerializableExtra("Dto");
        System.out.println(get_festivalDto.getCall()+"awifnaseogasehiogasogiasjgioasejgio");
        foodMainTitle.setText(get_festivalDto.getMainTitle());
        //foodPlace1.setText(get_festivalDto.getPlace());
        //foodFullImg.setImageURI();
        Glide.with(getApplicationContext()).load(get_festivalDto.getImg()).into(foodFullImg);
        festivalDate.setText(get_festivalDto.getDate());
        festivalHomePage.setText(get_festivalDto.getHomePage());
        
        foodSubTitle.setText(get_festivalDto.getSubTitle());
        foodContext.setText(get_festivalDto.getContext());
        foodPlace2.setText(get_festivalDto.getPlace());

        //Map 해주기 latitude, longitude
        foodLatitude=get_festivalDto.getLatitude();
        foodLongitude=get_festivalDto.getLongitude();


//        int position=i.getExtras().getInt("position");
//        Log.v("fullImage.java:", Integer.toString(position));

        //ArrayList<BusanFestivalDto> festivalList=i.getExtras().getParcelableArrayList("festivalList");
//        ArrayList<BusanFestivalDto> festivalList=(ArrayList<BusanFestivalDto>) i.getSerializableExtra("festivalList");
//
//
//        Glide.with(getApplicationContext()).load(festivalList.get(position).getImg()).into(img);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),BusanFestivalStep1.class);
                startActivity(intent);
            }
        });

        //저장하기 하트 클릭
        ImageView heartsave=(ImageView) findViewById(R.id.heartSave);
        heartsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                heartsave.setImageResource(R.drawable.redheart);
                // userName 저장 누른 항목 리스트에 추가하기 *****************************
                //userid랑 선택 리스트값

            }
        });
        ImageView foodCalendar=(ImageView) findViewById(R.id.foodCalendar);
        foodCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Calendar에 추가하기

            }
        });

        ImageView foodCall=(ImageView) findViewById(R.id.foodCall);
        Log.v("click: ", "before click!!!");
        foodCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(),"Phone: "+get_foodDto.getCall(),Toast.LENGTH_LONG).show();
                //전화걸기
                if(get_festivalDto.getCall()==null || get_festivalDto.getCall()=="") {
                    //전화번호 없으면 Toast 띄우기
                    Toast.makeText(getApplicationContext(), "전화번호가 없습니다", Toast.LENGTH_LONG).show();
                }
                else{
                    String tel="tel:"+get_festivalDto.getCall();
                    startActivity(new Intent("android.intent.action.DIAL", Uri.parse(tel)));
                }
            }
        });
        //문자로 축제 링크 공유하기
        ImageView foodSend=(ImageView)findViewById(R.id.foodSend);
        foodSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String tel="smsto:"+"01000000000";
                //startActivity(new Intent("android.intent.action.DIAL", Uri.parse(tel)));
                String message;
                Intent smsIntent = new Intent(Intent.ACTION_VIEW);
                smsIntent.setType("vnd.android-dir/mms-sms");
                //smsIntent.putExtra("address", find_phone);
                message=get_festivalDto.getHomePage();
                smsIntent.putExtra("sms_body",message);
                startActivity(smsIntent);
            }
        });

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        LatLng location = new LatLng(foodLatitude, foodLongitude);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(location);
        markerOptions.title(get_festivalDto.getMainTitle());
        mMap.addMarker(markerOptions);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 10));
    }
}