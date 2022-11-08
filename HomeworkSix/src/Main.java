import java.util.ArrayList;
import java.util.Collections;

// COP 3330 - Gradi Tshielekeja Mbuyi
// November 8, 2022

public class Main {

    public static void main(String[] args) {

        Employee emp1 = new Employee(111, "Jimmy Dean", 5256.32, 0),
                 emp2 = new Employee(598, "Jen Johnson", 47370, 5),
                 emp3 = new Employee(920, "Jan Jones", 47834.25, 1);

        System.out.println(emp1.equals(emp3));

        ArrayList<Employee> list = new ArrayList<>();

        list.add(emp1);
        list.add(emp2);
        list.add(emp3);

        //sort call goes here…
        Collections.sort(list);

        for (Employee e : list)
            System.out.println(e);
    }
}

/* Employee Class:
    *   Stores user's information and calculates net salaray
    *   Implements the Comparable interface, which aids in our sorting
 */
class Employee implements Comparable<Employee>{
    private int id;
    private String name;
    private double salary;
    private int numberOfDependents;
    private double netSalary;

    // Constructor: stores the name, id, salary, and number of dependents
    public Employee(int id, String name, double salary, int numberOfDependents){
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.numberOfDependents = numberOfDependents;

        // Net salary = salary*0.91 + (numberOfDependent * 0.01 * salary)
        this.netSalary = this.salary * 0.91 + (this.numberOfDependents * 0.01 * this.salary);
    }

    // Only used one getter, which returns the net salary of the employee
    public double getNetSalary() {
        return netSalary;
    }

    // Overrides the equals() method
    @Override
    public boolean equals(Object anotherObject){
        // two employee objects are equal when they have the same net salary.
        if(!(anotherObject instanceof Employee)) return false;
        if(!(this.netSalary == ((Employee) anotherObject).getNetSalary())) return false;
        return true;
    }

    // Overrides the toString() method
    @Override
    public String toString(){
        // override the toString method to print an employee in the format [id, name, net salary]
        return String.format("[%d, %s, %.2f]", this.id, this.name, this.netSalary);

    }

    // Overrides the compareTo() method
    @Override
    public int compareTo(Employee employee) {
        return Double.compare(this.netSalary, employee.getNetSalary());
    }
}