package com.example.whatmain;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

    ListView listView;
    ListViewAdapter adapter = null;
    boolean clickCheck[]= new boolean[11];
    ArrayList<String> tags = new ArrayList<>();
    ArrayList<BusanFoodDto> FoodList = new ArrayList<>();

    BusanFoodDto busanFoodDto;

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
        adapter = new ListViewAdapter(this, FoodList);

        // 임의의 데이터 삽입///////////////////////////////////////////////
        busanFoodDto = new BusanFoodDto();
        busanFoodDto.setMainTitle("해운대포장마차존");
        busanFoodDto.setSubTitle("낭만 추억 만들어 주는 해운대 포장마차");
        Resources resources = this.getResources();
        busanFoodDto.setImg(BitmapFactory.decodeResource(resources, R.drawable.haeundae));
        busanFoodDto.setPlace("해운대포장마차촌");
        busanFoodDto.setTag1("#바다");
        busanFoodDto.setTag2("#술");
        busanFoodDto.setTag3("#낭만");
        FoodList.add(busanFoodDto);
        FoodList.add(busanFoodDto);
        FoodList.add(busanFoodDto);
        FoodList.add(busanFoodDto);
        ///////////////////////////////////////////////////////////////


        //리스트뷰에 Adapter 설정
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // position 값이랑 Dto 넘겨주기
                System.out.println("리스트뷰 클릭 !!!!!!!!"+position);
                Intent intent = new Intent(getApplicationContext(), FullImage.class);
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
    public void busanFoodByTags(ArrayList<String> tags){
        //DB에서 데이터 불러오기

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
            ImageView heartClick = (ImageView) convertView.findViewById(R.id.heartClick);

            mainImg.setImageBitmap(busanFoodDto.getImg());
            mainTitle.setText(busanFoodDto.getMainTitle());
            place.setText(busanFoodDto.getPlace());
            subTitle.setText(busanFoodDto.getSubTitle());
            tag1.setText(busanFoodDto.getTag1());
            tag2.setText(busanFoodDto.getTag2());
            tag3.setText(busanFoodDto.getTag3());

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


