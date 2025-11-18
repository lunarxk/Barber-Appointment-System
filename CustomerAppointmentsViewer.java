package com.domebarbershop;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CustomerAppointmentsViewer extends JFrame {
    private AppointmentManager appointmentManager;
    private User customer;
    private JTable table;
    private DefaultTableModel model;

    public CustomerAppointmentsViewer(AppointmentManager am, User customer){
        this.appointmentManager = am;
        this.customer = customer;

        setTitle("My Appointments");
        setSize(600,400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel lbl = new JLabel("My Appointments", JLabel.CENTER);
        lbl.setFont(new Font("Arial", Font.BOLD, 20));
        add(lbl, BorderLayout.NORTH);

        model = new DefaultTableModel(new Object[]{"Barber","Service","Date","Time","Payment","Detail"},0);
        table = new JTable(model);
        refreshTable();
        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton btnBack = new JButton("Back");
        add(btnBack, BorderLayout.SOUTH);

        btnBack.addActionListener(e ->
            NavigationHelper.goTo(this, new Dashboard(appointmentManager, new UserManager(), customer))
        );
    }

    private void refreshTable(){
        model.setRowCount(0);
        for(Appointment a : appointmentManager.getAppointments()){
            if(a.getCustomerName().equals(customer.getUsername())){
                model.addRow(new Object[]{
                        a.getBarberName(),
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
