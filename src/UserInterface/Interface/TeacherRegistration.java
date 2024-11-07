package UserInterface.Interface;

import Database.TeacherDatabase;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TeacherRegistration extends JPanel {
    private List<Teacher> teachers;
    private JTable teacherTable;
    private TeacherTableModel teacherTableModel;
    private JTextField nameField, idField, emailField, phoneNumberField, classInChargeField;

    public TeacherRegistration() {
        setLayout(new BorderLayout());

        teachers = new ArrayList<>();
        teacherTableModel = new TeacherTableModel(teachers);
        teacherTable = new JTable(teacherTableModel);

        // Create form panel
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        nameField = new JTextField();
        idField = new JTextField();
        emailField = new JTextField();
        phoneNumberField = new JTextField();
        classInChargeField = new JTextField();

        formPanel.add(new JLabel("Name:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("ID:"));
        formPanel.add(idField);
        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailField);
        formPanel.add(new JLabel("Phone Number:"));
        formPanel.add(phoneNumberField);
        formPanel.add(new JLabel("Class In Charge:"));
        formPanel.add(classInChargeField);

        JButton addButton = new JButton("Add Teacher");
        addButton.setBackground(Color.GREEN);
        addButton.setForeground(Color.WHITE);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String id = idField.getText();
                String email = emailField.getText();
                String phoneNumber = phoneNumberField.getText();
                String classInCharge = classInChargeField.getText();

                if (name.isEmpty() || id.isEmpty() || email.isEmpty() || phoneNumber.isEmpty() || classInCharge.isEmpty()) {
                    JOptionPane.showMessageDialog(TeacherRegistration.this, "Please fill all fields.");
                } else {
                    TeacherDatabase teacherDatabase = new TeacherDatabase();
                    boolean isInserted = teacherDatabase.insertTeacher(name, id, email, phoneNumber, classInCharge);

                    if (isInserted) {
                        Teacher teacher = new Teacher(name, id, email, phoneNumber, classInCharge);
                        teachers.add(teacher);
                        teacherTableModel.fireTableDataChanged();

                        nameField.setText("");
                        idField.setText("");
                        emailField.setText("");
                        phoneNumberField.setText("");
                        classInChargeField.setText("");

                        JOptionPane.showMessageDialog(TeacherRegistration.this, "Teacher added successfully.");
                    } else {
                        JOptionPane.showMessageDialog(TeacherRegistration.this, "Failed to add teacher.");
                    }
                }
            }
        });

        formPanel.add(new JLabel());
        formPanel.add(addButton);

        // Create table panel
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        tablePanel.add(new JScrollPane(teacherTable), BorderLayout.CENTER);

        // Add panels to main panel
        add(formPanel, BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);
    }

    private static class TeacherTableModel extends AbstractTableModel {
        private final List<Teacher> teachers;
        private final String[] columnNames = {"Name", "ID", "Email", "Phone Number", "Class In Charge"};

        public TeacherTableModel(List<Teacher> teachers) {
            this.teachers = teachers;
        }

        @Override
        public int getRowCount() {
            return teachers.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Teacher teacher = teachers.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return teacher.getName();
                case 1:
                    return teacher.getId();
                case 2:
                    return teacher.getEmail();
                case 3:
                    return teacher.getPhoneNumber();
                case 4:
                    return teacher.getClassInCharge();
                default:
                    return null;
            }
        }

        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }
    }

    public static class Teacher {
        private String name;
        private String id;
        private String email;
        private String phoneNumber;
        private String classInCharge;

        public Teacher(String name, String id, String email, String phoneNumber, String classInCharge) {
            this.name = name;
            this.id = id;
            this.email = email;
            this.phoneNumber = phoneNumber;
            this.classInCharge = classInCharge;
        }

        public String getName() {
            return name;
        }

        public String getId() {
            return id;
        }

        public String getEmail() {
            return email;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public String getClassInCharge() {
            return classInCharge;
        }
    }
}