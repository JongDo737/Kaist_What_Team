package com.example.whatmain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditProfile extends AppCompatActivity {

    String prev_id;
    String userid;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        Intent i_login=getIntent();
        userid=i_login.getStringExtra("userid");
        username=i_login.getStringExtra("username");

        EditText edit_id=(EditText) findViewById(R.id.editUsername);
        EditText edit_pw=(EditText) findViewById(R.id.editPW);
        Button updateBtn=(Button) findViewById(R.id.upDateBtn);

        edit_id.setHint(username);

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //내가 새롭게 입력한 게 edit_id로 참조되는 게 맞는 건가?
                String new_id=edit_id.getText().toString();
                String new_pw=edit_pw.getText().toString();
                updateUser(new_id,new_pw);
                Toast.makeText(getApplicationContext(), "수정되었습니다", Toast.LENGTH_SHORT).show();
//                Intent i_n=new Intent(getApplicationContext(),FirstPage.class);
//                startActivity(i_n);
            }
        });
    }
    public void updateUser(String username, String password){
        //DB에 userId, userPw 업데이트


    }
}