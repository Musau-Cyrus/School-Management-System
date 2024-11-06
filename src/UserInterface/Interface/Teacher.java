package UserInterface.Interface;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Teacher {
    private String name;
    private String id;
    private String email;
    private String phoneNumber;
    private String classInCharge;

    public Teacher() {
        this.name = name;
        this.id = id;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.classInCharge = classInCharge;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getClassInCharge() {
        return classInCharge;
    }

    public void setClassInCharge(String classInCharge) {
        this.classInCharge = classInCharge;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", classInCharge='" + classInCharge + '\'' +
                '}';
    }

    public static class TeacherTableModel extends AbstractTableModel {
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

    public class TeacherManagementPanel extends JPanel {
        private List<Teacher> teachers;
        private JTable teacherTable;
        private TeacherTableModel teacherTableModel;

        public TeacherManagementPanel() {
            setLayout(new BorderLayout());

            teachers = new ArrayList<>();
            teacherTableModel = new TeacherTableModel(teachers);
            teacherTable = new JTable(teacherTableModel);

            // Create form panel
            JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
            formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            JTextField nameField = new JTextField();
            JTextField idField = new JTextField();
            JTextField emailField = new JTextField();
            JTextField phoneNumberField = new JTextField();
            JTextField classInChargeField = new JTextField();

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

                    Teacher teacher = new Teacher();
                    teachers.add(teacher);
                    teacherTableModel.fireTableDataChanged();

                    nameField.setText("");
                    idField.setText("");
                    emailField.setText("");
                    phoneNumberField.setText("");
                    classInChargeField.setText("");
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
    }
}