package UserInterface;

import UserInterface.Interface.Calender;
import UserInterface.Interface.ClassRegistration;
import UserInterface.Interface.StudentRegistration;
import UserInterface.Interface.Teacher;

import javax.swing.*;
import java.awt.*;

public class AdminDashboard {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("Admin Dashboard");
        frame.setSize(800, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the left sidebar
        JPanel sidebar = new JPanel(new GridBagLayout());
        sidebar.setBackground(new Color(0x2196F3)); // Teal color
        sidebar.setPreferredSize(new Dimension(225, 600));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS)); // Use BoxLayout for vertical alignment

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;


        // Add Icons
        ImageIcon icon = new ImageIcon("C:/Users/Administrator/IdeaProjects/School Management System/src/icons/study.png");
        Image scaledIcon = icon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledIcon);

        // Add school icon
        //JPanel dashboardPanel = new JPanel(new BorderLayout());
        ImageIcon icon2 = new ImageIcon("C:/Users/Administrator/IdeaProjects/School Management System/src/icons/school.png");
        Image scaledIcon2 = icon2.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon2 = new ImageIcon(scaledIcon2);
        JLabel dashboard = new JLabel("Dashboard");
        dashboard.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
        sidebar.add(new JLabel(resizedIcon2), gbc);
        gbc.gridy++;
        sidebar.add(dashboard, gbc);


        // Student Registration Button
        JButton btn1 = createStyledButton("STUDENT", resizedIcon);
        btn1.setPreferredSize(new Dimension(50, 50));
        btn1.setBorderPainted(false);
        btn1.setFocusPainted(false);
        btn1.setToolTipText("Click to Register new Student");
        gbc.gridy++;
        sidebar.add(btn1, gbc);


        // Class Records
        ImageIcon classIcon = new ImageIcon("C:/Users/Administrator/IdeaProjects/School Management System/src/icons/student.png");
        Image scaledClassIcon = classIcon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        ImageIcon resizedClassIcon = new ImageIcon(scaledClassIcon);

        JButton btnClasses = createStyledButton("CLASSES ", resizedClassIcon);
        btnClasses.setPreferredSize(new Dimension(50, 50));
        btnClasses.setBorderPainted(false);
        btnClasses.setFocusPainted(false);
        btnClasses.setToolTipText("Click to Create and manage classes");
        gbc.gridy++;
        sidebar .add(btnClasses, gbc);

        ImageIcon teacherIcon = new ImageIcon("C:/Users/Administrator/IdeaProjects/School Management System/src/icons/presentation.png");
        Image scaledTeacherIcon = teacherIcon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        ImageIcon resizedTeacherIcon = new ImageIcon(scaledTeacherIcon);

        gbc.gridy++;
        JButton btnTeachers = createStyledButton("TEACHERS", resizedTeacherIcon);
        btnTeachers.setBorderPainted(false);
        btnTeachers.setFocusPainted(false);
        btnTeachers.setToolTipText("Click to Create and manage Teachers");
        sidebar.add(btnTeachers, gbc);

        gbc.gridy++;
        ImageIcon calenderIcon = new ImageIcon("C:/Users/Administrator/IdeaProjects/School Management System/src/icons/calender.png");
        Image scaledCalenderIcon = calenderIcon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        ImageIcon resizedCalenderIcon = new ImageIcon(scaledCalenderIcon);
        JButton calender = createStyledButton("CALENDER", resizedCalenderIcon);
        calender.setBorderPainted(false);
        calender.setFocusPainted(false);
        calender.setToolTipText("Click to Create and manage Teachers");
        sidebar.add(calender, gbc);

        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);
        JPanel contentPanel = new JPanel(new BorderLayout());

        JLabel l = new JLabel("Administrator panel");
        l.setFont(new Font("Papyrus", Font.BOLD, 20));
        contentPanel.add(l, BorderLayout.NORTH);

        JPanel panelContents = new JPanel();
        panelContents.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));

        // Student Registration and records update panel
        // Load and resize Student icon
        ImageIcon originalIcon = new ImageIcon("C:/Users/Administrator/IdeaProjects/School Management System/src/icons/profile.png");
        ImageIcon iconStudent = resizeIcon(originalIcon, 75, 75);
        // Create Student Button
        JButton btnStudents = createStyledButton("STUDENTS", iconStudent);
        btnStudents.addActionListener(e -> cardLayout.show(mainPanel, "Student"));
        btn1.addActionListener(e -> cardLayout.show(mainPanel, "Student"));

        StudentRegistration studentRegistration = new StudentRegistration();

        // Teacher registration Section
        // Load and resize teacher icon
        ImageIcon originalIcon1 = new ImageIcon("C:/Users/Administrator/IdeaProjects/School Management System/src/icons/teacher.png");
        ImageIcon iconTeachers = resizeIcon(originalIcon1, 75, 75);

        JButton btnTeacher = createStyledButton("TEACHERS", iconTeachers);
        btnTeacher.addActionListener(e -> cardLayout.show(mainPanel, "Teacher"));


        // Class section
        // Load and resize Class icon
        ImageIcon originalIcon2 = new ImageIcon("Users/Administrator/IdeaProjects/School Management System/src/icons/class .png");
        ImageIcon iconClasses = resizeIcon(originalIcon2, 75, 75);
        // Create Class button
        JButton btnClass = createStyledButton("CLASSES", iconClasses);
        btnClasses.addActionListener(e -> cardLayout.show(mainPanel, "Class"));
        btnClass.addActionListener(e -> cardLayout.show(mainPanel, "Class"));

        ClassRegistration classRegistration = new ClassRegistration();

        // Load and resize Class icon
        ImageIcon originalIcon3 = new ImageIcon("C:/Users/Administrator/IdeaProjects/School Management System/src/icons/calender.png");
        ImageIcon iconCalender = resizeIcon(originalIcon3, 75, 75);
        // Create Class button
        JButton btnCalender = createStyledButton("CALENDER", iconCalender);
        btnCalender.addActionListener(e -> cardLayout.show(mainPanel, "Calender"));
        calender.addActionListener(e -> cardLayout.show(mainPanel, "Calender"));
        Calender calenderPanel = new Calender();

        panelContents.add(btnStudents);
        panelContents.add(btnTeacher);
        panelContents.add(btnClass);
        panelContents.add(btnCalender);

        contentPanel.add(panelContents);
        mainPanel.add(contentPanel, "Home");
        mainPanel.add(studentRegistration, "Student");
       // mainPanel.add(teacherPanel, "Teacher");
        mainPanel.add(classRegistration, "Class");
        mainPanel.add(calenderPanel, "Calender");

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