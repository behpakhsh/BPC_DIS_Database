package database.bpc.bpc_dis_database;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.RawQuery;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SimpleSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteQuery;


@Dao
public abstract class BaseRepository<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract long update(T obj);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract long[] update(List<T> objs);

    @Insert(onConflict = OnConflictStrategy.FAIL)
    public abstract long insert(T obj);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract long[] insert(List<T> objs);

    @Delete
    public abstract void delete(T obj);

    @RawQuery
    protected abstract List<T> getAll(SupportSQLiteQuery query);

    @RawQuery
    protected abstract T get(SupportSQLiteQuery query);

    @RawQuery
    protected abstract int delete(SupportSQLiteQuery query);

    public List<T> getAll() {
        try {
            SimpleSQLiteQuery query = new SimpleSQLiteQuery("select * from " + getTableName());
            return getAll(query);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public T get(long id) {
        return get(new SimpleSQLiteQuery("select * from " + getTableName() + " where id = ?", new Object[]{id}));
    }

    public T getByCode(int code) {
        return getByCode(String.valueOf(code));
    }

    private T getByCode(String code) {
        return get(new SimpleSQLiteQuery("select * from " + getTableName() + " where code = ?", new Object[]{code}));
    }

    public void deleteAll() {
        try {
            delete(new SimpleSQLiteQuery("delete  from " + getTableName()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<T> getByCondition(String condition) {
        try {
            return getAll(new SimpleSQLiteQuery("select * from " + getTableName() + " where " + condition));
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }

    public String getTableName() {
        return ((Class) ((ParameterizedType) getClass().getSuperclass().getGenericSuperclass()).getActualTypeArguments()[0]).getSimpleName();
    }

    public List<T> getByQuery(String query) {
        try {
            return getAll(new SimpleSQLiteQuery(query));
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }

    public  void deleteByCondition(String condition){
        try {
            if (condition == null || condition.trim().equals("")){
                return;
            }
            delete(new SimpleSQLiteQuery("delete  from " + getTableName() + " Where " + condition));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    };
}