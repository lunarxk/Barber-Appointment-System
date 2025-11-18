package com.domebarbershop;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AdminGUI extends JFrame {
    private AppointmentManager appointmentManager;
    private UserManager userManager;
    private JTable table;
    private DefaultTableModel model;

    public AdminGUI(AppointmentManager am, UserManager um){
        this.appointmentManager = am;
        this.userManager = um;

        setTitle("Admin Dashboard - Dome Barber Shop");
        setSize(750,500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel lbl = new JLabel("All Appointments", JLabel.CENTER);
        lbl.setFont(new Font("Arial", Font.BOLD, 22));
        add(lbl, BorderLayout.NORTH);

        model = new DefaultTableModel(new Object[]{"Customer","Barber","Service","Date","Time","Payment","Detail"},0);
        table = new JTable(model);
        refreshTable();
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel panel = new JPanel();
        JButton btnRefresh = new JButton("Refresh");
        JButton btnDelete = new JButton("Delete Selected");
        JButton btnPrint = new JButton("Print Report");
        JButton btnBack = new JButton("Logout");

        panel.add(btnRefresh);
        panel.add(btnDelete);
        panel.add(btnPrint);
        panel.add(btnBack);

        add(panel, BorderLayout.SOUTH);

        btnRefresh.addActionListener(e -> refreshTable());

        btnDelete.addActionListener(e -> {
            int selected = table.getSelectedRow();
            if(selected >= 0){
                Appointment a = appointmentManager.getAppointments().get(selected);
                appointmentManager.removeAppointment(a);
                refreshTable();
            } else {
                JOptionPane.showMessageDialog(this,"Please select an appointment to delete!");
            }
        });

        btnPrint.addActionListener(e -> {
            try{ table.print(); }
            catch(Exception ex){ JOptionPane.showMessageDialog(this,"Printing failed: "+ex.getMessage()); }
        });

        btnBack.addActionListener(e -> NavigationHelper.goToAndDispose(this, new SalonAuthModule()));
    }

    private void refreshTable(){
        model.setRowCount(0);
        for(Appointment a : appointmentManager.getAppointments()){
            model.addRow(new Object[]{
                    a.getCustomerName(),
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
