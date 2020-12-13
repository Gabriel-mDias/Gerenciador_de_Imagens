/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.repository;

import br.ufes.dao.implement.NotificacaoDAO;
import br.ufes.dao.interfaces.INotificacaoDAO;
import br.ufes.models.Notificacao;
import java.util.List;

/**
 *
 * @author gabriel
 */
public class NotificacaoRepository {
    
    private INotificacaoDAO notificacaoDAO;

    public NotificacaoRepository() {
        this.notificacaoDAO = new NotificacaoDAO();
    }

    public void insert(Notificacao notificacao) throws Exception{
        if(notificacao == null){
            throw new RuntimeException("Inserção com notificação nula é inválido!");
        } else if(notificacao.getMensagem() == null){
            throw new RuntimeException("Inserção com mensagem de notificação nula é inválido!");
        } else if(notificacao.getUsuario().getId() == null){
            throw new RuntimeException("Inserção de notificação com usuário nulo é inválido!");
        }
        
        this.notificacaoDAO.insert(notificacao);
    }
    
    public void delete(Notificacao notificacao) throws Exception{
        if(notificacao == null){
            throw new RuntimeException("Exclusão com notificação nula é inválido!");
        } else if(notificacao.getId() == null){
            throw new RuntimeException("Exclusão com id da notificação nula é inválido!");
        } else if(notificacao.getUsuario().getId() == null){
            throw new RuntimeException("Exclusão de notificação com usuário nulo é inválido!");
        }
        
        this.notificacaoDAO.delete(notificacao);
    }
    
    public void deleteAllByIdUsuario(Long idUsuario) throws Exception{
        if(idUsuario == null){
            throw new RuntimeException("Exclusão com id da notificação nula é inválido!");
        }
        
        this.notificacaoDAO.deleteAllByIdUsuario(idUsuario);
    }
    
    public List<Notificacao> getAllByIdUsuario(Long idUsuario) throws Exception{
        if(idUsuario == null){
            throw new RuntimeException("Exclusão com id da notificação nula é inválido!");
        }
        
        return this.notificacaoDAO.getAllByIdUsuario(idUsuario);
    }
}
