package com.example.project2;
import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface ReservationDao {

    @Insert
    long  addReservation(Reservation  reservation);



}
