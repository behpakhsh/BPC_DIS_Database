package database.bpc.bpc_dis_database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.RawQuery;
import androidx.sqlite.db.SimpleSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteQuery;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;

import database.bpc.bpc_dis_database.Logger.DbLogInfo;
import database.bpc.bpc_dis_database.Logger.DbLoggerListener;
import database.bpc.bpc_dis_database.Logger.QueryType;
import database.bpc.bpc_dis_database.Logger.ResultType;

@Dao
public abstract class BaseRepository<T> {

    private DbLoggerListener dbLoggerListener;

    public String getTableName() {
        if (getClass().getSuperclass() != null) {
            ParameterizedType parameterizedType = (ParameterizedType) getClass().getSuperclass().getGenericSuperclass();
            if (parameterizedType != null) {
                Class aClass = ((Class) (parameterizedType).getActualTypeArguments()[0]);
                return aClass.getSimpleName();
            }
        }
        return "";
    }

    public void setDbLoggerListener(DbLoggerListener dbLoggerListener) {
        this.dbLoggerListener = dbLoggerListener;
    }

    ////////////////////////////////////////////////////////

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    protected abstract long dbUpdate(T t);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    protected abstract long[] dbUpdate(List<T> list);

    public long update(T t) {
        long i = -1;
        try {
            i = dbUpdate(t);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        logUpdateQuery(t.toString(), i);
        return i;
    }

    public long[] update(List<T> list) {
        long[] i = null;
        try {
            i = dbUpdate(list);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        logUpdateQuery(list.toString(), i);
        return i;
    }

    private void logUpdateQuery(String userQuery, long result) {
        DbLogInfo dbLogInfo = new DbLogInfo();
        dbLogInfo.setTableName(getTableName());
        dbLogInfo.setQuery("");
        dbLogInfo.setUserQuery(userQuery);
        dbLogInfo.setQueryType(QueryType.UPDATE);
        dbLogInfo.setResult(String.valueOf(result));
        if (result != -1) {
            dbLogInfo.setResultType(ResultType.OK);
        } else {
            dbLogInfo.setResultType(ResultType.NOT_OK);
        }
        if (dbLoggerListener != null) {
            dbLoggerListener.onLog(dbLogInfo);
        }
    }

    private void logUpdateQuery(String userQuery, long[] result) {
        DbLogInfo dbLogInfo = new DbLogInfo();
        dbLogInfo.setTableName(getTableName());
        dbLogInfo.setQuery("");
        dbLogInfo.setUserQuery(userQuery);
        dbLogInfo.setQueryType(QueryType.UPDATE_LIST);
        if (result == null) {
            dbLogInfo.setResult("");
            dbLogInfo.setResultType(ResultType.NULL);
        } else if (result.length == 0) {
            dbLogInfo.setResult("");
            dbLogInfo.setResultType(ResultType.EMPTY);
        } else {
            dbLogInfo.setResult(Arrays.toString(result));
            dbLogInfo.setResultType(ResultType.OK);
        }
        if (dbLoggerListener != null) {
            dbLoggerListener.onLog(dbLogInfo);
        }
    }

    ////////////////////////////////////////////////////////

    @Insert(onConflict = OnConflictStrategy.FAIL)
    protected abstract long dbInsert(T t);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    protected abstract long[] dbInsert(List<T> list);

    public long insert(T t) {
        long i = -1;
        try {
            i = dbInsert(t);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        logInsertQuery(t.toString(), i);
        return i;
    }

    public long[] insert(List<T> list) {
        long[] i = null;
        try {
            i = dbInsert(list);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        logInsertQuery(list.toString(), i);
        return i;
    }

    private void logInsertQuery(String userQuery, long result) {
        DbLogInfo dbLogInfo = new DbLogInfo();
        dbLogInfo.setTableName(getTableName());
        dbLogInfo.setQuery("");
        dbLogInfo.setUserQuery(userQuery);
        dbLogInfo.setQueryType(QueryType.INSERT_LIST);
        dbLogInfo.setResult(String.valueOf(result));
        if (result != -1) {
            dbLogInfo.setResultType(ResultType.OK);
        } else {
            dbLogInfo.setResultType(ResultType.NOT_OK);
        }
        if (dbLoggerListener != null) {
            dbLoggerListener.onLog(dbLogInfo);
        }
    }

    private void logInsertQuery(String userQuery, long[] result) {
        DbLogInfo dbLogInfo = new DbLogInfo();
        dbLogInfo.setTableName(getTableName());
        dbLogInfo.setQuery("");
        dbLogInfo.setUserQuery(userQuery);
        dbLogInfo.setQueryType(QueryType.INSERT);
        if (result == null) {
            dbLogInfo.setResult("");
            dbLogInfo.setResultType(ResultType.NULL);
        } else if (result.length == 0) {
            dbLogInfo.setResult("");
            dbLogInfo.setResultType(ResultType.EMPTY);
        } else {
            dbLogInfo.setResult(Arrays.toString(result));
            dbLogInfo.setResultType(ResultType.OK);
        }
        if (dbLoggerListener != null) {
            dbLoggerListener.onLog(dbLogInfo);
        }
    }

    ////////////////////////////////////////////////////////

    @RawQuery
    protected abstract int dbDelete(SupportSQLiteQuery supportSQLiteQuery);

    @Delete
    protected abstract int dbDelete(T t);

    public void delete(T t) {
        int i = -1;
        try {
            i = dbDelete(t);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        logDeleteQuery("", t.toString(), QueryType.DELETE, i);
    }

    public void deleteAll() {
        SimpleSQLiteQuery simpleSQLiteQuery = new SimpleSQLiteQuery("delete  from " + getTableName());
        int i = -1;
        try {
            i = dbDelete(simpleSQLiteQuery);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        logDeleteQuery(simpleSQLiteQuery.getSql(), "", QueryType.DELETE_ALL, i);
    }

    public void deleteByCondition(String condition) {
        if (condition != null && !condition.trim().isEmpty()) {
            SimpleSQLiteQuery simpleSQLiteQuery = new SimpleSQLiteQuery("delete  from " + getTableName() + " where " + condition);
            int i = -1;
            try {
                i = dbDelete(simpleSQLiteQuery);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            logDeleteQuery(simpleSQLiteQuery.getSql(), condition, QueryType.DELETE_BY_CONDITION, i);
        }
    }

    private void logDeleteQuery(String query, String userQuery, QueryType queryType, int result) {
        DbLogInfo dbLogInfo = new DbLogInfo();
        dbLogInfo.setTableName(getTableName());
        dbLogInfo.setQuery(query);
        dbLogInfo.setUserQuery(userQuery);
        dbLogInfo.setQueryType(queryType);
        dbLogInfo.setResult(String.valueOf(result));
        if (result != -1) {
            dbLogInfo.setResultType(ResultType.OK);
        } else {
            dbLogInfo.setResultType(ResultType.NOT_OK);
        }
        if (dbLoggerListener != null) {
            dbLoggerListener.onLog(dbLogInfo);
        }
    }

    ////////////////////////////////////////////////////////

    @RawQuery
    protected abstract List<T> getAll(SupportSQLiteQuery supportSQLiteQuery);

    @RawQuery
    protected abstract T get(SupportSQLiteQuery supportSQLiteQuery);

    public T get(long id) {
        SimpleSQLiteQuery simpleSQLiteQuery = new SimpleSQLiteQuery("select * from " + getTableName() + " where id = ?", new Object[]{id});
        T t = null;
        try {
            t = get(simpleSQLiteQuery);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        logGetQuery(simpleSQLiteQuery.getSql(), String.valueOf(id), t);
        return t;
    }

    public List<T> getAll() {
        SimpleSQLiteQuery simpleSQLiteQuery = new SimpleSQLiteQuery("select * from " + getTableName());
        List<T> ts = null;
        try {
            ts = getAll(simpleSQLiteQuery);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        logGetQuery(simpleSQLiteQuery.getSql(), "", QueryType.SELECT_ALL, ts);
        return ts;
    }

    public List<T> getByQuery(String query) {
        SimpleSQLiteQuery simpleSQLiteQuery = new SimpleSQLiteQuery(query);
        List<T> ts = null;
        try {
            ts = getAll(simpleSQLiteQuery);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        logGetQuery(simpleSQLiteQuery.getSql(), query, QueryType.SELECT_BY_QUERY, ts);
        return ts;
    }

    public List<T> getByCondition(String condition) {
        SimpleSQLiteQuery simpleSQLiteQuery = new SimpleSQLiteQuery("select * from " + getTableName() + " where " + condition);
        List<T> ts = null;
        try {
            ts = getAll(simpleSQLiteQuery);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        logGetQuery(simpleSQLiteQuery.getSql(), condition, QueryType.SELECT_BY_CONDITION, ts);
        return ts;
    }

    private void logGetQuery(String query, String userQuery, QueryType queryType, List<T> result) {
        DbLogInfo dbLogInfo = new DbLogInfo();
        dbLogInfo.setTableName(getTableName());
        dbLogInfo.setQuery(query);
        dbLogInfo.setUserQuery(userQuery);
        dbLogInfo.setQueryType(queryType);
        if (result == null) {
            dbLogInfo.setResult("");
            dbLogInfo.setResultType(ResultType.NULL);
        } else if (result.isEmpty()) {
            dbLogInfo.setResult("");
            dbLogInfo.setResultType(ResultType.EMPTY);
        } else {
            dbLogInfo.setResult(result.toString());
            dbLogInfo.setResultType(ResultType.OK);
        }
        if (dbLoggerListener != null) {
            dbLoggerListener.onLog(dbLogInfo);
        }
    }

    private void logGetQuery(String query, String userQuery, T result) {
        DbLogInfo dbLogInfo = new DbLogInfo();
        dbLogInfo.setTableName(getTableName());
        dbLogInfo.setQuery(query);
        dbLogInfo.setUserQuery(userQuery);
        dbLogInfo.setQueryType(QueryType.SELECT_BY_ID);
        if (result == null) {
            dbLogInfo.setResult("");
            dbLogInfo.setResultType(ResultType.NULL);
        } else {
            dbLogInfo.setResult(result.toString());
            dbLogInfo.setResultType(ResultType.OK);
        }
        if (dbLoggerListener != null) {
            dbLoggerListener.onLog(dbLogInfo);
        }
    }

}