import javax.swing.*; 
import java.awt.*; 
import java.sql.*; 

public class ClienteView { 
    public static void exibir() { 

    JFrame frame = new JFrame("Cadastro de Clientes"); 
    frame.setLayout(new BorderLayout()); 
    JPanel formPanel = new JPanel(new GridLayout(3, 2)); 
    JTextField nomeField = new JTextField(); 
    JTextField emailField = new JTextField(); 

    formPanel.add(new JLabel("Nome:")); 
    formPanel.add(nomeField); 
    formPanel.add(new JLabel("Email:")); 
    formPanel.add(emailField); 

    JButton salvarButton = new JButton("Salvar"); 
    JButton listarButton = new JButton("Listar"); 

    salvarButton.addActionListener(e -> { 
    String nome = nomeField.getText(); 
    String email = emailField.getText(); 

    try (Connection conn = Database.getConnection(); 
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO clientes (nome, email) VALUES (?, ?)")) { 
        stmt.setString(1, nome); 
        stmt.setString(2, email); 
        stmt.executeUpdate(); 
        JOptionPane.showMessageDialog(frame, "Cliente salvo com sucesso!"); 
    } catch (SQLException ex) { 
        JOptionPane.showMessageDialog(frame, "Erro ao salvar cliente: " + ex.getMessage()); 
    } 
 }); 

    listarButton.addActionListener(e -> { 
    try (Connection conn = Database.getConnection(); 
        Statement stmt = conn.createStatement(); 
        ResultSet rs = stmt.executeQuery("SELECT * FROM clientes")) { 
            StringBuilder clientes = new StringBuilder("Clientes cadastrados:\n"); 
    while (rs.next()) { 
        clientes.append("ID: ").append(rs.getInt("id"))
         .append(", Nome: ").append(rs.getString("nome")) 
         .append(", Email: ").append(rs.getString("email")).append("\n"); 
    } 

    JOptionPane.showMessageDialog(frame, 
    clientes.toString()); 
    } catch (SQLException ex) { 
        JOptionPane.showMessageDialog(frame, "Erro ao listar clientes: " + ex.getMessage()); 
    } 
 }); 

    frame.add(formPanel, BorderLayout.CENTER); 
    JPanel buttonPanel = new JPanel(); 

    buttonPanel.add(salvarButton); 
    buttonPanel.add(listarButton); 

    frame.add(buttonPanel, BorderLayout.SOUTH);
    frame.setSize(400, 200); 
    frame.setVisible(true); 
    } 
}