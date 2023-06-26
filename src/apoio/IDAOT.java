/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package apoio;

import java.util.ArrayList;

/**
 *
 * @author jonatan
 */
public interface IDAOT <T> {
    
    public String salvar(T o);
    
    public String atualizar(T o);
    
    public String excluir(int id);
    
    public ArrayList<T> consultarTodos();
    
    public ArrayList<T> consultar(String criterio);
    
    public T consultarId(int id);
    
}
