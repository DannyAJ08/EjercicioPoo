package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // Configuración para SQLyog (MySQL local)
    private static final String URL = "jdbc:mysql://localhost:3306/mesa_ayuda";
    private static final String USER = "root";
    private static final String PASSWORD = "qwerty2506D"; // Dejar vacío si no tienes contraseña

    private static Connection connection = null;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                // Driver para MySQL - dos opciones por compatibilidad
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                } catch (ClassNotFoundException e1) {
                    Class.forName("com.mysql.jdbc.Driver");
                }
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (ClassNotFoundException e) {
                throw new SQLException("Driver MySQL no encontrado", e);
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar conexión: " + e.getMessage());
            }
        }
    }

    // MÉTODO NUEVO QUE FALTABA
    public static void testConnection() {
        try (Connection conn = getConnection()) {
            if (conn != null && !conn.isClosed()) {
                System.out.println("✅ Conexión a MySQL establecida correctamente");
            }
        } catch (SQLException e) {
            System.err.println("❌ Error en conexión a MySQL: " + e.getMessage());
            System.err.println("URL: " + URL);
            System.err.println("Usuario: " + USER);
            System.err.println("Verifica que:");
            System.err.println("1. MySQL esté ejecutándose");
            System.err.println("2. La base de datos 'mesa_ayuda' exista");
            System.err.println("3. El usuario 'root' tenga permisos");
            System.err.println("4. El driver JDBC esté en el classpath");
        }
    }
}