package com.example.project2;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BookDao {
    @Insert
    void addBook(Book book);

    @Query("SELECT COUNT(*) FROM books")
    int count();

    @Query("SELECT * FROM books")
    List<Book> getAll();


    @Query("SELECT * FROM books where  genre = :genre ")
    List<Book> getAllByGenre( String genre);

    @Query("SELECT * FROM books where  genre = :genre  and id not in (select idBook from reservation  )")
    List<Book> getAllDisponibleByGenre( String genre);

    @Delete
    void delete(Book book);

    @Update
    void update(Book book);


    @Query("SELECT * FROM books WHERE id = :id")
    Book findById(int id);


    @Query("SELECT * FROM books WHERE title = :title")
    Book findByTitle(String title);

}