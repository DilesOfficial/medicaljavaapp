package medicalapp.test;

import medicalapp.controller.DataManager;
import medicalapp.model.*;

public class DataVerification {
    public static void main(String[] args) {
        System.out.println("Starting Data Verification...");
        
        try {
            DataManager dm = new DataManager();
            
            // Verify Patients
            System.out.println("Patients loaded: " + dm.getPatients().size());
            if (!dm.getPatients().isEmpty()) {
                System.out.println("First Patient: " + dm.getPatients().get(0));
            } else {
                System.err.println("ERROR: No patients loaded!");
            }

            // Verify Clinicians
            System.out.println("Clinicians loaded: " + dm.getClinicians().size());
            if (!dm.getClinicians().isEmpty()) {
                System.out.println("First Clinician: " + dm.getClinicians().get(0));
            } else {
                System.err.println("ERROR: No clinicians loaded!");
            }

            // Verify Appointments
            System.out.println("Appointments loaded: " + dm.getAppointments().size());
            if (!dm.getAppointments().isEmpty()) {
                System.out.println("First Appointment: " + dm.getAppointments().get(0).getAppointmentId());
            }

            // Verify Prescriptions
            System.out.println("Prescriptions loaded: " + dm.getPrescriptions().size());
            if (!dm.getPrescriptions().isEmpty()) {
                System.out.println("First Prescription: " + dm.getPrescriptions().get(0).getPrescriptionId());
            }

            // Verify Facilities
            System.out.println("Facilities loaded: " + dm.getFacilities().size());
            if (!dm.getFacilities().isEmpty()) {
                System.out.println("First Facility: " + dm.getFacilities().get(0).getName());
            }

            // Verify Staff
            System.out.println("Staff loaded: " + dm.getStaff().size());
            if (!dm.getStaff().isEmpty()) {
                System.out.println("First Staff: " + dm.getStaff().get(0).getFirstName());
            }

            // Verify Referrals
            System.out.println("Referrals loaded: " + dm.getReferrals().size());
            if (!dm.getReferrals().isEmpty()) {
                System.out.println("First Referral: " + dm.getReferrals().get(0).getReferralId());
            }

            System.out.println("Verification Complete.");

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Verification Failed due to exception.");
        }
    }
}
