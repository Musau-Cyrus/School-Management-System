package UserInterface;

import Database.LoginDatabase;

import javax.swing.*;
import java.awt.*;

public class LogIn {

    public static void main(String[] args) {
        JFrame frame = new JFrame("LogIn page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();

        // Set the text to the left and expand it Horizontally
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel welcome = new JLabel("Welcome");
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.ipadx = 10;
        gbc.ipady = 50;
        panel.add(welcome, gbc);

        JLabel roleLabel = new JLabel("Role:");
        String[] roles = {"--Select Role--", "Teacher", "Admin"};
        JComboBox<String> roleComboBox = new JComboBox<>(roles);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(roleLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.ipady = 10;
        gbc.ipadx = 10;
        gbc.insets = new Insets(0, 0, 10, 10);
        panel.add(roleComboBox, gbc);

        JLabel userLabel = new JLabel("Username:");
        JTextField userText = new JTextField();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.ipadx = 10;
        gbc.ipady = 10;
        panel.add(userLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.ipadx = 10;
        gbc.ipady = 10;
        panel.add(userText, gbc);

        JLabel passLabel = new JLabel("Password:");
        JPasswordField passText = new JPasswordField();
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(passLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(passText, gbc);

        JButton b1 = new JButton("Login");
        gbc.gridx = 1;
        gbc.gridy = 5;
        panel.add(b1, gbc);
        b1.setToolTipText("Click to login");
        b1.setFont(new Font("Arial", Font.BOLD, 16));
        b1.setForeground(Color.RED);
        b1.setBackground(new Color(0, 102, 204));

        // Add action listener to the login button
        b1.addActionListener(e -> {
            String selectedRole = (String) roleComboBox.getSelectedItem();
            String username = userText.getText();
            String password = new String(passText.getPassword());

            LoginDatabase loginDatabase = new LoginDatabase();
            boolean isValid = loginDatabase.validateUser(username, password, selectedRole);

            if (isValid) {
                if ("Admin".equals(selectedRole)) {
                    frame.dispose(); // Close the login frame
                    AdminDashboard.main(new String[]{}); // Open AdminDashboard
                }
                // Add other role-based navigation if needed
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid credentials. Please try again.");
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }
}