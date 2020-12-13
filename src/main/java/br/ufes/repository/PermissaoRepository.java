/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.repository;

import br.ufes.dao.implement.PermissaoDAO;
import br.ufes.dao.interfaces.IPermissaoDAO;
import br.ufes.models.Permissao;
import java.util.List;

/**
 *
 * @author gabriel
 */
public class PermissaoRepository {
    
    private IPermissaoDAO permissaoDAO;

    public PermissaoRepository() {
        this.permissaoDAO = new PermissaoDAO();
    }
    
    public List<Permissao> getByIdUsuario(Long id) throws Exception{
        if(id == null){
            throw new RuntimeException("Id de Usuário fornecida é inválida!");
        }
        
        return permissaoDAO.getByIdUsuario(id);
    }
    
}
