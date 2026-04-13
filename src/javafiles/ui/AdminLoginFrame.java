package javafiles.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javafiles.services.VotingSystem;

public class AdminLoginFrame extends JFrame implements ActionListener {

    private JTextField userField;
    private JPasswordField passField;
    private JButton loginBtn;

    public AdminLoginFrame() {
        setTitle("Admin Login");
        setSize(450, 320);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(null);
        mainPanel.setBackground(new Color(245, 247, 250));

        JLabel title = new JLabel("Admin Access");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(new Color(44, 62, 80));
        title.setBounds(145, 25, 180, 30);
        mainPanel.add(title);

        JPanel formPanel = new JPanel(null);
        formPanel.setBounds(60, 85, 310, 160);
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));
        mainPanel.add(formPanel);

        JLabel userLabel = new JLabel("Username");
        userLabel.setFont(new Font("Arial", Font.BOLD, 14));
        userLabel.setBounds(25, 20, 100, 20);
        formPanel.add(userLabel);

        userField = new JTextField();
        userField.setBounds(25, 45, 250, 30);
        formPanel.add(userField);

        JLabel passLabel = new JLabel("Password");
        passLabel.setFont(new Font("Arial", Font.BOLD, 14));
        passLabel.setBounds(25, 80, 100, 20);
        formPanel.add(passLabel);

        passField = new JPasswordField();
        passField.setBounds(25, 105, 250, 30);
        formPanel.add(passField);

        loginBtn = new JButton("Login");
        loginBtn.setBounds(110, 135, 90, 28);
        loginBtn.setBackground(new Color(155, 89, 182));
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFocusPainted(false);
        loginBtn.setBorder(BorderFactory.createEmptyBorder());
        loginBtn.addActionListener(this);
        formPanel.add(loginBtn);

        add(mainPanel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = userField.getText().trim();
        String password = new String(passField.getPassword()).trim();

        if (VotingSystem.adminLogin(username, password)) {
            JOptionPane.showMessageDialog(this, "Admin login successful");
            new AdminDashboardFrame();
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid admin credentials");
        }
    }
}