import jakarta.persistence.*;

import java.sql.Date;
@Entity
public class LinkedPurchaseList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "subscription_date")
    private Date subscriptionDate;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Student student;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Course course;
    private int price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
