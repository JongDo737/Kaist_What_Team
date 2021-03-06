//package com.example.whatmain;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.annotation.SuppressLint;
//import android.content.Context;
//import android.content.Intent;
//import android.content.res.Resources;
//import android.graphics.Color;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.BaseAdapter;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.ListView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.bumptech.glide.Glide;
//
//import java.util.ArrayList;
//
//public class BusanWearStep1 extends AppCompatActivity {
//    Button keywordDate;
//    Button keywordParents;
//    Button keywordFriends;
//    Button keywordAlone;
//    Button keywordYammy;
//    Button keywordCafe;
//    Button keywordDrink;
//    Button keywordRomantic;
//    Button keywordRomance;
//    Button keySeafood;
//    Button keyOcean;
//
//    ListView listView;
//    ListViewAdapter adapter = null;
//    boolean clickCheck[]= new boolean[11];
//    ArrayList<String> tags = new ArrayList<>();
//    ArrayList<BusanWearDto> WearList = new ArrayList<>();
//
//    BusanWearDto busanFWearDto;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_busan_wear_step1);
//        keywordDate = findViewById(R.id.keywordDate);
//        keywordParents = findViewById(R.id.keywordParents);
//        keywordFriends = findViewById(R.id.keywordFriends);
//        keywordAlone = findViewById(R.id.keywordAlone);
//        keywordYammy = findViewById(R.id.keywordYammy);
//        keywordCafe = findViewById(R.id.keywordCafe);
//        keywordDrink = findViewById(R.id.keywordDrink);
//        keywordRomantic = findViewById(R.id.keywordRomantic);
//        keywordRomance = findViewById(R.id.keywordRomance);
//        keySeafood = findViewById(R.id.keySeafood);
//        keyOcean = findViewById(R.id.keyOcean);
//
//        listView = (ListView) findViewById(R.id.listView);
//        adapter = new ListViewAdapter(this, WearList);
//
//        // ????????? ????????? ??????///////////////////////////////////////////////
//        busanFWearDto = new BusanWearDto();
//        busanFWearDto.setMainTitle("????????????????????????");
//        busanFWearDto.setSubTitle("?????? ?????? ????????? ?????? ????????? ????????????");
//        Resources resources = this.getResources();
//        //busanFWearDto.setImg(BitmapFactory.decodeResource(resources, R.drawable.haeundae));
//        // Glide??? ????????? ????????????
//        String imageUrl = "https://www.visitbusan.net/uploadImgs/files/cntnts/20191230180157336_ttiel";
//        busanFWearDto.setImg(imageUrl);
//
//        busanFWearDto.setPlace("????????????????????????");
//        busanFWearDto.setTag1("#??????");
//        busanFWearDto.setTag2("#???");
//        busanFWearDto.setTag3("#??????");
//        WearList.add(busanFWearDto);
//        WearList.add(busanFWearDto);
//        WearList.add(busanFWearDto);
//        WearList.add(busanFWearDto);
//        ///////////////////////////////////////////////////////////////
//
//
//        //??????????????? Adapter ??????
//        listView.setAdapter(adapter);
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                // position ????????? Dto ????????????
//                System.out.println("???????????? ??????: wear !!!!!!!!"+position);
//                Intent intent = new Intent(getApplicationContext(), FoodFullImage.class);
//                intent.putExtra("position",Integer.toString(position));
//                intent.putExtra("festivalList",WearList);
//                startActivity(intent);
//            }
//        });
//
//        // ????????? ??????
//        keywordDate.setOnClickListener(new View.OnClickListener() {
//            @SuppressLint("ResourceAsColor")
//            @Override
//            public void onClick(View v) {
//                if(clickCheck[0] == false ) { // ??? ??????
//                    if(checkSize(clickCheck) < 3){
//                        keywordDate.setBackgroundResource(R.drawable.clicked_button);
//                        keywordDate.setTextColor(Color.parseColor("#ffffff"));
//                        clickCheck[0] = true;
//                        addTag(keywordDate.getText().toString());
//                    }else {
//                        System.out.println("???????????? ??????!!!!!!!!");
//                        Toast.makeText(BusanWearStep1.this, "????????? 3????????? ?????? ???????????????.", Toast.LENGTH_SHORT).show();
//                    }
//
//                }else{ // ?????? ??????
//                    keywordDate.setBackgroundResource(R.drawable.round_button);
//                    keywordDate.setTextColor(Color.parseColor("#0C0D0E"));
//                    clickCheck[0] = false;
//                    deleteTag(keywordDate.getText().toString());
//                }
//
//            }
//        });
//        keywordParents.setOnClickListener(new View.OnClickListener() {
//            @SuppressLint("ResourceAsColor")
//            @Override
//            public void onClick(View v) {
//                if(clickCheck[1] == false ) { // ??? ??????
//                    if(checkSize(clickCheck) < 3){
//                        keywordParents.setBackgroundResource(R.drawable.clicked_button);
//                        keywordParents.setTextColor(Color.parseColor("#ffffff"));
//                        clickCheck[1] = true;
//                        addTag(keywordParents.getText().toString());
//                    }else {
//                        System.out.println("???????????? ??????!!!!!!!!");
//                        Toast.makeText(BusanWearStep1.this, "????????? 3????????? ?????? ???????????????.", Toast.LENGTH_SHORT).show();
//                    }
//
//                }else{ // ?????? ??????
//                    keywordParents.setBackgroundResource(R.drawable.round_button);
//                    keywordParents.setTextColor(Color.parseColor("#0C0D0E"));
//                    clickCheck[1] = false;
//                    deleteTag(keywordParents.getText().toString());
//                }
//
//            }
//        });
//        keywordFriends.setOnClickListener(new View.OnClickListener() {
//            @SuppressLint("ResourceAsColor")
//            @Override
//            public void onClick(View v) {
//                if(clickCheck[2] == false ) { // ??? ??????
//                    if(checkSize(clickCheck) < 3){
//                        keywordFriends.setBackgroundResource(R.drawable.clicked_button);
//                        keywordFriends.setTextColor(Color.parseColor("#ffffff"));
//                        clickCheck[2] = true;
//                        addTag(keywordFriends.getText().toString());
//                    }else {
//                        System.out.println("???????????? ??????!!!!!!!!");
//                        Toast.makeText(BusanWearStep1.this, "????????? 3????????? ?????? ???????????????.", Toast.LENGTH_SHORT).show();
//                    }
//
//                }else{ // ?????? ??????
//                    keywordFriends.setBackgroundResource(R.drawable.round_button);
//                    keywordFriends.setTextColor(Color.parseColor("#0C0D0E"));
//                    clickCheck[2] = false;
//                    deleteTag(keywordFriends.getText().toString());
//                }
//
//            }
//        });
//        keywordAlone.setOnClickListener(new View.OnClickListener() {
//            @SuppressLint("ResourceAsColor")
//            @Override
//            public void onClick(View v) {
//                if(clickCheck[3] == false ) { // ??? ??????
//                    if(checkSize(clickCheck) < 3){
//                        keywordAlone.setBackgroundResource(R.drawable.clicked_button);
//                        keywordAlone.setTextColor(Color.parseColor("#ffffff"));
//                        clickCheck[3] = true;
//                        addTag(keywordAlone.getText().toString());
//                    }else {
//                        System.out.println("???????????? ??????!!!!!!!!");
//                        Toast.makeText(BusanWearStep1.this, "????????? 3????????? ?????? ???????????????.", Toast.LENGTH_SHORT).show();
//                    }
//
//                }else{ // ?????? ??????
//                    keywordAlone.setBackgroundResource(R.drawable.round_button);
//                    keywordAlone.setTextColor(Color.parseColor("#0C0D0E"));
//                    clickCheck[3] = false;
//                    deleteTag(keywordAlone.getText().toString());
//                }
//
//            }
//        });
//        keywordYammy.setOnClickListener(new View.OnClickListener() {
//            @SuppressLint("ResourceAsColor")
//            @Override
//            public void onClick(View v) {
//                if(clickCheck[4] == false ) { // ??? ??????
//                    if(checkSize(clickCheck) < 3){
//                        keywordYammy.setBackgroundResource(R.drawable.clicked_button);
//                        keywordYammy.setTextColor(Color.parseColor("#ffffff"));
//                        clickCheck[4] = true;
//                        addTag(keywordYammy.getText().toString());
//                    }else {
//                        System.out.println("???????????? ??????!!!!!!!!");
//                        Toast.makeText(BusanWearStep1.this, "????????? 3????????? ?????? ???????????????.", Toast.LENGTH_SHORT).show();
//                    }
//
//                }else{ // ?????? ??????
//                    keywordYammy.setBackgroundResource(R.drawable.round_button);
//                    keywordYammy.setTextColor(Color.parseColor("#0C0D0E"));
//                    clickCheck[4] = false;
//                    deleteTag(keywordYammy.getText().toString());
//                }
//
//            }
//        });
//        keywordCafe.setOnClickListener(new View.OnClickListener() {
//            @SuppressLint("ResourceAsColor")
//            @Override
//            public void onClick(View v) {
//                if(clickCheck[5] == false ) { // ??? ??????
//                    if(checkSize(clickCheck) < 3){
//                        keywordCafe.setBackgroundResource(R.drawable.clicked_button);
//                        keywordCafe.setTextColor(Color.parseColor("#ffffff"));
//                        clickCheck[5] = true;
//                        addTag(keywordCafe.getText().toString());
//                    }else {
//                        System.out.println("???????????? ??????!!!!!!!!");
//                        Toast.makeText(BusanWearStep1.this, "????????? 3????????? ?????? ???????????????.", Toast.LENGTH_SHORT).show();
//                    }
//
//                }else{ // ?????? ??????
//                    keywordCafe.setBackgroundResource(R.drawable.round_button);
//                    keywordCafe.setTextColor(Color.parseColor("#0C0D0E"));
//                    clickCheck[5] = false;
//                    deleteTag(keywordCafe.getText().toString());
//                }
//
//            }
//        });
//        keywordDrink.setOnClickListener(new View.OnClickListener() {
//            @SuppressLint("ResourceAsColor")
//            @Override
//            public void onClick(View v) {
//                if(clickCheck[6] == false ) { // ??? ??????
//                    if(checkSize(clickCheck) < 3){
//                        keywordDrink.setBackgroundResource(R.drawable.clicked_button);
//                        keywordDrink.setTextColor(Color.parseColor("#ffffff"));
//                        clickCheck[6] = true;
//                        addTag(keywordDrink.getText().toString());
//                    }else {
//                        System.out.println("???????????? ??????!!!!!!!!");
//                        Toast.makeText(BusanWearStep1.this, "????????? 3????????? ?????? ???????????????.", Toast.LENGTH_SHORT).show();
//                    }
//
//                }else{ // ?????? ??????
//                    keywordDrink.setBackgroundResource(R.drawable.round_button);
//                    keywordDrink.setTextColor(Color.parseColor("#0C0D0E"));
//                    clickCheck[6] = false;
//                    deleteTag(keywordDrink.getText().toString());
//                }
//
//            }
//        });
//        keywordRomantic.setOnClickListener(new View.OnClickListener() {
//            @SuppressLint("ResourceAsColor")
//            @Override
//            public void onClick(View v) {
//                if(clickCheck[7] == false ) { // ??? ??????
//                    if(checkSize(clickCheck) < 3){
//                        keywordRomantic.setBackgroundResource(R.drawable.clicked_button);
//                        keywordRomantic.setTextColor(Color.parseColor("#ffffff"));
//                        clickCheck[7] = true;
//                        addTag(keywordRomantic.getText().toString());
//                    }else {
//                        System.out.println("???????????? ??????!!!!!!!!");
//                        Toast.makeText(BusanWearStep1.this, "????????? 3????????? ?????? ???????????????.", Toast.LENGTH_SHORT).show();
//                    }
//
//                }else{ // ?????? ??????
//                    keywordRomantic.setBackgroundResource(R.drawable.round_button);
//                    keywordRomantic.setTextColor(Color.parseColor("#0C0D0E"));
//                    clickCheck[7] = false;
//                    deleteTag(keywordRomantic.getText().toString());
//                }
//
//            }
//        });
//        keywordRomance.setOnClickListener(new View.OnClickListener() {
//            @SuppressLint("ResourceAsColor")
//            @Override
//            public void onClick(View v) {
//                if(clickCheck[8] == false ) { // ??? ??????
//                    if(checkSize(clickCheck) < 3){
//                        keywordRomance.setBackgroundResource(R.drawable.clicked_button);
//                        keywordRomance.setTextColor(Color.parseColor("#ffffff"));
//                        clickCheck[8] = true;
//                        addTag(keywordRomance.getText().toString());
//                    }else {
//                        System.out.println("???????????? ??????!!!!!!!!");
//                        Toast.makeText(BusanWearStep1.this, "????????? 3????????? ?????? ???????????????.", Toast.LENGTH_SHORT).show();
//                    }
//
//                }else{ // ?????? ??????
//                    keywordRomance.setBackgroundResource(R.drawable.round_button);
//                    keywordRomance.setTextColor(Color.parseColor("#0C0D0E"));
//                    clickCheck[8]= false;
//                    deleteTag(keywordRomance.getText().toString());
//                }
//
//            }
//        });
//        keySeafood.setOnClickListener(new View.OnClickListener() {
//            @SuppressLint("ResourceAsColor")
//            @Override
//            public void onClick(View v) {
//                if(clickCheck[9] == false ) { // ??? ??????
//                    if(checkSize(clickCheck) < 3){
//                        keySeafood.setBackgroundResource(R.drawable.clicked_button);
//                        keySeafood.setTextColor(Color.parseColor("#ffffff"));
//                        clickCheck[9] = true;
//                        addTag(keySeafood.getText().toString());
//                    }else {
//                        System.out.println("???????????? ??????!!!!!!!!");
//                        Toast.makeText(BusanWearStep1.this, "????????? 3????????? ?????? ???????????????.", Toast.LENGTH_SHORT).show();
//                    }
//
//                }else{ // ?????? ??????
//                    keySeafood.setBackgroundResource(R.drawable.round_button);
//                    keySeafood.setTextColor(Color.parseColor("#0C0D0E"));
//                    clickCheck[9] = false;
//                    deleteTag(keySeafood.getText().toString());
//                }
//
//            }
//        });
//        keyOcean.setOnClickListener(new View.OnClickListener() {
//            @SuppressLint("ResourceAsColor")
//            @Override
//            public void onClick(View v) {
//                if(clickCheck[10] == false ) { // ??? ??????
//                    if(checkSize(clickCheck) < 3){
//                        keyOcean.setBackgroundResource(R.drawable.clicked_button);
//                        keyOcean.setTextColor(Color.parseColor("#ffffff"));
//                        clickCheck[10] = true;
//                        addTag(keyOcean.getText().toString());
//                    }else {
//                        System.out.println("???????????? ??????!!!!!!!!");
//                        Toast.makeText(BusanWearStep1.this, "????????? 3????????? ?????? ???????????????.", Toast.LENGTH_SHORT).show();
//                    }
//
//                }else{ // ?????? ??????
//                    keyOcean.setBackgroundResource(R.drawable.round_button);
//                    keyOcean.setTextColor(Color.parseColor("#0C0D0E"));
//                    clickCheck[10] = false;
//                    deleteTag(keyOcean.getText().toString());
//                }
//
//            }
//        });
//
//    }
//    // ?????? 3??? ?????? ??????
//    public int checkSize(boolean clickCheck[]){
//        int count = 0;
//        for(int i=0; i< clickCheck.length; i++){
//            if(clickCheck[i]){  // true ?????? count ++
//                count ++;
//            }
//        }
//        return count;
//    }
//    // ?????? ??????
//    public void addTag(String tag){
//        tags.add(tag);
//        // ????????? ??????
//        for(int i=0;i<tags.size(); i++){
//            System.out.println(tags.get(i));
//        }
//        busanFoodByTags(tags);
//    }
//    public void deleteTag(String tag){
//        tags.remove(tags.indexOf(tag));
//        //????????? ??????
//        for(int i=0;i<tags.size(); i++){
//            System.out.println(tags.get(i));
//        }
//        busanFoodByTags(tags);
//    }
//    public void busanFoodByTags(ArrayList<String> tags){
//        //DB?????? ????????? ????????????
//
//    }
//
//
//    /* ???????????? ????????? */
//    public class ListViewAdapter extends BaseAdapter {
//        ArrayList<BusanWearDto> items = new ArrayList<>();
//        Context mContext = null;
//        LayoutInflater mLayoutInflater = null;
//        public ListViewAdapter(Context context, ArrayList<BusanWearDto> data) {
//            mContext = context;
//            items = data;
//            mLayoutInflater = LayoutInflater.from(mContext);
//        }
//
//        @Override
//        public int getCount() {
//            return items.size();
//        }
//
//        public void addItem(BusanWearDto item) {
//            items.add(item);
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return items.get(position);
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return position;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup viewGroup) {
//            final Context context = viewGroup.getContext();
//            final BusanWearDto busanFWearDto = items.get(position);
//
//            if(convertView == null) {
//                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                convertView = inflater.inflate(R.layout.busan_wear_list, viewGroup, false);
//
//            } else {
//                View view = new View(context);
//                view = (View) convertView;
//            }
//            ImageView mainImg = (ImageView) convertView.findViewById(R.id.mainImg);
//            TextView mainTitle = (TextView) convertView.findViewById(R.id.mainTitle);
//            TextView subTitle = (TextView) convertView.findViewById(R.id.subTitle);
//            TextView place = (TextView) convertView.findViewById(R.id.mainContext);
//            TextView tag1 = (TextView) convertView.findViewById(R.id.tag1);
//            TextView tag2 = (TextView) convertView.findViewById(R.id.tag2);
//            TextView tag3 = (TextView) convertView.findViewById(R.id.tag3);
//            ImageView heartClick = (ImageView) convertView.findViewById(R.id.heartClick);
//
//            //mainImg.setImageBitmap(busanFWearDto.getImg());
//            Glide.with(getApplicationContext()).load(busanFWearDto.getImg()).into(mainImg);
//
//            mainTitle.setText(busanFWearDto.getMainTitle());
//            place.setText(busanFWearDto.getPlace());
//            subTitle.setText(busanFWearDto.getSubTitle());
//            tag1.setText(busanFWearDto.getTag1());
//            tag2.setText(busanFWearDto.getTag2());
//            tag3.setText(busanFWearDto.getTag3());
//
//            //??? ????????? ?????? event
////            convertView.setOnClickListener(new View.OnClickListener() {
////                @Override
////                public void onClick(View view) {
////                    Toast.makeText(context, busanFWearDto.getMainTitle(),Toast.LENGTH_SHORT).show();
////                }
////            });
//            heartClick.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    heartClick.setImageResource(R.drawable.redheart);
//                }
//            });
//
//            return convertView;  //??? ?????? ??????
//        }
//    }
//
//}
//
//
