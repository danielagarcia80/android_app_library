package com.example.project2;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void addUser(User user);

    @Query("SELECT COUNT(*) FROM Users")
    int count();

    @Query("SELECT * FROM Users")
    List<User> getAll();

    @Delete
    void delete(User user);

    @Update
    void update(User user);


    @Query("SELECT * FROM users WHERE id = :id")
    User findById(int id);

    @Query("SELECT * FROM users WHERE username = :username")
    User findByUsername(String username);

}
