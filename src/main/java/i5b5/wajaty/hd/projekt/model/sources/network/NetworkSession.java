package i5b5.wajaty.hd.projekt.model.sources.network;

import java.sql.Timestamp;

public class NetworkSession {
    private int networkSessionId;
    private int networkSessionPriceId;
    private int subscriptionTypeId;
    private int clientId;
    private long networkSessionKbAmount;
    private Timestamp networkSessionStartTime;
    private Timestamp networkSessionEndTime;
    private int localityId;

    public int getNetworkSessionId() {
        return networkSessionId;
    }

    public void setNetworkSessionId(int networkSessionId) {
        this.networkSessionId = networkSessionId;
    }

    public int getNetworkSessionPriceId() {
        return networkSessionPriceId;
    }

    public void setNetworkSessionPriceId(int networkSessionPriceId) {
        this.networkSessionPriceId = networkSessionPriceId;
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

    public long getNetworkSessionKbAmount() {
        return networkSessionKbAmount;
    }

    public void setNetworkSessionKbAmount(long networkSessionKbAmount) {
        this.networkSessionKbAmount = networkSessionKbAmount;
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

    public int getLocalityId() {
        return localityId;
    }

    public void setLocalityId(int localityId) {
        this.localityId = localityId;
    }
}
