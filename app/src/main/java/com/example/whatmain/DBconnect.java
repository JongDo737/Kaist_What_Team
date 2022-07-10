package com.example.whatmain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DBconnect extends AppCompatActivity implements DBconnectImpl{

    EditText editTextID;
    EditText editTextPW;
    Button buttonLogin;

    String ID;
    String PW;
    String localhost = "https://838e-192-249-18-216.jp.ngrok.io";
    ArrayList<BusanFoodDto> foodList = new ArrayList<>();
    int loginCode = 0;
    BusanFoodDto busanFoodDto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public int requestLogin(String ID, String PW, Context test) {
        String url = localhost + "/login" ;
        //JSON형식으로 데이터 통신을 진행합니다!
        JSONObject testjson = new JSONObject();
        try {
            //입력해둔 edittext의 id와 pw값을 받아와 put해줍니다 : 데이터를 json형식으로 바꿔 넣어주었습니다.
            testjson.put("username", ID);
            testjson.put("password", PW);
            String jsonString = testjson.toString(); //완성된 json 포맷
            System.out.println("11111111111111");
            //이제 전송해볼까요?
            final RequestQueue requestQueue = Volley.newRequestQueue(test);
            final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url,testjson, new Response.Listener<JSONObject>() {

                //데이터 전달을 끝내고 이제 그 응답을 받을 차례입니다.
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        //받은 json형식의 응답을 받아
                        JSONObject jsonObject = new JSONObject(response.toString());

                        //key값에 따라 value값을 쪼개 받아옵니다.
                        String resultCode = jsonObject.getString("login_code");
                        loginCode = Integer.parseInt(resultCode);
                        System.out.println(loginCode+"디비연결하는 곳에서");

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
        return loginCode;
    }

    //회원가입
    @Override
    public int insertUser(UserDto userDto, Context test) {
        String url = localhost + "/insertUser";

        //JSON형식으로 데이터 통신을 진행합니다!
        JSONObject testjson = new JSONObject();
        try {
            //입력해둔 edittext의 id와 pw값을 받아와 put해줍니다 : 데이터를 json형식으로 바꿔 넣어주었습니다.
            testjson.put("username", userDto.username);
            testjson.put("password", userDto.password);
            testjson.put("birth", userDto.birth);
            testjson.put("gender", userDto.gender);
            testjson.put("create_date", userDto.create_date);
            testjson.put("update_date", userDto.update_date);
            String jsonString = testjson.toString(); //완성된 json 포맷
            System.out.println("11111111111111111");
            System.out.println(url);
            System.out.println(testjson);
            //이제 전송해볼까요?
            final RequestQueue requestQueue = Volley.newRequestQueue(test);
            final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, testjson, new Response.Listener<JSONObject>() {

                //데이터 전달을 끝내고 이제 그 응답을 받을 차례입니다.
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        //받은 json형식의 응답을 받아
                        JSONObject jsonObject = new JSONObject(response.toString());

                        Toast.makeText(getApplicationContext(), "회원가입 성공", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), SignUp.class);
                        startActivity(intent);
                        finish();

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
            return 1;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 1;
    }

    // 태그로 음식 정보 받아오기
    @Override
    public ArrayList<BusanFoodDto> getFoodListByTags(ArrayList<String> tags, Context test){

        String url = localhost + "/getFoodByTags";

        //JSON형식으로 데이터 통신을 진행합니다!
        JSONObject testjson = new JSONObject();
        try {
            for(int i=0; i<tags.size(); i++){
                testjson.put("tag"+(i+1) ,tags.get(i));
            }
            testjson.put("count", tags.size()+"");

            String jsonString = testjson.toString(); //완성된 json 포맷
            System.out.println("11111111111111111");
            System.out.println(url);
            System.out.println(testjson);
            //이제 전송해볼까요?
            final RequestQueue requestQueue = Volley.newRequestQueue(test);

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
                        foodList = jsonParse(jsonData);
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
    return foodList;
    }




    @Override
    public ArrayList<BusanFestivalDto> getAllFestival() {








        return null;
    }

    void easyToast(String str){
        Toast.makeText(getApplicationContext(),str,Toast.LENGTH_SHORT).show();
    }
    public ArrayList<BusanFoodDto> jsonParse(String jsonStr) throws JSONException {

        JSONArray jsonArray = new JSONArray(jsonStr);
        ArrayList<BusanFoodDto> foodListS = new ArrayList<>();
        System.out.println(jsonArray.length()+"wlfmawflawflawnfkawlnfawklfnawkflanfakl");
        for(int i=0; i<jsonArray.length();i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            System.out.println(i+"번째"+(String)jsonObject.get("mainTitle"));

            busanFoodDto = new BusanFoodDto();
            busanFoodDto.setMainTitle((String)jsonObject.get("mainTitle"));
            busanFoodDto.setPlace((String)jsonObject.get("place"));
            busanFoodDto.setSubTitle((String)jsonObject.get("subTitle"));
            busanFoodDto.setImg((String)jsonObject.get("img"));
            busanFoodDto.setContext((String)jsonObject.get("context"));
            busanFoodDto.setCall((String)jsonObject.get("call"));

            //태그 작업
            String tags = (String)jsonObject.get("tag1");
            String[] arrTags = tags.split(" ");
            if(arrTags.length==1){
                busanFoodDto.setTag1(arrTags[0]);
            }else if(arrTags.length==2){
                busanFoodDto.setTag1(arrTags[0]);
                busanFoodDto.setTag2(arrTags[1]);
            }
            else if(arrTags.length==3){
                busanFoodDto.setTag1(arrTags[0]);
                busanFoodDto.setTag2(arrTags[1]);
                busanFoodDto.setTag3(arrTags[2]);
            }
            foodListS.add(busanFoodDto);
        }

        return foodListS;

    }

}