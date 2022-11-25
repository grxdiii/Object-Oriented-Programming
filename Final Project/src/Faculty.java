// class for faculty objects
class Faculty extends Employee {
    private String rank;
    public Faculty(String fullName, String id, String department, String rank) {
        super(fullName, id, department);
        super.setType("Faculty");
        this.rank = rank;
    }

    public String getRank() {
        return this.rank;
    }

    public void print() {
        System.out.println("\n\t-------------------------------------------------------");
        System.out.println("\t" + super.getFullName() + "         " + super.getId());
        System.out.println("\t" + super.getDepartment() + " Department, " + this.rank );
        System.out.println("\t-------------------------------------------------------");
    }

}