package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectionDetails
{
    public static Connection getConnection()
    {
        Connection connection = null;

        try
        {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/subscription_db", "***", "***");

            Statement stmt = connection.createStatement();

            ResultSet resultSet = stmt.executeQuery("SELECT * FROM tbl_users");

            if (!resultSet.next())
            {
                System.out.println("No rows in the current table");
            }
            else
            {
                System.out.println("DB Connection OK");
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

        return connection;
        }
}
