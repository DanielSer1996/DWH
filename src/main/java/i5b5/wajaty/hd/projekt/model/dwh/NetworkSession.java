package i5b5.wajaty.hd.projekt.model.dwh;

import java.sql.Timestamp;

public class NetworkSession implements FactEntity {
    private Long networkSessionId;
    private Long clientId;
    private Integer localityId;
    private Integer networkSessionPriceId;
    private Integer subscriptionTypeId;
    private Long networkSessionKbsAmount;
    private Timestamp networkSessionStartTime;
    private Timestamp networkSessionEndTime;

    public Long getNetworkSessionId() {
        return networkSessionId;
    }

    public void setNetworkSessionId(Long networkSessionId) {
        this.networkSessionId = networkSessionId;
    }

    public Integer getNetworkSessionPriceId() {
        return networkSessionPriceId;
    }

    public void setNetworkSessionPriceId(Integer networkSessionPriceId) {
        this.networkSessionPriceId = networkSessionPriceId;
    }

    public Long getNetworkSessionKbsAmount() {
        return networkSessionKbsAmount;
    }

    public void setNetworkSessionKbsAmount(Long networkSessionKbsAmount) {
        this.networkSessionKbsAmount = networkSessionKbsAmount;
    }
    public Timestamp getNetworkSessionStartTime() {
        return networkSessionStartTime;
    }

    public void setNetworkSessionStartTime(Timestamp networkSessionStartTime) {
        this.networkSessionStartTime = networkSessionStartTime;
    }

    public Timestamp getNetworkSessionEndTime() {
        return networkSessionEndTime;
    }

    public void setNetworkSessionEndTime(Timestamp networkSessionEndTime) {
        this.networkSessionEndTime = networkSessionEndTime;
    }

    public Integer getSubscriptionTypeId() {
        return subscriptionTypeId;
    }

    public void setSubscriptionTypeId(Integer subscriptionTypeId) {
        this.subscriptionTypeId = subscriptionTypeId;
    }

    public Integer getLocalityId() {
        return localityId;
    }

    public void setLocalityId(Integer localityId) {
        this.localityId = localityId;
    }

    public Long getClientId() { return clientId; }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
}
