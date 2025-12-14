package example.ex1.before;

import java.util.ArrayList;
import java.util.List;

class SimpleReportGenerator {
    public String generate(List<Customer> customers) {
        String report = String.format("고객 수: %d명\n", customers.size());
        for (Customer customer : customers) {
            report += String.format("%s : %d\n", customer.getName(), customer.getPoint());
        }
        return report;
    }
}

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
    }
}
