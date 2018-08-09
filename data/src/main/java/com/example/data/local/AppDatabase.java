package com.example.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;


/**
 * This class represents...
 *
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 09/08/18.
 * Jesus is alive!
 */

@Database(
        entities = {BeerEntity.class},
        version = 1
)
public abstract class AppDatabase extends RoomDatabase {

//    public static AppDatabase createInstance(Context context) {
//        return Room
//                .databaseBuilder(context.getApplicationContext(), AppDatabase.class, "database-name")
//                .build();
//    }

    public abstract BeerDao beerDao();
}
