package cacttus.education.cacttusproductcrud.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbHelper {
    private static String connectionUrl =
"jdbc:sqlserver://EXTREME:1433;databaseName=TSQL;user=user;password=user;encrypt=true;trustServerCertificate=true;";

    private static Connection connection = null;

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(connectionUrl);
            }
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
