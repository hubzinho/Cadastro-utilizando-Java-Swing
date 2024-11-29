import java.sql.Connection;
import java.sql.Statement;

public class CriarTabelas {
    public static void inicializarBanco() {
    String sqlClientes = """
        CREATE TABLE IF NOT EXISTS clientes (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            nome TEXT NOT NULL,
            email TEXT NOT NULL
        );
    """;

    String sqlVendedores = """
        CREATE TABLE IF NOT EXISTS vendedores (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            nome TEXT NOT NULL,
            setor TEXT NOT NULL
        );
    """;

    try (Connection conn = Database.getConnection();
         Statement stmt = conn.createStatement()) {

        stmt.execute(sqlClientes);
        stmt.execute(sqlVendedores);

        System.out.println("Tabelas criadas ou já existem.");
    } catch (Exception e) {
        System.out.println("Erro ao criar tabelas: " + e.getMessage());
    }
}
}