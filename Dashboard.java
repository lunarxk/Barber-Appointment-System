package com.domebarbershop;

import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {
    private AppointmentManager appointmentManager;
    private UserManager userManager;
    private User customer;

    public Dashboard(AppointmentManager am, UserManager um, User customer){
        this.appointmentManager = am;
        this.userManager = um;
        this.customer = customer;

        setTitle("Customer Dashboard - " + customer.getUsername());
        setSize(400,300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.DARK_GRAY);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);

        JLabel lbl = new JLabel("Welcome, " + customer.getUsername());
        lbl.setFont(new Font("Arial", Font.BOLD, 18));
        lbl.setForeground(Color.WHITE);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(lbl, gbc);

        JButton btnBook = new JButton("Book Appointment");
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 1;
        add(btnBook, gbc);

        JButton btnView = new JButton("View My Appointments");
        gbc.gridx = 1; gbc.gridy = 1;
        add(btnView, gbc);

        JButton btnLogout = new JButton("Logout");
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        add(btnLogout, gbc);

        btnBook.addActionListener(e ->
            NavigationHelper.goTo(this, new CustomerAppointmentGUI(appointmentManager, customer))
        );

        btnView.addActionListener(e ->
            NavigationHelper.goTo(this, new CustomerAppointmentsViewer(appointmentManager, customer))
        );

        btnLogout.addActionListener(e ->
            NavigationHelper.goToAndDispose(this, new SalonAuthModule())
        );
    }
}
