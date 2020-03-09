package database.bpc.bpc_dis_database.Logger;

import androidx.annotation.NonNull;

import java.util.Date;

public class DbLogInfo {

    private String tableName;
    private QueryType queryType;
    private String query;
    private String userQuery;
    private ResultType resultType;
    private Date date = new Date();
    private String result;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
                ", queryType=" + queryType +
                ", query='" + query + '\'' +
                ", userQuery='" + userQuery + '\'' +
                ", resultType=" + resultType +
                ", date=" + date +
                ", result='" + result + '\'' +
                '}';
    }

}