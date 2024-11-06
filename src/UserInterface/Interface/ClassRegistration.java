package UserInterface.Interface;

import Database.ClassDatabase;
import Database.ClassDetails;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.List;

public class ClassRegistration extends JPanel {
    private JTable classTable;
    private JScrollPane scrollPane;

    public ClassRegistration() {
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        formPanel.add(new JLabel("Class ID:"));
        JTextField classIdField = new JTextField();
        formPanel.add(classIdField);

        formPanel.add(new JLabel("Class Name:"));
        JTextField classNameField = new JTextField();
        formPanel.add(classNameField);

        formPanel.add(new JLabel("Teacher Incharge:"));
        JTextField teacherInchargeField = new JTextField();
        formPanel.add(teacherInchargeField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton submitButton = new JButton("Save");
        submitButton.setBackground(Color.GREEN);
        submitButton.setForeground(Color.WHITE);
        submitButton.addActionListener(e -> {
            String classId = classIdField.getText();
            String className = classNameField.getText();
            String teacherIncharge = teacherInchargeField.getText();

            if (classId.isEmpty() || className.isEmpty() || teacherIncharge.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields.");
            } else {
                ClassDatabase classDatabase = new ClassDatabase();
                boolean isInserted = classDatabase.insertClass(classId, className, teacherIncharge);

                if (isInserted) {
                    JOptionPane.showMessageDialog(this, "Class Registered: " + className + ", ID: " + classId + ", Teacher Incharge: " + teacherIncharge);
                    classIdField.setText("");
                    classNameField.setText("");
                    teacherInchargeField.setText("");
                    refreshTable();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to register class.");
                }
            }
        });

        JButton backButton = new JButton("Back");
        backButton.setBackground(Color.RED);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> {
            CardLayout cardLayout = (CardLayout) getParent().getLayout();
            cardLayout.show(getParent(), "Home");
        });

        buttonPanel.add(backButton);
        buttonPanel.add(submitButton);

        add(formPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        // Add table to display class data
        classTable = new JTable(new ClassTableModel(new ClassDatabase().fetchClasses()));
        scrollPane = new JScrollPane(classTable);
        scrollPane.setPreferredSize(new Dimension(800, 400)); // Set preferred size to fit the frame
        add(scrollPane, BorderLayout.SOUTH);
    }

    private void refreshTable() {
        // Refresh the table data
        classTable.setModel(new ClassTableModel(new ClassDatabase().fetchClasses()));
        scrollPane.setViewportView(classTable);
        revalidate();
        repaint();
    }

    private static class ClassTableModel extends AbstractTableModel {
        private final String[] columnNames = { "Class ID", "Class Name", "Teacher Incharge" };
        private final List<ClassDetails> classes;

        public ClassTableModel(List<ClassDetails> classes) {
            this.classes = classes;
        }

        @Override
        public int getRowCount() {
            return classes.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            ClassDetails classDetails = classes.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return classDetails.getClassId();
                case 1:
                    return classDetails.getClassName();
                case 2:
                    return classDetails.getTeacherIncharge();
                default:
                    return null;
            }
        }

        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }
    }
}