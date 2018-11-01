package i5b5.wajaty.hd.projekt.model.dwh;

import java.math.BigDecimal;

public class Client extends TechnicalDwhClass {
    private long clientId;
    private String clientPesel;
    private String clientName;
    private String clientSurname;
    private int clientAge;
    private BigDecimal clientIncome;

    public String getClientPesel() {
        return clientPesel;
    }

    public void setClientPesel(String clientPesel) {
        this.clientPesel = clientPesel;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientSurname() {
        return clientSurname;
    }

    public void setClientSurname(String clientSurname) {
        this.clientSurname = clientSurname;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public int getClientAge() {
        return clientAge;
    }

    public void setClientAge(int clientAge) {
        this.clientAge = clientAge;
    }

    public BigDecimal getClientIncome() {
        return clientIncome;
    }

    public void setClientIncome(BigDecimal clientIncome) {
        this.clientIncome = clientIncome;
    }
}
