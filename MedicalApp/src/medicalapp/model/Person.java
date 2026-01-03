package medicalapp.model;

/**
 * Abstract Class representing a generic Person.
 * Demonstrates Inheritance as per requirements.
 */
public abstract class Person {
    protected String id;
    protected String name;
    protected String contactInfo;

    public Person() {}

    public Person(String id, String name, String contactInfo) {
        this.id = id;
        this.name = name;
        this.contactInfo = contactInfo;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getContactInfo() { return contactInfo; }
    public void setContactInfo(String contactInfo) { this.contactInfo = contactInfo; }

    @Override
    public String toString() {
        return name;
    }
}
