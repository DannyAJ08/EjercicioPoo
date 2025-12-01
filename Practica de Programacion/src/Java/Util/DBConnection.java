package Java.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/practica2_banco?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "qwerty2506D";

    private static Connection connection = null;

    private DBConnection() { }

    public static Connection getConnection() throws SQLException {

        if (connection == null || connection.isClosed()) {

            try {
                // Cargar el driver de MySQL
                Class.forName("com.mysql.cj.jdbc.Driver");

                // Crear la conexión
                connection = DriverManager.getConnection(URL, USER, PASSWORD);

                System.out.println("✔ Conexión establecida correctamente con la base de datos.");
            }
            catch (ClassNotFoundException e) {
                throw new SQLException("❌ No se encontró el Driver JDBC de MySQL.", e);
            }
            catch (SQLException e) {
                throw new SQLException("❌ Error al conectar a MySQL: " + e.getMessage(), e);
            }
        }

        return connection;
    }
}
