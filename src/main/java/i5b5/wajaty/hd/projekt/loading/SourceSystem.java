package i5b5.wajaty.hd.projekt.loading;

public enum SourceSystem {
    NETWORK(1),
    MESSAGE(2),
    CALL(3),
    TV(4);

    int value;

    SourceSystem(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
