package medicalapp.model;

public class Patient extends Person {
    private String dob;
    private String nhsNumber;
    private String gender;
    private String address;
    private String postcode;
    private String emergencyName;
    private String emergencyPhone;
    private String regDate;
    private String gpSurgeryId;

    public Patient(String id, String firstName, String lastName, String dob, String nhsNumber, 
                   String gender, String phone, String email, String address, String postcode, 
                   String emergencyName, String emergencyPhone, String regDate, String gpSurgeryId) {
        super(id, firstName, lastName, phone, email);
        this.dob = dob;
        this.nhsNumber = nhsNumber;
        this.gender = gender;
        this.address = address;
        this.postcode = postcode;
        this.emergencyName = emergencyName;
        this.emergencyPhone = emergencyPhone;
        this.regDate = regDate;
        this.gpSurgeryId = gpSurgeryId;
    }

    // Getters and Setters
    public String getDob() { return dob; }
    public void setDob(String dob) { this.dob = dob; }

    public String getNhsNumber() { return nhsNumber; }
    public void setNhsNumber(String nhsNumber) { this.nhsNumber = nhsNumber; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPostcode() { return postcode; }
    public void setPostcode(String postcode) { this.postcode = postcode; }

    public String getEmergencyName() { return emergencyName; }
    public void setEmergencyName(String emergencyName) { this.emergencyName = emergencyName; }

    public String getEmergencyPhone() { return emergencyPhone; }
    public void setEmergencyPhone(String emergencyPhone) { this.emergencyPhone = emergencyPhone; }

    public String getRegDate() { return regDate; }
    public void setRegDate(String regDate) { this.regDate = regDate; }

    public String getGpSurgeryId() { return gpSurgeryId; }
    public void setGpSurgeryId(String gpSurgeryId) { this.gpSurgeryId = gpSurgeryId; }
}
