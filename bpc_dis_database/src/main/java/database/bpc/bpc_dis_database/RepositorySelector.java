package database.bpc.bpc_dis_database;

import android.content.Context;


public abstract class RepositorySelector {

    public abstract String getDataBaseName();
    public abstract BaseDatabase getDatabase(Context context);
    public BaseRepository select(Context context, Class type) {

        BaseRepository repository = getDatabase(context).selectRepository(type);
        return repository;
    }

}