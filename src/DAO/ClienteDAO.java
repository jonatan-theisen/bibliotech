/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import apoio.ConexaoBD;
import apoio.IDAOT;
import java.util.ArrayList;
import entidade.Cliente;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author jonatan
 */
public class ClienteDAO implements IDAOT<Cliente>{

    @Override
    public String salvar(Cliente o) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            
            String sql = "insert into cliente "
                    + "values"
                    + "(default, "
                    + "'" + o.getNome() + "', "
                    + "'" + o.getTelefone()+ "', "
                    + "'" + o.getGenero()+ "', "
                    + "'" + o.getEmail()+ "', "
                    + "'" + o.getEndereco()+ "')";
            
            
            int retorno = st.executeUpdate(sql);
            return null;
                    
        } catch (Exception e) {
            
            System.out.println("Erro ao inserir Cliente: " + e);
            return e.toString();
            
        }
    }

    @Override
    public String atualizar(Cliente o) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            
            String sql = "update cliente set "
                         + "nome = '" + o.getNome() 
                         + "' ,email = '" + o.getEmail() 
                         + "' ,genero = '" + o.getGenero()
                         + "' ,telefone = '" + o.getTelefone()
                         + "' ,endereco = '" + o.getEndereco()
                         + "' where id = " + o.getId() 
                         + ";";
            
            
            int retorno = st.executeUpdate(sql);
            return null;
                    
        } catch (Exception e) {
            
            System.out.println("Erro ao editar Cliente: " + e);
            return e.toString();
            
        }
    }

    @Override
    public String excluir(int id) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            
            String sql = "delete from cliente where id = " + id + ";";
            
            
            int retorno = st.executeUpdate(sql);
            return null;
                    
        } catch (Exception e) {
            
            System.out.println("Erro ao excluir Cliente: " + e);
            return e.toString();
            
        }
    }

    @Override
    public ArrayList<Cliente> consultarTodos() {
        List<Cliente> clientes = new ArrayList();
        
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            
            String sql = "select id, nome from cliente order by id";
            
            System.out.println(sql);
            
            ResultSet retorno = st.executeQuery(sql);
            
            while (retorno.next()){
                Cliente cliente = new Cliente();
                
                cliente.setId(retorno.getInt("id"));
                cliente.setNome(retorno.getString("nome"));
                
                clientes.add(cliente);
            }
                    
        } catch (Exception e) {
            
            System.out.println("Erro ao consultar Clientes: " + e);
                  
        }
        
        return (ArrayList<Cliente>) clientes;
    }

    @Override
    public ArrayList<Cliente> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Cliente consultarId(int id) {
        Cliente cliente = null;
        
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            
            String sql = "select * from cliente where id = " + id;
            
            System.out.println(sql);
            
            ResultSet retorno = st.executeQuery(sql);
            
            while (retorno.next()) {                
               
                cliente = new Cliente();
                
                cliente.setId(retorno.getInt("id"));
                cliente.setNome(retorno.getString("nome"));
                cliente.setTelefone(retorno.getLong("telefone"));
                cliente.setGenero(retorno.getString("genero"));
                cliente.setEmail(retorno.getString("email"));
                cliente.setEndereco(retorno.getString("endereco"));
                
                
            } 
                    
        } catch (Exception e) {
            
            System.out.println("Erro ao editar Cliente: " + e);
                  
        }
        
        return cliente;
    }
    
    public void popularTabela(JTable tabela, String criterio) {
        
        ResultSet resultadoQ;
        
        Object[][] dadosTabela = null;
        
        Object[] cabecalho = new Object[4];
        cabecalho[0] = "Código";
        cabecalho[1] = "Nome";
        cabecalho[2] = "Email";
        cabecalho[3] = "Telefone";
        
        try {
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                         + "SELECT count(*) FROM cliente WHERE nome ILIKE '%" + criterio + "%'");
            
            resultadoQ.next();
            
            dadosTabela = new Object[resultadoQ.getInt(1)][4];
        } 
        catch (Exception e) {
            System.out.println("Erro ao consultar cliente" + e);
        }
                    
            int lin = 0;
            
            try {
                resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                             + "SELECT * FROM cliente WHERE nome ILIKE '%" + criterio + "%'");
                
                while (resultadoQ.next()) {
                    
                    dadosTabela[lin][0] = resultadoQ.getInt("id");
                    dadosTabela[lin][1] = resultadoQ.getString("nome");
                    dadosTabela[lin][2] = resultadoQ.getString("email");
                    dadosTabela[lin][3] = resultadoQ.getLong("telefone");
                    
                    lin++;
                }
            } 
            catch (Exception e) {
                System.out.println("Problema ao popular tabela:" + e);
            }
            
        tabela.setModel(new DefaultTableModel(dadosTabela, cabecalho) {
        @Override
            
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
            
        @Override
        
            public Class getColumnClass(int Column) {
                return Object.class;
            }
        });
        
        tabela.setSelectionMode(0);
        
        TableColumn column = null;
        for (int i = 0; i < tabela.getColumnCount(); i++){
            column = tabela.getColumnModel().getColumn(i);
            
            switch (i) {
                case 0:
                    column.setPreferredWidth(10);
                    break;
                case 1:
                    column.setPreferredWidth(100);
                    break;
                case 2:
                    column.setPreferredWidth(45);
                    break;
                case 3:
                    column.setPreferredWidth(25);
                    break;
                case 4:
                    column.setPreferredWidth(25);
                    break;    
            }
        }
    }
    
}
