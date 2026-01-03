package medicalapp.model;

/**
 * Patient class extending Person.
 */
public class Patient extends Person {
    private int age;
    private String gender;
    private String medicalHistory;

    public Patient() {}

    public Patient(String id, String name, String contactInfo, int age, String gender, String medicalHistory) {
        super(id, name, contactInfo);
        this.age = age;
        this.gender = gender;
        this.medicalHistory = medicalHistory;
    }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getMedicalHistory() { return medicalHistory; }
    public void setMedicalHistory(String medicalHistory) { this.medicalHistory = medicalHistory; }

    @Override
    public String toString() {
        return super.toString() + " (ID: " + id + ")";
    }
}
