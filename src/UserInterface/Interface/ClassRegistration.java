package UserInterface.Interface;

import Database.ClassDatabase;
import Database.ClassDetails;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.List;

public class ClassRegistration extends JPanel {
    private JTable classTable;
    private JScrollPane scrollPane;
    private TableRowSorter<ClassTableModel> rowSorter;
    private JTextField classIdField, classNameField, teacherInchargeField;

    public ClassRegistration() {
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        formPanel.add(new JLabel("Class ID:"));
        classIdField = new JTextField();
        formPanel.add(classIdField);

        formPanel.add(new JLabel("Class Name:"));
        classNameField = new JTextField();
        formPanel.add(classNameField);

        formPanel.add(new JLabel("Teacher Incharge:"));
        teacherInchargeField = new JTextField();
        formPanel.add(teacherInchargeField);

        JPanel buttonPanel = new JPanel(new BorderLayout());
        JPanel leftButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

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

        JButton updateButton = new JButton("Update");
        updateButton.setBackground(Color.ORANGE);
        updateButton.setForeground(Color.WHITE);
        updateButton.addActionListener(e -> {
            int selectedRow = classTable.getSelectedRow();
            if (selectedRow != -1) {
                String classId = classIdField.getText();
                String className = classNameField.getText();
                String teacherIncharge = teacherInchargeField.getText();

                if (classId.isEmpty() || className.isEmpty() || teacherIncharge.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill all fields.");
                } else {
                    ClassDatabase classDatabase = new ClassDatabase();
                    boolean isUpdated = classDatabase.updateClass(classId, className, teacherIncharge);

                    if (isUpdated) {
                        JOptionPane.showMessageDialog(this, "Class Updated: " + className + ", ID: " + classId + ", Teacher Incharge: " + teacherIncharge);
                        refreshTable();
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to update class.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a class to update.");
            }
        });

        JButton deleteButton = new JButton("Delete");
        deleteButton.setBackground(Color.RED);
        deleteButton.setForeground(Color.WHITE);
        deleteButton.addActionListener(e -> {
            int selectedRow = classTable.getSelectedRow();
            if (selectedRow != -1) {
                String classId = (String) classTable.getValueAt(selectedRow, 0);
                ClassDatabase classDatabase = new ClassDatabase();
                boolean isDeleted = classDatabase.deleteClass(classId);

                if (isDeleted) {
                    JOptionPane.showMessageDialog(this, "Class Deleted: ID: " + classId);
                    refreshTable();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to delete class.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a class to delete.");
            }
        });

        JButton backButton = new JButton("Back");
        backButton.setBackground(Color.RED);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> {
            CardLayout cardLayout = (CardLayout) getParent().getLayout();
            cardLayout.show(getParent(), "Home");
        });

        leftButtonPanel.add(backButton);
        leftButtonPanel.add(submitButton);
        leftButtonPanel.add(updateButton);
        leftButtonPanel.add(deleteButton);

        SearchBar searchBar = new SearchBar(20);
        searchBar.addActionListener(e -> filterTable(searchBar.getText()));

        buttonPanel.add(leftButtonPanel, BorderLayout.WEST);
        buttonPanel.add(searchBar, BorderLayout.SOUTH);

        add(formPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        classTable = new JTable(new ClassTableModel(new ClassDatabase().fetchClasses()));
        rowSorter = new TableRowSorter<>((ClassTableModel) classTable.getModel());
        classTable.setRowSorter(rowSorter);
        scrollPane = new JScrollPane(classTable);
        scrollPane.setPreferredSize(new Dimension(800, 400));
        add(scrollPane, BorderLayout.SOUTH);

        classTable.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting() && classTable.getSelectedRow() != -1) {
                int selectedRow = classTable.getSelectedRow();
                classIdField.setText((String) classTable.getValueAt(selectedRow, 0));
                classNameField.setText((String) classTable.getValueAt(selectedRow, 1));
                teacherInchargeField.setText((String) classTable.getValueAt(selectedRow, 2));
            }
        });
    }

    private void refreshTable() {
        classTable.setModel(new ClassTableModel(new ClassDatabase().fetchClasses()));
        rowSorter.setModel((ClassTableModel) classTable.getModel());
        revalidate();
        repaint();
    }

    private void filterTable(String searchText) {
        if (searchText.trim().length() == 0) {
            rowSorter.setRowFilter(null);
        } else {
            rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
        }
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