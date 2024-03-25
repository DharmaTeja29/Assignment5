import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DepartmentData {

    private String url = "jdbc:mysql://localhost:3306/departments";
    private String user = "your_username";
    private String password = "your_password";

    public DepartmentData(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public void saveDepartment(Department department) {
        String query = "INSERT INTO department (id, name) VALUES (?, ?)";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, department.getId());
            preparedStatement.setString(2, department.getName());

            preparedStatement.executeUpdate();
            System.out.println("Department saved successfully.");

        } catch (SQLException e) {
            System.out.println("Failed to save department: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Configure your database connection details here
        String url = "jdbc:mysql://localhost:3306/departments";
        String user = "your_username";
        String password = "your_password";

        DepartmentData departmentData = new DepartmentData(url, user, password);

        // Example usage:
        Department department = new Department(1, "Engineering");
        departmentData.saveDepartment(department);
    }
}

class Department {
    private int id;
    private String name;

    public Department(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
