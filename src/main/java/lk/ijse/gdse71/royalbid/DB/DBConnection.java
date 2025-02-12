package lk.ijse.gdse71.royalbid.DB;

import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Getter
public class DBConnection {
    private static DBConnection dBConnection;
    private Connection connection;

      private DBConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
              connection = DriverManager.getConnection(
                      "jdbc:mysql://localhost:3306/royal_bid_management_system_project_databases ",
                      "root",
                      "12345"
              );
    }


    
    public static DBConnection getInstance() throws ClassNotFoundException, SQLException {
        if (dBConnection == null)  {
                  dBConnection = new DBConnection();
        }
        return dBConnection;
    }

//    public Connection getConnection(){
//        return connection;
    //  }
}
