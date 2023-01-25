

public class Manager
        implements Employee {

    private final double fixedPart;
    private Company company;

    private double salary;
    private int contributionToIncome;

    public Manager(Company company, double fixedPart) {
        this.fixedPart = fixedPart;
        this.company = company;
        contributionToIncome = (int) ((Math.random() * ((140000 - 115000) + 1)) + 115000);
    }

    public double getMonthSalary() {
        salary = fixedPart + contributionToIncome * 0.05;
        return salary;
    }

    public String toString() {
        return "- " + getMonthSalary() + " руб.";
    }

}
