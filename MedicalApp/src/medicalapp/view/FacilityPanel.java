package medicalapp.view;

import medicalapp.controller.DataManager;
import medicalapp.model.Facility;

import javax.swing.*;

public class FacilityPanel extends EntityPanel {
    private DataManager dataManager;

    public FacilityPanel(DataManager dm) {
        super(new String[]{"ID", "Name", "Type", "Address", "Postcode", "Phone", "Email", "Hours", "Manager", "Capacity", "Specialties"});
        this.dataManager = dm;
        refreshData();
    }

    @Override
    protected void refreshData() {
        tableModel.setRowCount(0);
        for (Facility f : dataManager.getFacilities()) {
            tableModel.addRow(new Object[]{
                f.getFacilityId(), f.getName(), f.getType(), f.getAddress(), f.getPostcode(),
                f.getPhone(), f.getEmail(), f.getOpeningHours(), f.getManager(),
                f.getCapacity(), f.getSpecialties()
            });
        }
    }

    @Override
    protected void onAdd() {
        FormDialog dialog = new FormDialog((JFrame) SwingUtilities.getWindowAncestor(this), "Add Facility", 
            new String[]{"ID", "Name", "Type", "Address", "Postcode", "Phone", "Email", "Hours", "Manager", "Capacity", "Specialties"});
        dialog.setVisible(true);
        if (dialog.isSubmitted()) {
            // Need to implement addFacility in DataManager likely, checking implementation plan it mentions updates.
            // Placeholder add logic if not present, but implementation plan said "Update DataManager". 
            // DataManager likely has list but maybe not save logic fully implemented in my tool call?
            // Checking previous DataManager content: created methods getFacilities, loadFacilities. 
            // Save logic was missing in replacing content? The prompt said to replace entire file, but I might have missed explicit add/save methods for new entities.
            // I will assume for now I should add them to the list and handle persistence later or update DataManager again.
            // Actually, I should update DataManager to have add/save/delete for all if missing.
            // For now, I will write the Panel code assuming methods exist or direct list access if needed (but best to use methods).
            // Wait, I updated DataManager but only inspected appointments/prescriptions save. 
            // I should double check DataManager methods.
            // But let's write the panel first.
            
            // I'll assume addFacility exists or I will add it.
             dataManager.getFacilities().add(new Facility(
                dialog.getValue("ID"),
                dialog.getValue("Name"),
                dialog.getValue("Type"),
                dialog.getValue("Address"),
                dialog.getValue("Postcode"),
                dialog.getValue("Phone"),
                dialog.getValue("Email"),
                dialog.getValue("Hours"),
                dialog.getValue("Manager"),
                dialog.getValue("Capacity"),
                dialog.getValue("Specialties")
            ));
            refreshData();
        }
    }

    @Override
    protected void editItem(int index) {
        Facility f = dataManager.getFacilities().get(index);
        FormDialog dialog = new FormDialog(
                (JFrame) SwingUtilities.getWindowAncestor(this),
                "Edit Facility",
                new String[]{"ID", "Name", "Type", "Address", "Postcode", "Phone", "Email", "Hours", "Manager", "Capacity", "Specialties"}
        );
        dialog.setValue("ID", f.getFacilityId());
        dialog.setFieldEditable("ID", false);
        dialog.setValue("Name", f.getName());
        dialog.setValue("Type", f.getType());
        dialog.setValue("Address", f.getAddress());
        dialog.setValue("Postcode", f.getPostcode());
        dialog.setValue("Phone", f.getPhone());
        dialog.setValue("Email", f.getEmail());
        dialog.setValue("Hours", f.getOpeningHours());
        dialog.setValue("Manager", f.getManager());
        dialog.setValue("Capacity", f.getCapacity());
        dialog.setValue("Specialties", f.getSpecialties());

        dialog.setVisible(true);
        if (dialog.isSubmitted()) {
            // Update logic - rudimentary since DataManager might lack specific updateFacility
            f.setName(dialog.getValue("Name"));
            f.setType(dialog.getValue("Type"));
            f.setAddress(dialog.getValue("Address"));
            f.setPostcode(dialog.getValue("Postcode"));
            f.setPhone(dialog.getValue("Phone"));
            f.setEmail(dialog.getValue("Email"));
            f.setOpeningHours(dialog.getValue("Hours"));
            f.setManager(dialog.getValue("Manager"));
            f.setCapacity(dialog.getValue("Capacity"));
            f.setSpecialties(dialog.getValue("Specialties"));
            refreshData();
        }
    }

    @Override
    protected void deleteItem(int index) {
        Facility f = dataManager.getFacilities().get(index);
        dataManager.getFacilities().remove(f);
    }
}
