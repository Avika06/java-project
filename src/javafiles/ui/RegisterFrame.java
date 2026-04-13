package javafiles.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javafiles.services.VotingSystem;

public class RegisterFrame extends JFrame implements ActionListener {

    private JTextField nameField;
    private JTextField emailField;
    private JPasswordField passField;
    private JButton registerBtn;

    public RegisterFrame() {
        setTitle("Register");
        setSize(500, 420);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(null);
        mainPanel.setBackground(new Color(245, 247, 250));

        JLabel title = new JLabel("Create Account");
        title.setFont(new Font("Arial", Font.BOLD, 26));
        title.setForeground(new Color(44, 62, 80));
        title.setBounds(150, 25, 220, 35);
        mainPanel.add(title);

        JPanel formPanel = new JPanel(null);
        formPanel.setBounds(70, 90, 340, 240);
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));
        mainPanel.add(formPanel);

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        nameLabel.setBounds(30, 20, 100, 20);
        formPanel.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(30, 45, 280, 32);
        nameField.setFont(new Font("Arial", Font.PLAIN, 14));
        formPanel.add(nameField);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setFont(new Font("Arial", Font.BOLD, 14));
        emailLabel.setBounds(30, 85, 100, 20);
        formPanel.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(30, 110, 280, 32);
        emailField.setFont(new Font("Arial", Font.PLAIN, 14));
        formPanel.add(emailField);

        JLabel passLabel = new JLabel("Password");
        passLabel.setFont(new Font("Arial", Font.BOLD, 14));
        passLabel.setBounds(30, 150, 100, 20);
        formPanel.add(passLabel);

        passField = new JPasswordField();
        passField.setBounds(30, 175, 280, 32);
        passField.setFont(new Font("Arial", Font.PLAIN, 14));
        formPanel.add(passField);

        registerBtn = new JButton("Register");
        registerBtn.setBounds(115, 215, 110, 32);
        registerBtn.setBackground(new Color(46, 204, 113));
        registerBtn.setForeground(Color.WHITE);
        registerBtn.setFont(new Font("Arial", Font.BOLD, 13));
        registerBtn.setFocusPainted(false);
        registerBtn.setBorder(BorderFactory.createEmptyBorder());
        registerBtn.addActionListener(this);
        formPanel.add(registerBtn);

        add(mainPanel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String password = new String(passField.getPassword()).trim();

        boolean success = VotingSystem.register(name, email, password);

        if (success) {
            JOptionPane.showMessageDialog(this, "Registration successful");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Registration failed");
        }
    }
}