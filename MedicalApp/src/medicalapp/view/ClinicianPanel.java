package medicalapp.view;

import medicalapp.controller.DataManager;
import medicalapp.model.Clinician;

import javax.swing.*;

public class ClinicianPanel extends EntityPanel {
    private DataManager dataManager;

    public ClinicianPanel(DataManager dm) {
        super(new String[]{"ID", "First Name", "Last Name", "Title", "Specialty", "GMC No", "Phone", "Email", "Workplace ID", "Type", "Status", "Start Date"});
        this.dataManager = dm;
        refreshData();
    }

    @Override
    protected void refreshData() {
        tableModel.setRowCount(0);
        for (Clinician c : dataManager.getClinicians()) {
            tableModel.addRow(new Object[]{
                c.getId(), c.getFirstName(), c.getLastName(), c.getTitle(), c.getSpecialty(), 
                c.getGmcNumber(), c.getPhone(), c.getEmail(), c.getWorkplaceId(), 
                c.getWorkplaceType(), c.getEmpStatus(), c.getStartDate()
            });
        }
    }

    @Override
    protected void onAdd() {
        FormDialog dialog = new FormDialog((JFrame) SwingUtilities.getWindowAncestor(this), "Add Clinician", 
            new String[]{"ID", "First Name", "Last Name", "Title", "Specialty", "GMC No", "Phone", "Email", "Workplace ID", "Type", "Status", "Start Date"});
        dialog.setVisible(true);
        if (dialog.isSubmitted()) {
            dataManager.addClinician(new Clinician(
                dialog.getValue("ID"),
                dialog.getValue("First Name"),
                dialog.getValue("Last Name"),
                dialog.getValue("Title"),
                dialog.getValue("Specialty"),
                dialog.getValue("GMC No"),
                dialog.getValue("Phone"),
                dialog.getValue("Email"),
                dialog.getValue("Workplace ID"),
                dialog.getValue("Type"),
                dialog.getValue("Status"),
                dialog.getValue("Start Date")
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
                new String[]{"ID", "First Name", "Last Name", "Title", "Specialty", "GMC No", "Phone", "Email", "Workplace ID", "Type", "Status", "Start Date"}
        );
        dialog.setValue("ID", c.getId());
        dialog.setFieldEditable("ID", false);
        dialog.setValue("First Name", c.getFirstName());
        dialog.setValue("Last Name", c.getLastName());
        dialog.setValue("Title", c.getTitle());
        dialog.setValue("Specialty", c.getSpecialty());
        dialog.setValue("GMC No", c.getGmcNumber());
        dialog.setValue("Phone", c.getPhone());
        dialog.setValue("Email", c.getEmail());
        dialog.setValue("Workplace ID", c.getWorkplaceId());
        dialog.setValue("Type", c.getWorkplaceType());
        dialog.setValue("Status", c.getEmpStatus());
        dialog.setValue("Start Date", c.getStartDate());

        dialog.setVisible(true);
        if (dialog.isSubmitted()) {
            dataManager.updateClinician(new Clinician(
                dialog.getValue("ID"),
                dialog.getValue("First Name"),
                dialog.getValue("Last Name"),
                dialog.getValue("Title"),
                dialog.getValue("Specialty"),
                dialog.getValue("GMC No"),
                dialog.getValue("Phone"),
                dialog.getValue("Email"),
                dialog.getValue("Workplace ID"),
                dialog.getValue("Type"),
                dialog.getValue("Status"),
                dialog.getValue("Start Date")
            ));
            refreshData();
        }
    }

    @Override
    protected void deleteItem(int index) {
        Clinician c = dataManager.getClinicians().get(index);
        dataManager.deleteClinician(c);
        // Assuming DataManager has deleteClinician method implemented
    }
}
