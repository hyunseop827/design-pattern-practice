package example.ex1.after;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// Abstract Class
abstract class ReportGenerator {

    // Template Method: 알고리즘의 뼈대를 확정 (final로 막는 것을 권장)
    public final String generate(List<Customer> customers) {
        // 1. 필터링 (Hook 메서드 사용)
        List<Customer> selectedCustomers = new ArrayList<>();
        for (Customer customer : customers) {
            if (customerFilter(customer)) {
                selectedCustomers.add(customer);
            }
        }

        // 2. 정렬
        selectedCustomers.sort(Comparator.comparingInt(Customer::getPoint));

        // 3. 보고서 생성 (String concatenation 대신 StringBuilder 사용 권장)
        StringBuilder report = new StringBuilder();

        report.append(createHeader(selectedCustomers));

        for (Customer customer : selectedCustomers) {
            report.append(String.format("%s : %d\n", customer.getName(), customer.getPoint()));
        }

        report.append(createFooter(selectedCustomers));

        return report.toString();
    }

    // Abstract Methods
    protected abstract String createHeader(List<Customer> customers);
    protected abstract String createFooter(List<Customer> customers);

    // Hook Method: 기본값은 true (모두 통과), 필요하면 오버라이딩
    protected boolean customerFilter(Customer customer) {
        return true;
    }
}

// Concrete Class 1: 단순 보고서
class SimpleReportGenerator extends ReportGenerator {

    @Override
    protected String createHeader(List<Customer> customers) {
        return String.format("고객 수: %d명입니다.\n", customers.size());
    }

    @Override
    protected String createFooter(List<Customer> customers) {
        int total = customers.stream().mapToInt(Customer::getPoint).sum();
        return String.format("점수 합계 : %d\n", total);
    }

    // customerFilter를 오버라이딩 하지 않으므로 기본값(true) 사용 -> 모든 고객 출력
}

// Concrete Class 2: 고급 보고서
class AdvancedReportGenerator extends ReportGenerator {

    @Override
    protected boolean customerFilter(Customer customer) {
        // 300점 이상만 통과하도록 Hook 재정의
        return customer.getPoint() >= 300;
    }

    @Override
    protected String createHeader(List<Customer> customers) {
        return String.format(
                "********************************************\n" +
                        "           해당 고객 수 :  %d명입니다.         \n" +
                        "********************************************\n",
                customers.size());
    }

    @Override
    protected String createFooter(List<Customer> customers) {
        int total = customers.stream().mapToInt(Customer::getPoint).sum();
        return String.format(
                "********************************************\n" +
                        "          점수 합계 : %d         \n" +
                        "********************************************\n",
                total);
    }
}

// Customer 클래스와 Main은 그대로 유지...
class Customer {
    private String name;
    private int point;

    public Customer(String name, int point) {
        this.name = name;
        this.point = point;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}

public class Main {
    public static void main(String[] args) {
        List<Customer> customers = new ArrayList<>();

        customers.add(new Customer("홍길동", 150));
        customers.add(new Customer("우수한", 350));
        customers.add(new Customer("부족한", 50));
        customers.add(new Customer("훌륭한", 450));
        customers.add(new Customer("최고의", 550));

        SimpleReportGenerator simpleReportGenerator = new SimpleReportGenerator();
        System.out.println(simpleReportGenerator.generate(customers));

        AdvancedReportGenerator advancedReportGenerator = new AdvancedReportGenerator();
        System.out.println(advancedReportGenerator.generate(customers));
    }
}
