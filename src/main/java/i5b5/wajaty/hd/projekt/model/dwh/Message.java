package i5b5.wajaty.hd.projekt.model.dwh;

import java.sql.Timestamp;

public class Message implements FactEntity {
    private Long messageId;
    private Integer messagePriceId;
    private Long clientId;
    private Integer subscriptionTypeId;
    private Integer localityId;
    private Timestamp messageDate;
    private char messageType;
    private int messageCharNumber;

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Integer getMessagePriceId() {
        return messagePriceId;
    }

    public Timestamp getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(Timestamp messageDate) {
        this.messageDate = messageDate;
    }

    public char getMessageType() {
        return messageType;
    }

    public void setMessageType(char messageType) {
        this.messageType = messageType;
    }

    public int getMessageCharNumber() {
        return messageCharNumber;
    }

    public void setMessageCharNumber(int messageCharNumber) {
        this.messageCharNumber = messageCharNumber;
    }

    public void setMessagePriceId(Integer messagePriceId) {
        this.messagePriceId = messagePriceId;
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
