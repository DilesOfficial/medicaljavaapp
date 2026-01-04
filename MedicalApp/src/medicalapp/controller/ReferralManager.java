package medicalapp.controller;

import medicalapp.model.Referral;
import medicalapp.util.CSVHandler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Singleton Controller for managing Referrals.
 * Demonstrates Singleton Pattern as per requirements.
 */
public class ReferralManager {
    private static ReferralManager instance;
    private List<Referral> referrals;

    private static final String DATA_DIR = "data/";
    private static final String REFERRAL_FILE = DATA_DIR + "referrals.csv";

    // Private constructor prevents instantiation from other classes
    private ReferralManager() {
        new File(DATA_DIR).mkdirs();
        referrals = loadReferrals();
    }

    // Public static method to provide global access to the instance
    public static synchronized ReferralManager getInstance() {
        if (instance == null) {
            instance = new ReferralManager();
        }
        return instance;
    }

    // referral_id,patient_id,referring_clinician_id,referred_to_clinician_id,referring_facility_id,referred_to_facility_id,referral_date,urgency_level,referral_reason,clinical_summary,requested_investigations,status,appointment_id,notes,created_date,last_updated
    private List<Referral> loadReferrals() {
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

    public void saveReferrals() {
        List<String> lines = new ArrayList<>();
        for (Referral r : referrals) {
            lines.add(String.join(",", 
                r.getReferralId(), r.getPatientId(), r.getReferringClinicianId(), r.getReferredToClinicianId(),
                r.getReferringFacilityId(), r.getReferredToFacilityId(), r.getReferralDate(), r.getUrgency(),
                r.getReason(), r.getClinicalSummary(), r.getInvestigations(), r.getStatus(),
                r.getAppointmentId(), r.getNotes(), r.getCreatedDate(), r.getLastUpdated()
            ));
        }
        CSVHandler.write(REFERRAL_FILE, lines);
    }

    public List<Referral> getReferrals() { return referrals; }
    public void addReferral(Referral r) { 
        referrals.add(r); 
        saveReferrals(); 
        generateReferralFile(r);
    }
    public void deleteReferral(Referral r) { referrals.remove(r); saveReferrals(); }

    public void updateReferral(Referral r) {
        for (int i = 0; i < referrals.size(); i++) {
            if (referrals.get(i).getReferralId().equals(r.getReferralId())) {
                referrals.set(i, r);
                saveReferrals();
                generateReferralFile(r);
                return;
            }
        }
    }

    private void generateReferralFile(Referral r) {
        try {
            String folder = DATA_DIR + "referrals/";
            new File(folder).mkdirs();
            String content = "Referral Record\n" +
                             "===============\n" +
                             "Referral ID: " + r.getReferralId() + "\n" +
                             "Patient ID:  " + r.getPatientId() + "\n" +
                             "From Clinician: " + r.getReferringClinicianId() + "\n" +
                             "To Clinician:   " + r.getReferredToClinicianId() + "\n" +
                             "From Facility:  " + r.getReferringFacilityId() + "\n" +
                             "To Facility:    " + r.getReferredToFacilityId() + "\n" +
                             "Date:        " + r.getReferralDate() + "\n" +
                             "Urgency:     " + r.getUrgency() + "\n" +
                             "Reason:      " + r.getReason() + "\n" +
                             "Summary:     " + r.getClinicalSummary() + "\n";
            java.nio.file.Files.write(java.nio.file.Paths.get(folder + r.getReferralId() + ".txt"), content.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
