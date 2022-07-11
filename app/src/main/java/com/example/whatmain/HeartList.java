package com.example.whatmain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class HeartList extends AppCompatActivity {

    //좋아요 누른 리스트
    ArrayList<BusanFoodDto> busanFoodDtoListBytagsLoved = new ArrayList<>();
    ArrayList<BusanTodoDto> busanTodoDtoListBytagsLoved = new ArrayList<>();
    ArrayList<BusanFestivalDto> busanFestivalDtoListBytagsLoved = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart_list);

        //adapter 설정은 안해줘도 되는 것인가????
        //food 하트에 해당하는 데이터 불러와서 list에 올리기
        ListView foodListView = (ListView) findViewById(R.id.listFood);

        foodListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // position 값이랑 Dto 넘겨주기
                System.out.println("리스트뷰 클릭: food !!!!!!!!"+position);
                Intent intent = new Intent(getApplicationContext(), FoodFullImage.class);
                //intent.putExtra("position",Integer.toString(position));
                System.out.println(busanFoodDtoListBytagsLoved.get(position).getCall()+"여기에요 여기 !!");
                intent.putExtra("Dto",busanFoodDtoListBytagsLoved.get(position));
                //.getMainTitle()
                startActivity(intent);
            }
        });

        //Todo 하트에 해당하는 데이터 불러와서 list에 올리기
        ListView todoListView = (ListView) findViewById(R.id.listEnter);

        todoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // position 값이랑 Dto 넘겨주기
                System.out.println("리스트뷰 클릭: food !!!!!!!!"+position);
                Intent intent = new Intent(getApplicationContext(), TodoFullImage.class);
                //intent.putExtra("position",Integer.toString(position));
                //System.out.println(busanTodoDtoListBytagsLoved.get(position).getCall()+"여기에요 여기 !!");
                intent.putExtra("Dto",busanTodoDtoListBytagsLoved.get(position));
                //.getMainTitle()
                startActivity(intent);
            }
        });

        //Festival 하트에 해당하는 데이터 불러와서 list에 올리기
        ListView festivalListView = (ListView) findViewById(R.id.listFire);

        festivalListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // position 값이랑 Dto 넘겨주기
                System.out.println("리스트뷰 클릭: food !!!!!!!!"+position);
                Intent intent = new Intent(getApplicationContext(), FestivalFullImage.class);
                //intent.putExtra("position",Integer.toString(position));
                //System.out.println(busanTodoDtoListBytagsLoved.get(position).getCall()+"여기에요 여기 !!");
                intent.putExtra("Dto",busanFestivalDtoListBytagsLoved.get(position));
                //.getMainTitle()
                startActivity(intent);
            }
        });
    }
}