package medicalapp.model;

/**
 * Clinician class extending Person.
 */
public class Clinician extends Person {
    private String specialty;
    private String qualification;

    public Clinician() {}

    public Clinician(String id, String name, String contactInfo, String specialty, String qualification) {
        super(id, name, contactInfo);
        this.specialty = specialty;
        this.qualification = qualification;
    }

    public String getSpecialty() { return specialty; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }

    public String getQualification() { return qualification; }
    public void setQualification(String qualification) { this.qualification = qualification; }

    @Override
    public String toString() {
        return name + " [" + specialty + "]";
    }
}
