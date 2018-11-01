package i5b5.wajaty.hd.projekt.model.dwh;

import java.sql.Timestamp;

public class Call implements FactEntity{
    private Long callId;
    private Integer callPriceId;
    private Long clientId;
    private Integer subscriptionTypeId;
    private Integer localityId;
    private Timestamp callStartTime;
    private Timestamp callEndTime;

    public Long getCallId() {
        return callId;
    }

    public void setCallId(Long callId) {
        this.callId = callId;
    }

    public Integer getCallPriceId() {
        return callPriceId;
    }

    public void setCallPriceId(Integer callPriceId) {
        this.callPriceId = callPriceId;
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

    public Timestamp getCallStartTime() {
        return callStartTime;
    }

    public void setCallStartTime(Timestamp callStartTime) {
        this.callStartTime = callStartTime;
    }

    public Timestamp getCallEndTime() {
        return callEndTime;
    }

    public void setCallEndTime(Timestamp callEndTime) {
        this.callEndTime = callEndTime;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
}
