package i5b5.wajaty.hd.projekt.model.dwh;

import java.math.BigDecimal;

public class NetworkSessionPrice extends TechnicalDwhClass{
    private int networkSessionPriceId;
    private BigDecimal networkSessionPricePerKb;

    public int getNetworkSessionPriceId() {
        return networkSessionPriceId;
    }

    public void setNetworkSessionPriceId(int networkSessionPriceId) {
        this.networkSessionPriceId = networkSessionPriceId;
    }

    public BigDecimal getNetworkSessionPricePerKb() {
        return networkSessionPricePerKb;
    }

    public void setNetworkSessionPricePerKb(BigDecimal networkSessionPricePerKb) {
        this.networkSessionPricePerKb = networkSessionPricePerKb;
    }
}
