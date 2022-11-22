// class for faculty objects
class Faculty extends Employee {
    private String rank;
    public Faculty(String fullName, String id, String department, String rank) {
        super(fullName, id, department);
        super.setType("Faculty");
        this.rank = rank;
    }

    public String getRank() {
        return rank;
    }

    public void print() { // prints the faculty's info
        System.out.println("\n     -------------------------------------------------------\n");
        System.out.println("     " + super.getFullName() + "         " + super.getId() + "\n");
        System.out.println("     " + super.getDepartment() + " Department, " + rank + "\n");
        System.out.println("     -------------------------------------------------------");
    }
}