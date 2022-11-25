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
        return this.fullName;
    }

    public String getId() {
        return this.id;
    }

    public void setType(String type) {
        this.type = type;
    }
}