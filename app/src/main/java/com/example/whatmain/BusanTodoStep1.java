package com.example.whatmain;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BusanTodoStep1 extends AppCompatActivity {
    Button keywordDate;
    Button keywordParents;
    Button keywordFriends;
    Button keywordAlone;
    Button keywordYammy;
    Button keywordCafe;
    Button keywordDrink;
    Button keywordRomantic;
    Button keywordRomance;
    Button keySeafood;
    Button keyOcean;

    ListView listView;
    ListViewAdapter adapter;
    boolean clickCheck[]= new boolean[11];
    ArrayList<String> tags = new ArrayList<>();
    ArrayList<BusanTodoDto> todoList = new ArrayList<>();
    BusanTodoDto busanTodoDto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busan_todo_step1);
        keywordDate = findViewById(R.id.keywordDate);
        keywordParents = findViewById(R.id.keywordParents);
        keywordFriends = findViewById(R.id.keywordFriends);
        keywordAlone = findViewById(R.id.keywordAlone);
        keywordYammy = findViewById(R.id.keywordYammy);
        keywordCafe = findViewById(R.id.keywordCafe);
        keywordDrink = findViewById(R.id.keywordDrink);
        keywordRomantic = findViewById(R.id.keywordRomantic);
        keywordRomance = findViewById(R.id.keywordRomance);
        keySeafood = findViewById(R.id.keySeafood);
        keyOcean = findViewById(R.id.keyOcean);

        listView = (ListView) findViewById(R.id.listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // position 값이랑 Dto 넘겨주기
                System.out.println("리스트뷰 클릭: todo !!!!!!!!"+position);
                Intent intent = new Intent(getApplicationContext(), FoodFullImage.class);
                intent.putExtra("Dto",todoList.get(position));
                startActivity(intent);
            }
        });

        // 키워드 클릭
        keywordDate.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                if(clickCheck[0] == false ) { // 첫 체크
                    if(checkSize(clickCheck) < 3){
                        keywordDate.setBackgroundResource(R.drawable.clicked_button);
                        keywordDate.setTextColor(Color.parseColor("#ffffff"));
                        clickCheck[0] = true;
                        addTag(keywordDate.getText().toString());
                    }else {
                        System.out.println("세개이상 선택!!!!!!!!");
                        Toast.makeText(BusanTodoStep1.this, "태그는 3개까지 선택 가능합니다.", Toast.LENGTH_SHORT).show();
                    }

                }else{ // 다시 체크
                    keywordDate.setBackgroundResource(R.drawable.round_button);
                    keywordDate.setTextColor(Color.parseColor("#0C0D0E"));
                    clickCheck[0] = false;
                    deleteTag(keywordDate.getText().toString());
                }

            }
        });
        keywordParents.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                if(clickCheck[1] == false ) { // 첫 체크
                    if(checkSize(clickCheck) < 3){
                        keywordParents.setBackgroundResource(R.drawable.clicked_button);
                        keywordParents.setTextColor(Color.parseColor("#ffffff"));
                        clickCheck[1] = true;
                        addTag(keywordParents.getText().toString());
                    }else {
                        System.out.println("세개이상 선택!!!!!!!!");
                        Toast.makeText(BusanTodoStep1.this, "태그는 3개까지 선택 가능합니다.", Toast.LENGTH_SHORT).show();
                    }

                }else{ // 다시 체크
                    keywordParents.setBackgroundResource(R.drawable.round_button);
                    keywordParents.setTextColor(Color.parseColor("#0C0D0E"));
                    clickCheck[1] = false;
                    deleteTag(keywordParents.getText().toString());
                }

            }
        });
        keywordFriends.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                if(clickCheck[2] == false ) { // 첫 체크
                    if(checkSize(clickCheck) < 3){
                        keywordFriends.setBackgroundResource(R.drawable.clicked_button);
                        keywordFriends.setTextColor(Color.parseColor("#ffffff"));
                        clickCheck[2] = true;
                        addTag(keywordFriends.getText().toString());
                    }else {
                        System.out.println("세개이상 선택!!!!!!!!");
                        Toast.makeText(BusanTodoStep1.this, "태그는 3개까지 선택 가능합니다.", Toast.LENGTH_SHORT).show();
                    }

                }else{ // 다시 체크
                    keywordFriends.setBackgroundResource(R.drawable.round_button);
                    keywordFriends.setTextColor(Color.parseColor("#0C0D0E"));
                    clickCheck[2] = false;
                    deleteTag(keywordFriends.getText().toString());
                }

            }
        });
        keywordAlone.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                if(clickCheck[3] == false ) { // 첫 체크
                    if(checkSize(clickCheck) < 3){
                        keywordAlone.setBackgroundResource(R.drawable.clicked_button);
                        keywordAlone.setTextColor(Color.parseColor("#ffffff"));
                        clickCheck[3] = true;
                        addTag(keywordAlone.getText().toString());
                    }else {
                        System.out.println("세개이상 선택!!!!!!!!");
                        Toast.makeText(BusanTodoStep1.this, "태그는 3개까지 선택 가능합니다.", Toast.LENGTH_SHORT).show();
                    }

                }else{ // 다시 체크
                    keywordAlone.setBackgroundResource(R.drawable.round_button);
                    keywordAlone.setTextColor(Color.parseColor("#0C0D0E"));
                    clickCheck[3] = false;
                    deleteTag(keywordAlone.getText().toString());
                }

            }
        });
        keywordYammy.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                if(clickCheck[4] == false ) { // 첫 체크
                    if(checkSize(clickCheck) < 3){
                        keywordYammy.setBackgroundResource(R.drawable.clicked_button);
                        keywordYammy.setTextColor(Color.parseColor("#ffffff"));
                        clickCheck[4] = true;
                        addTag(keywordYammy.getText().toString());
                    }else {
                        System.out.println("세개이상 선택!!!!!!!!");
                        Toast.makeText(BusanTodoStep1.this, "태그는 3개까지 선택 가능합니다.", Toast.LENGTH_SHORT).show();
                    }

                }else{ // 다시 체크
                    keywordYammy.setBackgroundResource(R.drawable.round_button);
                    keywordYammy.setTextColor(Color.parseColor("#0C0D0E"));
                    clickCheck[4] = false;
                    deleteTag(keywordYammy.getText().toString());
                }

            }
        });
        keywordCafe.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                if(clickCheck[5] == false ) { // 첫 체크
                    if(checkSize(clickCheck) < 3){
                        keywordCafe.setBackgroundResource(R.drawable.clicked_button);
                        keywordCafe.setTextColor(Color.parseColor("#ffffff"));
                        clickCheck[5] = true;
                        addTag(keywordCafe.getText().toString());
                    }else {
                        System.out.println("세개이상 선택!!!!!!!!");
                        Toast.makeText(BusanTodoStep1.this, "태그는 3개까지 선택 가능합니다.", Toast.LENGTH_SHORT).show();
                    }

                }else{ // 다시 체크
                    keywordCafe.setBackgroundResource(R.drawable.round_button);
                    keywordCafe.setTextColor(Color.parseColor("#0C0D0E"));
                    clickCheck[5] = false;
                    deleteTag(keywordCafe.getText().toString());
                }

            }
        });
        keywordDrink.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                if(clickCheck[6] == false ) { // 첫 체크
                    if(checkSize(clickCheck) < 3){
                        keywordDrink.setBackgroundResource(R.drawable.clicked_button);
                        keywordDrink.setTextColor(Color.parseColor("#ffffff"));
                        clickCheck[6] = true;
                        addTag(keywordDrink.getText().toString());
                    }else {
                        System.out.println("세개이상 선택!!!!!!!!");
                        Toast.makeText(BusanTodoStep1.this, "태그는 3개까지 선택 가능합니다.", Toast.LENGTH_SHORT).show();
                    }

                }else{ // 다시 체크
                    keywordDrink.setBackgroundResource(R.drawable.round_button);
                    keywordDrink.setTextColor(Color.parseColor("#0C0D0E"));
                    clickCheck[6] = false;
                    deleteTag(keywordDrink.getText().toString());
                }

            }
        });
        keywordRomantic.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                if(clickCheck[7] == false ) { // 첫 체크
                    if(checkSize(clickCheck) < 3){
                        keywordRomantic.setBackgroundResource(R.drawable.clicked_button);
                        keywordRomantic.setTextColor(Color.parseColor("#ffffff"));
                        clickCheck[7] = true;
                        addTag(keywordRomantic.getText().toString());
                    }else {
                        System.out.println("세개이상 선택!!!!!!!!");
                        Toast.makeText(BusanTodoStep1.this, "태그는 3개까지 선택 가능합니다.", Toast.LENGTH_SHORT).show();
                    }

                }else{ // 다시 체크
                    keywordRomantic.setBackgroundResource(R.drawable.round_button);
                    keywordRomantic.setTextColor(Color.parseColor("#0C0D0E"));
                    clickCheck[7] = false;
                    deleteTag(keywordRomantic.getText().toString());
                }

            }
        });
        keywordRomance.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                if(clickCheck[8] == false ) { // 첫 체크
                    if(checkSize(clickCheck) < 3){
                        keywordRomance.setBackgroundResource(R.drawable.clicked_button);
                        keywordRomance.setTextColor(Color.parseColor("#ffffff"));
                        clickCheck[8] = true;
                        addTag(keywordRomance.getText().toString());
                    }else {
                        System.out.println("세개이상 선택!!!!!!!!");
                        Toast.makeText(BusanTodoStep1.this, "태그는 3개까지 선택 가능합니다.", Toast.LENGTH_SHORT).show();
                    }

                }else{ // 다시 체크
                    keywordRomance.setBackgroundResource(R.drawable.round_button);
                    keywordRomance.setTextColor(Color.parseColor("#0C0D0E"));
                    clickCheck[8]= false;
                    deleteTag(keywordRomance.getText().toString());
                }

            }
        });
        keySeafood.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                if(clickCheck[9] == false ) { // 첫 체크
                    if(checkSize(clickCheck) < 3){
                        keySeafood.setBackgroundResource(R.drawable.clicked_button);
                        keySeafood.setTextColor(Color.parseColor("#ffffff"));
                        clickCheck[9] = true;
                        addTag(keySeafood.getText().toString());
                    }else {
                        System.out.println("세개이상 선택!!!!!!!!");
                        Toast.makeText(BusanTodoStep1.this, "태그는 3개까지 선택 가능합니다.", Toast.LENGTH_SHORT).show();
                    }

                }else{ // 다시 체크
                    keySeafood.setBackgroundResource(R.drawable.round_button);
                    keySeafood.setTextColor(Color.parseColor("#0C0D0E"));
                    clickCheck[9] = false;
                    deleteTag(keySeafood.getText().toString());
                }

            }
        });
        keyOcean.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                if(clickCheck[10] == false ) { // 첫 체크
                    if(checkSize(clickCheck) < 3){
                        keyOcean.setBackgroundResource(R.drawable.clicked_button);
                        keyOcean.setTextColor(Color.parseColor("#ffffff"));
                        clickCheck[10] = true;
                        addTag(keyOcean.getText().toString());
                    }else {
                        System.out.println("세개이상 선택!!!!!!!!");
                        Toast.makeText(BusanTodoStep1.this, "태그는 3개까지 선택 가능합니다.", Toast.LENGTH_SHORT).show();
                    }

                }else{ // 다시 체크
                    keyOcean.setBackgroundResource(R.drawable.round_button);
                    keyOcean.setTextColor(Color.parseColor("#0C0D0E"));
                    clickCheck[10] = false;
                    deleteTag(keyOcean.getText().toString());
                }

            }
        });

    }
    // 태그 3개 이상 체크
    public int checkSize(boolean clickCheck[]){
        int count = 0;
        for(int i=0; i< clickCheck.length; i++){
            if(clickCheck[i]){  // true 일때 count ++
                count ++;
            }
        }
        return count;
    }
    // 태그 추가
    public void addTag(String tag){
        tags.add(tag);
        // 데이터 호출
        for(int i=0;i<tags.size(); i++){
            System.out.println(tags.get(i));
        }
        busanTodoByTags(tags);
    }
    public void deleteTag(String tag){
        tags.remove(tags.indexOf(tag));
        //데이터 호출
        for(int i=0;i<tags.size(); i++){
            System.out.println(tags.get(i));
        }
        busanTodoByTags(tags);
    }
    public void busanTodoByTags(ArrayList<String> tags){
        //DB에서 데이터 불러오기
        getTodoListByTags(tags);
;    }
    public void getTodoListByTags(ArrayList<String> tags){
        Localhost localhost = new Localhost();
        String url = localhost.getLocalhost() + "/getTodoByTags";

        //JSON형식으로 데이터 통신을 진행합니다!
        JSONObject testjson = new JSONObject();
        try {
            for(int i=0; i<tags.size(); i++){
                testjson.put("tag"+(i+1) ,tags.get(i));
            }
            testjson.put("count", tags.size()+"");

            String jsonString = testjson.toString(); //완성된 json 포맷
            System.out.println("11111111111111111");
            System.out.println(url);
            System.out.println(testjson);
            //이제 전송해볼까요?
            final RequestQueue requestQueue = Volley.newRequestQueue(this);

            final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, testjson, new Response.Listener<JSONObject>() {

                //데이터 전달을 끝내고 이제 그 응답을 받을 차례입니다.
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        //받은 json형식의 응답을 받아

                        JSONObject jsonObj_1 = new JSONObject(response.toString());
                        System.out.println("jsonObj_1 : "+jsonObj_1);
                        String jsonData = jsonObj_1.getString("body");
                        System.out.println("jsonData : "+jsonData);
                        todoList = jsonParseTodoData(jsonData);
                        adapter = new ListViewAdapter(BusanTodoStep1.this, todoList);
                        //리스트뷰에 Adapter 설정
                        listView.setAdapter(adapter);
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
        }  catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<BusanTodoDto> jsonParseTodoData(String jsonStr) throws JSONException {

        JSONArray jsonArray = new JSONArray(jsonStr);
        ArrayList<BusanTodoDto> todoListS = new ArrayList<>();
        System.out.println(jsonArray.length()+"wlfmawflawflawnfkawlnfawklfnawkflanfakl");
        for(int i=0; i<jsonArray.length();i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            System.out.println(i+"번째"+(String)jsonObject.get("mainTitle"));

            busanTodoDto = new BusanTodoDto();
            busanTodoDto.setId((int)jsonObject.get("id"));
            busanTodoDto.setMainTitle((String)jsonObject.get("mainTitle"));
            busanTodoDto.setPlace((String)jsonObject.get("place"));
            busanTodoDto.setSubTitle((String)jsonObject.get("subTitle"));
            busanTodoDto.setImg((String)jsonObject.get("img"));
            busanTodoDto.setContext((String)jsonObject.get("context"));
            busanTodoDto.setLatitude((double)jsonObject.get("latitude"));
            busanTodoDto.setLongitude((double)jsonObject.get("longitude"));
            //태그 작업
            String tags = (String)jsonObject.get("tag1");
            String[] arrTags = tags.split(" ");
            if(arrTags.length==1){
                busanTodoDto.setTag1(arrTags[0]);
            }else if(arrTags.length==2){
                busanTodoDto.setTag1(arrTags[0]);
                busanTodoDto.setTag2(arrTags[1]);
            }
            else if(arrTags.length==3){
                busanTodoDto.setTag1(arrTags[0]);
                busanTodoDto.setTag2(arrTags[1]);
                busanTodoDto.setTag3(arrTags[2]);
            }
            todoListS.add(busanTodoDto);
        }

        return todoListS;

    }


    /* 리스트뷰 어댑터 */
    public class ListViewAdapter extends BaseAdapter {
        ArrayList<BusanTodoDto> items = new ArrayList<>();
        Context mContext = null;
        LayoutInflater mLayoutInflater = null;
        public ListViewAdapter(Context context, ArrayList<BusanTodoDto> data) {
            mContext = context;
            items = data;
            mLayoutInflater = LayoutInflater.from(mContext);
        }

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(BusanTodoDto item) {
            items.add(item);
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {
            final Context context = viewGroup.getContext();
            final BusanTodoDto busanTodoDto = items.get(position);

            if(convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.busan_todo_list, viewGroup, false);

            } else {
                View view = new View(context);
                view = (View) convertView;
            }
            ImageView mainImg = (ImageView) convertView.findViewById(R.id.mainImg);
            TextView mainTitle = (TextView) convertView.findViewById(R.id.mainTitle);
            TextView subTitle = (TextView) convertView.findViewById(R.id.subTitle);
            TextView place = (TextView) convertView.findViewById(R.id.mainContext);
            TextView tag1 = (TextView) convertView.findViewById(R.id.tag1);
            TextView tag2 = (TextView) convertView.findViewById(R.id.tag2);
            TextView tag3 = (TextView) convertView.findViewById(R.id.tag3);
            ImageView heartClick = (ImageView) convertView.findViewById(R.id.heartClick);

            //mainImg.setImageBitmap(busanTodoDto.getImg());
            Glide.with(getApplicationContext()).load(busanTodoDto.getImg()).into(mainImg);

            mainTitle.setText(busanTodoDto.getMainTitle());
            place.setText(busanTodoDto.getPlace());
            subTitle.setText(busanTodoDto.getSubTitle());
            tag1.setText(busanTodoDto.getTag1());
            tag2.setText(busanTodoDto.getTag2());
            tag3.setText(busanTodoDto.getTag3());

            //각 아이템 선택 event
//            convertView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Toast.makeText(context, busanTodoDto.getMainTitle(),Toast.LENGTH_SHORT).show();
//                }
//            });
            heartClick.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    heartClick.setImageResource(R.drawable.redheart);
                }
            });

            return convertView;  //뷰 객체 반환
        }
    }

}


