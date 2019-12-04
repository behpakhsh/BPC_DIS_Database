package database.bpc.bpc_dis_database;

import androidx.room.RoomDatabase;


public abstract class BaseDatabase extends RoomDatabase {
    public abstract BaseRepository selectRepository(Class type);
}
