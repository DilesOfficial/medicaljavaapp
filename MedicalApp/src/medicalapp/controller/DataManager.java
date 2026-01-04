package medicalapp.controller;

import medicalapp.model.*;
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
    private List<Referral> referrals;
    private List<Facility> facilities;
    private List<Staff> staffMembers;

    private static final String DATA_DIR = "data/";
    private static final String PATIENT_FILE = DATA_DIR + "patients.csv";
    private static final String CLINICIAN_FILE = DATA_DIR + "clinicians.csv";
    private static final String APPOINTMENT_FILE = DATA_DIR + "appointments.csv";
    private static final String PRESCRIPTION_FILE = DATA_DIR + "prescriptions.csv";
    private static final String REFERRAL_FILE = DATA_DIR + "referrals.csv";
    private static final String FACILITY_FILE = DATA_DIR + "facilities.csv";
    private static final String STAFF_FILE = DATA_DIR + "staff.csv";

    public DataManager() {
        new File(DATA_DIR).mkdirs();
        loadData();
    }

    public void loadData() {
        patients = loadPatients();
        clinicians = loadClinicians();
        appointments = loadAppointments();
        prescriptions = loadPrescriptions();
        facilities = loadFacilities();
        staffMembers = loadStaff();
        referrals = loadReferrals(); // Just simplified loading, ReferralManager handles logic
        
        if (patients == null) patients = new ArrayList<>();
        if (clinicians == null) clinicians = new ArrayList<>();
        if (appointments == null) appointments = new ArrayList<>();
        if (prescriptions == null) prescriptions = new ArrayList<>();
        if (facilities == null) facilities = new ArrayList<>();
        if (staffMembers == null) staffMembers = new ArrayList<>();
        if (referrals == null) referrals = new ArrayList<>();
    }

    // --- Patients ---
    public List<Patient> loadPatients() {
        List<Patient> list = new ArrayList<>();
        List<String[]> rows = CSVHandler.read(PATIENT_FILE);
        for (String[] row : rows) {
            if (row.length >= 14) {
                 if(row[0].equalsIgnoreCase("patient_id")) continue;
                list.add(new Patient(row[0], row[1], row[2], row[3], row[4], row[5], row[6], row[7], row[8], row[9], row[10], row[11], row[12], row[13]));
            }
        }
        return list;
    }
    public void savePatients() {
        List<String> lines = new ArrayList<>();
        for (Patient p : patients) {
            lines.add(String.join(",", p.getId(), p.getFirstName(), p.getLastName(), p.getDob(), p.getNhsNumber(), p.getGender(), p.getPhone(), p.getEmail(), p.getAddress(), p.getPostcode(), p.getEmergencyName(), p.getEmergencyPhone(), p.getRegDate(), p.getGpSurgeryId()));
        }
        CSVHandler.write(PATIENT_FILE, lines);
    }
    public List<Patient> getPatients() { return patients; }
    public void addPatient(Patient p) { patients.add(p); savePatients(); }
    public void deletePatient(Patient p) { patients.remove(p); savePatients(); }
    public void updatePatient(Patient p) {
        for (int i = 0; i < patients.size(); i++) {
            if (patients.get(i).getId().equals(p.getId())) {
                patients.set(i, p);
                savePatients();
                return;
            }
        }
    }
    public Patient getPatient(String id) {
        for (Patient p : patients) if (p.getId().equals(id)) return p;
        return null;
    }

    // --- Clinicians ---
    public List<Clinician> loadClinicians() {
        List<Clinician> list = new ArrayList<>();
        List<String[]> rows = CSVHandler.read(CLINICIAN_FILE);
        for (String[] row : rows) {
            if (row.length >= 12) {
                if(row[0].equalsIgnoreCase("clinician_id")) continue;
                list.add(new Clinician(row[0], row[1], row[2], row[3], row[4], row[5], row[6], row[7], row[8], row[9], row[10], row[11]));
            }
        }
        return list;
    }
    public void saveClinicians() {
        List<String> lines = new ArrayList<>();
        for (Clinician c : clinicians) {
            lines.add(String.join(",", c.getId(), c.getFirstName(), c.getLastName(), c.getTitle(), c.getSpecialty(), c.getGmcNumber(), c.getPhone(), c.getEmail(), c.getWorkplaceId(), c.getWorkplaceType(), c.getEmpStatus(), c.getStartDate()));
        }
        CSVHandler.write(CLINICIAN_FILE, lines);
    }
    public List<Clinician> getClinicians() { return clinicians; }
    public void addClinician(Clinician c) { clinicians.add(c); saveClinicians(); }
    public void deleteClinician(Clinician c) { clinicians.remove(c); saveClinicians(); }
    public void updateClinician(Clinician c) {
        for (int i = 0; i < clinicians.size(); i++) {
            if (clinicians.get(i).getId().equals(c.getId())) {
                clinicians.set(i, c);
                saveClinicians();
                return;
            }
        }
    }
    public Clinician getClinician(String id) {
        for (Clinician c : clinicians) if (c.getId().equals(id)) return c;
        return null;
    }

    // --- Appointments ---
    public List<Appointment> loadAppointments() {
        List<Appointment> list = new ArrayList<>();
        List<String[]> rows = CSVHandler.read(APPOINTMENT_FILE);
        for (String[] row : rows) {
            if (row.length >= 13) {
                 if(row[0].equalsIgnoreCase("appointment_id")) continue;
                list.add(new Appointment(row[0], row[1], row[2], row[3], row[4], row[5], row[6], row[7], row[8], row[9], row[10], row[11], row[12]));
            }
        }
        return list;
    }
    public void saveAppointments() {
        List<String> lines = new ArrayList<>();
        for (Appointment a : appointments) {
            lines.add(String.join(",", a.getAppointmentId(), a.getPatientId(), a.getClinicianId(), a.getFacilityId(), a.getDate(), a.getTime(), a.getDuration(), a.getType(), a.getStatus(), a.getReason(), a.getNotes(), a.getCreatedDate(), a.getLastModified()));
        }
        CSVHandler.write(APPOINTMENT_FILE, lines);
    }
    public List<Appointment> getAppointments() { return appointments; }
    public void addAppointment(Appointment a) { appointments.add(a); saveAppointments(); }
    public void deleteAppointment(Appointment a) { appointments.remove(a); saveAppointments(); }
    public void updateAppointment(Appointment a) {
         for (int i = 0; i < appointments.size(); i++) {
            if (appointments.get(i).getAppointmentId().equals(a.getAppointmentId())) {
                appointments.set(i, a);
                saveAppointments();
                return;
            }
        }
    }

    // --- Prescriptions ---
    public List<Prescription> loadPrescriptions() {
        List<Prescription> list = new ArrayList<>();
        List<String[]> rows = CSVHandler.read(PRESCRIPTION_FILE);
        for (String[] row : rows) {
            if (row.length >= 15) {
                if(row[0].equalsIgnoreCase("prescription_id")) continue;
                list.add(new Prescription(row[0], row[1], row[2], row[3], row[4], row[5], row[6], row[7], row[8], row[9], row[10], row[11], row[12], row[13], row[14]));
            }
        }
        return list;
    }
    public void savePrescriptions() {
        List<String> lines = new ArrayList<>();
        for (Prescription p : prescriptions) {
            lines.add(String.join(",", p.getPrescriptionId(), p.getPatientId(), p.getClinicianId(), p.getAppointmentId(), p.getDate(), p.getMedication(), p.getDosage(), p.getFrequency(), p.getDuration(), p.getQuantity(), p.getInstructions(), p.getPharmacy(), p.getStatus(), p.getIssueDate(), p.getCollectionDate()));
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

    // --- Facilities ---
    public List<Facility> loadFacilities() {
        List<Facility> list = new ArrayList<>();
        List<String[]> rows = CSVHandler.read(FACILITY_FILE);
        for (String[] row : rows) {
            if (row.length >= 11) {
                if(row[0].equalsIgnoreCase("facility_id")) continue;
                list.add(new Facility(row[0], row[1], row[2], row[3], row[4], row[5], row[6], row[7], row[8], row[9], row[10]));
            }
        }
        return list;
    }
    public List<Facility> getFacilities() { return facilities; }
    // Minimal support if needed by FacilityPanel if I updated it to use DataManager fully
    
    // --- Staff ---
    public List<Staff> loadStaff() {
         List<Staff> list = new ArrayList<>();
        List<String[]> rows = CSVHandler.read(STAFF_FILE);
        for (String[] row : rows) {
            if (row.length >= 12) {
                if(row[0].equalsIgnoreCase("staff_id")) continue;
                list.add(new Staff(row[0], row[1], row[2], row[3], row[4], row[5], row[6], row[7], row[8], row[9], row[10], row[11]));
            }
        }
        return list;
    }
    public List<Staff> getStaff() { return staffMembers; }

    // --- Referrals ---
    public List<Referral> loadReferrals() {
        List<Referral> list = new ArrayList<>();
        List<String[]> rows = CSVHandler.read(REFERRAL_FILE);
        for (String[] row : rows) {
            if (row.length >= 16) {
                if(row[0].equalsIgnoreCase("referral_id")) continue;
                list.add(new Referral(row[0], row[1], row[2], row[3], row[4], row[5], row[6], row[7], row[8], row[9], row[10], row[11], row[12], row[13], row[14], row[15]));
            }
        }
        return list;
    }
    public List<Referral> getReferrals() { return referrals; }
}
