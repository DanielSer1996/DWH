package i5b5.wajaty.hd.projekt.model.dwh;

import java.math.BigDecimal;

public class CallPrice extends TechnicalDwhClass {
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
