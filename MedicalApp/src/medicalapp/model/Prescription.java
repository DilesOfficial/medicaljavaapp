package medicalapp.model;

public class Prescription {
    private String prescriptionId;
    private String patientId;
    private String medication;
    private String dosage;

    public Prescription() {}

    public Prescription(String prescriptionId, String patientId, String medication, String dosage) {
        this.prescriptionId = prescriptionId;
        this.patientId = patientId;
        this.medication = medication;
        this.dosage = dosage;
    }

    public String getPrescriptionId() { return prescriptionId; }
    public void setPrescriptionId(String prescriptionId) { this.prescriptionId = prescriptionId; }

    public String getPatientId() { return patientId; }
    public void setPatientId(String patientId) { this.patientId = patientId; }

    public String getMedication() { return medication; }
    public void setMedication(String medication) { this.medication = medication; }

    public String getDosage() { return dosage; }
    public void setDosage(String dosage) { this.dosage = dosage; }
}
