package i5b5.wajaty.hd.projekt.model.sources.message;

import java.sql.Timestamp;

public class Message {
    private int messageId;
    private int subscriptionTypeId;
    private int clientId;
    private int messagePriceId;
    private Timestamp messageDate;
    private int messageCharNumber;
    private char messageType;
    private int localityId;

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
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

    public int getMessagePriceId() {
        return messagePriceId;
    }

    public void setMessagePriceId(int messagePriceId) {
        this.messagePriceId = messagePriceId;
    }

    public Timestamp getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(Timestamp messageDate) {
        this.messageDate = messageDate;
    }

    public int getMessageCharNumber() {
        return messageCharNumber;
    }

    public void setMessageCharNumber(int messageCharNumber) {
        this.messageCharNumber = messageCharNumber;
    }

    public char getMessageType() {
        return messageType;
    }

    public void setMessageType(char messageType) {
        this.messageType = messageType;
    }

    public int getLocalityId() {
        return localityId;
    }

    public void setLocalityId(int localityId) {
        this.localityId = localityId;
    }
}
