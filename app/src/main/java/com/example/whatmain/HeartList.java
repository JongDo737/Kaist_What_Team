package com.example.whatmain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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

public class HeartList extends AppCompatActivity {

    //좋아요 누른 리스트
    ArrayList<BusanFoodDto> busanFoodDtoListBytagsLoved = new ArrayList<>();
    BusanFoodDto busanFoodDto;
    ArrayList<BusanTodoDto> busanTodoDtoListBytagsLoved = new ArrayList<>();
    BusanTodoDto busanTodoDto;
    ArrayList<BusanFestivalDto> busanFestivalDtoListBytagsLoved = new ArrayList<>();
    BusanFestivalDto busanFestivalDto;
    String userId;
    ListViewFoodAdapter listViewFoodAdapter;
    ListViewFestivalAdapter listViewFestivalAdapter;
    ListViewTodoAdapter listViewTodoAdapter;
    ListView foodListView;
    ListView todoListView;
    ListView festivalListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart_list);

        Intent intent = getIntent();
        userId= intent.getStringExtra("userId");
        getFoodLikedList(Integer.parseInt(userId));
        getFestivalLikedList(Integer.parseInt(userId));
        getTodoLikedList(Integer.parseInt(userId));


        //food 하트에 해당하는 데이터 불러와서 list에 올리기
        foodListView = (ListView) findViewById(R.id.listFood);

        foodListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // position 값이랑 Dto 넘겨주기
                System.out.println("리스트뷰 클릭: food !!!!!!!!"+position);
                Intent intent = new Intent(getApplicationContext(), FoodFullImage.class);
                //intent.putExtra("position",Integer.toString(position));
                System.out.println(busanFoodDtoListBytagsLoved.get(position).getCall()+"여기에요 여기 !!");
                intent.putExtra("Dto",busanFoodDtoListBytagsLoved.get(position));
                intent.putExtra("userId",userId);
                //.getMainTitle()
                startActivity(intent);
            }
        });
        foodListView.setOnTouchListener(new ListView.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                int action = event.getAction();
                switch (action)
                {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;

                    case MotionEvent.ACTION_UP:
                        // Allow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }

                // Handle ListView touch events.
                v.onTouchEvent(event);
                return true;
            }
        });
        foodListView.setClickable(true);

        //Todo 하트에 해당하는 데이터 불러와서 list에 올리기
        todoListView = (ListView) findViewById(R.id.listEnter);

        todoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // position 값이랑 Dto 넘겨주기
                System.out.println("리스트뷰 클릭: food !!!!!!!!"+position);
                Intent intent = new Intent(getApplicationContext(), TodoFullImage.class);
                //intent.putExtra("position",Integer.toString(position));
                //System.out.println(busanTodoDtoListBytagsLoved.get(position).getCall()+"여기에요 여기 !!");
                intent.putExtra("Dto",busanTodoDtoListBytagsLoved.get(position));
                intent.putExtra("userId",userId);
                //.getMainTitle()
                startActivity(intent);
            }
        });
        todoListView.setOnTouchListener(new ListView.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                int action = event.getAction();
                switch (action)
                {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;

                    case MotionEvent.ACTION_UP:
                        // Allow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }

                // Handle ListView touch events.
                v.onTouchEvent(event);
                return true;
            }
        });
        todoListView.setClickable(true);

        //Festival 하트에 해당하는 데이터 불러와서 list에 올리기
        festivalListView = (ListView) findViewById(R.id.listFire);

        festivalListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // position 값이랑 Dto 넘겨주기
                System.out.println("리스트뷰 클릭: food !!!!!!!!"+position);
                Intent intent = new Intent(getApplicationContext(), FestivalFullImage.class);
                //intent.putExtra("position",Integer.toString(position));
                //System.out.println(busanTodoDtoListBytagsLoved.get(position).getCall()+"여기에요 여기 !!");
                intent.putExtra("Dto",busanFestivalDtoListBytagsLoved.get(position));
                intent.putExtra("userId",userId);
                //.getMainTitle()
                startActivity(intent);
            }
        });
        festivalListView.setOnTouchListener(new ListView.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                int action = event.getAction();
                switch (action)
                {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;

                    case MotionEvent.ACTION_UP:
                        // Allow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }

                // Handle ListView touch events.
                v.onTouchEvent(event);
                return true;
            }
        });
        festivalListView.setClickable(true);
    }
    public void getFoodLikedList(int userId){
        Localhost localhost = new Localhost();
        String url = localhost.getLocalhost() + "/likeFoodAll";

        //JSON형식으로 데이터 통신을 진행합니다!
        JSONObject testjson = new JSONObject();
        try {

            testjson.put("userId", userId);

            String jsonString = testjson.toString(); //완성된 json 포맷
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
                        busanFoodDtoListBytagsLoved = jsonParseFoodData(jsonData);
                        listViewFoodAdapter = new ListViewFoodAdapter(HeartList.this, busanFoodDtoListBytagsLoved);
                        //리스트뷰에 Adapter 설정
                        foodListView.setAdapter(listViewFoodAdapter);
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
            busanFoodDto.setCall(((String) jsonObject.get("tel")).replaceAll("-",""));
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
    public class ListViewFoodAdapter extends BaseAdapter {
        ArrayList<BusanFoodDto> items = new ArrayList<>();
        Context mContext = null;
        LayoutInflater mLayoutInflater = null;
        public ListViewFoodAdapter(Context context, ArrayList<BusanFoodDto> data) {
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
    public void getFestivalLikedList(int userId) {
        Localhost localhost = new Localhost();
        String url = localhost.getLocalhost() + "/likeFestivalAll";

        //JSON형식으로 데이터 통신을 진행합니다!
        JSONObject testjson = new JSONObject();
        try {
            testjson.put("userId",userId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
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
                    busanFestivalDtoListBytagsLoved = jsonParseFestivalData(jsonData);
                    listViewFestivalAdapter = new ListViewFestivalAdapter(HeartList.this, busanFestivalDtoListBytagsLoved);
                    //리스트뷰에 Adapter 설정
                    festivalListView.setAdapter(listViewFestivalAdapter);

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
    }
    public ArrayList<BusanFestivalDto> jsonParseFestivalData(String jsonStr) throws JSONException {

        JSONArray jsonArray = new JSONArray(jsonStr);
        ArrayList<BusanFestivalDto> festivalListS = new ArrayList<>();
        System.out.println(jsonArray.length()+"wlfmawflawflawnfkawlnfawklfnawkflanfakl");
        for(int i=0; i<jsonArray.length();i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            System.out.println(i+"번째"+(String)jsonObject.get("mainTitle"));

            busanFestivalDto = new BusanFestivalDto();
            busanFestivalDto.setId((int)jsonObject.get("id"));
            busanFestivalDto.setMainTitle((String)jsonObject.get("mainTitle"));
            busanFestivalDto.setPlace((String)jsonObject.get("place"));
            busanFestivalDto.setSubTitle((String)jsonObject.get("subTitle"));
            busanFestivalDto.setImg((String)jsonObject.get("img"));
            busanFestivalDto.setContext((String)jsonObject.get("context"));
            busanFestivalDto.setLatitude((double)jsonObject.get("latitude"));
            busanFestivalDto.setLongitude((double)jsonObject.get("longitude"));
            busanFestivalDto.setHomePage((String)jsonObject.get("home_page"));
            busanFestivalDto.setDate((String)jsonObject.get("date"));
            busanFestivalDto.setCall((String)jsonObject.get("tel"));

            festivalListS.add(busanFestivalDto);
        }
        for(int i=0;i<festivalListS.size(); i++){
            System.out.println(festivalListS.get(i).getMainTitle());
        }
        return festivalListS;

    }
    /* 리스트뷰 어댑터 */
    public class ListViewFestivalAdapter extends BaseAdapter {
        ArrayList<BusanFestivalDto> items = new ArrayList<>();
        Context mContext = null;
        LayoutInflater mLayoutInflater = null;
        public ListViewFestivalAdapter(Context context, ArrayList<BusanFestivalDto> data) {
            mContext = context;
            items = data;
            mLayoutInflater = LayoutInflater.from(mContext);
        }

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(BusanFestivalDto item) {
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
            final BusanFestivalDto busanFestivalDto = items.get(position);

            if(convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.busan_festival_list, viewGroup, false);

            } else {
                View view = new View(context);
                view = (View) convertView;
            }

            ImageView mainImg = (ImageView) convertView.findViewById(R.id.mainImg);
            //Glide.with(getApplicationContext()).load(busanFestivalDto.getImg()).into(mainImg);

            TextView mainTitle = (TextView) convertView.findViewById(R.id.mainTitle);
            TextView title = (TextView) convertView.findViewById(R.id.title);
            TextView date = (TextView) convertView.findViewById(R.id.date);
            TextView place = (TextView) convertView.findViewById(R.id.mainContext);

            //ImageView heartClick = (ImageView) convertView.findViewById(R.id.heartClick);

            //mainImg.setImage(busanFestivalDto.getImg());
            Glide.with(getApplicationContext()).load(busanFestivalDto.getImg()).into(mainImg);
            mainTitle.setText(busanFestivalDto.getMainTitle());
            title.setText(busanFestivalDto.getSubTitle());
            date.setText(busanFestivalDto.getDate());
            place.setText(busanFestivalDto.getPlace());

            //각 아이템 선택 event
//            convertView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Toast.makeText(context, busanFoodDto.getMainTitle(),Toast.LENGTH_SHORT).show();
//                }
//            });
//            heartClick.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    heartClick.setImageResource(R.drawable.redheart);
//                }
//            });

            return convertView;  //뷰 객체 반환
        }
    }
    public void getTodoLikedList(int userId) {
        Localhost localhost = new Localhost();
        String url = localhost.getLocalhost() + "/likeTodoAll";

        //JSON형식으로 데이터 통신을 진행합니다!
        JSONObject testjson = new JSONObject();
        try {
            testjson.put("userId",userId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
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
                    busanTodoDtoListBytagsLoved = jsonParseTodoData(jsonData);
                    listViewTodoAdapter = new ListViewTodoAdapter(HeartList.this, busanTodoDtoListBytagsLoved);
                    //리스트뷰에 Adapter 설정
                    festivalListView.setAdapter(listViewFestivalAdapter);

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
        for(int i=0;i<todoListS.size(); i++){
            System.out.println(todoListS.get(i).getMainTitle());
        }
        return todoListS;

    }
    /* 리스트뷰 어댑터 */
    public class ListViewTodoAdapter extends BaseAdapter {
        ArrayList<BusanTodoDto> items = new ArrayList<>();
        Context mContext = null;
        LayoutInflater mLayoutInflater = null;
        public ListViewTodoAdapter(Context context, ArrayList<BusanTodoDto> data) {
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
            //ImageView heartClick = (ImageView) convertView.findViewById(R.id.heartClick);

            //mainImg.setImageBitmap(busanTodoDto.getImg());
            Glide.with(getApplicationContext()).load(busanTodoDto.getImg()).into(mainImg);

            mainTitle.setText(busanTodoDto.getMainTitle());
            place.setText(busanTodoDto.getPlace());
            subTitle.setText(busanTodoDto.getSubTitle());
            tag1.setText(busanTodoDto.getTag1());
            tag2.setText(busanTodoDto.getTag2());
            tag3.setText(busanTodoDto.getTag3());

            return convertView;  //뷰 객체 반환
        }
    }
}