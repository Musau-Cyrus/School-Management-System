package UserInterface.Interface;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Calendar;
import java.util.Locale;

public class Calender extends JPanel {
    public Calender() {
        setLayout(new BorderLayout());

        // Create a panel for the top section (month name)
        JPanel topPanel = new JPanel(new BorderLayout());

        // Add month name label
        JLabel monthLabel = new JLabel();
        monthLabel.setFont(new Font("Arial", Font.BOLD, 16));
        monthLabel.setHorizontalAlignment(SwingConstants.CENTER);
        topPanel.add(monthLabel, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);

        String[] columns = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        DefaultTableModel model = new DefaultTableModel(null, columns);
        JTable table = new JTable(model);

        // Customize table appearance
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(30);
        table.setBackground(Color.LIGHT_GRAY);
        table.setGridColor(Color.BLACK);

        // Get the current date
        Calendar today = Calendar.getInstance();
        int currentDay = today.get(Calendar.DAY_OF_MONTH);
        int currentMonth = today.get(Calendar.MONTH);
        int currentYear = today.get(Calendar.YEAR);

        // Set the month name
        monthLabel.setText(today.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()) + " " + currentYear);

        // Custom cell renderer for specific cell appearance
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (value != null && value.equals(currentDay)) { // Highlight current day
                    cell.setBackground(Color.YELLOW);
                } else if (column == 0 || column == 6) { // Highlight weekends
                    cell.setBackground(Color.PINK);
                } else {
                    cell.setBackground(Color.WHITE);
                }
                return cell;
            }
        };
        table.setDefaultRenderer(Object.class, cellRenderer);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        int firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        Object[] week = new Object[7];
        for (int i = 0; i < firstDayOfWeek; i++) {
            week[i] = "";
        }

        for (int day = 1; day <= daysInMonth; day++) {
            week[firstDayOfWeek] = day;
            firstDayOfWeek++;
            if (firstDayOfWeek == 7) {
                model.addRow(week);
                week = new Object[7];
                firstDayOfWeek = 0;
            }
        }

        if (firstDayOfWeek != 0) {
            model.addRow(week);
        }

        add(new JScrollPane(table), BorderLayout.CENTER);

        // Add back button at the bottom
        JButton backButton = new JButton("Back");
        backButton.setBackground(Color.BLUE);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> {
            // Handle back button action (e.g., switch to the previous panel)
            CardLayout cardLayout = (CardLayout) getParent().getLayout();
            cardLayout.show(getParent(), "Home");
        });
        add(backButton, BorderLayout.SOUTH);
    }
}