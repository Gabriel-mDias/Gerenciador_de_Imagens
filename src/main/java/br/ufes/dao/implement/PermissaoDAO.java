/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.dao.implement;

import br.ufes.dao.db.connection.ConectorSQLite;
import br.ufes.dao.interfaces.IPermissaoDAO;
import br.ufes.models.imagem.ImagemReal;
import br.ufes.models.Permissao;
import br.ufes.models.Usuario;
import java.util.List;

/**
 *
 * @author gabriel
 */
public class PermissaoDAO implements IPermissaoDAO{

    private ConectorSQLite gerenciadorConexao;

    public PermissaoDAO() {
        this.gerenciadorConexao = new ConectorSQLite();
    }
    
    @Override
    public List<Permissao> getByIdUsuario(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Permissao getPermissao(Usuario usuario, ImagemReal imagem) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
