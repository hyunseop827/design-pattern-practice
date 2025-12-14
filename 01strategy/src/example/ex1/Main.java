package example.ex1;

// 임금 계산 전략 인터페이스
interface SalaryCalculator {
    double calculateSalary(double hourlyWage, double totalHours);
}

// 기본 임금 계산 전략 (기본 근무 시간 8시간, 초과 근무는 1.5배)
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

// 특별 임금 계산 전략 (예: 초과근무 2배)
class SpecialSalaryCalculator implements SalaryCalculator {
    private static final int STANDARD_HOURS = 8;

    @Override
    public double calculateSalary(double hourlyWage, double totalHours) {
        double regularHours = Math.min(totalHours, STANDARD_HOURS);
        double overtimeHours = Math.max(0, totalHours - STANDARD_HOURS);

        double regularPay = regularHours * hourlyWage;
        double overtimePay = overtimeHours * hourlyWage * 2.0; // 더 세게 쳐주는 버전

        return regularPay + overtimePay;
    }
}

// 컨텍스트: Employee가 전략을 “가지고” 있고, 그걸로 급여를 계산함
class Employee {
    private final String name;
    private final double hourlyWage;
    private final double totalHours;
    private final SalaryCalculator salaryCalculator; // 전략 객체

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

// 클라이언트 코드
public class Main {
    public static void main(String[] args) {
        // 기본 전략 사용
        Employee emp1 =
                new Employee("John Doe", 20.0, 10, new RegularSalaryCalculator());
        double salary1 = emp1.calculateSalary();
        System.out.println(emp1.getName() + "의 주급은: $" + salary1);

        // 특별 전략으로 갈아끼움
        Employee emp2 =
                new Employee("Jane Smith", 20.0, 10, new SpecialSalaryCalculator());
        double salary2 = emp2.calculateSalary();
        System.out.println(emp2.getName() + "의 주급은: $" + salary2);
    }
}

