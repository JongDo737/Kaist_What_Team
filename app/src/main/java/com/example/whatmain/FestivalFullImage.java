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

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class FestivalFullImage extends AppCompatActivity implements OnMapReadyCallback, Serializable {

    private GoogleMap mMap;
    Button button;
    ImageView heartsave;
    double foodLatitude;
    double foodLongitude;
    BusanFestivalDto get_festivalDto;
    boolean checkHeart = false;
    String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("festivalFullImageģ¼!!!!!!!");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_festival_full_image);

        TextView foodMainTitle=(TextView) findViewById(R.id.foodMainTitle);
        TextView foodPlace1=(TextView) findViewById(R.id.foodPlace1);
        TextView festivalDate=(TextView) findViewById(R.id.festivalDate);
        TextView festivalHomePage=(TextView) findViewById(R.id.festivalHomePage);
        ImageView foodFullImg=(ImageView) findViewById(R.id.foodFullImg);
        TextView foodSubTitle=(TextView) findViewById(R.id.foodSubTitle);
        TextView foodContext=(TextView) findViewById(R.id.foodContext);
        TextView foodPlace2=(TextView) findViewById(R.id.foodPlace2);



        //intent ģ ķ“ė¦­ė dto ė°ģģ¤źø°
        Intent i=getIntent();
        userId = i.getStringExtra("userId");

        // ģ²«ķė©“ ķķø ģ“źø°ķ
        try {
            getHeartByUserId(Integer.parseInt(userId));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //intent ģ ķ“ė¦­ė dto ė” ģė°ģ“ķø ķ“ģ£¼źø°
        get_festivalDto=(BusanFestivalDto) i.getSerializableExtra("Dto");
        System.out.println(get_festivalDto.getCall()+"awifnaseogasehiogasogiasjgioasejgio");
        foodMainTitle.setText(get_festivalDto.getMainTitle());
        foodPlace1.setText(get_festivalDto.getPlace());
        //foodFullImg.setImageURI();
        Glide.with(getApplicationContext()).load(get_festivalDto.getImg()).into(foodFullImg);
        festivalDate.setText(get_festivalDto.getDate());
        festivalHomePage.setText(get_festivalDto.getHomePage());
        
        foodSubTitle.setText(get_festivalDto.getSubTitle());
        foodContext.setText(get_festivalDto.getContext());
        foodPlace2.setText(get_festivalDto.getPlace());

        //Map ķ“ģ£¼źø° latitude, longitude
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

        //ģ ģ„ķźø° ķķø ķ“ė¦­
        heartsave=(ImageView) findViewById(R.id.heartSave);
        heartsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                heartsave.setImageResource(R.drawable.redheart);

                // ģ¢ģģ ģķģ¼ė ķ“ė¦­
                if(checkHeart){
                    heartsave.setImageResource(R.drawable.heart);
                    // ģ¢ģģ ģ·Øģ
                    checkHeart = false;

                    dislikeHeart(Integer.parseInt(userId),get_festivalDto.getId());


                }else {
                    // ķķø ė¹ģ¹øģ¼ ėė
                    heartsave.setImageResource(R.drawable.redheart);
                    // ģ¢ģģ ė£ģ“ģ£¼źø°
                    checkHeart = true;
                    System.out.println("ģØķ“ė¦­~~~~~~~~~ id :"+get_festivalDto.getId());
                    likeHeart(Integer.parseInt(userId),get_festivalDto.getId());

                }
            }
        });
        ImageView foodCalendar=(ImageView) findViewById(R.id.foodCalendar);
        foodCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Calendarģ ģ¶ź°ķźø°

            }
        });

        ImageView foodCall=(ImageView) findViewById(R.id.foodCall);
        Log.v("click: ", "before click!!!");
        foodCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ģ ķź±øźø°
                if(get_festivalDto.getCall()==null || get_festivalDto.getCall()=="") {
                    //ģ ķė²ķø ģģ¼ė©“ Toast ėģ°źø°
                    Toast.makeText(getApplicationContext(), "ģ ķė²ķøź° ģģµėė¤", Toast.LENGTH_LONG).show();
                }
                else{
                    String tel="tel:"+get_festivalDto.getCall();
                    startActivity(new Intent("android.intent.action.DIAL", Uri.parse(tel)));
                }
            }
        });
        //ė¬øģė” ģ¶ģ  ė§ķ¬ ź³µģ ķźø°
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
    //ģ“źø°ķ
    public void getHeartByUserId(int userId) throws JSONException {
        Localhost localhost = new Localhost();
        String url = localhost.getLocalhost() + "/likeFestivalList";

        //JSONķģģ¼ė” ė°ģ“ķ° ķµģ ģ ģ§ķķ©ėė¤!
        JSONObject testjson = new JSONObject();
        try {

            testjson.put("userId", userId);

            String jsonString = testjson.toString(); //ģģ±ė json ķ¬ė§·
            System.out.println(jsonString);
            //ģ“ģ  ģ ģ”ķ“ė³¼ź¹ģ?
            final RequestQueue requestQueue = Volley.newRequestQueue(this);

            final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, testjson, new Response.Listener<JSONObject>() {

                //ė°ģ“ķ° ģ ė¬ģ ėė“ź³  ģ“ģ  ź·ø ģėµģ ė°ģ ģ°Øė”ģėė¤.
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        //ė°ģ jsonķģģ ģėµģ ė°ģ

                        JSONObject jsonObj_1 = new JSONObject(response.toString());
                        System.out.println("jsonObj_1 : "+jsonObj_1);
                        String jsonData = jsonObj_1.getString("body");
                        System.out.println("jsonData : "+jsonData);
                        JSONArray jsonArray = new JSONArray(jsonData);
                        ArrayList<Integer> likeList = new ArrayList<>();
                        for(int i=0; i<jsonArray.length();i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            likeList.add(Integer.parseInt((String) jsonObject.get("festival_id")));
                        }
                        for(int i=0; i<likeList.size(); i++){
                            if(get_festivalDto.getId() == likeList.get(i)) checkHeart =true;
                        }
                        if(checkHeart) heartsave.setImageResource(R.drawable.redheart);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                //ģė²ė” ė°ģ“ķ° ģ ė¬ ė° ģėµ ė°źø°ģ ģ¤ķØķ ź²½ģ° ģė ģ½ėź° ģ¤ķė©ėė¤.
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
            });
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            requestQueue.add(jsonObjectRequest);
        }  catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void likeHeart(int userId, int festivalId){
        Localhost localhost = new Localhost();
        String url = localhost.getLocalhost() + "/likeFestivalHeart";

        //JSONķģģ¼ė” ė°ģ“ķ° ķµģ ģ ģ§ķķ©ėė¤!
        JSONObject testjson = new JSONObject();
        try {
            //ģė „ķ“ė edittextģ idģ pwź°ģ ė°ģģ putķ“ģ¤ėė¤ : ė°ģ“ķ°ė„¼ jsonķģģ¼ė” ė°źæ ė£ģ“ģ£¼ģģµėė¤.
            testjson.put("userId", userId);
            testjson.put("festivalId", festivalId);

            String jsonString = testjson.toString(); //ģģ±ė json ķ¬ė§·
            System.out.println(jsonString);
            //ģ“ģ  ģ ģ”ķ“ė³¼ź¹ģ?
            final RequestQueue requestQueue = Volley.newRequestQueue(this);
            final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, testjson, new Response.Listener<JSONObject>() {

                //ė°ģ“ķ° ģ ė¬ģ ėė“ź³  ģ“ģ  ź·ø ģėµģ ė°ģ ģ°Øė”ģėė¤.
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        //ė°ģ jsonķģģ ģėµģ ė°ģ
                        JSONObject jsonObject = new JSONObject(response.toString());
//
//                       //keyź°ģ ė°ė¼ valueź°ģ ģŖ¼ź° ė°ģģµėė¤.
                        String resultCode = jsonObject.getString("code");
                        int loginCode = Integer.parseInt(resultCode);
                        System.out.println("loginCode : "+loginCode);
                        if(loginCode==1){
                            Toast.makeText(getApplicationContext(), get_festivalDto.getMainTitle()+" ģ¢ģģ !", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getApplicationContext(), "ģ¢ģģ ģ¤ķØ", Toast.LENGTH_SHORT).show();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                //ģė²ė” ė°ģ“ķ° ģ ė¬ ė° ģėµ ė°źø°ģ ģ¤ķØķ ź²½ģ° ģė ģ½ėź° ģ¤ķė©ėė¤.
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
            });
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            requestQueue.add(jsonObjectRequest);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void dislikeHeart(int userId, int festivalId){
        Localhost localhost = new Localhost();
        String url = localhost.getLocalhost() + "/dislikeFestivalHeart";

        //JSONķģģ¼ė” ė°ģ“ķ° ķµģ ģ ģ§ķķ©ėė¤!
        JSONObject testjson = new JSONObject();
        try {
            //ģė „ķ“ė edittextģ idģ pwź°ģ ė°ģģ putķ“ģ¤ėė¤ : ė°ģ“ķ°ė„¼ jsonķģģ¼ė” ė°źæ ė£ģ“ģ£¼ģģµėė¤.
            testjson.put("userId", userId);
            testjson.put("festivalId", festivalId);
            String jsonString = testjson.toString(); //ģģ±ė json ķ¬ė§·
            System.out.println(jsonString);
            //ģ“ģ  ģ ģ”ķ“ė³¼ź¹ģ?
            final RequestQueue requestQueue = Volley.newRequestQueue(this);
            final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, testjson, new Response.Listener<JSONObject>() {

                //ė°ģ“ķ° ģ ė¬ģ ėė“ź³  ģ“ģ  ź·ø ģėµģ ė°ģ ģ°Øė”ģėė¤.
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        //ė°ģ jsonķģģ ģėµģ ė°ģ
                        JSONObject jsonObject = new JSONObject(response.toString());
//
//                       //keyź°ģ ė°ė¼ valueź°ģ ģŖ¼ź° ė°ģģµėė¤.
                        String resultCode = jsonObject.getString("code");
                        int loginCode = Integer.parseInt(resultCode);
                        System.out.println("loginCode : "+loginCode);
                        if(loginCode==1){
                            Toast.makeText(getApplicationContext(), get_festivalDto.getMainTitle()+" ģ¢ģģ ģ·Øģ!", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getApplicationContext(), "ģ¢ģģ ģ·Øģ ģ¤ķØ", Toast.LENGTH_SHORT).show();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                //ģė²ė” ė°ģ“ķ° ģ ė¬ ė° ģėµ ė°źø°ģ ģ¤ķØķ ź²½ģ° ģė ģ½ėź° ģ¤ķė©ėė¤.
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
            });
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            requestQueue.add(jsonObjectRequest);

        } catch (JSONException e) {
            e.printStackTrace();
        }
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