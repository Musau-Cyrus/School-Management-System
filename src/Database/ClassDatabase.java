package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClassDatabase {
    private String url = "jdbc:mysql://localhost:3306/school management system";
    private String user = "root";
    private String password = "";

    public boolean insertClass(String classId, String className, String teacherIncharge) {
        boolean isInserted = false;

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "INSERT INTO classes (class_id, class_name, teacher_incharge) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, classId);
            preparedStatement.setString(2, className);
            preparedStatement.setString(3, teacherIncharge);

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

    public List<ClassDetails> fetchClasses() {
        List<ClassDetails> classes = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM classes";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String classId = resultSet.getString("class_id");
                String className = resultSet.getString("class_name");
                String teacherIncharge = resultSet.getString("teacher_incharge");

                classes.add(new ClassDetails(classId, className, teacherIncharge));
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return classes;
    }

    public boolean deleteClass(String classId){
        boolean isDeleted = false;
        try{
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "DELETE FROM classes WHERE class_id =  ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, classId);


            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected > 0){
                isDeleted = true;
            }

            preparedStatement.close();
            connection.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return isDeleted;

    }

    public boolean updateClass(String classId, String className, String teacherIncharge) {
        boolean isUpdated = false;
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "UPDATE classes SET class_name = ?, teacher_incharge = ? WHERE class_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, className);
            preparedStatement.setString(2, teacherIncharge);
            preparedStatement.setString(3, classId);

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