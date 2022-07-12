package com.example.whatmain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.icu.text.SymbolTable;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MyPageActivity extends AppCompatActivity {

    Fragment1 fragment1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);

        Intent i=getIntent();
        String userid=i.getStringExtra("id");
        //String userpw=i.getStringExtra("pw");

//        MyPageActivity fragment=new MyPageActivity();

//        ViewPager vp=findViewById(R.id.viewpager);
//        VPAdapter adapter=new VPAdapter(getSupportFragmentManager());
//        vp.setAdapter(adapter);

        final ViewPager viewPager=(ViewPager) findViewById(R.id.viewpager);
        final VPAdapter vpAdapter=new VPAdapter(getSupportFragmentManager());
        viewPager.setAdapter(vpAdapter);



        TabLayout tabs=(TabLayout) findViewById(R.id.tab);
        tabs.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));

        fragment1=new Fragment1();

        Bundle bundle=new Bundle(1);
        System.out.println("MyPage!!!!! 여기!!!");
        bundle.putString("id",userid);
        System.out.println("My Page 여깅러안러ㅣㄴ"+bundle.getString("id"));
        //bundle.putString("pw",userpw);
        System.out.println("MyPage id: "+userid);
        //System.out.println("MyPage pw: "+userpw);
        fragment1.setArguments(bundle);


        //탭에 이미지 설정할거면
//        ArrayList<Integer> images=new ArrayList<>();
//        images.add(R.drawable.setting);
//        images.add(R.drawable.heart);
//        //images.add(R.drawable.set);
//        for (int i=0;i<2;i++)tabs.getTabAt(i).setIcon(images.get(i));
        //images.add(R.drawable.set);

    }
}