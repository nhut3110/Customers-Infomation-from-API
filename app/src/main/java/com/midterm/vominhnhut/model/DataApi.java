package com.midterm.vominhnhut.model;

import com.google.gson.annotations.SerializedName;

public class DataApi {
    @SerializedName("lat")
    private String lat;


    @SerializedName("lng")
    private String lng;


    @SerializedName("desc")
    private String desc;


    @SerializedName("zip")
    private String zip;


    @SerializedName("title")
    private String title;


    @SerializedName("timeStamp")
    private String timeStamp;


    @SerializedName("addr")
    private String addr;


    @SerializedName("e")
    private String e;

    public DataApi(String title, String desc, String timestamp, String lat, String lng, String addr, String e, String zip){
        this.title = title;
        this.desc = desc;
        this.timeStamp = timestamp;
        this.lat = lat;
        this.lng = lng;
        this.addr = addr;
        this.e = e;
        this.zip = zip;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getE() {
        return e;
    }

    public void setE(String e) {
        this.e = e;
    }
}
