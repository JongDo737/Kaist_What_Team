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

public class TodoFullImage extends AppCompatActivity implements OnMapReadyCallback, Serializable {
    private GoogleMap mMap;
    Button button;

    double foodLatitude;
    double foodLongitude;
    BusanTodoDto get_todoDto;
    String userId;
    boolean checkHeart = false;
    ArrayList<BusanTodoDto> TodoLikeList = new ArrayList<>();
    BusanTodoDto busanTodoDto;
    ImageView heartsave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_full_image);

        TextView foodMainTitle=(TextView) findViewById(R.id.foodMainTitle);
        TextView foodPlace1=(TextView) findViewById(R.id.foodPlace1);
        ImageView foodFullImg=(ImageView) findViewById(R.id.foodFullImg);
        TextView foodSubTitle=(TextView) findViewById(R.id.foodSubTitle);
        TextView foodContext=(TextView) findViewById(R.id.foodContext);
        TextView foodPlace2=(TextView) findViewById(R.id.foodPlace2);
        Intent i=getIntent();
        userId = i.getStringExtra("userId");
        // ????????? ?????? ?????????
        try {
            getHeartByUserId(Integer.parseInt(userId));
        } catch (JSONException e) {
            e.printStackTrace();
        }


        //intent ??? ????????? dto ????????????

        //intent ??? ????????? dto ??? ???????????? ?????????
        get_todoDto=(BusanTodoDto) i.getSerializableExtra("Dto");
        System.out.println(get_todoDto.getMainTitle()+"todo!!!: awifnaseogasehiogasogiasjgioasejgio");
        foodMainTitle.setText(get_todoDto.getMainTitle());
        foodPlace1.setText(get_todoDto.getPlace());
        //foodFullImg.setImageURI();
        Glide.with(getApplicationContext()).load(get_todoDto.getImg()).into(foodFullImg);
        foodSubTitle.setText(get_todoDto.getSubTitle());
        foodContext.setText(get_todoDto.getContext());
        foodPlace2.setText(get_todoDto.getPlace());

        //Map ????????? latitude, longitude
        foodLatitude=get_todoDto.getLatitude();
        foodLongitude=get_todoDto.getLongitude();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),BusanTodoStep1.class);
                startActivity(intent);
            }
        });


        ImageView foodCalendar=(ImageView) findViewById(R.id.foodCalendar);
        foodCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Calendar??? ????????????

            }
        });
        //???????????? ????????? ??????!!!
