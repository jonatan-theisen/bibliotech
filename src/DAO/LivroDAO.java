/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import apoio.ConexaoBD;
import apoio.IDAOT;
import entidade.Cliente;
import java.util.ArrayList;
import entidade.Livro;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author jonatan
 */
public class LivroDAO implements IDAOT<Livro>{

    @Override
    public String salvar(Livro o) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            
            String sql = "insert into livro "
                    + "values"
                    + "(default, "
                    + "'" + o.getTitulo()+ "', "
                    + "'" + o.getAutor()+ "', "
                    + "'" + o.getGenero()+ "', "
                    + "'" + o.getIdioma()+ "', "
                    + "'" + o.isDisponivel()+ "', "
                    + "'" + o.getNmr_paginas()+ "')";
            
            
            int retorno = st.executeUpdate(sql);
            return null;
                    
        } catch (Exception e) {
            
            System.out.println("Erro ao inserir livro: " + e);
            return e.toString();
            
        }
    }

    @Override
    public String atualizar(Livro o) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            
            String sql = "update livro set "
                         + "titulo = '" + o.getTitulo()
                         + "' ,autor = '" + o.getAutor()
                         + "' ,genero = '" + o.getGenero()
                         + "' ,idioma = '" + o.getIdioma()
                         + "' ,disponivel = '" + o.isDisponivel()
                         + "' ,nmr_paginas = '" + o.getNmr_paginas()
                         + "' where id = " + o.getId()
                         + ";";
            
            
            int retorno = st.executeUpdate(sql);
            return null;
                    
        } catch (Exception e) {
            
            System.out.println("Erro ao editar livro: " + e);
            return e.toString();
            
        }
    }
    
    public void popularTabela(JTable tabela, String criterio) {
        
        ResultSet resultadoQ;
        
        Object[][] dadosTabela = null;
        
        Object[] cabecalho = new Object[5];
        cabecalho[0] = "Código";
        cabecalho[1] = "Título";
        cabecalho[2] = "Autor";
        cabecalho[3] = "Gênero";
        cabecalho[4] = "Disponível";
        
        try {
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                         + "SELECT count(*) FROM livro WHERE titulo ILIKE '%" + criterio + "%'");
            
            resultadoQ.next();
            
            dadosTabela = new Object[resultadoQ.getInt(1)][5];
        } 
        catch (Exception e) {
            System.out.println("Erro ao consultar livro" + e);
        }
                    
            int lin = 0;
            
            try {
                resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                             + "SELECT * FROM livro WHERE titulo ILIKE '%" + criterio + "%'");
                
                while (resultadoQ.next()) {
                    
                    dadosTabela[lin][0] = resultadoQ.getInt("id");
                    dadosTabela[lin][1] = resultadoQ.getString("titulo");
                    dadosTabela[lin][2] = resultadoQ.getString("autor");
                    dadosTabela[lin][3] = resultadoQ.getString("genero");
                    dadosTabela[lin][4] = resultadoQ.getBoolean("disponivel");
                    
                    
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

    @Override
    public String excluir(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Livro> consultarTodos() {
        List<Livro> livros = new ArrayList();
        
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            
            String sql = "select id, titulo from livro order by id";
            
            System.out.println(sql);
            
            ResultSet retorno = st.executeQuery(sql);
            
            while (retorno.next()){
                Livro livro = new Livro();
                
                livro.setId(retorno.getInt("id"));
                livro.setTitulo(retorno.getString("titulo"));
                
                livros.add(livro);
            }
                    
        } catch (Exception e) {
            
            System.out.println("Erro ao consultar livros: " + e);
                  
        }
        
        return (ArrayList<Livro>) livros;
    }

    @Override
    public ArrayList<Livro> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Livro consultarId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
