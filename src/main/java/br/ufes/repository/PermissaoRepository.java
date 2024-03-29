/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.repository;

import br.ufes.dao.implement.PermissaoDAO;
import br.ufes.dao.interfaces.IPermissaoDAO;
import br.ufes.models.Permissao;
import br.ufes.models.Usuario;
import br.ufes.models.imagem.ImagemReal;
import java.util.List;

/**
 *
 * @author gabriel
 */
public class PermissaoRepository{
    
    private IPermissaoDAO permissaoDAO;

    public PermissaoRepository() {
        this.permissaoDAO = new PermissaoDAO();
    }
    

    public Permissao getPermissao(Usuario usuario, ImagemReal imagem) throws Exception {
        if(usuario == null){
            throw new RuntimeException("Usuário fornecida é nulo!");
        } else if(imagem == null){
            throw new RuntimeException("Imagem fornecida é nulo!");
        }
        
        return permissaoDAO.getPermissao(usuario, imagem);
    }


    public Long insert(Permissao permissao) throws Exception {
        if(permissao == null){
            throw new RuntimeException("Inserção com permissão fornecida nula é inválida!");
        }
        
        return this.permissaoDAO.insert(permissao);
    }
    

    public void update(Permissao permissao) throws Exception {
        if(permissao == null){
            throw new RuntimeException("Atualização com permissão fornecida nula é inválida!");
        }
        
        this.permissaoDAO.update(permissao);
    }


    public Permissao getPermissaoByIdsUsuarioImagem(Permissao permissao) throws Exception {
        if(permissao == null){
            throw new RuntimeException("Inserção com permissão fornecida nula é inválida!");
        }
        
        return this.permissaoDAO.getPermissaoByIdsUsuarioImagem(permissao);
    }


    public Permissao getPermissaoById(Long id) throws Exception {
        if(id == null){
            throw new RuntimeException("Busca por permissão com id fornecida nula é inválida!");
        }
        
        return this.permissaoDAO.getPermissaoById(id);
    }
    
    public List<Permissao> getByUsuario(Usuario usuario) throws Exception{
        if(usuario == null){
            throw new RuntimeException("Busca de permissão com usuário fornecido nulo é inválida!");
        }
        
        return this.permissaoDAO.getByUsuario(usuario);
    }
    
}
