package Models;

public class SubscriptionUser extends User
{
    private String subscriptionType;

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(String subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public SubscriptionUser(int userID, String userName, String userMail, String userCountry, int userPlanID, String subscriptionType) {
        super(userID, userName, userMail, userCountry, userPlanID);
        this.subscriptionType = subscriptionType;
    }


}
