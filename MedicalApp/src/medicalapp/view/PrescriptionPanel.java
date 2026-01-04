package medicalapp.view;

import medicalapp.controller.DataManager;
import medicalapp.model.Prescription;

import javax.swing.*;

public class PrescriptionPanel extends EntityPanel {
    private DataManager dataManager;

    public PrescriptionPanel(DataManager dm) {
        super(new String[]{"ID", "Patient", "Clinician", "Appt ID", "Date", "Drug", "Dosage", "Freq", "Duration", "Qty", "Instruction", "Pharmacy", "Status", "Issued", "Collected"});
        this.dataManager = dm;
        refreshData();
    }

    @Override
    protected void refreshData() {
        tableModel.setRowCount(0);
        for (Prescription p : dataManager.getPrescriptions()) {
            tableModel.addRow(new Object[]{
                p.getPrescriptionId(), p.getPatientId(), p.getClinicianId(), p.getAppointmentId(),
                p.getDate(), p.getMedication(), p.getDosage(), p.getFrequency(), p.getDuration(),
                p.getQuantity(), p.getInstructions(), p.getPharmacy(), p.getStatus(),
                p.getIssueDate(), p.getCollectionDate()
            });
        }
    }

    @Override
    protected void onAdd() {
         FormDialog dialog = new FormDialog((JFrame) SwingUtilities.getWindowAncestor(this), "Add Prescription", 
            new String[]{"ID", "Patient ID", "Clinician ID", "Appt ID", "Date", "Drug", "Dosage", "Freq", "Duration", "Qty", "Instruction", "Pharmacy", "Status", "Issued", "Collected"});
        dialog.setVisible(true);
        if (dialog.isSubmitted()) {
             String patientId = dialog.getValue("Patient ID");
            if (dataManager.getPatient(patientId) == null) {
                JOptionPane.showMessageDialog(this, "Error: Patient ID not found.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            dataManager.addPrescription(new Prescription(
                dialog.getValue("ID"), patientId,
                dialog.getValue("Clinician ID"),
                dialog.getValue("Appt ID"),
                dialog.getValue("Date"),
                dialog.getValue("Drug"),
                dialog.getValue("Dosage"),
                dialog.getValue("Freq"),
                dialog.getValue("Duration"),
                dialog.getValue("Qty"),
                dialog.getValue("Instruction"),
                dialog.getValue("Pharmacy"),
                dialog.getValue("Status"),
                dialog.getValue("Issued"),
                dialog.getValue("Collected")
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
                new String[]{"ID", "Patient ID", "Clinician ID", "Appt ID", "Date", "Drug", "Dosage", "Freq", "Duration", "Qty", "Instruction", "Pharmacy", "Status", "Issued", "Collected"}
        );
        dialog.setValue("ID", p.getPrescriptionId());
        dialog.setFieldEditable("ID", false);
        dialog.setValue("Patient ID", p.getPatientId());
        dialog.setValue("Clinician ID", p.getClinicianId());
        dialog.setValue("Appt ID", p.getAppointmentId());
        dialog.setValue("Date", p.getDate());
        dialog.setValue("Drug", p.getMedication());
        dialog.setValue("Dosage", p.getDosage());
        dialog.setValue("Freq", p.getFrequency());
        dialog.setValue("Duration", p.getDuration());
        dialog.setValue("Qty", p.getQuantity());
        dialog.setValue("Instruction", p.getInstructions());
        dialog.setValue("Pharmacy", p.getPharmacy());
        dialog.setValue("Status", p.getStatus());
        dialog.setValue("Issued", p.getIssueDate());
        dialog.setValue("Collected", p.getCollectionDate());

        dialog.setVisible(true);
        if (dialog.isSubmitted()) {
            dataManager.updatePrescription(new Prescription(
                dialog.getValue("ID"),
                dialog.getValue("Patient ID"),
                dialog.getValue("Clinician ID"),
                dialog.getValue("Appt ID"),
                dialog.getValue("Date"),
                dialog.getValue("Drug"),
                dialog.getValue("Dosage"),
                dialog.getValue("Freq"),
                dialog.getValue("Duration"),
                dialog.getValue("Qty"),
                dialog.getValue("Instruction"),
                dialog.getValue("Pharmacy"),
                dialog.getValue("Status"),
                dialog.getValue("Issued"),
                dialog.getValue("Collected")
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
