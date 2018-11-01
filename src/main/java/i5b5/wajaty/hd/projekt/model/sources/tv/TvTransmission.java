package i5b5.wajaty.hd.projekt.model.sources.tv;

import java.sql.Timestamp;

public class TvTransmission {
    private int tvTrasnsmissionId;
    private int tvChannelId;
    private int subscriptionTypeId;
    private int clientId;
    private int tvTransmissionLength;
    private Timestamp tvTransmissionStartTime;
    private Timestamp tvTransmissionEndTime;
    private int localityId;

    public int getTvTrasnsmissionId() {
        return tvTrasnsmissionId;
    }

    public void setTvTrasnsmissionId(int tvTrasnsmissionId) {
        this.tvTrasnsmissionId = tvTrasnsmissionId;
    }

    public int getTvChannelId() {
        return tvChannelId;
    }

    public void setTvChannelId(int tvChannelId) {
        this.tvChannelId = tvChannelId;
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

    public int getTvTransmissionLength() {
        return tvTransmissionLength;
    }

    public void setTvTransmissionLength(int tvTransmissionLength) {
        this.tvTransmissionLength = tvTransmissionLength;
    }

    public Timestamp getTvTransmissionStartTime() {
        return tvTransmissionStartTime;
    }

    public void setTvTransmissionStartTime(Timestamp tvTransmissionStartTime) {
        this.tvTransmissionStartTime = tvTransmissionStartTime;
    }

    public Timestamp getTvTransmissionEndTime() {
        return tvTransmissionEndTime;
    }

    public void setTvTransmissionEndTime(Timestamp tvTransmissionEndTime) {
        this.tvTransmissionEndTime = tvTransmissionEndTime;
    }

    public int getLocalityId() {
        return localityId;
    }

    public void setLocalityId(int localityId) {
        this.localityId = localityId;
    }
}
