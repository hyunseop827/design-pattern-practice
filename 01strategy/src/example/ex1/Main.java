package example.ex1;

// Strategy - defines the interface for salary calculation algorithms
interface SalaryCalculator {
    double calculateSalary(double hourlyWage, double totalHours);
}

// ConcreteStrategy - regular salary calculation (8 standard hours, 1.5x overtime)
class RegularSalaryCalculator implements SalaryCalculator {
    private static final int STANDARD_HOURS = 8;

    @Override
    public double calculateSalary(double hourlyWage, double totalHours) {
        double regularHours = Math.min(totalHours, STANDARD_HOURS);
        double overtimeHours = Math.max(0, totalHours - STANDARD_HOURS);

        double regularPay = regularHours * hourlyWage;
        double overtimePay = overtimeHours * hourlyWage * 1.5;

        return regularPay + overtimePay;
    }
}

// ConcreteStrategy - special salary calculation (2x overtime pay)
class SpecialSalaryCalculator implements SalaryCalculator {
    private static final int STANDARD_HOURS = 8;

    @Override
    public double calculateSalary(double hourlyWage, double totalHours) {
        double regularHours = Math.min(totalHours, STANDARD_HOURS);
        double overtimeHours = Math.max(0, totalHours - STANDARD_HOURS);

        double regularPay = regularHours * hourlyWage;
        double overtimePay = overtimeHours * hourlyWage * 2.0;

        return regularPay + overtimePay;
    }
}

// Context - maintains a reference to a Strategy object and delegates salary calculation
class Employee {
    private final String name;
    private final double hourlyWage;
    private final double totalHours;
    private final SalaryCalculator salaryCalculator;

    public Employee(String name,
                    double hourlyWage,
                    double totalHours,
                    SalaryCalculator salaryCalculator) {
        this.name = name;
        this.hourlyWage = hourlyWage;
        this.totalHours = totalHours;
        this.salaryCalculator = salaryCalculator;
    }

    public double calculateSalary() {
        return salaryCalculator.calculateSalary(hourlyWage, totalHours);
    }

    public String getName() {
        return name;
    }
}

// Client
public class Main {
    public static void main(String[] args) {
        // Using RegularSalaryCalculator strategy
        Employee emp1 =
                new Employee("John Doe", 20.0, 10, new RegularSalaryCalculator());
        double salary1 = emp1.calculateSalary();
        System.out.println(emp1.getName() + "'s weekly salary: $" + salary1);

        // Swapping to SpecialSalaryCalculator strategy
        Employee emp2 =
                new Employee("Jane Smith", 20.0, 10, new SpecialSalaryCalculator());
        double salary2 = emp2.calculateSalary();
        System.out.println(emp2.getName() + "'s weekly salary: $" + salary2);
    }
}
