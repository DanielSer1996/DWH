package i5b5.wajaty.hd.projekt.model.sources.message;

import i5b5.wajaty.hd.projekt.model.sources.SubjectActionClass;

import java.math.BigDecimal;

public class MessagePrice extends SubjectActionClass {
    private int messagePriceId;
    private BigDecimal messagePrice;
    private char messageType;

    public int getMessagePriceId() {
        return messagePriceId;
    }

    public void setMessagePriceId(int messagePriceId) {
        this.messagePriceId = messagePriceId;
    }

    public BigDecimal getMessagePrice() {
        return messagePrice;
    }

    public void setMessagePrice(BigDecimal messagePrice) {
        this.messagePrice = messagePrice;
    }

    public char getMessageType() {
        return messageType;
    }

    public void setMessageType(char messageType) {
        this.messageType = messageType;
    }
}
