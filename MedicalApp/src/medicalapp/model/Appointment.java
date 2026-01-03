package medicalapp.model;

public class Appointment {
    private String appointmentId;
    private String patientId;
    private String clinicianId;
    private String date;
    private String time;
    private String status; // Pending, Completed, Cancelled

    public Appointment() {}

    public Appointment(String appointmentId, String patientId, String clinicianId, String date, String time, String status) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.clinicianId = clinicianId;
        this.date = date;
        this.time = time;
        this.status = status;
    }

    public String getAppointmentId() { return appointmentId; }
    public void setAppointmentId(String appointmentId) { this.appointmentId = appointmentId; }

    public String getPatientId() { return patientId; }
    public void setPatientId(String patientId) { this.patientId = patientId; }

    public String getClinicianId() { return clinicianId; }
    public void setClinicianId(String clinicianId) { this.clinicianId = clinicianId; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
