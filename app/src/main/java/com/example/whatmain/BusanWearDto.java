package com.example.whatmain;

import android.graphics.Bitmap;

import java.io.Serializable;

public class BusanWearDto implements Serializable {
    String mainTitle ;
    String place;
    String subTitle;
    String shortContext;
    String img;
    String context;
    String tag1;
    String tag2;
    String tag3;
    double latitude;
    double longitude;
    public String getMainTitle() {
        return mainTitle;
    }
    public void setMainTitle(String mainTitle) {
        this.mainTitle = mainTitle;
    }
    public String getPlace() {
        return place;
    }
    public void setPlace(String place) {
        this.place = place;
    }
    public String getSubTitle() {
        return subTitle;
    }
    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }
    public String getShortContext() {
        return shortContext;
    }
    public void setShortContext(String shortContext) {
        this.shortContext = shortContext;
    }
    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }
    public String getContext() {
        return context;
    }
    public void setContext(String context) {
        this.context = context;
    }
    public String getTag1() {
        return tag1;
    }
    public void setTag1(String tag1) {
        this.tag1 = tag1;
    }
    public String getTag2() {
        return tag2;
    }
    public void setTag2(String tag2) {
        this.tag2 = tag2;
    }
    public String getTag3() {
        return tag3;
    }
    public void setTag3(String tag3) {
        this.tag3 = tag3;
    }
    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
