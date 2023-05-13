/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apoio;

/**
 *
 * @author jonatan
 */
import java.sql.*;
import java.io.*;
import java.util.*;

public class ConexaoBD {
    private static ConexaoBD  instancia = null;
    private Connection conexao = null;
    
    public ConexaoBD() 
    {
        try
        {
            Properties prop = new Properties();
            prop.load(new FileInputStream("db.properties.txt"));
            
            String dbdriver = prop.getProperty("dbdriver");
            String dburl = prop.getProperty("dburl");
            String dbuser = prop.getProperty("dbuser");
            String dbpassword = prop.getProperty("dbpassword");
            
          
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
                        
        }
        catch (Exception e)
        {
            System.err.println("Erro ao tentar conectar: " + e);
            
        }
    }    
   // return instance 
   public static ConexaoBD getInstance()
   {
        if (instancia == null)
        {
            instancia = new ConexaoBD();
        }
        return instancia;
    }
    
    // return connection
    public Connection getConnection()
    {
        if (conexao == null) 
        {
            throw new RuntimeException("conexao==null");
        }
        return conexao;
    }
    
    // close the connection
    public void shutDown()
    {
        try 
        {
            conexao.close();
            instancia = null;
            conexao = null;
        } 
        catch (Exception e) 
        {
            System.err.println(e);
        }
    }        
    
}
