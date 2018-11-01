package i5b5.wajaty.hd.projekt.model.sources.call;

import java.sql.Timestamp;

public class Call {
    private int callId;
    private int callPriceId;
    private int subscriptionTypeId;
    private int clientId;
    private Timestamp callStartTime;
    private Timestamp callEndTime;
    private int localityId;

    public int getCallId() {
        return callId;
    }

    public void setCallId(int callId) {
        this.callId = callId;
    }

    public int getCallPriceId() {
        return callPriceId;
    }

    public void setCallPriceId(int callPriceId) {
        this.callPriceId = callPriceId;
    }

    public int getSubscriptionTypeId() {
        return subscriptionTypeId;
    }

    public void setSubscriptionTypeId(int subscriptionTypeId) {
        this.subscriptionTypeId = subscriptionTypeId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
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

    public int getLocalityId() {
        return localityId;
    }

    public void setLocalityId(int localityId) {
        this.localityId = localityId;
    }
}
