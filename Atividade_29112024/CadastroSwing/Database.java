import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String URL = "jdbc:sqlite:cadastro.db";

    static {
        try {
            Class.forName("org.sqlite.JDBC"); // Isso garante o carregamento do driver!
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Erro ao carregar o driver SQLite", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}