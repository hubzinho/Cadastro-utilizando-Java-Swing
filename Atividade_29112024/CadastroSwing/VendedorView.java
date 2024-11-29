import javax.swing.*; 
import java.awt.*; 
import java.sql.*; 

public class VendedorView { 
    public static void exibir() { 
        JFrame frame = new JFrame("Cadastro de Vendedores"); 
        frame.setLayout(new BorderLayout()); 

        JPanel formPanel = new JPanel(new GridLayout(3, 2)); 
        JTextField nomeField = new JTextField(); 
        JTextField setorField = new JTextField(); 

        formPanel.add(new JLabel("Nome:")); 
        formPanel.add(nomeField); 
        formPanel.add(new JLabel("Setor:")); 
        formPanel.add(setorField); 

        JButton salvarButton = new JButton("Salvar"); 
        JButton listarButton = new JButton("Listar");

        salvarButton.addActionListener(e -> { 
        String nome = nomeField.getText(); 
        String setor = setorField.getText();

        try (Connection conn = Database.getConnection(); 
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO vendedores (nome, setor) VALUES (?, ?)")) { 
            stmt.setString(1, nome); 
            stmt.setString(2, setor); 
            stmt.executeUpdate(); 
            JOptionPane.showMessageDialog(frame, "Vendedor salvo com sucesso!"); 
         } catch (SQLException ex) { 
            JOptionPane.showMessageDialog(frame, "Erro ao salvar vendedor: " + ex.getMessage()); 
         } 
    }); 

    listarButton.addActionListener(e -> { 
    try (Connection conn = Database.getConnection(); 
    Statement stmt = conn.createStatement(); 
    ResultSet rs = stmt.executeQuery("SELECT * FROM vendedores")) { 
        StringBuilder vendedores = new 
        StringBuilder("Vendedores cadastrados:\n"); 
    while (rs.next()) { 
        vendedores.append("ID: ").append(rs.getInt("id")) 
        .append(", Nome: ").append(rs.getString("nome")) 
        .append(", Setor: ").append(rs.getString("setor")).append("\n"); 
    } 

    JOptionPane.showMessageDialog(frame, 
    vendedores.toString()); 
    } catch (SQLException ex) { 
        JOptionPane.showMessageDialog(frame, "Erro ao listar vendedores: " + ex.getMessage()); 
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