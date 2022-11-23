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
        System.out.println("\n     -------------------------------------------------------\n");
        System.out.println("     " + super.getFullName() + "         " + super.getId() + "\n");
        if(status.equals("F")) System.out.println("     " + super.getDepartment() + " Department, Full Time\n");
        else System.out.println("     " + super.getDepartment() + " Department, Part Time\n");
        System.out.println("     -------------------------------------------------------");
    }
}
