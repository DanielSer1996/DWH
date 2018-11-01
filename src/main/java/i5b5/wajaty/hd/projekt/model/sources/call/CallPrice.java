package i5b5.wajaty.hd.projekt.model.sources.call;

import i5b5.wajaty.hd.projekt.model.sources.SubjectActionClass;

import java.math.BigDecimal;

public class CallPrice extends SubjectActionClass {
    private int callPriceId;
    private BigDecimal callPricePerMin;

    public int getCallPriceId() {
        return callPriceId;
    }

    public void setCallPriceId(int callPriceId) {
        this.callPriceId = callPriceId;
    }

    public BigDecimal getCallPricePerMin() {
        return callPricePerMin;
    }

    public void setCallPricePerMin(BigDecimal callPricePerMin) {
        this.callPricePerMin = callPricePerMin;
    }
}
