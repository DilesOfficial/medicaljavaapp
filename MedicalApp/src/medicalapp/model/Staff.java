package medicalapp.model;

public class Staff extends Person {
    private String role;
    private String department;
    private String facilityId;
    private String empStatus;
    private String startDate;
    private String lineManager;
    private String accessLevel;

    public Staff(String id, String firstName, String lastName, String role, String department, 
                 String facilityId, String phone, String email, String empStatus, 
                 String startDate, String lineManager, String accessLevel) {
        super(id, firstName, lastName, phone, email);
        this.role = role;
        this.department = department;
        this.facilityId = facilityId;
        this.empStatus = empStatus;
        this.startDate = startDate;
        this.lineManager = lineManager;
        this.accessLevel = accessLevel;
    }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getFacilityId() { return facilityId; }
    public void setFacilityId(String facilityId) { this.facilityId = facilityId; }

    public String getEmpStatus() { return empStatus; }
    public void setEmpStatus(String empStatus) { this.empStatus = empStatus; }

    public String getStartDate() { return startDate; }
    public void setStartDate(String startDate) { this.startDate = startDate; }

    public String getLineManager() { return lineManager; }
    public void setLineManager(String lineManager) { this.lineManager = lineManager; }

    public String getAccessLevel() { return accessLevel; }
    public void setAccessLevel(String accessLevel) { this.accessLevel = accessLevel; }
}
