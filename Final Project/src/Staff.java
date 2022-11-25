// class for staff objects
class Staff extends Employee {
    private String status;

    public Staff(String fullName, String id, String department, String status) {
        super(fullName, id, department);
        super.setType("Staff");
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void print() { // prints the staff's info
        System.out.println("\n\t-------------------------------------------------------");
        System.out.println("\t" + super.getFullName() + "\t" + super.getId());
        if(status.equals("F")) System.out.println("\t" + super.getDepartment() + " Department, Full Time");
        else System.out.println("\t" + super.getDepartment() + " Department, Part Time");
        System.out.println("\t-------------------------------------------------------");
    }
}
