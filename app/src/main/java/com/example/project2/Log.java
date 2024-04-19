package com.example.project2;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "logs")
public class Log {
    @PrimaryKey(autoGenerate = true)
    private int id ;
    @ColumnInfo(name = "type")
    private String type ;
    @ColumnInfo(name = "user")
    private String user ;

    @ColumnInfo(name = "idreservation")
    private int idreservation ;

    public Log(String type, String user  ) {
        this.type = type;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getIdreservation() {
        return idreservation;
    }

    public void setIdreservation(int idreservation) {
        this.idreservation = idreservation;
    }

    @NonNull
    @Override
    public String toString() {
        return type + " - " +  user  +  " - " +idreservation;
    }
}
