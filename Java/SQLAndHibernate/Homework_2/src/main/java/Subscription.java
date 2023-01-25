import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "Subscriptions")
public class Subscription {
    @EmbeddedId
    private SubscriptionPK pk;
    @Column(name = "subscription_date")
    private Date subscriptionDate;

    public SubscriptionPK getPk() {
        return pk;
    }

    public void setPk(SubscriptionPK pk) {
        this.pk = pk;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }
}
