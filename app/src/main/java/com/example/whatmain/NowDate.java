package com.example.whatmain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class NowDate {
    public String getDate(){
        // 현재 날짜 구하기
        LocalDate now = LocalDate.now();

        // 포맷 정의
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        // 포맷 적용
        String formatedNow = now.format(formatter);

        return formatedNow;
    }
}
