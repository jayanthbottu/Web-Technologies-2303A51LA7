import java.sql.*;

public class DatabaseConnection {
    public static void main(String[] args) {
        
        String url = "jdbc:mysql://localhost:3306/studentDB?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
        String username = "root";  
        String password = "admin";  

        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");

            
            if (!checkIfDatabaseExists(url, username, password)) {
                System.out.println("Database 'studentDB' does not exist.");
                return;
            }

            
            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                System.out.println("Connection Successful!");
            }

        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Database connection failed!");
            e.printStackTrace();
        }
    }

    
    private static boolean checkIfDatabaseExists(String url, String username, String password) {
        try (Connection conn = DriverManager.getConnection(url.replace("studentDB", ""), username, password);
             ResultSet rs = conn.getMetaData().getCatalogs()) {
            while (rs.next()) {
                if (rs.getString(1).equalsIgnoreCase("studentDB")) {
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error checking database existence.");
            e.printStackTrace();
        }
        return false;
    }
}
