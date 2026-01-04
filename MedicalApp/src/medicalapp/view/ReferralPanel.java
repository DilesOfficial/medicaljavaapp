package medicalapp.view;

import medicalapp.controller.ReferralManager;
import medicalapp.model.Referral;

import javax.swing.*;

public class ReferralPanel extends EntityPanel {
    
    public ReferralPanel() {
        super(new String[]{"ID", "Patient", "From Clinician", "To Clinician", "From Fac", "To Fac", "Date", "Urgency", "Reason", "Summary", "Labs", "Status", "Appt ID", "Notes", "Created", "Updated"});
        refreshData();
    }

    @Override
    protected void refreshData() {
        tableModel.setRowCount(0);
        for (Referral r : ReferralManager.getInstance().getReferrals()) {
            tableModel.addRow(new Object[]{
                r.getReferralId(), r.getPatientId(), r.getReferringClinicianId(), r.getReferredToClinicianId(),
                r.getReferringFacilityId(), r.getReferredToFacilityId(), r.getReferralDate(), r.getUrgency(),
                r.getReason(), r.getClinicalSummary(), r.getInvestigations(), r.getStatus(),
                r.getAppointmentId(), r.getNotes(), r.getCreatedDate(), r.getLastUpdated()
            });
        }
    }

    @Override
    protected void onAdd() {
        FormDialog dialog = new FormDialog((JFrame) SwingUtilities.getWindowAncestor(this), "Add Referral", 
            new String[]{"ID", "Patient ID", "From Clinician ID", "To Clinician ID", "From Fac ID", "To Fac ID", "Date", "Urgency", "Reason", "Summary", "Labs", "Status", "Appt ID", "Notes", "Created", "Updated"});
        dialog.setVisible(true);
        if (dialog.isSubmitted()) {
            ReferralManager.getInstance().addReferral(new Referral(
                dialog.getValue("ID"),
                dialog.getValue("Patient ID"),
                dialog.getValue("From Clinician ID"),
                dialog.getValue("To Clinician ID"),
                dialog.getValue("From Fac ID"),
                dialog.getValue("To Fac ID"),
                dialog.getValue("Date"),
                dialog.getValue("Urgency"),
                dialog.getValue("Reason"),
                dialog.getValue("Summary"),
                dialog.getValue("Labs"),
                dialog.getValue("Status"),
                dialog.getValue("Appt ID"),
                dialog.getValue("Notes"),
                dialog.getValue("Created"),
                dialog.getValue("Updated")
            ));
            refreshData();
        }
    }

    @Override
    protected void editItem(int index) {
        Referral r = ReferralManager.getInstance().getReferrals().get(index);
        FormDialog dialog = new FormDialog(
                (JFrame) SwingUtilities.getWindowAncestor(this),
                "Edit Referral",
                new String[]{"ID", "Patient ID", "From Clinician ID", "To Clinician ID", "From Fac ID", "To Fac ID", "Date", "Urgency", "Reason", "Summary", "Labs", "Status", "Appt ID", "Notes", "Created", "Updated"}
        );
        dialog.setValue("ID", r.getReferralId());
        dialog.setFieldEditable("ID", false);
        dialog.setValue("Patient ID", r.getPatientId());
        dialog.setValue("From Clinician ID", r.getReferringClinicianId());
        dialog.setValue("To Clinician ID", r.getReferredToClinicianId());
        dialog.setValue("From Fac ID", r.getReferringFacilityId());
        dialog.setValue("To Fac ID", r.getReferredToFacilityId());
        dialog.setValue("Date", r.getReferralDate());
        dialog.setValue("Urgency", r.getUrgency());
        dialog.setValue("Reason", r.getReason());
        dialog.setValue("Summary", r.getClinicalSummary());
        dialog.setValue("Labs", r.getInvestigations());
        dialog.setValue("Status", r.getStatus());
        dialog.setValue("Appt ID", r.getAppointmentId());
        dialog.setValue("Notes", r.getNotes());
        dialog.setValue("Created", r.getCreatedDate());
        dialog.setValue("Updated", r.getLastUpdated());

        dialog.setVisible(true);
        if (dialog.isSubmitted()) {
            ReferralManager.getInstance().updateReferral(new Referral(
                dialog.getValue("ID"),
                dialog.getValue("Patient ID"),
                dialog.getValue("From Clinician ID"),
                dialog.getValue("To Clinician ID"),
                dialog.getValue("From Fac ID"),
                dialog.getValue("To Fac ID"),
                dialog.getValue("Date"),
                dialog.getValue("Urgency"),
                dialog.getValue("Reason"),
                dialog.getValue("Summary"),
                dialog.getValue("Labs"),
                dialog.getValue("Status"),
                dialog.getValue("Appt ID"),
                dialog.getValue("Notes"),
                dialog.getValue("Created"),
                dialog.getValue("Updated")
            ));
            refreshData();
        }
    }

    @Override
    protected void deleteItem(int index) {
        Referral r = ReferralManager.getInstance().getReferrals().get(index);
        ReferralManager.getInstance().deleteReferral(r);
    }
}
