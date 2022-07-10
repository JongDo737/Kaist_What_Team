package com.example.whatmain;

import java.util.ArrayList;

public interface DBconnectImpl {

    //회원가입
    public int insertUser(UserDto userDto);

    // 뭐물래
    // 태그를 통해서 축제정보 받아오기
    public ArrayList<BusanFoodDto> getFoodListByTags(ArrayList<String> tags);

    // 축제
    //모든 축제 정보 받아오기
    public ArrayList<BusanFestivalDto> getAllFestival();


}
