package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDatabase {
    private String url = "jdbc:mysql://localhost:3306/school management system";
    private String user = "root";
    private String password = "";

    public boolean validateUser(String inputUsername, String inputPassword, String inputRole) {
        boolean isValid = false;

        try {
            // Establish the connection
            Connection connection = DriverManager.getConnection(url, user, password);

            // Create a parameterized query
            String query = "SELECT * FROM login WHERE username = ? AND password = ? AND role = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, inputUsername);
            preparedStatement.setString(2, inputPassword);
            preparedStatement.setString(3, inputRole);

            // Execute the query
            ResultSet resultSet = preparedStatement.executeQuery();

            // Check if credentials match
            if (resultSet.next()) {
                isValid = true;
            }

            // Close the resources
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isValid;
    }
}