package example.ex1.before;

import java.util.ArrayList;
import java.util.List;

class SimpleReportGenerator {
    public String generate(List<Customer> customers) {
        String report = String.format("Customer count: %d\n", customers.size());
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

        customers.add(new Customer("Alice", 150));
        customers.add(new Customer("Bob", 350));
        customers.add(new Customer("Charlie", 50));
        customers.add(new Customer("Diana", 450));
        customers.add(new Customer("Eve", 550));

        SimpleReportGenerator simpleReportGenerator = new SimpleReportGenerator();
        System.out.println(simpleReportGenerator.generate(customers));
    }
}
