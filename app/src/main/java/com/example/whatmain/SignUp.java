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
    DBconnect dBconnect = new DBconnect();
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
                            dBconnect.insertUser(userDto, getApplicationContext());
                            //회원가입 완료 후 다시 로그인 창
                            Intent intent = new Intent(getApplicationContext(),Login.class);
                            startActivity(intent);
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


}