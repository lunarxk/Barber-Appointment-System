package com.domebarbershop;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CustomerAppointmentGUI extends JFrame {
    private AppointmentManager appointmentManager;
    private User customer;

    public CustomerAppointmentGUI(AppointmentManager am, User customer){
        this.appointmentManager = am;
        this.customer = customer;

        setTitle("Book Appointment");
        setSize(450,400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.DARK_GRAY);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblBarber = new JLabel("Select Barber:");
        lblBarber.setForeground(Color.WHITE);
        gbc.gridx = 0; gbc.gridy = 0;
        add(lblBarber, gbc);

        List<User> barbersList = new UserManager().getBarbers();
        String[] barbers = barbersList.stream().map(User::getUsername).toArray(String[]::new);
        JComboBox<String> cmbBarber = new JComboBox<>(barbers);
        gbc.gridx = 1; gbc.gridy = 0;
        add(cmbBarber, gbc);

        JLabel lblService = new JLabel("Service:");
        lblService.setForeground(Color.WHITE);
        gbc.gridx = 0; gbc.gridy = 1;
        add(lblService, gbc);
        JComboBox<String> cmbService = new JComboBox<>(new String[]{"Haircut","Beard Trim","Hairstyle"});
        gbc.gridx = 1; gbc.gridy = 1;
        add(cmbService, gbc);

        JLabel lblDate = new JLabel("Date:");
        lblDate.setForeground(Color.WHITE);
        gbc.gridx = 0; gbc.gridy = 2;
        add(lblDate, gbc);
        JComboBox<String> cmbDate = new JComboBox<>(new String[]{"2025-11-18","2025-11-19","2025-11-20"});
        gbc.gridx = 1; gbc.gridy = 2;
        add(cmbDate, gbc);

        JLabel lblTime = new JLabel("Time:");
        lblTime.setForeground(Color.WHITE);
        gbc.gridx = 0; gbc.gridy = 3;
        add(lblTime, gbc);
        JComboBox<String> cmbTime = new JComboBox<>(new String[]{"09:00","10:00","11:00","12:00","13:00","14:00"});
        gbc.gridx = 1; gbc.gridy = 3;
        add(cmbTime, gbc);

        JLabel lblPayment = new JLabel("Payment Method:");
        lblPayment.setForeground(Color.WHITE);
        gbc.gridx = 0; gbc.gridy = 4;
        add(lblPayment, gbc);
        JComboBox<String> cmbPayment = new JComboBox<>(new String[]{"Cash","Mobile Money","Visa"});
        gbc.gridx = 1; gbc.gridy = 4;
        add(cmbPayment, gbc);

        JLabel lblDetail = new JLabel("Detail:");
        lblDetail.setForeground(Color.WHITE);
        gbc.gridx = 0; gbc.gridy = 5;
        add(lblDetail, gbc);
        JTextField txtDetail = new JTextField();
        gbc.gridx = 1; gbc.gridy = 5;
        add(txtDetail, gbc);

        JButton btnBook = new JButton("Book");
        gbc.gridx = 0; gbc.gridy = 6;
        add(btnBook, gbc);
        JButton btnBack = new JButton("Back");
        gbc.gridx = 1; gbc.gridy = 6;
        add(btnBack, gbc);

        btnBack.addActionListener(e ->
            NavigationHelper.goTo(this, new Dashboard(appointmentManager, new UserManager(), customer))
        );

        btnBook.addActionListener(e -> {
            String barber = (String)cmbBarber.getSelectedItem();
            String service = (String)cmbService.getSelectedItem();
            String date = (String)cmbDate.getSelectedItem();
            String time = (String)cmbTime.getSelectedItem();
            String payment = (String)cmbPayment.getSelectedItem();
            String detail = txtDetail.getText();

            if(detail.isEmpty()){
                JOptionPane.showMessageDialog(this,"Please enter a detail!");
                return;
            }

            Appointment a = new Appointment(customer.getUsername(), barber, service, date, time, payment, detail);
            boolean success = appointmentManager.addAppointment(a);
            if(success){
                JOptionPane.showMessageDialog(this,"Appointment booked successfully!");
            } else {
                JOptionPane.showMessageDialog(this,"This barber is already booked at this date and time!");
            }
        });
    }
}