//        ImageView foodCall=(ImageView) findViewById(R.id.foodCall);
//        Log.v("click: ", "before click!!!");
//        foodCall.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //Toast.makeText(getApplicationContext(),"Phone: "+get_foodDto.getCall(),Toast.LENGTH_LONG).show();
//                //????????????
//                if(get_todoDto.getCall()==null || get_todoDto.getCall()=="") {
//                    //???????????? ????????? Toast ?????????
//                    Toast.makeText(getApplicationContext(), "??????????????? ????????????", Toast.LENGTH_LONG).show();
//                }
//                else{
//                    String tel="tel:"+get_todoDto.getCall();
//                    startActivity(new Intent("android.intent.action.DIAL", Uri.parse(tel)));
//                }
//            }
//        });
        //????????? ????????? ?????? ????????????
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
                message="https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=0&ie=utf8&query="+get_todoDto.getMainTitle();
                smsIntent.putExtra("sms_body",message);
                startActivity(smsIntent);
            }
        });
        //???????????? ?????? ??????
        heartsave=(ImageView) findViewById(R.id.heartSave);

        heartsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // ????????? ???????????? ??????
                if(checkHeart){
                    heartsave.setImageResource(R.drawable.heart);
                    // ????????? ??????
                    checkHeart = false;

                    dislikeHeart(Integer.parseInt(userId),get_todoDto.getId());


                }else {
                    // ?????? ????????? ??????
                    heartsave.setImageResource(R.drawable.redheart);
                    // ????????? ????????????
                    checkHeart = true;
                    System.out.println("?????????~~~~~~~~~ id :"+get_todoDto.getId());
                    likeHeart(Integer.parseInt(userId),get_todoDto.getId());

                }

            }
        });

    }

    ///???????????? ?????? ??????!!!!!************************************************
    //?????????
    public void getHeartByUserId(int userId) throws JSONException {
        Localhost localhost = new Localhost();
        String url = localhost.getLocalhost() + "/likeTodoList";

        //JSON???????????? ????????? ????????? ???????????????!
        JSONObject testjson = new JSONObject();
        try {

            testjson.put("userId", userId);

            String jsonString = testjson.toString(); //????????? json ??????
            System.out.println(jsonString);
            //?????? ???????????????????
            final RequestQueue requestQueue = Volley.newRequestQueue(this);

            final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, testjson, new Response.Listener<JSONObject>() {

                //????????? ????????? ????????? ?????? ??? ????????? ?????? ???????????????.
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        //?????? json????????? ????????? ??????

                        JSONObject jsonObj_1 = new JSONObject(response.toString());
                        System.out.println("jsonObj_1 : "+jsonObj_1);
                        String jsonData = jsonObj_1.getString("body");
                        System.out.println("jsonData : "+jsonData);
                        JSONArray jsonArray = new JSONArray(jsonData);
                        ArrayList<Integer> likeList = new ArrayList<>();
                        for(int i=0; i<jsonArray.length();i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            likeList.add(Integer.parseInt((String) jsonObject.get("todo_id")));
                        }
                        for(int i=0; i<likeList.size(); i++){
                            if(get_todoDto.getId() == likeList.get(i)) checkHeart =true;
                        }
                        if(checkHeart) heartsave.setImageResource(R.drawable.redheart);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                //????????? ????????? ?????? ??? ?????? ????????? ????????? ?????? ?????? ????????? ???????????????.
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
    public void likeHeart(int userId, int todoId){
        Localhost localhost = new Localhost();
        String url = localhost.getLocalhost() + "/likeTodoHeart";

        //JSON???????????? ????????? ????????? ???????????????!
        JSONObject testjson = new JSONObject();
        try {
            //???????????? edittext??? id??? pw?????? ????????? put???????????? : ???????????? json???????????? ?????? ?????????????????????.
            testjson.put("userId", userId);
            testjson.put("todoId", todoId);

            String jsonString = testjson.toString(); //????????? json ??????
            System.out.println(jsonString);
            //?????? ???????????????????
            final RequestQueue requestQueue = Volley.newRequestQueue(this);
            final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, testjson, new Response.Listener<JSONObject>() {

                //????????? ????????? ????????? ?????? ??? ????????? ?????? ???????????????.
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        //?????? json????????? ????????? ??????
                        JSONObject jsonObject = new JSONObject(response.toString());
//
//                       //key?????? ?????? value?????? ?????? ???????????????.
                        String resultCode = jsonObject.getString("code");
                        int loginCode = Integer.parseInt(resultCode);
                        System.out.println("loginCode : "+loginCode);
                        if(loginCode==1){
                            Toast.makeText(getApplicationContext(), get_todoDto.getMainTitle()+" ????????? !", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getApplicationContext(), "????????? ??????", Toast.LENGTH_SHORT).show();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                //????????? ????????? ?????? ??? ?????? ????????? ????????? ?????? ?????? ????????? ???????????????.
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

    public void dislikeHeart(int userId, int todoId){
        Localhost localhost = new Localhost();
        String url = localhost.getLocalhost() + "/dislikeTodoHeart";

        //JSON???????????? ????????? ????????? ???????????????!
        JSONObject testjson = new JSONObject();
        try {
            //???????????? edittext??? id??? pw?????? ????????? put???????????? : ???????????? json???????????? ?????? ?????????????????????.
            testjson.put("userId", userId);
            testjson.put("todoId", todoId);
            String jsonString = testjson.toString(); //????????? json ??????
            System.out.println(jsonString);
            //?????? ???????????????????
            final RequestQueue requestQueue = Volley.newRequestQueue(this);
            final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, testjson, new Response.Listener<JSONObject>() {

                //????????? ????????? ????????? ?????? ??? ????????? ?????? ???????????????.
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        //?????? json????????? ????????? ??????
                        JSONObject jsonObject = new JSONObject(response.toString());
//
//                       //key?????? ?????? value?????? ?????? ???????????????.
                        String resultCode = jsonObject.getString("code");
                        int loginCode = Integer.parseInt(resultCode);
                        System.out.println("loginCode : "+loginCode);
                        if(loginCode==1){
                            Toast.makeText(getApplicationContext(), get_todoDto.getMainTitle()+" ????????? ??????!", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getApplicationContext(), "????????? ?????? ??????", Toast.LENGTH_SHORT).show();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                //????????? ????????? ?????? ??? ?????? ????????? ????????? ?????? ?????? ????????? ???????????????.
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
        markerOptions.title(get_todoDto.getMainTitle());
        mMap.addMarker(markerOptions);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 10));
    }
}
