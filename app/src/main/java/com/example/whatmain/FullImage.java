package com.example.whatmain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.Serializable;
import java.util.ArrayList;

public class FullImage extends AppCompatActivity implements OnMapReadyCallback, Serializable {

    private GoogleMap mMap;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image);

//        ImageView img=(ImageView) findViewById(R.id.fullImg);
//        Intent i=getIntent();
//
//        int position=i.getExtras().getInt("position");
//        Log.v("fullImage.java:", Integer.toString(position));
//
//        //ArrayList<BusanFestivalDto> festivalList=i.getExtras().getParcelableArrayList("festivalList");
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
                Intent intent = new Intent(getApplicationContext(),BusanFoodStep1.class);
                startActivity(intent);
            }
        });

//        ImageView heartsave=findViewById(R.id.heartSave);
//        heartsave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                heartsave.setImageResource(R.drawable.redheart);
//            }
//        });

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        LatLng location = new LatLng(35.1583788, 129.1569762);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(location);
        markerOptions.title("해운대포장마차촌");
        mMap.addMarker(markerOptions);


        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 10));
    }
}