import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SubscriptionPK implements Serializable {
    @ManyToOne(cascade = CascadeType.ALL)
    private Student student;
    @ManyToOne(cascade = CascadeType.ALL)
    private Course course;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubscriptionPK that = (SubscriptionPK) o;
        return Objects.equals(student, that.student) && Objects.equals(course, that.course);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, course);
    }
}
