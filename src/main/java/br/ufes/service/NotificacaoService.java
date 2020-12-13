/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.service;

import br.ufes.models.Notificacao;
import br.ufes.repository.NotificacaoRepository;
import java.util.List;

/**
 *
 * @author gabriel
 */
public class NotificacaoService {
    
    private NotificacaoRepository notificacaoRepository;

    public NotificacaoService() {
        this.notificacaoRepository = new NotificacaoRepository();
    }
    
    public void novaNotificacao(Notificacao notificacao) throws Exception{
        this.notificacaoRepository.insert(notificacao);
    }
    
    public List<Notificacao> listarTodas(Long idUsuario) throws Exception{
        return this.notificacaoRepository.getAllByIdUsuario(idUsuario);
    }
    
    public void removerTodas(Long idUsuario) throws Exception{
        this.notificacaoRepository.deleteAllByIdUsuario(idUsuario);
    }
    
    public void removerNotificacao(Notificacao notificacao) throws Exception{
        this.notificacaoRepository.delete(notificacao);
    }
}
