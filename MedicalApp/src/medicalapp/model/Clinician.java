package medicalapp.model;

public class Clinician extends Person {
    private String title;
    private String specialty;
    private String gmcNumber;
    private String workplaceId;
    private String workplaceType;
    private String empStatus;
    private String startDate;

    public Clinician(String id, String firstName, String lastName, String title, String specialty, 
                     String gmcNumber, String phone, String email, String workplaceId, 
                     String workplaceType, String empStatus, String startDate) {
        super(id, firstName, lastName, phone, email);
        this.title = title;
        this.specialty = specialty;
        this.gmcNumber = gmcNumber;
        this.workplaceId = workplaceId;
        this.workplaceType = workplaceType;
        this.empStatus = empStatus;
        this.startDate = startDate;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getSpecialty() { return specialty; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }

    public String getGmcNumber() { return gmcNumber; }
    public void setGmcNumber(String gmcNumber) { this.gmcNumber = gmcNumber; }

    public String getWorkplaceId() { return workplaceId; }
    public void setWorkplaceId(String workplaceId) { this.workplaceId = workplaceId; }

    public String getWorkplaceType() { return workplaceType; }
    public void setWorkplaceType(String workplaceType) { this.workplaceType = workplaceType; }

    public String getEmpStatus() { return empStatus; }
    public void setEmpStatus(String empStatus) { this.empStatus = empStatus; }

    public String getStartDate() { return startDate; }
    public void setStartDate(String startDate) { this.startDate = startDate; }
}
