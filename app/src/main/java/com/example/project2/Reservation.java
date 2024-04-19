package com.example.project2;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "reservation")
public class Reservation
{
    @PrimaryKey(autoGenerate = true)
    int id ;

    @ColumnInfo(name = "idBook")
    int book ;


    @ColumnInfo(name = "username")
    String user  ;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBook() {
        return book;
    }

    public void setBook(int book) {
        this.book = book;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Reservation(int book, String user) {
        this.book = book;
        this.user = user;
    }

    @NonNull
    @Override
    public String toString() {



        return id + " - " + user  ;
    }
}
