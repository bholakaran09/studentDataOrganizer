import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class createDB {
   
   public static void main(String[] args) {
      // Open a connection
      try{
         String DB_URL = "jdbc:mysql://localhost:3306/";
         String USER = "root";
         String PASS = "manager";

         Connection connection=DriverManager.getConnection(DB_URL,USER,PASS);
         String sql="CREATE DATABASE STUDENTS";

         Statement statement=connection.createStatement();
         statement.executeUpdate(sql);
         statement.close();

         System.out.println("Database Created Successfully");

      }
         catch (SQLException e) {
         e.printStackTrace();
      } 
   }
}