package com.midterm.vominhnhut.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.midterm.vominhnhut.model.DataDB;

import java.util.List;

@Dao
public interface DataDao {
    @Query("SELECT * FROM DataDB")
    List<DataDB> getAllData();

    @Insert
    void insertAll(DataDB... dataApis);

    @Delete
    void delete(DataDB dataDB);
}
