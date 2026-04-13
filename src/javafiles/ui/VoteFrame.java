package javafiles.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javafiles.User.User;
import javafiles.services.VotingSystem;

public class VoteFrame extends JFrame implements ActionListener {

    private final User currentUser;
    private JRadioButton option1;
    private JRadioButton option2;
    private JButton voteBtn;
    private JButton resultBtn;
    private JButton logoutBtn;

    public VoteFrame(User currentUser) {
        this.currentUser = currentUser;

        setTitle("Vote");
        setSize(520, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(null);
        mainPanel.setBackground(new Color(245, 247, 250));

        JLabel title = new JLabel("Cast Your Vote");
        title.setFont(new Font("Arial", Font.BOLD, 26));
        title.setForeground(new Color(44, 62, 80));
        title.setBounds(170, 20, 220, 35);
        mainPanel.add(title);

        JLabel welcome = new JLabel("Welcome, " + currentUser.getName());
        welcome.setFont(new Font("Arial", Font.PLAIN, 15));
        welcome.setForeground(new Color(100, 100, 100));
        welcome.setBounds(180, 55, 220, 20);
        mainPanel.add(welcome);

        JPanel votePanel = new JPanel(null);
        votePanel.setBounds(70, 100, 360, 190);
        votePanel.setBackground(Color.WHITE);
        votePanel.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));
        mainPanel.add(votePanel);

        JLabel chooseLabel = new JLabel("Choose Candidate");
        chooseLabel.setFont(new Font("Arial", Font.BOLD, 16));
        chooseLabel.setBounds(110, 20, 150, 25);
        votePanel.add(chooseLabel);

        option1 = new JRadioButton("Candidate A");
        option1.setBounds(110, 70, 140, 25);
        option1.setBackground(Color.WHITE);
        option1.setFont(new Font("Arial", Font.PLAIN, 15));

        option2 = new JRadioButton("Candidate B");
        option2.setBounds(110, 105, 140, 25);
        option2.setBackground(Color.WHITE);
        option2.setFont(new Font("Arial", Font.PLAIN, 15));

        ButtonGroup bg = new ButtonGroup();
        bg.add(option1);
        bg.add(option2);

        votePanel.add(option1);
        votePanel.add(option2);

        voteBtn = createButton("Submit Vote", new Color(230, 126, 34));
        voteBtn.setBounds(55, 320, 120, 35);
        voteBtn.addActionListener(this);
        mainPanel.add(voteBtn);

        resultBtn = createButton("View Results", new Color(52, 73, 94));
        resultBtn.setBounds(190, 320, 120, 35);
        resultBtn.addActionListener(this);
        mainPanel.add(resultBtn);

        logoutBtn = createButton("Logout", new Color(231, 76, 60));
        logoutBtn.setBounds(325, 320, 100, 35);
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
        if (e.getSource() == voteBtn) {
            String candidate = null;

            if (option1.isSelected()) {
                candidate = "Candidate A";
            } else if (option2.isSelected()) {
                candidate = "Candidate B";
            }

            if (candidate == null) {
                JOptionPane.showMessageDialog(this, "Select a candidate");
                return;
            }

            String message = VotingSystem.vote(currentUser, candidate);
            JOptionPane.showMessageDialog(this, message);
        }

        if (e.getSource() == resultBtn) {
            JOptionPane.showMessageDialog(this, VotingSystem.getResults());
        }

        if (e.getSource() == logoutBtn) {
            new LoginFrame();
            dispose();
        }
    }
}