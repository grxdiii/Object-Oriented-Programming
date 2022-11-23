import java.util.Scanner;

// abstract class for a person
abstract class Person {
    private String fullName;
    private String id;
    private String type;

    public Person(String fullName, String id) {
        this.fullName = fullName;
        this.id = id;
    }

    public abstract void print();
    public String getFullName() {
        return fullName;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}