package database.bpc.bpc_dis_database.Logger;

public enum QueryType {

    DELETE(1),
    DELETE_BY_CONDITION(2),
    DELETE_ALL(3),
    INSERT(4),
    INSERT_LIST(5),
    SELECT_ALL(6),
    SELECT_BY_CONDITION(7),
    SELECT_BY_QUERY(8),
    SELECT_BY_ID(9),
    UPDATE_LIST(10),
    UPDATE(11),
    QUERY(12);

    private int value;

    QueryType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}