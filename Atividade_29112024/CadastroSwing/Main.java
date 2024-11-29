//import java.sql.Connection;
//import java.sql.Statement;

import javax.swing.*; 

public class Main { 
 public static void main(String[] args) { 
    CriarTabelas.inicializarBanco();

    SwingUtilities.invokeLater(() -> { 
    JFrame frame = new JFrame("Sistema de Cadastro"); 
    JMenuBar menuBar = new JMenuBar(); 
    JMenu menu = new JMenu("Cadastros"); 
    JMenuItem clientesItem = new JMenuItem("Clientes"); 
    JMenuItem vendedoresItem = new JMenuItem("Vendedores"); 
    

 clientesItem.addActionListener(e -> ClienteView.exibir()); 
 vendedoresItem.addActionListener(e -> 
    VendedorView.exibir()); 
    menu.add(clientesItem); 
    menu.add(vendedoresItem); 
    menuBar.add(menu); 
    frame.setJMenuBar(menuBar); 
    frame.setSize(400, 300); 
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    frame.setVisible(true); 
        }); 
    }

}