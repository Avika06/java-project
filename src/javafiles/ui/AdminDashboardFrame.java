package javafiles.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javafiles.services.VotingSystem;

public class AdminDashboardFrame extends JFrame implements ActionListener {

    private JButton viewResultsBtn;
    private JButton resetBtn;
    private JButton usersBtn;
    private JButton logoutBtn;

    public AdminDashboardFrame() {
        setTitle("Admin Dashboard");
        setSize(520, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(null);
        mainPanel.setBackground(new Color(245, 247, 250));

        JLabel title = new JLabel("Admin Dashboard");
        title.setFont(new Font("Arial", Font.BOLD, 26));
        title.setForeground(new Color(44, 62, 80));
        title.setBounds(150, 25, 230, 35);
        mainPanel.add(title);

        JPanel box = new JPanel(null);
        box.setBounds(90, 90, 320, 220);
        box.setBackground(Color.WHITE);
        box.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));
        mainPanel.add(box);

        viewResultsBtn = createButton("View Results", new Color(52, 152, 219));
        viewResultsBtn.setBounds(85, 25, 150, 35);
        viewResultsBtn.addActionListener(this);
        box.add(viewResultsBtn);

        resetBtn = createButton("Reset Election", new Color(231, 76, 60));
        resetBtn.setBounds(85, 80, 150, 35);
        resetBtn.addActionListener(this);
        box.add(resetBtn);

        usersBtn = createButton("View Users", new Color(46, 204, 113));
        usersBtn.setBounds(85, 135, 150, 35);
        usersBtn.addActionListener(this);
        box.add(usersBtn);

        logoutBtn = createButton("Logout", new Color(127, 140, 141));
        logoutBtn.setBounds(180, 330, 100, 32);
        logoutBtn.addActionListener(this);
        mainPanel.add(logoutBtn);

        add(mainPanel);
        setVisible(true);
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
        if (e.getSource() == viewResultsBtn) {
            JOptionPane.showMessageDialog(this, VotingSystem.getResults());
        }

        if (e.getSource() == resetBtn) {
            int choice = JOptionPane.showConfirmDialog(
                this,
                "Reset all votes?",
                "Confirm",
                JOptionPane.YES_NO_OPTION
            );

            if (choice == JOptionPane.YES_OPTION) {
                VotingSystem.resetElection();
                JOptionPane.showMessageDialog(this, "Election reset successfully");
            }
        }

        if (e.getSource() == usersBtn) {
            JTextArea area = new JTextArea(VotingSystem.getAllUsers());
            area.setEditable(false);
            area.setFont(new Font("Monospaced", Font.PLAIN, 13));

            JScrollPane pane = new JScrollPane(area);

            JFrame usersFrame = new JFrame("Registered Users");
            usersFrame.setSize(450, 300);
            usersFrame.add(pane);
            usersFrame.setLocationRelativeTo(null);
            usersFrame.setVisible(true);
        }

        if (e.getSource() == logoutBtn) {
            new LoginFrame();
            dispose();
        }
    }
}