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
        System.out.println("festivalFullImage야!!!!!!!");
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



        //intent 에 클릭된 dto 받아오기
        Intent i=getIntent();
        userId = i.getStringExtra("userId");

        // 첫화면 하트 초기화
        try {
            getHeartByUserId(Integer.parseInt(userId));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //intent 에 클릭된 dto 로 업데이트 해주기
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
        heartsave=(ImageView) findViewById(R.id.heartSave);
        heartsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                heartsave.setImageResource(R.drawable.redheart);

                // 좋아요 상태일때 클릭
                if(checkHeart){
                    heartsave.setImageResource(R.drawable.heart);
                    // 좋아요 취소
                    checkHeart = false;

                    dislikeHeart(Integer.parseInt(userId),get_festivalDto.getId());


                }else {
                    // 하트 빈칸일 때때
                    heartsave.setImageResource(R.drawable.redheart);
                    // 좋아요 넣어주기
                    checkHeart = true;
                    System.out.println("온클릭~~~~~~~~~ id :"+get_festivalDto.getId());
                    likeHeart(Integer.parseInt(userId),get_festivalDto.getId());

                }
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
    //초기화
    public void getHeartByUserId(int userId) throws JSONException {
        Localhost localhost = new Localhost();
        String url = localhost.getLocalhost() + "/likeFestivalList";

        //JSON형식으로 데이터 통신을 진행합니다!
        JSONObject testjson = new JSONObject();
        try {

            testjson.put("userId", userId);

            String jsonString = testjson.toString(); //완성된 json 포맷
            System.out.println(jsonString);
            //이제 전송해볼까요?
            final RequestQueue requestQueue = Volley.newRequestQueue(this);

            final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, testjson, new Response.Listener<JSONObject>() {

                //데이터 전달을 끝내고 이제 그 응답을 받을 차례입니다.
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        //받은 json형식의 응답을 받아

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
                //서버로 데이터 전달 및 응답 받기에 실패한 경우 아래 코드가 실행됩니다.
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

        //JSON형식으로 데이터 통신을 진행합니다!
        JSONObject testjson = new JSONObject();
        try {
            //입력해둔 edittext의 id와 pw값을 받아와 put해줍니다 : 데이터를 json형식으로 바꿔 넣어주었습니다.
            testjson.put("userId", userId);
            testjson.put("festivalId", festivalId);

            String jsonString = testjson.toString(); //완성된 json 포맷
            System.out.println(jsonString);
            //이제 전송해볼까요?
            final RequestQueue requestQueue = Volley.newRequestQueue(this);
            final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, testjson, new Response.Listener<JSONObject>() {

                //데이터 전달을 끝내고 이제 그 응답을 받을 차례입니다.
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        //받은 json형식의 응답을 받아
                        JSONObject jsonObject = new JSONObject(response.toString());
//
//                       //key값에 따라 value값을 쪼개 받아옵니다.
                        String resultCode = jsonObject.getString("code");
                        int loginCode = Integer.parseInt(resultCode);
                        System.out.println("loginCode : "+loginCode);
                        if(loginCode==1){
                            Toast.makeText(getApplicationContext(), get_festivalDto.getMainTitle()+" 좋아요 !", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getApplicationContext(), "좋아요 실패", Toast.LENGTH_SHORT).show();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                //서버로 데이터 전달 및 응답 받기에 실패한 경우 아래 코드가 실행됩니다.
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

        //JSON형식으로 데이터 통신을 진행합니다!
        JSONObject testjson = new JSONObject();
        try {
            //입력해둔 edittext의 id와 pw값을 받아와 put해줍니다 : 데이터를 json형식으로 바꿔 넣어주었습니다.
            testjson.put("userId", userId);
            testjson.put("festivalId", festivalId);
            String jsonString = testjson.toString(); //완성된 json 포맷
            System.out.println(jsonString);
            //이제 전송해볼까요?
            final RequestQueue requestQueue = Volley.newRequestQueue(this);
            final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, testjson, new Response.Listener<JSONObject>() {

                //데이터 전달을 끝내고 이제 그 응답을 받을 차례입니다.
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        //받은 json형식의 응답을 받아
                        JSONObject jsonObject = new JSONObject(response.toString());
//
//                       //key값에 따라 value값을 쪼개 받아옵니다.
                        String resultCode = jsonObject.getString("code");
                        int loginCode = Integer.parseInt(resultCode);
                        System.out.println("loginCode : "+loginCode);
                        if(loginCode==1){
                            Toast.makeText(getApplicationContext(), get_festivalDto.getMainTitle()+" 좋아요 취소!", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getApplicationContext(), "좋아요 취소 실패", Toast.LENGTH_SHORT).show();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                //서버로 데이터 전달 및 응답 받기에 실패한 경우 아래 코드가 실행됩니다.
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