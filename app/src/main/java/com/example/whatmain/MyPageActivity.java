package com.example.whatmain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MyPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);

        ViewPager vp=findViewById(R.id.viewpager);
        VPAdapter adapter=new VPAdapter(getSupportFragmentManager());
        vp.setAdapter(adapter);

        final ViewPager viewPager=(ViewPager) findViewById(R.id.viewpager);
        final VPAdapter vpAdapter=new VPAdapter(getSupportFragmentManager());
        viewPager.setAdapter(vpAdapter);

        TabLayout tabs=(TabLayout) findViewById(R.id.tab);
        tabs.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));

        //탭에 이미지 설정할거면
//        ArrayList<Integer> images=new ArrayList<>();
//        images.add(R.drawable.setting);
//        images.add(R.drawable.heart);
//        //images.add(R.drawable.set);
//        for (int i=0;i<2;i++)tabs.getTabAt(i).setIcon(images.get(i));
        //images.add(R.drawable.set);

    }
}