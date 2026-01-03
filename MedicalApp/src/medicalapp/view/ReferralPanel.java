package medicalapp.view;

import medicalapp.controller.ReferralManager;
import medicalapp.model.Referral;

import javax.swing.*;

public class ReferralPanel extends EntityPanel {
    
    public ReferralPanel() {
        super(new String[]{"ID", "Patient ID", "Details", "Urgency"});
        refreshData();
    }

    @Override
    protected void refreshData() {
        tableModel.setRowCount(0);
        for (Referral r : ReferralManager.getInstance().getReferrals()) {
            tableModel.addRow(new Object[]{r.getReferralId(), r.getPatientId(), r.getDetails(), r.getUrgency()});
        }
    }

    @Override
    protected void onAdd() {
        FormDialog dialog = new FormDialog((JFrame) SwingUtilities.getWindowAncestor(this), "Add Referral", new String[]{"ID", "Patient ID", "Details", "Urgency"});
        dialog.setVisible(true);
        if (dialog.isSubmitted()) {
            ReferralManager.getInstance().addReferral(new Referral(
                dialog.getValue("ID"),
                dialog.getValue("Patient ID"),
                dialog.getValue("Details"),
                dialog.getValue("Urgency")
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
                new String[]{"ID", "Patient ID", "Details", "Urgency"}
        );
        dialog.setValue("ID", r.getReferralId());
        dialog.setFieldEditable("ID", false);
        dialog.setValue("Patient ID", r.getPatientId());
        dialog.setValue("Details", r.getDetails());
        dialog.setValue("Urgency", r.getUrgency());

        dialog.setVisible(true);
        if (dialog.isSubmitted()) {
            ReferralManager.getInstance().updateReferral(new Referral(
                    dialog.getValue("ID"),
                    dialog.getValue("Patient ID"),
                    dialog.getValue("Details"),
                    dialog.getValue("Urgency")
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
