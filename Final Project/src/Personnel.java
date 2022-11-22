import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

// class for personnel
class Personnel {
    private Person [] list;
    public Personnel() {
        list = new Person[100];
        for(int i = 0; i < 100; i++) list[i] = null;
    }

    public boolean addPersonnel(Person person) { // adds a person to our list
        for(int i = 0; i < 100; i++) {
            if(list[i] == null) {
                list[i] = person; return true;
            }
        }	return false;
    }

    public Person search(String id, String type) { // search for person given their id
        for(int i = 0; i < 100; i++) {
            if(list[i] != null && list[i].getId().equals(id) && list[i].getType().equals(type)) return list[i];
        }	return null;
    }

    public void print(PrintWriter printWriter, int sortedBy){
        ArrayList<Faculty> faculties = new ArrayList<>();
        ArrayList <Staff> staff = new ArrayList<>();
        ArrayList <Student> students = new ArrayList<>();

        for(Person person : list){
            if(person != null && person.getType().equals("Faculty")) faculties.add((Faculty) person);
            else if (person != null && person.getType().equals("Staff")) staff.add((Staff) person);
            else if(person != null && person.getType().equals("Student")) {
                ((Student)person).setSortedBy(sortedBy); students.add((Student) person);
            }
        }

        if(!faculties.isEmpty()) {
            printWriter.println("Faculty Members\n"); int i = 1;
            for(Faculty faculty : faculties) {
                printWriter.println("\t" + i + ". " + faculty.getFullName()); i++;
                printWriter.println("\t" + "ID: " + faculty.getId());
                printWriter.println("\t" + faculty.getRank() + ", " + faculty.getDepartment());
                printWriter.println();
            }
        }

        if(!staff.isEmpty()) {
            printWriter.println("Staff Members\n"); int i = 1;
            for(Staff s : staff) {
                printWriter.println("\t" + i + ". " + s.getFullName()); i++;
                printWriter.println("\t" + "ID: " + s.getId());
                printWriter.println("\t" + s.getDepartment()+ ", " + s.getStatus());
                printWriter.println();
            }
        }

        if(!students.isEmpty()) {
            if(sortedBy == 1) printWriter.println("Students (Sorted by gpa)\n");
            else if(sortedBy == 2) printWriter.println("Students (Sorted by credit hours)\n");
            Collections.sort(students, Collections.reverseOrder()); int i = 1;
            for(Student student : students){
                printWriter.println("\t" + i + ". " + student.getFullName()); i++;
                printWriter.println("\t" + "ID: " + student.getId());
                printWriter.println("\t" + "Gpa: " + student.getGpa());
                printWriter.println("\t" + "Credit hours: " + student.getCreditHour());
                printWriter.println();
            }

        }

    }
}
