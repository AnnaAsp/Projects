import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Company company = new Company(2000000);
        ArrayList<Employee> employees = new ArrayList<>();
        for (int i = 0; i < 180; i++) {
            employees.add(new Operator(company, 70000));
        }
        for (int i = 0; i < 80; i++) {
            employees.add(new Manager(company, 100000));
        }
        for (int i = 0; i < 10; i++) {
            employees.add(new TopManager(company, 100000));
        }
        company.hireAll(employees);

        System.out.println("Самые высокие зарплаты:");
        company.getTopSalaryStaff(10);
        System.out.println("Самые низкие зарплаты:");
        company.getLowestSalaryStaff(30);

        for (int i = 0; i < 135; i++) {
            company.fire(employees.get(i));
        }

        System.out.println("Самые высокие зарплаты:");
        company.getTopSalaryStaff(10);
        System.out.println("Самые низкие зарплаты:");
        company.getLowestSalaryStaff(30);
    }

}
