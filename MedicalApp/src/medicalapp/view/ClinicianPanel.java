package medicalapp.view;

import medicalapp.controller.DataManager;
import medicalapp.model.Clinician;

import javax.swing.*;

public class ClinicianPanel extends EntityPanel {
    private DataManager dataManager;

    public ClinicianPanel(DataManager dm) {
        super(new String[]{"ID", "Name", "Contact", "Specialty", "Qualification"});
        this.dataManager = dm;
        refreshData();
    }

    @Override
    protected void refreshData() {
        tableModel.setRowCount(0);
        for (Clinician c : dataManager.getClinicians()) {
            tableModel.addRow(new Object[]{c.getId(), c.getName(), c.getContactInfo(), c.getSpecialty(), c.getQualification()});
        }
    }

    @Override
    protected void onAdd() {
        FormDialog dialog = new FormDialog(
            (JFrame) SwingUtilities.getWindowAncestor(this), 
            "Add Clinician", 
            new String[]{"ID", "Name", "Contact", "Specialty", "Qualification"}
        );
        dialog.setVisible(true);
        if (dialog.isSubmitted()) {
            dataManager.addClinician(new Clinician(
                dialog.getValue("ID"),
                dialog.getValue("Name"),
                dialog.getValue("Contact"),
                dialog.getValue("Specialty"),
                dialog.getValue("Qualification")
            ));
            refreshData();
        }
    }

    @Override
    protected void editItem(int index) {
        Clinician c = dataManager.getClinicians().get(index);
        FormDialog dialog = new FormDialog(
                (JFrame) SwingUtilities.getWindowAncestor(this),
                "Edit Clinician",
                new String[]{"ID", "Name", "Contact", "Specialty", "Qualification"}
        );
        dialog.setValue("ID", c.getId());
        dialog.setFieldEditable("ID", false);
        dialog.setValue("Name", c.getName());
        dialog.setValue("Contact", c.getContactInfo());
        dialog.setValue("Specialty", c.getSpecialty());
        dialog.setValue("Qualification", c.getQualification());

        dialog.setVisible(true);
        if (dialog.isSubmitted()) {
            dataManager.updateClinician(new Clinician(
                    dialog.getValue("ID"),
                    dialog.getValue("Name"),
                    dialog.getValue("Contact"),
                    dialog.getValue("Specialty"),
                    dialog.getValue("Qualification")
            ));
            refreshData();
        }
    }

    @Override
    protected void deleteItem(int index) {
        Clinician c = dataManager.getClinicians().get(index);
        dataManager.deleteClinician(c);
    }
}
