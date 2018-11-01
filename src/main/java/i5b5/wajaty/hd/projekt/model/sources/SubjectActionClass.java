package i5b5.wajaty.hd.projekt.model.sources;

import java.sql.Timestamp;

public abstract class SubjectActionClass {
    protected boolean isDeleted;
    protected Timestamp lastActionTime;

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Timestamp getLastActionTime() {
        return lastActionTime;
    }

    public void setLastActionTime(Timestamp lastActionTime) {
        this.lastActionTime = lastActionTime;
    }
}
