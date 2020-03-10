package database.bpc.bpc_dis_database.Logger;

import java.util.Date;

public interface DbLoggerListener {

    void onLog(Date date, String tableName, QueryType queryType, String query, String userQuery, ResultType resultType, String result);

}