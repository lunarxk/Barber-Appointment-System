package com.domebarbershop;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class BarberGUI extends JFrame {
    private AppointmentManager appointmentManager;
    private User barber;
    private JTable table;
    private DefaultTableModel model;

    public BarberGUI(AppointmentManager am, User barber){
        this.appointmentManager = am;
        this.barber = barber;

        setTitle("Barber Dashboard - " + barber.getUsername());
        setSize(600,400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel lbl = new JLabel("Appointments for " + barber.getUsername(), JLabel.CENTER);
        lbl.setFont(new Font("Arial", Font.BOLD, 20));
        add(lbl, BorderLayout.NORTH);

        model = new DefaultTableModel(new Object[]{"Customer","Service","Date","Time","Payment","Detail"},0);
        table = new JTable(model);
        refreshTable();
        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton btnBack = new JButton("Back");
        add(btnBack, BorderLayout.SOUTH);

        btnBack.addActionListener(e ->
            NavigationHelper.goToAndDispose(this, new SalonAuthModule())
        );

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent e) { refreshTable(); }
        });
    }

    private void refreshTable(){
        model.setRowCount(0);
        for(Appointment a : appointmentManager.getAppointments()){
            if(a.getBarberName().equals(barber.getUsername())){
                model.addRow(new Object[]{
                        a.getCustomerName(),
                        a.getService(),
                        a.getDate(),
                        a.getTime(),
                        a.getPaymentMethod(),
                        a.getDetail()
                });
            }
        }
    }
}
