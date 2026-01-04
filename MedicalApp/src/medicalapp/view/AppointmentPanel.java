package medicalapp.view;

import medicalapp.controller.DataManager;
import medicalapp.model.Appointment;

import javax.swing.*;

public class AppointmentPanel extends EntityPanel {
    private DataManager dataManager;

    public AppointmentPanel(DataManager dm) {
        super(new String[]{"ID", "Patient", "Clinician", "Facility", "Date", "Time", "Duration", "Type", "Status", "Reason", "Notes", "Created", "Modified"});
        this.dataManager = dm;
        refreshData();
    }

    @Override
    protected void refreshData() {
        tableModel.setRowCount(0);
        for (Appointment a : dataManager.getAppointments()) {
            tableModel.addRow(new Object[]{
                a.getAppointmentId(), a.getPatientId(), a.getClinicianId(), a.getFacilityId(),
                a.getDate(), a.getTime(), a.getDuration(), a.getType(), a.getStatus(),
                a.getReason(), a.getNotes(), a.getCreatedDate(), a.getLastModified()
            });
        }
    }

    @Override
    protected void onAdd() {
        FormDialog dialog = new FormDialog((JFrame) SwingUtilities.getWindowAncestor(this), "Add Appointment", 
            new String[]{"ID", "Patient ID", "Clinician ID", "Facility ID", "Date", "Time", "Duration", "Type", "Status", "Reason", "Notes", "Created", "Modified"});
        dialog.setVisible(true);
        if (dialog.isSubmitted()) {
            String patientId = dialog.getValue("Patient ID");
            if (dataManager.getPatient(patientId) == null) {
                JOptionPane.showMessageDialog(this, "Error: Patient ID not found.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String clinicianId = dialog.getValue("Clinician ID");
            if (dataManager.getClinician(clinicianId) == null) {
                JOptionPane.showMessageDialog(this, "Error: Clinician ID not found.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            dataManager.addAppointment(new Appointment(
                dialog.getValue("ID"), patientId, clinicianId,
                dialog.getValue("Facility ID"),
                dialog.getValue("Date"),
                dialog.getValue("Time"),
                dialog.getValue("Duration"),
                dialog.getValue("Type"),
                dialog.getValue("Status"),
                dialog.getValue("Reason"),
                dialog.getValue("Notes"),
                dialog.getValue("Created"),
                dialog.getValue("Modified")
            ));
            refreshData();
        }
    }

    @Override
    protected void editItem(int index) {
        Appointment a = dataManager.getAppointments().get(index);
        FormDialog dialog = new FormDialog(
                (JFrame) SwingUtilities.getWindowAncestor(this),
                "Edit Appointment",
                new String[]{"ID", "Patient ID", "Clinician ID", "Facility ID", "Date", "Time", "Duration", "Type", "Status", "Reason", "Notes", "Created", "Modified"}
        );
        dialog.setValue("ID", a.getAppointmentId());
        dialog.setFieldEditable("ID", false);
        dialog.setValue("Patient ID", a.getPatientId());
        dialog.setValue("Clinician ID", a.getClinicianId());
        dialog.setValue("Facility ID", a.getFacilityId());
        dialog.setValue("Date", a.getDate());
        dialog.setValue("Time", a.getTime());
        dialog.setValue("Duration", a.getDuration());
        dialog.setValue("Type", a.getType());
        dialog.setValue("Status", a.getStatus());
        dialog.setValue("Reason", a.getReason());
        dialog.setValue("Notes", a.getNotes());
        dialog.setValue("Created", a.getCreatedDate());
        dialog.setValue("Modified", a.getLastModified());

        dialog.setVisible(true);
        if (dialog.isSubmitted()) {
            String patientId = dialog.getValue("Patient ID");
             // Validation logic remains same
            if (dataManager.getPatient(patientId) == null) {
                JOptionPane.showMessageDialog(this, "Error: Patient ID not found.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
             String clinicianId = dialog.getValue("Clinician ID");
            if (dataManager.getClinician(clinicianId) == null) {
                JOptionPane.showMessageDialog(this, "Error: Clinician ID not found.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            dataManager.updateAppointment(new Appointment(
                dialog.getValue("ID"), patientId, clinicianId,
                dialog.getValue("Facility ID"),
                dialog.getValue("Date"),
                dialog.getValue("Time"),
                dialog.getValue("Duration"),
                dialog.getValue("Type"),
                dialog.getValue("Status"),
                dialog.getValue("Reason"),
                dialog.getValue("Notes"),
                dialog.getValue("Created"),
                dialog.getValue("Modified")
            ));
            refreshData();
        }
    }

    @Override
    protected void deleteItem(int index) {
        Appointment a = dataManager.getAppointments().get(index);
        dataManager.deleteAppointment(a);
    }
}
