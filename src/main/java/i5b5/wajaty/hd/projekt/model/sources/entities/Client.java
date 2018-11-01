package i5b5.wajaty.hd.projekt.model.sources.entities;

import i5b5.wajaty.hd.projekt.model.sources.SubjectActionClass;

import java.math.BigDecimal;
import java.sql.Date;

public class Client extends SubjectActionClass {
    private int clientId;
    private String clientPesel;
    private String clientName;
    private String clientSurname;
    private BigDecimal clientMonthlyIncome;
    private Date clientBirthdate;

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

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

    public BigDecimal getClientMonthlyIncome() {
        return clientMonthlyIncome;
    }

    public void setClientMonthlyIncome(BigDecimal clientMonthlyIncome) {
        this.clientMonthlyIncome = clientMonthlyIncome;
    }

    public Date getClientBirthdate() {
        return clientBirthdate;
    }

    public void setClientBirthdate(Date clientBirthdate) {
        this.clientBirthdate = clientBirthdate;
    }
}
