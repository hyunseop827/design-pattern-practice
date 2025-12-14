package example.ex1.after;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// AbstractClass - defines abstract primitive operations that concrete subclasses define
abstract class ReportGenerator {

    // Template Method - defines the skeleton of an algorithm (final is recommended)
    public final String generate(List<Customer> customers) {
        // 1. Filtering (using Hook method)
        List<Customer> selectedCustomers = new ArrayList<>();
        for (Customer customer : customers) {
            if (customerFilter(customer)) {
                selectedCustomers.add(customer);
            }
        }

        // 2. Sorting
        selectedCustomers.sort(Comparator.comparingInt(Customer::getPoint));

        // 3. Report generation (StringBuilder recommended over String concatenation)
        StringBuilder report = new StringBuilder();

        report.append(createHeader(selectedCustomers));

        for (Customer customer : selectedCustomers) {
            report.append(String.format("%s : %d\n", customer.getName(), customer.getPoint()));
        }

        report.append(createFooter(selectedCustomers));

        return report.toString();
    }

    // Primitive Operations - subclasses must implement
    protected abstract String createHeader(List<Customer> customers);
    protected abstract String createFooter(List<Customer> customers);

    // Hook Method - default is true (all pass), override if needed
    protected boolean customerFilter(Customer customer) {
        return true;
    }
}

// ConcreteClass - simple report
class SimpleReportGenerator extends ReportGenerator {

    @Override
    protected String createHeader(List<Customer> customers) {
        return String.format("Customer count: %d\n", customers.size());
    }

    @Override
    protected String createFooter(List<Customer> customers) {
        int total = customers.stream().mapToInt(Customer::getPoint).sum();
        return String.format("Total points: %d\n", total);
    }

    // Does not override customerFilter, so default (true) is used -> all customers printed
}

// ConcreteClass - advanced report
class AdvancedReportGenerator extends ReportGenerator {

    @Override
    protected boolean customerFilter(Customer customer) {
        // Override Hook to pass only customers with 300+ points
        return customer.getPoint() >= 300;
    }

    @Override
    protected String createHeader(List<Customer> customers) {
        return String.format(
                "********************************************\n" +
                        "           Customer count: %d              \n" +
                        "********************************************\n",
                customers.size());
    }

    @Override
    protected String createFooter(List<Customer> customers) {
        int total = customers.stream().mapToInt(Customer::getPoint).sum();
        return String.format(
                "********************************************\n" +
                        "          Total points: %d                 \n" +
                        "********************************************\n",
                total);
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

        AdvancedReportGenerator advancedReportGenerator = new AdvancedReportGenerator();
        System.out.println(advancedReportGenerator.generate(customers));
    }
}
