package i5b5.wajaty.hd.projekt.model.dwh.mappers;

public class MappingClass {
    private long dwhId;
    private int sourceSystemNo;
    private String sourceSystemKey;

    public long getDwhId() {
        return dwhId;
    }

    public void setDwhId(long dwhId) {
        this.dwhId = dwhId;
    }

    public int getSourceSystemNo() {
        return sourceSystemNo;
    }

    public void setSourceSystemNo(int sourceSystemNo) {
        this.sourceSystemNo = sourceSystemNo;
    }

    public String getSourceSystemKey() {
        return sourceSystemKey;
    }

    public void setSourceSystemKey(String sourceSystemKey) {
        this.sourceSystemKey = sourceSystemKey;
    }
}
