// class for student objects
class Student extends Person implements Comparable<Student>{
    private float gpa;
    private int creditHour;
    private static int sortedBy;

    public Student(String fullName, String id, float gpa, int creditHour) {
        super(fullName, id);
        super.setType("Student");
        this.gpa = gpa;
        this.creditHour = creditHour;
    }

    // prints the student's tuition invoice
    public void print() {
        float payment = (float) (236.45 * creditHour) + 52;
        float discount = payment;
        if(gpa > 3.85) payment = (float) (payment * 0.75);
        discount = discount - payment;
        System.out.println("\n\tHere is the tuition invoice for " + super.getFullName() + ":\n");
        System.out.println("\t-------------------------------------------------------");
        System.out.println("\t" + super.getFullName() + "         " + super.getId());
        System.out.println("\t" + "Credit Hours: " + creditHour + "       ($236.45/credit hour)");
        System.out.println("\t" + "Fees: $52\n");
        System.out.println(String.format("\tTotal payment: $%.02f     ($%d discount applied)", payment, (int) discount));
        System.out.println("\t-------------------------------------------------------");
    }

    public static void setSortedBy(int sortedBy) {
        Student.sortedBy = sortedBy;
    }

    public float getGpa() {
        return gpa;
    }

    public int getCreditHour() {
        return creditHour;
    }

    @Override
    public int compareTo(Student student) {
        if(this.sortedBy == 1) return Double.compare(this.gpa, student.getGpa());
        return Integer.compare(this.creditHour, student.getCreditHour());
    }
}