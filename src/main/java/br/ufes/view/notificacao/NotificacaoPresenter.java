/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.view.notificacao;

import br.ufes.models.Notificacao;
import br.ufes.models.Usuario;
import br.ufes.service.NotificacaoService;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JDesktopPane;

/**
 *
 * @author gabriel
 */
public class NotificacaoPresenter {
    
    private NotificacaoView view;
    private NotificacaoService notificacaoService;
    private Usuario usuarioLogado;

    public NotificacaoPresenter(Usuario usuario, JDesktopPane desktop) {
        this.usuarioLogado = usuario;
        this.view = new NotificacaoView();
        this.notificacaoService = new NotificacaoService();
        
        try {
            this.preencheLista(notificacaoService.listarTodas(this.usuarioLogado.getId()));
        } catch (Exception ex) {
            Logger.getLogger(NotificacaoPresenter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.view.getBtnLimpar().addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    notificacaoService.removerTodas(usuarioLogado.getId());
                    preencheLista(notificacaoService.listarTodas(usuarioLogado.getId()));
                } catch (Exception ex) {
                    Logger.getLogger(NotificacaoPresenter.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        
        this.view.setVisible(true);
        desktop.add(this.view);
        Dimension desktopSize = desktop.getSize();
        this.view.setLocation((desktopSize.width - this.view.getSize().width) / 2,(desktopSize.height - this.view.getSize().height) / 2);
        try {
            this.view.setSelected(true);
        } catch (PropertyVetoException ex) {
            new RuntimeException("Erro ao requisitar o foco para um JInternalFrame");
        }
    }
    
    private void preencheLista(List<Notificacao> notificacoes){
        DefaultListModel listModel = new DefaultListModel();
        for(Notificacao notificacao : notificacoes){
            listModel.addElement(notificacao.getMensagem());
        }
        
        this.view.getListaNotificacoes().setModel(listModel);
    }
    
}
