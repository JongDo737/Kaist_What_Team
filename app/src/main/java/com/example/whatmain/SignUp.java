package com.example.whatmain;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

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
                System.out.println(userDto.getUsername());
                System.out.println(userDto.getPassword());
                System.out.println(userDto.getBirth());
                System.out.println(userDto.getGender());
                System.out.println(userDto.getCreate_date());
                System.out.println(userDto.getUpdate_date());
                //회원가입 DB에 넣기
                DBconnect dBconnect = new DBconnect();
                dBconnect.insertUser(userDto);
                //회원가입 완료 후 다시 로그인 창
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
            }
        });

    }


}