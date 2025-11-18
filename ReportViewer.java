package com.domebarbershop;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ReportViewer extends JFrame {
    private AppointmentManager appointmentManager;
    private JTable table;
    private DefaultTableModel model;

    public ReportViewer(AppointmentManager am){
        this.appointmentManager = am;
        setTitle("Appointment Report");
        setSize(700,450);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel lbl = new JLabel("All Appointments Report", JLabel.CENTER);
        lbl.setFont(new Font("Arial", Font.BOLD, 22));
        add(lbl, BorderLayout.NORTH);

        model = new DefaultTableModel(new Object[]{"Customer","Barber","Service","Date","Time","Payment","Detail"},0);
        table = new JTable(model);
        refreshTable();
        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton btnPrint = new JButton("Print");
        add(btnPrint, BorderLayout.SOUTH);

        btnPrint.addActionListener(e -> {
            try{ table.print(); }
            catch(Exception ex){ JOptionPane.showMessageDialog(this,"Printing failed: "+ex.getMessage()); }
        });
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
