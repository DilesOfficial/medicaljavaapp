package medicalapp.view;

import medicalapp.controller.DataManager;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        super("Medical Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        DataManager dm = new DataManager();

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Patients", new PatientPanel(dm));
        tabbedPane.addTab("Clinicians", new ClinicianPanel(dm));
        tabbedPane.addTab("Appointments", new AppointmentPanel(dm));
        tabbedPane.addTab("Prescriptions", new PrescriptionPanel(dm));
        tabbedPane.addTab("Referrals", new ReferralPanel());

        add(tabbedPane, BorderLayout.CENTER);
    }
}
