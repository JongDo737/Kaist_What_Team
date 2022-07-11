package com.example.whatmain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
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

import java.io.Serializable;
import java.util.ArrayList;

public class BusanFestivalStep1 extends AppCompatActivity implements Serializable {

    ListView listView;
    BusanFestivalStep1.ListViewAdapter adapter;
    //ArrayList<BusanFestivalDto> festivalList = new ArrayList<>();
    ArrayList<BusanFestivalDto> busanFestivalDtoList = new ArrayList<>();

    BusanFestivalDto busanFestivalDto;
    Button test;
    String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busan_festival_step1);
        Intent intent = getIntent();
        userId = intent.getStringExtra("userId");
        listView = (ListView) findViewById(R.id.listView);
        getAllFestival();

        System.out.println("어답터 연결");
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // position 값이랑 Dto 넘겨주기
                System.out.println("리스트뷰 클릭: festival !!!!!!!!"+position);
                Intent intent = new Intent(getApplicationContext(), FestivalFullImage.class);
                //intent.putExtra("position",Integer.toString(position));
                System.out.println(busanFestivalDtoList.get(position).getCall()+" festival 여기에요 여기 !!");
                intent.putExtra("Dto", busanFestivalDtoList.get(position));
                intent.putExtra("userId",userId);
                //.getMainTitle();
                startActivity(intent);
            }
        });
    }



    /* 리스트뷰 어댑터 */
    public class ListViewAdapter extends BaseAdapter {
        ArrayList<BusanFestivalDto> items = new ArrayList<>();
        Context mContext = null;
        LayoutInflater mLayoutInflater = null;
        public ListViewAdapter(Context context, ArrayList<BusanFestivalDto> data) {
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
    public void getAllFestival() {
        Localhost localhost = new Localhost();
        String url = localhost.getLocalhost() + "/getFestivalAll";

        //JSON형식으로 데이터 통신을 진행합니다!
        JSONObject testjson = new JSONObject();

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
                    busanFestivalDtoList = jsonParseFestivalData(jsonData);
                    adapter = new BusanFestivalStep1.ListViewAdapter(BusanFestivalStep1.this, busanFestivalDtoList);
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
        System.out.println("여기 컨텍스트 테스트 !!!!!!");
        System.out.println(festivalListS.get(5).getContext());
        return festivalListS;

    }

}