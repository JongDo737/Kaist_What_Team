//package com.example.whatmain;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//
//import org.json.JSONObject;
//
//public class DBconnect extends AppCompatActivity {
//    String localhost = "https://e100-143-248-38-166.jp.ngrok.io";
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_dbconnect);
//
//    }
//
//    public void request(){
//        //url 요청주소 넣는 editText를 받아 url만들기
//        String url = localhost + ":4000";
//        //JSON형식으로 데이터 통신을 진행합니다!
//        JSONObject testjson = new JSONObject();
//        try {
//
//            //입력해둔 edittext의 id와 pw값을 받아와 put해줍니다 : 데이터를 json형식으로 바꿔 넣어주었습니다.
//
//            testjson.put("id", id.getText().toString());
//            testjson.put("password", pw.getText().toString());
//            String jsonString = testjson.toString(); //완성된 json 포맷
//
//            //이제 전송해볼까요?
//            final RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
//            final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url,testjson, new Response.Listener<JSONObject>() {
//
//                //데이터 전달을 끝내고 이제 그 응답을 받을 차례입니다.
//                @Override
//                public void onResponse(JSONObject response) {
//                    try {
//                        println("데이터전송 성공");
//
//                        //받은 json형식의 응답을 받아
//                        JSONObject jsonObject = new JSONObject(response.toString());
//
//                        //key값에 따라 value값을 쪼개 받아옵니다.
//                        String resultId = jsonObject.getString("approve_id");
//                        String resultPassword = jsonObject.getString("approve_pw");
//
//                        //만약 그 값이 같다면 로그인에 성공한 것입니다.
//                        if(resultId.equals("OK") & resultPassword.equals("OK")){
//
//                            //이 곳에 성공 시 화면이동을 하는 등의 코드를 입력하시면 됩니다.
//                        }else{
//                            //로그인에 실패했을 경우 실행할 코드를 입력하시면 됩니다.
//                        }
//
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//                //서버로 데이터 전달 및 응답 받기에 실패한 경우 아래 코드가 실행됩니다.
//            }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    error.printStackTrace();
//                    //Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
//                }
//            });
//            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//            requestQueue.add(jsonObjectRequest);
//            //
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//}