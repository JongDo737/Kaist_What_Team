package com.example.whatmain;

import android.content.Context;

import java.util.ArrayList;

public interface DBconnectImpl {
    //로그인
    public int requestLogin(String ID, String PW, Context test);
    //회원가입
    public int insertUser(UserDto userDto, Context test);

    // 뭐물래
    // 태그를 통해서 축제정보 받아오기
    public ArrayList<BusanFoodDto> getFoodListByTags(ArrayList<String> tags, Context test);

    // 축제
    //모든 축제 정보 받아오기
    public ArrayList<BusanFestivalDto> getAllFestival(Context test);
}
