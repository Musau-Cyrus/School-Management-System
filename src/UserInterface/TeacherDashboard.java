package UserInterface;

import UserInterface.Interface.Calender;

import javax.swing.*;
import java.awt.*;

public class TeacherDashboard {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("Teacher Dashboard");
        frame.setSize(800, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the left sidebar
        JPanel sidebar = new JPanel(new GridBagLayout());
        sidebar.setBackground(new Color(0x2196F3)); // Teal color
        sidebar.setPreferredSize(new Dimension(225, 600));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS)); // Use BoxLayout for vertical alignment

        // Add label "Teacher Dashboard"
        JLabel dashboardLabel = new JLabel("WELCOME");
        dashboardLabel.setFont(new Font("Arial", Font.BOLD, 20));
        dashboardLabel.setForeground(Color.WHITE);
        dashboardLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        sidebar.add(dashboardLabel);

        // Add drop-down buttons to the sidebar
        String[] studentOptions = {"Manage Attendance", "Take Attendance", "View Attendance"};
        JComboBox<String> studentDropdown = new JComboBox<>(studentOptions);
        studentDropdown.setMaximumSize(new Dimension(200, 30));
        sidebar.add(studentDropdown);

        String[] calendarOptions = {"Add Event", "View Calendar"};
        JComboBox<String> calendarDropdown = new JComboBox<>(calendarOptions);
        calendarDropdown.setMaximumSize(new Dimension(200, 30));
        sidebar.add(calendarDropdown);

        // Resize buttons to make them larger
        for (Component component : sidebar.getComponents()) {
            if (component instanceof JComboBox) {
                component.setPreferredSize(new Dimension(200, 50)); // Increase height to 50
            }
        }

        // Create the main panel with CardLayout
        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);
        JPanel contentPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 50));

        ImageIcon originalIcon3 = new ImageIcon("C:/Users/Administrator/IdeaProjects/School Management System/src/icons/calender.png");
        ImageIcon iconCalender = resizeIcon(originalIcon3, 75, 75);
        JButton btnCalender = createStyledButton("CALENDER", iconCalender);
        btnCalender.addActionListener(e -> cardLayout.show(mainPanel, "Calender"));
        mainPanel.add(btnCalender);

        ImageIcon originalIcon = new ImageIcon("C:/Users/Administrator/IdeaProjects/School Management System/src/icons/class.png");
        ImageIcon iconClass = resizeIcon(originalIcon, 75, 75);
        JButton btnClass = createStyledButton("CLASS", iconClass);

        // Add event listener to calendarDropdown
        calendarDropdown.addActionListener(e -> {
            if (calendarDropdown.getSelectedItem().equals("View Calendar")) {
                cardLayout.show(mainPanel, "Calender");
            }
        });

        contentPanel.add(btnClass);
        contentPanel.add(btnCalender);
        Calender calender = new Calender();

        mainPanel.add(contentPanel, "Home");
        mainPanel.add(calender, "Calender");

        // Add sidebar and main panel to the frame
        frame.add(sidebar, BorderLayout.WEST);
        frame.add(mainPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private static ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image image = icon.getImage(); // Get the original image
        Image resizedImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH); // Resize the image
        return new ImageIcon(resizedImage); // Return the resized icon
    }

    private static JButton createStyledButton(String text, ImageIcon icon) {
        JButton button = new JButton(text, icon);
        styleButton(button);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        return button;
    }

    private static void styleButton(JButton button) {
        button.setPreferredSize(new Dimension(120, 120));
        button.setFont(new Font("Arial", Font.BOLD, 14)); // Set font for the text
        button.setFocusPainted(false); // Remove focus outline
        button.setBackground(new Color(255, 255, 255)); // Set background color
        button.setForeground(Color.BLACK); // Set the Button color
    }
}