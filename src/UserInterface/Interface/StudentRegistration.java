package UserInterface.Interface;

import Database.Student;
import Database.StudentDatabase;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.List;

public class StudentRegistration extends JPanel {
    private JTable studentTable;
    private JScrollPane scrollPane;
    private TableRowSorter<StudentTableModel> rowSorter;
    private StudentTableModel studentTableModel;
    private JTextField nameField, idField, ageField, classField, contactField;

    public StudentRegistration() {
        setLayout(new BorderLayout());

        // Create a panel for the form
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Add form fields with prompt texts
        formPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        nameField.setUI(new PromptTextFieldUI("Enter name"));
        formPanel.add(nameField);

        formPanel.add(new JLabel("ID:"));
        idField = new JTextField();
        idField.setUI(new PromptTextFieldUI("Enter ID"));
        formPanel.add(idField);

        formPanel.add(new JLabel("Age:"));
        ageField = new JTextField();
        ageField.setUI(new PromptTextFieldUI("Enter age"));
        formPanel.add(ageField);

        formPanel.add(new JLabel("Class:"));
        classField = new JTextField();
        classField.setUI(new PromptTextFieldUI("Enter class"));
        formPanel.add(classField);

        formPanel.add(new JLabel("Contact Info:"));
        contactField = new JTextField();
        contactField.setUI(new PromptTextFieldUI("Enter contact info"));
        formPanel.add(contactField);

        // Add save button
        JButton saveButton = new JButton("Save");
        saveButton.setBackground(Color.GREEN);
        saveButton.setForeground(Color.WHITE);
        saveButton.addActionListener(e -> {
            String name = nameField.getText();
            String id = idField.getText();
            String age = ageField.getText();
            String className = classField.getText();
            String contact = contactField.getText();

            // Check if all fields are filled
            if (name.isEmpty() || id.isEmpty() || age.isEmpty() || className.isEmpty() || contact.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields.");
            } else {
                // Insert student data into the database
                StudentDatabase studentDatabase = new StudentDatabase();
                boolean isInserted = studentDatabase.insertStudent(name, id, age, className, contact);

                if (isInserted) {
                    JOptionPane.showMessageDialog(this, "Student Registered: " + name + ", ID: " + id + ", Age: " + age + ", Class: " + className + ", Contact: " + contact);
                    // Clear the text fields
                    nameField.setText("");
                    idField.setText("");
                    ageField.setText("");
                    classField.setText("");
                    contactField.setText("");
                    refreshTable();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to register student.");
                }
            }
        });

        // Add delete button
        JButton deleteButton = new JButton("Delete");
        deleteButton.setBackground(Color.RED);
        deleteButton.setForeground(Color.WHITE);
        deleteButton.addActionListener(e -> {
            int selectedRow = studentTable.getSelectedRow();
            if (selectedRow != -1) {
                String studentId = (String) studentTable.getValueAt(selectedRow, 1);
                StudentDatabase studentDatabase = new StudentDatabase();
                boolean isDeleted = studentDatabase.deleteStudent(studentId);

                if (isDeleted) {
                    JOptionPane.showMessageDialog(this, "Student Deleted: ID: " + studentId);
                    refreshTable();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to delete student.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a student to delete.");
            }
        });

        // Add update button
        JButton updateButton = new JButton("Update");
        updateButton.setBackground(Color.ORANGE);
        updateButton.setForeground(Color.WHITE);
        updateButton.addActionListener(e -> {
            int selectedRow = studentTable.getSelectedRow();
            if (selectedRow != -1) {
                String name = nameField.getText();
                String id = idField.getText();
                String age = ageField.getText();
                String className = classField.getText();
                String contact = contactField.getText();

                // Check if all fields are filled
                if (name.isEmpty() || id.isEmpty() || age.isEmpty() || className.isEmpty() || contact.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill all fields.");
                } else {
                    StudentDatabase studentDatabase = new StudentDatabase();
                    boolean isUpdated = studentDatabase.updateStudent(name, id, age, className, contact);

                    if (isUpdated) {
                        JOptionPane.showMessageDialog(this, "Student Updated: " + name + ", ID: " + id + ", Age: " + age + ", Class: " + className + ", Contact: " + contact);
                        refreshTable();
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to update student.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a student to update.");
            }
        });

        // Add back button
        JButton backButton = new JButton("Back");
        backButton.setBackground(Color.RED);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> {
            // Handle back button action (e.g., switch to the previous panel)
            CardLayout cardLayout = (CardLayout) getParent().getLayout();
            cardLayout.show(getParent(), "Home");
        });

        // Create a panel for buttons and search bar
        JPanel buttonPanel = new JPanel(new BorderLayout());
        JPanel leftButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftButtonPanel.add(backButton);
        leftButtonPanel.add(saveButton);
        leftButtonPanel.add(updateButton);
        leftButtonPanel.add(deleteButton);

        // Add the search bar
        SearchBar searchBar = new SearchBar(20);
        searchBar.addActionListener(e -> filterTable(searchBar.getText()));

        buttonPanel.add(leftButtonPanel, BorderLayout.WEST);
        buttonPanel.add(searchBar, BorderLayout.SOUTH);

        // Add table to display student data
        studentTableModel = new StudentTableModel(new StudentDatabase().fetchStudents());
        studentTable = new JTable(studentTableModel);
        rowSorter = new TableRowSorter<>(studentTableModel);
        studentTable.setRowSorter(rowSorter);
        scrollPane = new JScrollPane(studentTable);
        scrollPane.setPreferredSize(new Dimension(800, 400)); // Set preferred size to fit the frame
        add(scrollPane, BorderLayout.SOUTH);

        add(formPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        // Add ListSelectionListener to the table
        studentTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting() && studentTable.getSelectedRow() != -1) {
                    int selectedRow = studentTable.getSelectedRow();
                    nameField.setText((String) studentTable.getValueAt(selectedRow, 0));
                    idField.setText((String) studentTable.getValueAt(selectedRow, 1));
                    ageField.setText((String) studentTable.getValueAt(selectedRow, 2));
                    classField.setText((String) studentTable.getValueAt(selectedRow, 3));
                    contactField.setText((String) studentTable.getValueAt(selectedRow, 4));
                }
            }
        });
    }

    private void refreshTable() {
        // Refresh the table data
        studentTableModel.setStudents(new StudentDatabase().fetchStudents());
        rowSorter.setModel(studentTableModel);
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

    private static class StudentTableModel extends AbstractTableModel {
        private final String[] columnNames = { "Name", "ID", "Age", "Class", "Contact Info" };
        private List<Student> students;

        public StudentTableModel(List<Student> students) {
            this.students = students;
        }

        public void setStudents(List<Student> students) {
            this.students = students;
            fireTableDataChanged();
        }

        @Override
        public int getRowCount() {
            return students.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Student student = students.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return student.getName();
                case 1:
                    return student.getId();
                case 2:
                    return student.getAge();
                case 3:
                    return student.getStudentClass();
                case 4:
                    return student.getContactInfo();
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