/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bibliotech;
import java.sql.*;
import javax.swing.JOptionPane;
import tela.FrmPrincipalLogin;
import apoio.ConexaoBD;

/**
 *
 * @author jonatan
 */
public class Bibliotech {

    static Connection conexao = null;
    
    public static void main(String[] args) 
    {
        if (ConexaoBD.getInstance().getConnection() != null)
        {
        new FrmPrincipalLogin().setVisible(true); 
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Erro ao conectar no Banco de Dados!");
            
        }
        
    }
    
    
}
