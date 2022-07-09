package com.example.whatmain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BusanFestivalStep1 extends AppCompatActivity {

    ListView listView;
    BusanFestivalStep1.ListViewAdapter adapter = null;
    ArrayList<BusanFestivalDto> festivalList = new ArrayList<>();

    BusanFestivalDto busanFestivalDto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busan_festival_step1);

        listView = (ListView) findViewById(R.id.listView);
        adapter = new BusanFestivalStep1.ListViewAdapter(this, festivalList);
// 임의의 데이터 삽입///////////////////////////////////////////////
        busanFestivalDto = new BusanFestivalDto();
        busanFestivalDto.setMainTitle("부산불꽃축제");
        busanFestivalDto.setSubTitle("아름다운 부산 밤하늘의 하모니, 부산불꽃축제");
        // Glide로 이미지 표시하기
        String imageUrl = "https://www.visitbusan.net/uploadImgs/files/cntnts/20191230180157336_ttiel";
        busanFestivalDto.setImg(imageUrl);

        busanFestivalDto.setPlace("부산 수영구 광안해변로 219");
        busanFestivalDto.setDate("매년 11월 불꽃쇼 20:00 ~ 21:00");
        festivalList.add(busanFestivalDto);
        festivalList.add(busanFestivalDto);
        festivalList.add(busanFestivalDto);
        festivalList.add(busanFestivalDto);
        ///////////////////////////////////////////////////////////////


        //리스트뷰에 Adapter 설정
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // position 값이랑 Dto 넘겨주기
                System.out.println("리스트뷰 클릭: festival !!!!!!!!"+position);
                Intent intent = new Intent(getApplicationContext(), FullImage.class);
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
            TextView place = (TextView) convertView.findViewById(R.id.mainContext);
            TextView date = (TextView) convertView.findViewById(R.id.date);

            ImageView heartClick = (ImageView) convertView.findViewById(R.id.heartClick);

            //mainImg.setImage(busanFestivalDto.getImg());
            Glide.with(getApplicationContext()).load(busanFestivalDto.getImg()).into(mainImg);
            mainTitle.setText(busanFestivalDto.getMainTitle());
            place.setText(busanFestivalDto.getPlace());
            title.setText(busanFestivalDto.getSubTitle());

            date.setText(busanFestivalDto.getDate());

            //각 아이템 선택 event
//            convertView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Toast.makeText(context, busanFoodDto.getMainTitle(),Toast.LENGTH_SHORT).show();
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