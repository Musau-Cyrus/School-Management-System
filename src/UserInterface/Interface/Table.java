package UserInterface.Interface;

import Database.Student;
import Database.StudentDatabase;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;

public class Table extends JPanel {
    public Table() {
        setLayout(new BorderLayout());

        StudentDatabase studentDatabase = new StudentDatabase();
        List<Student> students = studentDatabase.fetchStudents();

        JTable table = new JTable(new StudentTableModel(students));
        table.setPreferredScrollableViewportSize(new Dimension(500, 150));

        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(30);
        table.setGridColor(Color.LIGHT_GRAY);
        table.setShowGrid(true);
        table.setSelectionBackground(new Color(0x2196F3));
        table.setSelectionForeground(Color.WHITE);

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 16));
        header.setBackground(new Color(0x2196F3));
        header.setForeground(Color.WHITE);
        header.setReorderingAllowed(false);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(500, 150));
        add(scrollPane, BorderLayout.CENTER);
    }

    private static class StudentTableModel extends AbstractTableModel {
        private final String[] columnNames = { "Name", "ID", "Age", "Class", "Contact Info"};
        private final List<Student> students;

        public StudentTableModel(List<Student> students) {
            this.students = students;
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