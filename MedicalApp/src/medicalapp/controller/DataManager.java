package medicalapp.controller;

import medicalapp.model.Appointment;
import medicalapp.model.Clinician;
import medicalapp.model.Patient;
import medicalapp.model.Prescription;
import medicalapp.util.CSVHandler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller for managing data entities.
 * Follows MVC Controller role.
 */
public class DataManager {
    private List<Patient> patients;
    private List<Clinician> clinicians;
    private List<Appointment> appointments;
    private List<Prescription> prescriptions;

    private static final String DATA_DIR = "data/";
    private static final String PATIENT_FILE = DATA_DIR + "patients.csv";
    private static final String CLINICIAN_FILE = DATA_DIR + "clinicians.csv";
    private static final String APPOINTMENT_FILE = DATA_DIR + "appointments.csv";
    private static final String PRESCRIPTION_FILE = DATA_DIR + "prescriptions.csv";

    public DataManager() {
        new File(DATA_DIR).mkdirs();
        loadData();
    }

    public void loadData() {
        patients = loadPatients();
        clinicians = loadClinicians();
        appointments = loadAppointments();
        prescriptions = loadPrescriptions();
    }

    // --- Patients ---
    // CSV: id, name, contactInfo, age, gender, medicalHistory
    public List<Patient> loadPatients() {
        List<Patient> list = new ArrayList<>();
        List<String[]> rows = CSVHandler.read(PATIENT_FILE);
        for (String[] row : rows) {
            if (row.length >= 6) {
                try {
                    list.add(new Patient(row[0], row[1], row[2], Integer.parseInt(row[3]), row[4], row[5]));
                } catch (NumberFormatException ignored) {}
            }
        }
        return list;
    }

    public void savePatients() {
        List<String> lines = new ArrayList<>();
        for (Patient p : patients) {
            lines.add(String.join(",", p.getId(), p.getName(), p.getContactInfo(), String.valueOf(p.getAge()), p.getGender(), p.getMedicalHistory()));
        }
        CSVHandler.write(PATIENT_FILE, lines);
    }
    public List<Patient> getPatients() { return patients; }
    public void addPatient(Patient p) { patients.add(p); savePatients(); }
    public void deletePatient(Patient p) { patients.remove(p); savePatients(); }

    public Patient getPatient(String id) {
        for (Patient p : patients) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    // --- Clinicians ---
    // CSV: id, name, contactInfo, specialty, qualification
    public List<Clinician> loadClinicians() {
        List<Clinician> list = new ArrayList<>();
        List<String[]> rows = CSVHandler.read(CLINICIAN_FILE);
        for (String[] row : rows) {
            if (row.length >= 5) {
                list.add(new Clinician(row[0], row[1], row[2], row[3], row[4]));
            }
        }
        return list;
    }
    public void saveClinicians() {
        List<String> lines = new ArrayList<>();
        for (Clinician c : clinicians) {
            lines.add(String.join(",", c.getId(), c.getName(), c.getContactInfo(), c.getSpecialty(), c.getQualification()));
        }
        CSVHandler.write(CLINICIAN_FILE, lines);
    }
    public List<Clinician> getClinicians() { return clinicians; }
    public void addClinician(Clinician c) { clinicians.add(c); saveClinicians(); }
    public void deleteClinician(Clinician c) { clinicians.remove(c); saveClinicians(); }

    public Clinician getClinician(String id) {
        for (Clinician c : clinicians) {
            if (c.getId().equals(id)) {
                return c;
            }
        }
        return null;
    }

    // --- Appointments ---
    // CSV: id, patientId, clinicianId, date, time, status
    public List<Appointment> loadAppointments() {
        List<Appointment> list = new ArrayList<>();
        List<String[]> rows = CSVHandler.read(APPOINTMENT_FILE);
        for (String[] row : rows) {
            if (row.length >= 6) {
                list.add(new Appointment(row[0], row[1], row[2], row[3], row[4], row[5]));
            }
        }
        return list;
    }
    public void saveAppointments() {
        List<String> lines = new ArrayList<>();
        for (Appointment a : appointments) {
            lines.add(String.join(",", a.getAppointmentId(), a.getPatientId(), a.getClinicianId(), a.getDate(), a.getTime(), a.getStatus()));
        }
        CSVHandler.write(APPOINTMENT_FILE, lines);
    }
    public List<Appointment> getAppointments() { return appointments; }
    public void addAppointment(Appointment a) { appointments.add(a); saveAppointments(); }
    public void deleteAppointment(Appointment a) { appointments.remove(a); saveAppointments(); }

    // --- Prescriptions ---
    // CSV: id, patientId, medication, dosage
    public List<Prescription> loadPrescriptions() {
        List<Prescription> list = new ArrayList<>();
        List<String[]> rows = CSVHandler.read(PRESCRIPTION_FILE);
        for (String[] row : rows) {
            if (row.length >= 4) {
                list.add(new Prescription(row[0], row[1], row[2], row[3]));
            }
        }
        return list;
    }
    public void savePrescriptions() {
        List<String> lines = new ArrayList<>();
        for (Prescription p : prescriptions) {
            lines.add(String.join(",", p.getPrescriptionId(), p.getPatientId(), p.getMedication(), p.getDosage()));
        }
        CSVHandler.write(PRESCRIPTION_FILE, lines);
    }
    public List<Prescription> getPrescriptions() { return prescriptions; }
    public void addPrescription(Prescription p) { 
        prescriptions.add(p); 
        savePrescriptions(); 
        generatePrescriptionFile(p);
    }
    public void deletePrescription(Prescription p) { prescriptions.remove(p); savePrescriptions(); }

    // --- Update Methods ---
    public void updatePatient(Patient p) {
        for (int i = 0; i < patients.size(); i++) {
            if (patients.get(i).getId().equals(p.getId())) {
                patients.set(i, p);
                savePatients();
                return;
            }
        }
    }

    public void updateClinician(Clinician c) {
        for (int i = 0; i < clinicians.size(); i++) {
            if (clinicians.get(i).getId().equals(c.getId())) {
                clinicians.set(i, c);
                saveClinicians();
                return;
            }
        }
    }

    public void updateAppointment(Appointment a) {
        for (int i = 0; i < appointments.size(); i++) {
            if (appointments.get(i).getAppointmentId().equals(a.getAppointmentId())) {
                appointments.set(i, a);
                saveAppointments();
                return;
            }
        }
    }

    public void updatePrescription(Prescription p) {
        for (int i = 0; i < prescriptions.size(); i++) {
            if (prescriptions.get(i).getPrescriptionId().equals(p.getPrescriptionId())) {
                prescriptions.set(i, p);
                savePrescriptions();
                generatePrescriptionFile(p);
                return;
            }
        }
    }

    private void generatePrescriptionFile(Prescription p) {
        try {
            String folder = DATA_DIR + "prescriptions/";
            new File(folder).mkdirs();
            String content = "Prescription SCRIPT\n" +
                             "===================\n" +
                             "ID:         " + p.getPrescriptionId() + "\n" +
                             "Patient ID: " + p.getPatientId() + "\n" +
                             "Medication: " + p.getMedication() + "\n" +
                             "Dosage:     " + p.getDosage() + "\n";
            java.nio.file.Files.write(java.nio.file.Paths.get(folder + p.getPrescriptionId() + ".txt"), content.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
