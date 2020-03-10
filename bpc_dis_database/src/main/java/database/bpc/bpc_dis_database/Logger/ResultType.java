package database.bpc.bpc_dis_database.Logger;

public enum ResultType {

    OK(1),
    NULL(2),
    EMPTY(3),
    NOT_OK(4);

    private int value;

    ResultType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}