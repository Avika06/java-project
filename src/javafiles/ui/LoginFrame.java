package javafiles.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javafiles.User.User;
import javafiles.services.OTPService;
import javafiles.services.VotingSystem;

public class LoginFrame extends JFrame implements ActionListener {

    private JTextField emailField;
    private JPasswordField passField;
    private JButton loginBtn;
    private JButton registerBtn;
    private JButton adminBtn;

    public LoginFrame() {
        setTitle("Secure Voting System");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(null);
        mainPanel.setBackground(new Color(245, 247, 250));

        JLabel title = new JLabel("Secure Voting System");
        title.setFont(new Font("Arial", Font.BOLD, 26));
        title.setForeground(new Color(44, 62, 80));
        title.setBounds(120, 25, 300, 35);
        mainPanel.add(title);

        JLabel subtitle = new JLabel("Login to continue");
        subtitle.setFont(new Font("Arial", Font.PLAIN, 15));
        subtitle.setForeground(new Color(110, 110, 110));
        subtitle.setBounds(175, 60, 160, 20);
        mainPanel.add(subtitle);

        JPanel formPanel = createCardPanel(70, 100, 340, 220);
        mainPanel.add(formPanel);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setFont(new Font("Arial", Font.BOLD, 14));
        emailLabel.setBounds(30, 20, 100, 20);
        formPanel.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(30, 45, 280, 32);
        emailField.setFont(new Font("Arial", Font.PLAIN, 14));
        formPanel.add(emailField);

        JLabel passLabel = new JLabel("Password");
        passLabel.setFont(new Font("Arial", Font.BOLD, 14));
        passLabel.setBounds(30, 90, 100, 20);
        formPanel.add(passLabel);

        passField = new JPasswordField();
        passField.setBounds(30, 115, 280, 32);
        passField.setFont(new Font("Arial", Font.PLAIN, 14));
        formPanel.add(passField);

        loginBtn = createButton("Login", new Color(52, 152, 219));
        loginBtn.setBounds(20, 170, 90, 32);
        loginBtn.addActionListener(this);
        formPanel.add(loginBtn);

        registerBtn = createButton("Register", new Color(46, 204, 113));
        registerBtn.setBounds(125, 170, 95, 32);
        registerBtn.addActionListener(this);
        formPanel.add(registerBtn);

        adminBtn = createButton("Admin", new Color(155, 89, 182));
        adminBtn.setBounds(235, 170, 75, 32);
        adminBtn.addActionListener(this);
        formPanel.add(adminBtn);

        add(mainPanel);
        setVisible(true);
    }

    private JPanel createCardPanel(int x, int y, int w, int h) {
        JPanel panel = new JPanel(null);
        panel.setBounds(x, y, w, h);
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));
        return panel;
    }

    private JButton createButton(String text, Color color) {
        JButton btn = new JButton(text);
        btn.setFocusPainted(false);
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Arial", Font.BOLD, 13));
        btn.setBorder(BorderFactory.createEmptyBorder());
        return btn;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerBtn) {
            new RegisterFrame();
            return;
        }

        if (e.getSource() == adminBtn) {
            new AdminLoginFrame();
            return;
        }

        String email = emailField.getText().trim();
        String password = new String(passField.getPassword()).trim();

        User user = VotingSystem.login(email, password);

        if (user == null) {
            JOptionPane.showMessageDialog(this, "Invalid email or password");
            return;
        }

        int otp = OTPService.generateOTP();
        JOptionPane.showMessageDialog(this, "Your OTP is: " + otp);

        String input = JOptionPane.showInputDialog(this, "Enter OTP:");
        if (input == null || input.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "OTP cancelled");
            return;
        }

        try {
            int enteredOtp = Integer.parseInt(input);

            if (VotingSystem.verifyUser(user, enteredOtp, otp)) {
                JOptionPane.showMessageDialog(this, "OTP verified successfully");
                new VoteFrame(user);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Wrong OTP");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Enter numeric OTP only");
        }
    }
}