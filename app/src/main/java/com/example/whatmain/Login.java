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

public class Login extends AppCompatActivity {

    EditText et_id;
    EditText et_password;

    Button signinBtn;
    Button signupBtn;

    LinearLayout naverBtn;
    LinearLayout kakaoBtn;

    DBconnectImpl dBconnect = new DBconnect();


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
                Intent intent = new Intent(getApplicationContext(), FirstPage.class);
                startActivity(intent);
//                if(isEmpty(username, password) && login(username, password,Login.this)){
//                    if(login(username,password,getApplicationContext())){
//
//                    }
//                    else{
//                        System.out.println("로그인 실패 !!!!!!!!!!!!!!!!!!!!");
//                    }
//
//                }
                System.out.println(et_id.getText().toString()+"여기여기여기여기"+ et_password.getText().toString());


//                Intent intent = new Intent(getApplicationContext(), FirstPage.class);
//                startActivity(intent);
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

    // 회원체크
    public boolean login(String username, String password, Context context){
        //아이디 비밀번호 체크
        boolean check = false;
        int loginCode = dBconnect.requestLogin(username, password, context);
        System.out.println(loginCode+"다시 로그인창");
        if(loginCode == 1) {
            check = true;

            Toast.makeText(getApplicationContext(),"로그인 성공했습니다.",Toast.LENGTH_SHORT).show();
        }
        else if(loginCode == 2) {
            Toast.makeText(getApplicationContext(),"아이디가 틀렸습니다.",Toast.LENGTH_SHORT).show();
            check = false;
        }
        else if(loginCode == 3) {
            Toast.makeText(getApplicationContext(),"비밀번호가 틀렸습니다.",Toast.LENGTH_SHORT).show();
            check = false;
        }
        return check;
    }


}