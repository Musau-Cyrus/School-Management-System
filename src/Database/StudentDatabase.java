package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentDatabase {
    private String url = "jdbc:mysql://localhost:3306/school management system";
    private String user = "root";
    private String password = "";

    public boolean insertStudent(String studentName, String studentId, String studentAge, String studentClass, String studentContactInfo) {
        boolean isInserted = false;

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "INSERT INTO students (name, id, age, class, contact) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, studentName);
            preparedStatement.setString(2, studentId);
            preparedStatement.setString(3, studentAge);
            preparedStatement.setString(4, studentClass);
            preparedStatement.setString(5, studentContactInfo);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                isInserted = true;
            }

            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isInserted;
    }

    public List<Student> fetchStudents() {
        List<Student> students = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM students";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String id = resultSet.getString("id");
                String age = resultSet.getString("age");
                String studentClass = resultSet.getString("class");
                String contactInfo = resultSet.getString("contact");

                students.add(new Student(name, id, age, studentClass, contactInfo));
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return students;
    }

    public boolean deleteStudent(String studentId) {
        boolean isDeleted = false;

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "DELETE FROM students WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, studentId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                isDeleted = true;
            }

            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isDeleted;
    }

    public boolean updateStudent(String name, String id, String age, String className, String contact) {
        boolean isUpdated = false;

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "UPDATE students SET name = ?, age = ?, class = ?, contact = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, age);
            preparedStatement.setString(3, className);
            preparedStatement.setString(4, contact);
            preparedStatement.setString(5, id);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                isUpdated = true;
            }

            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isUpdated;
    }
}