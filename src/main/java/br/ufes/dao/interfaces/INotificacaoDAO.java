/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.dao.interfaces;

import br.ufes.models.Notificacao;
import br.ufes.models.Usuario;
import java.util.List;

/**
 *
 * @author gabriel
 */
public interface INotificacaoDAO {
    
    public void insert(Notificacao notificacao) throws Exception;
    public void delete(Notificacao notificacao) throws Exception;
    public void deleteAllByIdUsuario(Long idUsuario) throws Exception;
    public List<Notificacao> getAllByIdUsuario(Long idUsuario) throws Exception;
}
