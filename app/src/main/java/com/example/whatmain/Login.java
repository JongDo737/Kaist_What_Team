package com.example.whatmain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.Request;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {

    EditText et_id;
    EditText et_password;

    Button signinBtn;
    Button signupBtn;

    LinearLayout naverBtn;
    LinearLayout kakaoBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_id = findViewById(R.id.et_id);
        et_password = findViewById(R.id.et_password);
        signinBtn = findViewById(R.id.signinBtn);
        signupBtn = findViewById(R.id.signupBtn);
        naverBtn = findViewById(R.id.naverBtn);
        kakaoBtn = findViewById(R.id.kakaoBtn);

        // 로그인 버튼
        signinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = et_id.getText().toString();
                String password = et_password.getText().toString();
                // 빈칸 처리
                if(isEmpty(username, password)){
                    requestLogin(username,password);
                }
            }
        });
        // 회원가입 버튼
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(intent);
            }
        });
    }
    //빈칸체크
    public boolean isEmpty(String username, String password) {
        // 빈칸체크
        System.out.println("password");
        System.out.println(username);
        System.out.println(password);
        System.out.println("password");
        Toast myToast;
        if (username == null && username == "") {
            myToast = Toast.makeText(this.getApplicationContext(), "아이디를 입력해주세요", Toast.LENGTH_SHORT);
            myToast.show();
            return false;
        }
        if (password == null && password == "") {
            myToast = Toast.makeText(this.getApplicationContext(), "비밀번호를 입력해주세요", Toast.LENGTH_SHORT);
            myToast.show();
            return false;
        }

        return true;
    }

    public void requestLogin(String ID, String PW) {
        Localhost localhost = new Localhost();
        String url = localhost.getLocalhost() + "/login" ;
        //JSON형식으로 데이터 통신을 진행합니다!
        JSONObject testjson = new JSONObject();
        try {
            //입력해둔 edittext의 id와 pw값을 받아와 put해줍니다 : 데이터를 json형식으로 바꿔 넣어주었습니다.
            testjson.put("username", ID);
            testjson.put("password", PW);
            String jsonString = testjson.toString(); //완성된 json 포맷
            //이제 전송해볼까요?
            final RequestQueue requestQueue = Volley.newRequestQueue(this);
            final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url,testjson, new Response.Listener<JSONObject>() {

                //데이터 전달을 끝내고 이제 그 응답을 받을 차례입니다.
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        //받은 json형식의 응답을 받아
                        JSONObject jsonObject = new JSONObject(response.toString());

                        //key값에 따라 value값을 쪼개 받아옵니다.
                        String resultCode = jsonObject.getString("login_code");

                        int loginCode = Integer.parseInt(resultCode);
                        System.out.println(loginCode+"ㅁㄴㅇㅁㄴㅇ");
                        if(loginCode == 1){
                            System.out.println("로긴성공");
                            Toast.makeText(getApplicationContext(),"로그인 성공",Toast.LENGTH_LONG).show();
                            String userId = jsonObject.getString("user_id");
                            Intent intent = new Intent(getApplicationContext(), FirstPage.class);
                            intent.putExtra("userId",userId);
                            startActivity(intent);
                        }else if(loginCode == 2){
                            System.out.println("아이디 틀림");
                            Toast.makeText(getApplicationContext(),"아이디가 존재하지 않습니다.",Toast.LENGTH_LONG).show();
                        }else if(loginCode == 3){
                            System.out.println("비번틀림");
                            Toast.makeText(getApplicationContext(),"비밀번호가 틀렸습니다.",Toast.LENGTH_LONG).show();
                        }else {
                            System.out.println("시스템오류");
                            Toast.makeText(getApplicationContext(),"시스템 오류",Toast.LENGTH_LONG).show();
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



}