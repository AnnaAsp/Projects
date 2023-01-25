import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.sql.Date;

@Entity
public class PurchaseList {
    @EmbeddedId
    private PurchaseListPK pk;
    private int price;
    @Column(name = "subscription_date")
    private Date subscriptionDate;

    public PurchaseListPK getPk() {
        return pk;
    }

    public void setPk(PurchaseListPK pk) {
        this.pk = pk;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }
}
