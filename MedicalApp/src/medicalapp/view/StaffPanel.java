package medicalapp.view;

import medicalapp.controller.DataManager;
import medicalapp.model.Staff;

import javax.swing.*;

public class StaffPanel extends EntityPanel {
    private DataManager dataManager;

    public StaffPanel(DataManager dm) {
        super(new String[]{"ID", "First Name", "Last Name", "Role", "Dept", "Facility ID", "Phone", "Email", "Status", "Joined", "Manager", "Access"});
        this.dataManager = dm;
        refreshData();
    }

    @Override
    protected void refreshData() {
        tableModel.setRowCount(0);
        for (Staff s : dataManager.getStaff()) {
            tableModel.addRow(new Object[]{
                s.getId(), s.getFirstName(), s.getLastName(), s.getRole(), s.getDepartment(),
                s.getFacilityId(), s.getPhone(), s.getEmail(), s.getEmpStatus(),
                s.getStartDate(), s.getLineManager(), s.getAccessLevel()
            });
        }
    }

    @Override
    protected void onAdd() {
        FormDialog dialog = new FormDialog((JFrame) SwingUtilities.getWindowAncestor(this), "Add Staff", 
            new String[]{"ID", "First Name", "Last Name", "Role", "Dept", "Facility ID", "Phone", "Email", "Status", "Joined", "Manager", "Access"});
        dialog.setVisible(true);
        if (dialog.isSubmitted()) {
            dataManager.getStaff().add(new Staff(
                dialog.getValue("ID"),
                dialog.getValue("First Name"),
                dialog.getValue("Last Name"),
                dialog.getValue("Role"),
                dialog.getValue("Dept"),
                dialog.getValue("Facility ID"),
                dialog.getValue("Phone"),
                dialog.getValue("Email"),
                dialog.getValue("Status"),
                dialog.getValue("Joined"),
                dialog.getValue("Manager"),
                dialog.getValue("Access")
            ));
            refreshData();
        }
    }

    @Override
    protected void editItem(int index) {
        Staff s = dataManager.getStaff().get(index);
        FormDialog dialog = new FormDialog(
                (JFrame) SwingUtilities.getWindowAncestor(this),
                "Edit Staff",
                new String[]{"ID", "First Name", "Last Name", "Role", "Dept", "Facility ID", "Phone", "Email", "Status", "Joined", "Manager", "Access"}
        );
        dialog.setValue("ID", s.getId());
        dialog.setFieldEditable("ID", false);
        dialog.setValue("First Name", s.getFirstName());
        dialog.setValue("Last Name", s.getLastName());
        dialog.setValue("Role", s.getRole());
        dialog.setValue("Dept", s.getDepartment());
        dialog.setValue("Facility ID", s.getFacilityId());
        dialog.setValue("Phone", s.getPhone());
        dialog.setValue("Email", s.getEmail());
        dialog.setValue("Status", s.getEmpStatus());
        dialog.setValue("Joined", s.getStartDate());
        dialog.setValue("Manager", s.getLineManager());
        dialog.setValue("Access", s.getAccessLevel());

        dialog.setVisible(true);
        if (dialog.isSubmitted()) {
            s.setFirstName(dialog.getValue("First Name"));
            s.setLastName(dialog.getValue("Last Name"));
            s.setRole(dialog.getValue("Role"));
            s.setDepartment(dialog.getValue("Dept"));
            s.setFacilityId(dialog.getValue("Facility ID"));
            s.setPhone(dialog.getValue("Phone"));
            s.setEmail(dialog.getValue("Email"));
            s.setEmpStatus(dialog.getValue("Status"));
            s.setStartDate(dialog.getValue("Joined"));
            s.setLineManager(dialog.getValue("Manager"));
            s.setAccessLevel(dialog.getValue("Access"));
            refreshData();
        }
    }

    @Override
    protected void deleteItem(int index) {
        Staff s = dataManager.getStaff().get(index);
        dataManager.getStaff().remove(s);
    }
}
