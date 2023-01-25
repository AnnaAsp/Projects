import java.util.*;

public class Company {
    private final double income;
    public ArrayList<Employee> employeeList;

    public Company(double income) {
        this.income = income;
        employeeList = new ArrayList<>();
    }

    public double getIncome() {
        return income;
    }

    public void hire(Employee employee) {
        employeeList.add(employee);
    }

    public void hireAll(ArrayList<Employee> employees) {
        for (int i = 0; i < employees.size(); i++) {
            employeeList.add(employees.get(i));
        }
    }

    public void fire(Employee employee) {
        if (employeeList.contains(employee)) {
            for(int i = 0; i < employeeList.size(); i++) {
                if (employeeList.get(i).equals(employee)) {
                    employeeList.remove(i);
                    break;
                }
            }
        }
    }

    public ArrayList<Employee> getTopSalaryStaff(int count) {
        if (count < 0) {
            count = count * -1;
        }
        if (count >= employeeList.size()) {
            count = employeeList.size();
        }
        ArrayList<Employee> newEmployeeList = new ArrayList<>();
        Collections.sort(employeeList, new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return o1.getMonthSalary() > o2.getMonthSalary() ? -1 : (o1.getMonthSalary() < o2.getMonthSalary()) ? 1 : 0;
            }
        });
        for (int i = 0; i < count; i++) {
            newEmployeeList.add(employeeList.get(i));
            System.out.println(employeeList.get(i));
        }
        return newEmployeeList;
    }

    public ArrayList<Employee> getLowestSalaryStaff(int count) {
        if (count < 0) {
            count = count * -1;
        }
        if (count >= employeeList.size()) {
            count = employeeList.size();
        }
        ArrayList<Employee> newEmployeeList = new ArrayList<>();
        Collections.sort(employeeList, new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                    return o1.getMonthSalary() < o2.getMonthSalary() ? -1 : (o1.getMonthSalary() > o2.getMonthSalary()) ? 1 : 0;
            }
        });
        for (int i = 0; i < count; i++) {
            newEmployeeList.add(employeeList.get(i));
            System.out.println(employeeList.get(i));
        }
        return newEmployeeList;
    }



}
