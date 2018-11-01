package i5b5.wajaty.hd.projekt.model.dwh;

import java.sql.Timestamp;

public abstract class TechnicalDwhClass {
    protected Timestamp startDate;
    protected Timestamp endDate;

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }
}
