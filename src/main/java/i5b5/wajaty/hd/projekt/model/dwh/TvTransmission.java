package i5b5.wajaty.hd.projekt.model.dwh;

import java.sql.Timestamp;

public class TvTransmission implements FactEntity {
    private Long tvTransmissionId;
    private Long clientId;
    private Integer subscriptionTypeId;
    private Integer localityId;
    private Integer tvChannelId;
    private long tvTransmissionLength;
    private Timestamp tvTransmissionDate;

    public Long getTvTransmissionId() {
        return tvTransmissionId;
    }

    public void setTvTransmissionId(Long tvTransmissionId) {
        this.tvTransmissionId = tvTransmissionId;
    }

    public Integer getTvChannelId() {
        return tvChannelId;
    }

    public void setTvChannelId(Integer tvChannelId) {
        this.tvChannelId = tvChannelId;
    }

    public long getTvTransmissionLength() {
        return tvTransmissionLength;
    }

    public void setTvTransmissionLength(long tvTransmissionLength) {
        this.tvTransmissionLength = tvTransmissionLength;
    }

    public Timestamp getTvTransmissionDate() {
        return tvTransmissionDate;
    }

    public void setTvTransmissionDate(Timestamp tvTransmissionDate) {
        this.tvTransmissionDate = tvTransmissionDate;
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
