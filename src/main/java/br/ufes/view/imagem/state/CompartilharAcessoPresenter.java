/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.view.imagem.state;

import br.ufes.models.imagem.ImagemReal;
import br.ufes.models.Notificacao;
import br.ufes.models.Usuario;
import br.ufes.service.NotificacaoService;
import br.ufes.view.imagem.ManterAcessoPresenter;
import javax.swing.JOptionPane;

/**
 *
 * @author gabriel
 */
public class CompartilharAcessoPresenter extends ManterAcessoState{

    private NotificacaoService notificacaoService;
    
    public CompartilharAcessoPresenter(ManterAcessoPresenter presenter) {
        super(presenter);
        this.controlador.getView().getRbEditar().setVisible(false);
        this.controlador.getView().getRbExcluir().setVisible(false);
        this.controlador.getView().getRbVisualizar().setVisible(false);
        this.notificacaoService = new NotificacaoService();
    }

    @Override
    public void executar(Usuario usuario, ImagemReal imagem) {
        Notificacao n = new Notificacao(usuario, this.controlador.getLogado().getLogin()+" compartilhou a seguinte imagem: \n"+imagem.getPath());
        try {
            this.notificacaoService.novaNotificacao(n);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this.controlador.getView(), "Ocorreu um erro ao compartilhar a imagem!\n"+ex, "Compartilhar Imagem", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException("Erro ao notificar: "+ex);
        }
    }
    
}
