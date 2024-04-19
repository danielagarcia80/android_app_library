package com.example.project2;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface LogDao {

    @Insert
    void addLog(Log log);

    @Query("SELECT COUNT(*) FROM logs  ")
    int count();

    @Query("SELECT * FROM logs  order by id desc")
    List<Log> getAll();

    @Delete
    void delete(Log log);

    @Update
    void update(Log log);


    @Query("SELECT * FROM logs WHERE id = :id")
    Log findById(int id);
}
