package com.example.whatmain;

import androidx.appcompat.app.AppCompatActivity;

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

import org.json.JSONException;
import org.json.JSONObject;

public class EditProfile extends AppCompatActivity {

    String userId;
    String username;
    EditText edit_id;
    EditText edit_pw;
    Button updateBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        Intent i_login=getIntent();
        userId=i_login.getStringExtra("userId");

        username=i_login.getStringExtra("username");
        System.out.println("userId1111 : "+userId);
         edit_id=(EditText) findViewById(R.id.editUsername);
         edit_pw=(EditText) findViewById(R.id.editPW);
         updateBtn=(Button) findViewById(R.id.upDateBtn);
        init(username);


        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //내가 새롭게 입력한 게 edit_id로 참조되는 게 맞는 건가?
                String newUsername=edit_id.getText().toString();
                String newPassword=edit_pw.getText().toString();
                updateUser(newUsername,newPassword,Integer.parseInt(userId));
                Toast.makeText(getApplicationContext(), "수정되었습니다", Toast.LENGTH_SHORT).show();
//                Intent i_n=new Intent(getApplicationContext(),FirstPage.class);
//                startActivity(i_n);
            }
        });
    }

    public void updateUser(String newUsername, String newPassword, int userId){
        //DB에 userId, userPw 업데이트
        Localhost localhost = new Localhost();
        String url = localhost.getLocalhost() + "/updateUser";
        System.out.println("userId1111 : "+userId);
        //JSON형식으로 데이터 통신을 진행합니다!
        JSONObject testjson = new JSONObject();
        try {
            //입력해둔 edittext의 id와 pw값을 받아와 put해줍니다 : 데이터를 json형식으로 바꿔 넣어주었습니다.
            testjson.put("newUsername", newUsername);
            testjson.put("newPassword", newPassword);
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
                        JSONObject jsonObject = new JSONObject(response.toString());
//
//                       //key값에 따라 value값을 쪼개 받아옵니다.
                        String resultCode = jsonObject.getString("update_code");
                        int loginCode = Integer.parseInt(resultCode);
                        System.out.println("loginCode : "+loginCode);
                        if(loginCode==1){
                            Toast.makeText(getApplicationContext(), "회원수정 성공 !", Toast.LENGTH_SHORT).show();
                            System.out.println("성공 !");
                            init(newUsername);
                        }else {
                            Toast.makeText(getApplicationContext(), "회원수정 실패", Toast.LENGTH_SHORT).show();
                            System.out.println("실패 !");
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
    public void init(String username) {
        edit_id.setHint(username);
    }
}