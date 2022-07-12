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

public class BusanFoodStep1 extends AppCompatActivity {
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

    ListView listView ;
    ListViewAdapter adapter;
    boolean clickCheck[]= new boolean[11];
    ArrayList<String> tags = new ArrayList<>();
    ArrayList<BusanFoodDto> busanFoodDtoListBytags = new ArrayList<>();
    BusanFoodDto busanFoodDto;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busan_food_step1);
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

        Intent intent = getIntent();
        userId = intent.getStringExtra("userId");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // position 값이랑 Dto 넘겨주기
                System.out.println("리스트뷰 클릭: food !!!!!!!!"+position);
                Intent intent = new Intent(getApplicationContext(), FoodFullImage.class);
                //intent.putExtra("position",Integer.toString(position));
                System.out.println(busanFoodDtoListBytags.get(position).getCall()+"여기에요 여기 !!");
                intent.putExtra("Dto",busanFoodDtoListBytags.get(position));
                intent.putExtra("userId", userId);
                startActivity(intent);
            }
        });

        // 키워드 클릭
        keywordDate.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                System.out.println("데이트 버튼 클릭: food !!!!!!!!");
                if(clickCheck[0] == false ) { // 첫 체크
                    if(checkSize(clickCheck) < 3){
                        keywordDate.setBackgroundResource(R.drawable.clicked_button);
                        keywordDate.setTextColor(Color.parseColor("#ffffff"));
                        clickCheck[0] = true;
                        addTag(keywordDate.getText().toString());
                    }else {
                        System.out.println("세개이상 선택!!!!!!!!");
                        Toast.makeText(BusanFoodStep1.this, "태그는 3개까지 선택 가능합니다.", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(BusanFoodStep1.this, "태그는 3개까지 선택 가능합니다.", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(BusanFoodStep1.this, "태그는 3개까지 선택 가능합니다.", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(BusanFoodStep1.this, "태그는 3개까지 선택 가능합니다.", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(BusanFoodStep1.this, "태그는 3개까지 선택 가능합니다.", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(BusanFoodStep1.this, "태그는 3개까지 선택 가능합니다.", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(BusanFoodStep1.this, "태그는 3개까지 선택 가능합니다.", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(BusanFoodStep1.this, "태그는 3개까지 선택 가능합니다.", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(BusanFoodStep1.this, "태그는 3개까지 선택 가능합니다.", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(BusanFoodStep1.this, "태그는 3개까지 선택 가능합니다.", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(BusanFoodStep1.this, "태그는 3개까지 선택 가능합니다.", Toast.LENGTH_SHORT).show();
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
        busanFoodByTags(tags);
    }
    public void deleteTag(String tag){
        tags.remove(tags.indexOf(tag));
        //데이터 호출
        for(int i=0;i<tags.size(); i++){
            System.out.println(tags.get(i));
        }
        busanFoodByTags(tags);
    }



    /* 리스트뷰 어댑터 */
    public class ListViewAdapter extends BaseAdapter {
        ArrayList<BusanFoodDto> items = new ArrayList<>();
        Context mContext = null;
        LayoutInflater mLayoutInflater = null;
        public ListViewAdapter(Context context, ArrayList<BusanFoodDto> data) {
            mContext = context;
            items = data;
            mLayoutInflater = LayoutInflater.from(mContext);
        }

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(BusanFoodDto item) {
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
            final BusanFoodDto busanFoodDto = items.get(position);

            if(convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.busan_food_list, viewGroup, false);

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

            //mainImg.setImageBitmap(busanFoodDto.getImg());
            Glide.with(getApplicationContext()).load(busanFoodDto.getImg()).into(mainImg);

            mainTitle.setText(busanFoodDto.getMainTitle());
            place.setText(busanFoodDto.getPlace());
            subTitle.setText(busanFoodDto.getSubTitle());
            //tag 1, 2, 3 뭐뭐 들어가는지
            tag1.setText(busanFoodDto.getTag1());
            tag2.setText(busanFoodDto.getTag2());
            tag3.setText(busanFoodDto.getTag3());


            return convertView;  //뷰 객체 반환
        }
    }
    //tag 클릭할 때마다 busanFoodDtoListBytags 다시 업데이트 해줘야 하니까 이렇게 함수 선언함
    public void busanFoodByTags(ArrayList<String> tags){
        //DB에서 데이터 불러오기
        getFoodListByTags(tags);

    }
    public void getFoodListByTags(ArrayList<String> tags){
        Localhost localhost = new Localhost();
        String url = localhost.getLocalhost() + "/getFoodByTags";

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
                        busanFoodDtoListBytags = jsonParseFoodData(jsonData);
                        adapter = new ListViewAdapter(BusanFoodStep1.this, busanFoodDtoListBytags);
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
    public ArrayList<BusanFoodDto> jsonParseFoodData(String jsonStr) throws JSONException {

        JSONArray jsonArray = new JSONArray(jsonStr);
        ArrayList<BusanFoodDto> foodListS = new ArrayList<>();
        System.out.println(jsonArray.length()+"wlfmawflawflawnfkawlnfawklfnawkflanfakl");
        for(int i=0; i<jsonArray.length();i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            System.out.println(i+"번째"+(String)jsonObject.get("mainTitle"));

            busanFoodDto = new BusanFoodDto();
            busanFoodDto.setId((int)jsonObject.get("id"));
            busanFoodDto.setMainTitle((String)jsonObject.get("mainTitle"));
            busanFoodDto.setPlace((String)jsonObject.get("place"));
            busanFoodDto.setSubTitle((String)jsonObject.get("subTitle"));
            busanFoodDto.setImg((String)jsonObject.get("img"));
            busanFoodDto.setContext((String)jsonObject.get("context"));
            busanFoodDto.setLatitude((double)jsonObject.get("latitude"));
            busanFoodDto.setLongitude((double)jsonObject.get("longitude"));
            busanFoodDto.setCall((String)((String) jsonObject.get("tel")).replaceAll("-",""));
            //태그 작업
            String tags = (String)jsonObject.get("tag1");
            String[] arrTags = tags.split(" ");
            if(arrTags.length==1){
                busanFoodDto.setTag1(arrTags[0]);
            }else if(arrTags.length==2){
                busanFoodDto.setTag1(arrTags[0]);
                busanFoodDto.setTag2(arrTags[1]);
            }
            else if(arrTags.length==3){
                busanFoodDto.setTag1(arrTags[0]);
                busanFoodDto.setTag2(arrTags[1]);
                busanFoodDto.setTag3(arrTags[2]);
            }
            foodListS.add(busanFoodDto);
        }

        return foodListS;

    }
}


