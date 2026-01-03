package medicalapp.model;

public class Referral {
    private String referralId;
    private String patientId;
    private String details;
    private String urgency;

    public Referral() {}

    public Referral(String referralId, String patientId, String details, String urgency) {
        this.referralId = referralId;
        this.patientId = patientId;
        this.details = details;
        this.urgency = urgency;
    }

    public String getReferralId() { return referralId; }
    public void setReferralId(String referralId) { this.referralId = referralId; }

    public String getPatientId() { return patientId; }
    public void setPatientId(String patientId) { this.patientId = patientId; }

    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }

    public String getUrgency() { return urgency; }
    public void setUrgency(String urgency) { this.urgency = urgency; }
}
