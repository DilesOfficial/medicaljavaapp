package medicalapp.view;

import medicalapp.controller.DataManager;
import medicalapp.model.Patient;

import javax.swing.*;

public class PatientPanel extends EntityPanel {
    private DataManager dataManager;

    public PatientPanel(DataManager dm) {
        super(new String[]{"ID", "First Name", "Last Name", "DOB", "NHS No", "Gender", "Phone", "Email", "Address", "Postcode", "Emergency Name", "Emergency Phone", "Reg Date", "GP Surgery ID"});
        this.dataManager = dm;
        refreshData();
    }

    @Override
    protected void refreshData() {
        tableModel.setRowCount(0);
        for (Patient p : dataManager.getPatients()) {
            tableModel.addRow(new Object[]{
                p.getId(), p.getFirstName(), p.getLastName(), p.getDob(), p.getNhsNumber(), 
                p.getGender(), p.getPhone(), p.getEmail(), p.getAddress(), p.getPostcode(),
                p.getEmergencyName(), p.getEmergencyPhone(), p.getRegDate(), p.getGpSurgeryId()
            });
        }
    }

    @Override
    protected void onAdd() {
        FormDialog dialog = new FormDialog((JFrame) SwingUtilities.getWindowAncestor(this), "Add Patient", 
            new String[]{"ID", "First Name", "Last Name", "DOB", "NHS No", "Gender", "Phone", "Email", "Address", "Postcode", "Emergency Name", "Emergency Phone", "Reg Date", "GP Surgery ID"});
        dialog.setVisible(true);
        if (dialog.isSubmitted()) {
            dataManager.addPatient(new Patient(
                dialog.getValue("ID"),
                dialog.getValue("First Name"),
                dialog.getValue("Last Name"),
                dialog.getValue("DOB"),
                dialog.getValue("NHS No"),
                dialog.getValue("Gender"),
                dialog.getValue("Phone"),
                dialog.getValue("Email"),
                dialog.getValue("Address"),
                dialog.getValue("Postcode"),
                dialog.getValue("Emergency Name"),
                dialog.getValue("Emergency Phone"),
                dialog.getValue("Reg Date"),
                dialog.getValue("GP Surgery ID")
            ));
            refreshData();
        }
    }

    @Override
    protected void editItem(int index) {
        Patient p = dataManager.getPatients().get(index);
        FormDialog dialog = new FormDialog(
                (JFrame) SwingUtilities.getWindowAncestor(this),
                "Edit Patient",
                new String[]{"ID", "First Name", "Last Name", "DOB", "NHS No", "Gender", "Phone", "Email", "Address", "Postcode", "Emergency Name", "Emergency Phone", "Reg Date", "GP Surgery ID"}
        );
        dialog.setValue("ID", p.getId());
        dialog.setFieldEditable("ID", false);
        dialog.setValue("First Name", p.getFirstName());
        dialog.setValue("Last Name", p.getLastName());
        dialog.setValue("DOB", p.getDob());
        dialog.setValue("NHS No", p.getNhsNumber());
        dialog.setValue("Gender", p.getGender());
        dialog.setValue("Phone", p.getPhone());
        dialog.setValue("Email", p.getEmail());
        dialog.setValue("Address", p.getAddress());
        dialog.setValue("Postcode", p.getPostcode());
        dialog.setValue("Emergency Name", p.getEmergencyName());
        dialog.setValue("Emergency Phone", p.getEmergencyPhone());
        dialog.setValue("Reg Date", p.getRegDate());
        dialog.setValue("GP Surgery ID", p.getGpSurgeryId());

        dialog.setVisible(true);
        if (dialog.isSubmitted()) {
            dataManager.updatePatient(new Patient(
                dialog.getValue("ID"),
                dialog.getValue("First Name"),
                dialog.getValue("Last Name"),
                dialog.getValue("DOB"),
                dialog.getValue("NHS No"),
                dialog.getValue("Gender"),
                dialog.getValue("Phone"),
                dialog.getValue("Email"),
                dialog.getValue("Address"),
                dialog.getValue("Postcode"),
                dialog.getValue("Emergency Name"),
                dialog.getValue("Emergency Phone"),
                dialog.getValue("Reg Date"),
                dialog.getValue("GP Surgery ID")
            ));
            refreshData();
        }
    }

    @Override
    protected void deleteItem(int index) {
        Patient p = dataManager.getPatients().get(index);
        dataManager.deletePatient(p); // This assumes deletePatient only checks default equals or similar
    }
}
