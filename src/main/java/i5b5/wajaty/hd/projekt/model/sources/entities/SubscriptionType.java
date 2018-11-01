package i5b5.wajaty.hd.projekt.model.sources.entities;

import i5b5.wajaty.hd.projekt.model.sources.SubjectActionClass;

public class SubscriptionType extends SubjectActionClass {
    private int subscriptionTypeId;
    private String subscriptionTypeDescr;

    public int getSubscriptionTypeId() {
        return subscriptionTypeId;
    }

    public void setSubscriptionTypeId(int subscriptionTypeId) {
        this.subscriptionTypeId = subscriptionTypeId;
    }

    public String getSubscriptionTypeDescr() {
        return subscriptionTypeDescr;
    }

    public void setSubscriptionTypeDescr(String subscriptionTypeDescr) {
        this.subscriptionTypeDescr = subscriptionTypeDescr;
    }
}
