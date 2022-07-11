package com.example.whatmain;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SignUp extends AppCompatActivity {
    TextView title;

    EditText signUpUsername;
    EditText signupPW;

    ImageButton calendarBtn;
    TextView birthText;

    CheckBox checkMale;
    CheckBox checkFemale;

    Button commitBtn;

    DatePickerDialog datePickerDialog;
    NowDate nowDate = new NowDate();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // 밑줄긋기
        title = findViewById(R.id.title);
        title.setPaintFlags(title.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        signUpUsername =  findViewById(R.id.signUpUsername);
        signupPW = findViewById(R.id.signupPW);
        calendarBtn = findViewById(R.id.calendar);
        birthText = findViewById(R.id.birthText);
        checkMale = findViewById(R.id.checkMale);
        checkFemale = findViewById(R.id.checkFemale);
        commitBtn = findViewById(R.id.commitBtn);
        Calendar myCalendar = Calendar.getInstance();
        //기본값으로 오늘 날짜
        DatePickerDialog.OnDateSetListener setDate= new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                myCalendar.set(Calendar.YEAR, i);
                myCalendar.set(Calendar.MONTH,i1);
                myCalendar.set(Calendar.DAY_OF_MONTH,i2);
                datePicker.updateDate(i,i1,i2);
            }
        };
        calendarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //오늘 날짜(년,월,일) 변수에 담기
                Calendar calendar = Calendar.getInstance();
                int pYear = calendar.get(Calendar.YEAR); //년
                int pMonth = calendar.get(Calendar.MONTH);//월
                int pDay = calendar.get(Calendar.DAY_OF_MONTH);//일

                datePickerDialog = new DatePickerDialog(SignUp.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());
                                //1월은 0부터 시작하기 때문에 +1을 해준다.
                                month = month + 1;
                                String date = year + "/" + month + "/" + day;

                                birthText.setText(date);
                            }
                        }, pYear, pMonth, pDay);
                datePickerDialog.show();
            } //onClick
        });
        checkMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkFemale.setChecked(false);
            }
        });
        checkFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkMale.setChecked(false);
            }
        });
        //확인버튼
        commitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(signUpUsername.getText().toString() != "" && signupPW.getText().toString() != null){
                    if(signupPW.getText().toString() != "" && signupPW.getText().toString() != null){
                        if(birthText.getText().toString() != "" && birthText.getText().toString() != null){
                            UserDto userDto = new UserDto();
                            userDto.setUsername(signUpUsername.getText().toString());
                            userDto.setPassword(signupPW.getText().toString());
                            userDto.setBirth(birthText.getText().toString());
                            userDto.setCreate_date(nowDate.getDate());
                            userDto.setUpdate_date(nowDate.getDate());
                            if (checkMale.isChecked()) {
                                userDto.setGender("male");
                            } else if(checkFemale.isChecked()){
                                userDto.setGender("female");
                            }
                            //회원가입 DB에 넣기
                            insertUser(userDto);
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "생일을 입력해주세요 !", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(getApplicationContext(), "비밀번호를 입력해주세요 !", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(getApplicationContext(), "아이디를 입력해주세요 !", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
        //회원가입 DB
    public void insertUser(UserDto userDto) {
        Localhost localhost = new Localhost();
        String url = localhost.getLocalhost() + "/insertUser";

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
            System.out.println(testjson);
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
//                        //key값에 따라 value값을 쪼개 받아옵니다.
                        String resultCode = jsonObject.getString("code");
                        int loginCode = Integer.parseInt(resultCode);
                        System.out.println("loginCode : "+loginCode);
                        if(loginCode==1){
                            Toast.makeText(getApplicationContext(), "회원가입 성공", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), Login.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(getApplicationContext(), "회원가입 실패", Toast.LENGTH_SHORT).show();
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