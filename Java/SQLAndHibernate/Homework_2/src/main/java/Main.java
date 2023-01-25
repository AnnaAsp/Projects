import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "From " + PurchaseList.class.getSimpleName();
        List<PurchaseList> purchaseList = session.createQuery(hql, PurchaseList.class).getResultList();

        String hql2 = "From " + Course.class.getSimpleName();
        List<Course> courses = session.createQuery(hql2, Course.class).getResultList();

        String hql3 = "From " + Student.class.getSimpleName();
        List<Student> students = session.createQuery(hql3, Student.class).getResultList();

        for (PurchaseList purchase : purchaseList) {
            LinkedPurchaseList newPurchase = new LinkedPurchaseList();
            newPurchase.setPrice(purchase.getPrice());
            newPurchase.setSubscriptionDate(purchase.getSubscriptionDate());
            for (Course course : courses) {
                if (purchase.getPk().getCourseName().equals(course.getName())) {
                    newPurchase.setCourse(course);
                }
            }
            for (Student student : students) {
                if (purchase.getPk().getStudentName().equals(student.getName())) {
                    newPurchase.setStudent(student);
                }
            }
            session.persist(newPurchase);
        }

        transaction.commit();
        sessionFactory.close();
    }
}
