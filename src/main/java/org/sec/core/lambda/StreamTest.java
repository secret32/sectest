package org.sec.core.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhouwei
 * @since 2016/1/5
 */
public class StreamTest {

    public static void main(String[] args) {
        Employee alice = new Employee("Alice", "London", 200);
        Employee bob = new Employee("Bob", "London", 150);
        Employee charles = new Employee("Charles", "New York", 160);
        Employee dorothy = new Employee("Dorothy", "Hong Kong", 190);
        List<Employee> employees = new ArrayList<>();
        employees.add(alice);
        employees.add(bob);
        employees.add(charles);
        employees.add(dorothy);
        employees.stream().collect(Collectors.groupingBy(Employee::getCity)).forEach((a, b) -> System.out.println(a + "=" + b));
        employees.stream().collect(Collectors.partitioningBy(e -> e.getSales() > 150)).forEach((a, b) -> { if (a)
            System.out.println(b + "=" + true); else System.out.println(b + "=" + false);});
    }

    private static class Employee {
        private String name;
        private String city;
        private int sales;

        public Employee(String name, String city, int sales) {
            this.name = name;
            this.city = city;
            this.sales = sales;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public int getSales() {
            return sales;
        }

        public void setSales(int sales) {
            this.sales = sales;
        }
    }

}
