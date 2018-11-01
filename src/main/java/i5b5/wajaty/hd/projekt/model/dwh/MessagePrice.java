package i5b5.wajaty.hd.projekt.model.dwh;

import java.math.BigDecimal;

public class MessagePrice extends TechnicalDwhClass {
    private long messagePriceId;
    private BigDecimal messagePrice;
    private char messageType;

    public long getMessagePriceId() {
        return messagePriceId;
    }

    public void setMessagePriceId(long messagePriceId) {
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
