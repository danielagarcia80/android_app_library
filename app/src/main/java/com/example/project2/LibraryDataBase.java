package com.example.project2;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = {User.class , Book.class , Log.class ,Reservation.class}, version = 3, exportSchema = false)
public  abstract class LibraryDataBase  extends RoomDatabase {

    public abstract UserDao  user();
    public abstract LogDao  log();
    public abstract BookDao  book();

    public abstract ReservationDao reservation();
    private static LibraryDataBase sInstance;
    public static synchronized LibraryDataBase getInstance(Context context) {
        if (sInstance == null) {
            sInstance = Room.databaseBuilder(context.getApplicationContext(),
                            LibraryDataBase.class, "library.db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return sInstance;
    }

    public void populateInitialData() {
        runInTransaction(() -> {

            User user1 = user().findByUsername("hShuard") ;
            User user2 = user().findByUsername("bMishra") ;
            User user3 = user().findByUsername("shirleyBee") ;
            User user4 = user().findByUsername("!admin2") ;

            if (user1 == null  ){
                user1  = new User(  "hShuard" , "m@thl3t3\n");
                user().addUser(user1 );
            }

            if (user2 == null  ){
                user2  = new User(  "bMishra" , "bioN@no");
                user().addUser(user2 );
            }
            if (user3 == null  ){
                user3  = new User(  "shirleyBee" , "Carmel2Chicago");
                user().addUser(user3 );
            }
            if (user4 == null  ){
                user4  = new User(  "!admin2" , "!admin2");
                user().addUser(user4 );
            }

            Book book1 = book().findByTitle("A Heartbreaking Work of Staggering Genius") ;
            Book book2 = book().findByTitle("The IDA Pro Book") ;
            Book book3 = book().findByTitle("Frankenstein") ;

            if (book1 == null  ){
                book1  = new Book( "A Heartbreaking Work of Staggering Genius", "Dave Eggers", "Memoir");
                book().addBook(book1);
            }
            if (book2 == null  ){
                book2  = new Book( "The IDA Pro Book", "Chris Eagle", "Computer Science");
                book().addBook(book2);
            }
            if (book3 == null  ){
                book3  = new Book( "Frankenstein", "Mery Shelly", "Fiction");
                book().addBook(book3);
            }





        });
    }

}
