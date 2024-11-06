import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchBarExample {

    public static void main(String[] args) {
        // Create the frame
        JFrame frame = new JFrame("Search Bar Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 100);

        // Create a panel to hold the search bar components
        JPanel panel = new JPanel(new FlowLayout());

        // Create the search text field
        JTextField searchField = new JTextField(20); // 20 is the number of columns

        // Create the search button
        JButton searchButton = new JButton("Search");

        // Add an action listener to the search button
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = searchField.getText();
                // Perform the search or display the search text
                JOptionPane.showMessageDialog(frame, "Searching for: " + searchText);
            }
        });

        // Add the text field and button to the panel
        panel.add(searchField);
        panel.add(searchButton);

        // Add the panel to the frame
        frame.add(panel);
        frame.setVisible(true);
    }
}