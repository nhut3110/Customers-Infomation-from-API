package com.midterm.vominhnhut.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class DataDB {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo
    private String title;

    @ColumnInfo
    private String desc;


    @ColumnInfo
    private String timestamp;

    @ColumnInfo
    private String lat;

    @ColumnInfo
    private String lng;

    @ColumnInfo
    private String addr;

    @ColumnInfo
    private String e;

    @ColumnInfo
    private String zip;

    public DataDB(String title, String desc, String timestamp, String lat, String lng, String addr, String e, String zip){
        this.title = title;
        this.desc = desc;
        this.timestamp = timestamp;
        this.lat = lat;
        this.lng = lng;
        this.addr = addr;
        this.e = e;
        this.zip = zip;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
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

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
