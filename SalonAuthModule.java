package com.domebarbershop;

import javax.swing.*;
import java.awt.*;

public class SalonAuthModule extends JFrame {
    private UserManager userManager;
    private AppointmentManager appointmentManager;

    public SalonAuthModule(){
        userManager = new UserManager();
        appointmentManager = new AppointmentManager();

        setTitle("Dome Barber Shop - Login");
        setSize(450,350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.DARK_GRAY);
        setLayout(null);

        JLabel lbl = new JLabel("Login");
        lbl.setBounds(170,20,120,30);
        lbl.setFont(new Font("Arial",Font.BOLD,20));
        lbl.setForeground(Color.WHITE);
        add(lbl);

        JLabel lblUser = new JLabel("Username:");
        lblUser.setBounds(50,70,100,25);
        lblUser.setForeground(Color.WHITE);
        add(lblUser);
        JTextField txtUser = new JTextField();
        txtUser.setBounds(150,70,200,25);
        add(txtUser);

        JLabel lblPass = new JLabel("Password:");
        lblPass.setBounds(50,110,100,25);
        lblPass.setForeground(Color.WHITE);
        add(lblPass);
        JPasswordField txtPass = new JPasswordField();
        txtPass.setBounds(150,110,200,25);
        add(txtPass);

        JLabel lblRole = new JLabel("Role:");
        lblRole.setBounds(50,150,100,25);
        lblRole.setForeground(Color.WHITE);
        add(lblRole);
        String[] roles = {"Customer","Barber","Admin"};
        JComboBox<String> cmbRole = new JComboBox<>(roles);
        cmbRole.setBounds(150,150,200,25);
        add(cmbRole);

        JButton btnLogin = new JButton("Login");
        JButton btnRegister = new JButton("Register");
        btnLogin.setBounds(80,200,120,30);
        btnRegister.setBounds(220,200,120,30);
        add(btnLogin);
        add(btnRegister);

        btnLogin.addActionListener(e -> {
            String username = txtUser.getText();
            String password = new String(txtPass.getPassword());
            String role = (String)cmbRole.getSelectedItem();
            User u = userManager.login(username,password,role);
            if(u==null) JOptionPane.showMessageDialog(this,"Invalid credentials!");
            else{
                switch(role){
                    case "Customer":
                        NavigationHelper.goToAndDispose(this,new Dashboard(appointmentManager,userManager,u));
                        break;
                    case "Barber":
                        NavigationHelper.goToAndDispose(this,new BarberGUI(appointmentManager,u));
                        break;
                    case "Admin":
                        NavigationHelper.goToAndDispose(this,new AdminGUI(appointmentManager,userManager));
                        break;
                }
            }
        });

        btnRegister.addActionListener(e -> {
            String username = txtUser.getText();
            String password = new String(txtPass.getPassword());
            if(username.isEmpty() || password.isEmpty()){
                JOptionPane.showMessageDialog(this,"Please fill username and password!");
                return;
            }
            User u = new User(username,password,"Customer");
            boolean success = userManager.register(u);
            if(success) JOptionPane.showMessageDialog(this,"Registered successfully!");
            else JOptionPane.showMessageDialog(this,"Username exists!");
        });
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> new SalonAuthModule().setVisible(true));
    }
}
