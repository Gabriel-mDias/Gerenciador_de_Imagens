/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.dao.interfaces;

import br.ufes.models.Solicitacao;
import br.ufes.models.Usuario;
import java.util.List;

/**
 *
 * @author gabriel
 */
public interface ISolicitacaoDAO {
    
    public void insert(Solicitacao solicitacao) throws Exception;
    public void delete(Solicitacao solicitacao) throws Exception;
    public List<Solicitacao> getAllByAdmin(Usuario usuario) throws Exception;
}
