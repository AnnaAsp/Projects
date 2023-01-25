public class Operator
        implements Employee {

    private final double fixedPart;
    private Company company;

    private double salary;

    public Operator(Company company, double fixedPart) {
        this.fixedPart = fixedPart;
        this.company = company;
    }

    public double getMonthSalary() {
        salary = fixedPart;
        return salary;
    }

    public String toString() {
        return "- " + getMonthSalary() + " руб.";
    }




}
