package com.example.whatmain;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Paint;
import android.os.Bundle;
import android.widget.TextView;

public class SignUp extends AppCompatActivity {
    TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        title = (TextView) findViewById(R.id.title);
        title.setPaintFlags(title.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }
}