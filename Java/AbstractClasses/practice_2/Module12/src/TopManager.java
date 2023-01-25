public class TopManager
        implements Employee {

    private final double fixedPart;
    private Company company;

    private double salary;

    public TopManager(Company company, double fixedPart) {
        this.fixedPart = fixedPart;
        this.company = company;
    }

    public double getMonthSalary() {
        if (company.getIncome() > 10000000) {
            salary = fixedPart + fixedPart * 1.5;
        } else {
            salary = fixedPart;
        }
        return salary;
    }

    public String toString() {
        return "- " + getMonthSalary() + " руб.";
    }


}
