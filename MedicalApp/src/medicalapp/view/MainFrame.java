package medicalapp.view;

import medicalapp.controller.DataManager;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        super("Medical Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700); // Increased size for more tabs
        setLocationRelativeTo(null);

        DataManager dm = new DataManager();

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Patients", new PatientPanel(dm));
        tabbedPane.addTab("Clinicians", new ClinicianPanel(dm));
        tabbedPane.addTab("Appointments", new AppointmentPanel(dm));
        tabbedPane.addTab("Prescriptions", new PrescriptionPanel(dm));
        tabbedPane.addTab("Referrals", new ReferralPanel());
        tabbedPane.addTab("Facilities", new FacilityPanel(dm));
        tabbedPane.addTab("Staff", new StaffPanel(dm));

        add(tabbedPane, BorderLayout.CENTER);
    }
}
