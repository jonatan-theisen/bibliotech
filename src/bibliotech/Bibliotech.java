/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bibliotech;

import java.sql.*;
import javax.swing.JOptionPane;
import tela.FrmPrincipal;

/**
 *
 * @author jonatan
 */
public class Bibliotech {

    static Connection conexao = null;
    
    public static void main(String[] args) 
    {
        if (abrirConexao())
        {
        new FrmPrincipal().setVisible(true); 
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Erro ao conectar no Banco de Dados!");
        }
    }
    
    private static boolean abrirConexao() 
    {
        try
        {
            String dbdriver = "org.postgresql.Driver";
            String dburl = "jdbc:postgresql://localhost:5432/bibliotech";
            String dbuser = "postgres";
            String dbpassword = "postgres";
            
          
            // Carrega Driver do Banco de Dados
            Class.forName(dbdriver);
            
            if (dbuser.length() != 0) // conexão com usuário e senha
            {
                conexao = DriverManager.getConnection(dburl, dbuser, dbpassword);
            }
            else
            {
                conexao = DriverManager.getConnection(dburl);
            }
            
            return true;
            
        }
        catch (Exception e)
        {
            System.err.println("Erro ao tentar conectar: " + e);
            
            return false;
        }
    }
    
}
