// abstract class for employee
abstract class Employee extends Person {
    private String department;
    public Employee(String fullName, String id, String department) {
        super(fullName, id);
        this.department = department;
    }

    public String getDepartment() {
        return this.department;
    }
}