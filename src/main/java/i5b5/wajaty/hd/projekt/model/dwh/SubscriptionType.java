package i5b5.wajaty.hd.projekt.model.dwh;

public class SubscriptionType extends TechnicalDwhClass {
    private int subscriptionTypeId;
    private int subcriptionDescription;

    public int getSubscriptionTypeId() {
        return subscriptionTypeId;
    }

    public void setSubscriptionTypeId(int subscriptionTypeId) {
        this.subscriptionTypeId = subscriptionTypeId;
    }

    public int getSubcriptionDescription() {
        return subcriptionDescription;
    }

    public void setSubcriptionDescription(int subcriptionDescription) {
        this.subcriptionDescription = subcriptionDescription;
    }
}
