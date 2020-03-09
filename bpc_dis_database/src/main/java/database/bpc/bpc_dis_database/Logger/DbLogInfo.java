package database.bpc.bpc_dis_database.Logger;

import androidx.annotation.NonNull;

public class DbLogInfo {

    private String tableName;
    private String result;
    private String userQuery;
    private String query;
    private QueryType queryType;
    private ResultType resultType;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getUserQuery() {
        return userQuery;
    }

    public void setUserQuery(String userQuery) {
        this.userQuery = userQuery;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public QueryType getQueryType() {
        return queryType;
    }

    public void setQueryType(QueryType queryType) {
        this.queryType = queryType;
    }

    public ResultType getResultType() {
        return resultType;
    }

    public void setResultType(ResultType resultType) {
        this.resultType = resultType;
    }

    @NonNull
    @Override
    public String toString() {
        return "{" +
                "tableName='" + tableName + '\'' +
                ", result='" + result + '\'' +
                ", userQuery='" + userQuery + '\'' +
                ", query='" + query + '\'' +
                ", queryType=" + queryType +
                ", resultType=" + resultType +
                '}';
    }

}