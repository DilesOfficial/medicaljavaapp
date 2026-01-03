package medicalapp.view;

import medicalapp.controller.DataManager;
import medicalapp.model.Prescription;

import javax.swing.*;

public class PrescriptionPanel extends EntityPanel {
    private DataManager dataManager;

    public PrescriptionPanel(DataManager dm) {
        super(new String[]{"ID", "Patient ID", "Medication", "Dosage"});
        this.dataManager = dm;
        refreshData();
    }

    @Override
    protected void refreshData() {
        tableModel.setRowCount(0);
        for (Prescription p : dataManager.getPrescriptions()) {
            tableModel.addRow(new Object[]{p.getPrescriptionId(), p.getPatientId(), p.getMedication(), p.getDosage()});
        }
    }

    @Override
    protected void onAdd() {
        FormDialog dialog = new FormDialog((JFrame) SwingUtilities.getWindowAncestor(this), "Add Prescription", new String[]{"ID", "Patient ID", "Medication", "Dosage"});
        dialog.setVisible(true);
        if (dialog.isSubmitted()) {
            String patientId = dialog.getValue("Patient ID");
            if (dataManager.getPatient(patientId) == null) {
                JOptionPane.showMessageDialog(this, "Error: Patient ID not found. Please register the patient first.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            dataManager.addPrescription(new Prescription(
                    dialog.getValue("ID"),
                    patientId,
                    dialog.getValue("Medication"),
                    dialog.getValue("Dosage")
            ));
            refreshData();
        }
    }

    @Override
    protected void editItem(int index) {
        Prescription p = dataManager.getPrescriptions().get(index);
        FormDialog dialog = new FormDialog(
                (JFrame) SwingUtilities.getWindowAncestor(this),
                "Edit Prescription",
                new String[]{"ID", "Patient ID", "Medication", "Dosage"}
        );
        dialog.setValue("ID", p.getPrescriptionId());
        dialog.setFieldEditable("ID", false);
        dialog.setValue("Patient ID", p.getPatientId());
        dialog.setValue("Medication", p.getMedication());
        dialog.setValue("Dosage", p.getDosage());

        dialog.setVisible(true);
        if (dialog.isSubmitted()) {
            String patientId = dialog.getValue("Patient ID");
            if (dataManager.getPatient(patientId) == null) {
                JOptionPane.showMessageDialog(this, "Error: Patient ID not found.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            dataManager.updatePrescription(new Prescription(
                    dialog.getValue("ID"),
                    patientId,
                    dialog.getValue("Medication"),
                    dialog.getValue("Dosage")
            ));
            refreshData();
        }
    }

    @Override
    protected void deleteItem(int index) {
        Prescription p = dataManager.getPrescriptions().get(index);
        dataManager.deletePrescription(p);
    }
}
