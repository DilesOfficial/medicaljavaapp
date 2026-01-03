package medicalapp.view;

import medicalapp.controller.DataManager;
import medicalapp.model.Patient;

import javax.swing.*;

public class PatientPanel extends EntityPanel {
    private DataManager dataManager;

    public PatientPanel(DataManager dm) {
        // Columns: ID, Name, Contact, Age, Gender, History
        super(new String[]{"ID", "Name", "Contact", "Age", "Gender", "History"});
        this.dataManager = dm;
        refreshData();
    }

    @Override
    protected void refreshData() {
        tableModel.setRowCount(0);
        for (Patient p : dataManager.getPatients()) {
            tableModel.addRow(new Object[]{p.getId(), p.getName(), p.getContactInfo(), p.getAge(), p.getGender(), p.getMedicalHistory()});
        }
    }

    @Override
    protected void onAdd() {
        FormDialog dialog = new FormDialog(
            (JFrame) SwingUtilities.getWindowAncestor(this), 
            "Add Patient", 
            new String[]{"ID", "Name", "Contact", "Age", "Gender", "Medical History"}
        );
        dialog.setVisible(true);
        if (dialog.isSubmitted()) {
            try {
                String id = dialog.getValue("ID");
                String name = dialog.getValue("Name");
                String contact = dialog.getValue("Contact");
                int age = Integer.parseInt(dialog.getValue("Age"));
                String gender = dialog.getValue("Gender");
                String history = dialog.getValue("Medical History");

                if (!id.isEmpty() && !name.isEmpty()) {
                    dataManager.addPatient(new Patient(id, name, contact, age, gender, history));
                    refreshData();
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid Age format.");
            }
        }
    }

    @Override
    protected void editItem(int index) {
        Patient p = dataManager.getPatients().get(index);
        FormDialog dialog = new FormDialog(
                (JFrame) SwingUtilities.getWindowAncestor(this),
                "Edit Patient",
                new String[]{"ID", "Name", "Contact", "Age", "Gender", "Medical History"}
        );
        dialog.setValue("ID", p.getId());
        dialog.setFieldEditable("ID", false);
        dialog.setValue("Name", p.getName());
        dialog.setValue("Contact", p.getContactInfo());
        dialog.setValue("Age", String.valueOf(p.getAge()));
        dialog.setValue("Gender", p.getGender());
        dialog.setValue("Medical History", p.getMedicalHistory());

        dialog.setVisible(true);
        if (dialog.isSubmitted()) {
            try {
                String id = dialog.getValue("ID");
                String name = dialog.getValue("Name");
                String contact = dialog.getValue("Contact");
                int age = Integer.parseInt(dialog.getValue("Age"));
                String gender = dialog.getValue("Gender");
                String history = dialog.getValue("Medical History");

                if (!name.isEmpty()) {
                    dataManager.updatePatient(new Patient(id, name, contact, age, gender, history));
                    refreshData();
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid Age format.");
            }
        }
    }

    @Override
    protected void deleteItem(int index) {
        Patient p = dataManager.getPatients().get(index);
        dataManager.deletePatient(p);
    }
}
