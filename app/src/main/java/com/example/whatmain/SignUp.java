package com.example.whatmain;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Calendar;

public class SignUp extends AppCompatActivity {
    TextView title;

    EditText signUpUsername;
    EditText signupPW;

    ImageButton calendar;
    TextView birthText;

    CheckBox checkMale;
    CheckBox checkFemale;

    Button commitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // 밑줄긋기
        title = findViewById(R.id.title);
        title.setPaintFlags(title.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        signUpUsername =  findViewById(R.id.signUpUsername);
        signupPW = findViewById(R.id.signupPW);
        calendar = findViewById(R.id.calendar);
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
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        commitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserDto userDto = new UserDto();
                userDto.setUsername(signUpUsername.getText().toString());
                userDto.setPassword(signupPW.getText().toString());
                userDto.setBirth(birthText.getText().toString());
            }
        });



    }
}