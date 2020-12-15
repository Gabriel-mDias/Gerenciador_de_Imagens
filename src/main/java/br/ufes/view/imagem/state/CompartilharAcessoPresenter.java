/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.view.imagem.state;

import br.ufes.models.imagem.ImagemReal;
import br.ufes.models.Notificacao;
import br.ufes.models.Permissao;
import br.ufes.models.Usuario;
import br.ufes.service.ImagemService;
import br.ufes.service.NotificacaoService;
import br.ufes.service.PermissaoService;
import br.ufes.service.UsuarioService;
import br.ufes.view.imagem.ManterAcessoPresenter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author gabriel
 */
public class CompartilharAcessoPresenter extends ManterAcessoState{

    private NotificacaoService notificacaoService;
    private UsuarioService usuarioService;
    private ImagemService imagemService;
    private PermissaoService permissaoService;
    
    public CompartilharAcessoPresenter(ManterAcessoPresenter presenter) {
        super(presenter);
        this.controlador.getView().getRbCompartilhar().setVisible(false);
        this.controlador.getView().getRbExcluir().setVisible(false);
        this.controlador.getView().getRbVisualizar().setVisible(false);
        this.controlador.getView().getTxtAdmin().setVisible(false);
        this.controlador.getView().getLblAdmin().setVisible(false);
        this.controlador.getView().getLblUsuario().setText("Digite o login do usuário que você deseja compartilhar:");
        this.notificacaoService = new NotificacaoService();
        this.usuarioService = new UsuarioService();
        this.imagemService = new ImagemService();
        this.permissaoService = new PermissaoService();
    }

    @Override
    public void executar(Usuario usuario, ImagemReal imagem) {
        Notificacao n = new Notificacao(usuario, usuario.getLogin()+" compartilhou a seguinte imagem: \n"+imagem.getPath());
        Permissao permissao = new Permissao();
        permissao.setCompartilhar(false);
        permissao.setExcluir(false);
        permissao.setVisualizar(true);
        try {
            permissao.setUsuario(usuarioService.buscarPorLogin(usuario.getLogin()).get(0));
            permissao.setImagem(imagemService.buscarPorPath(imagem.getPath()).get(0));
        } catch (Exception ex) {
            Logger.getLogger(CompartilharAcessoPresenter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            Permissao verificador = this.permissaoService.buscaPermissao(usuario, imagem);
            if(verificador == null || !verificador.isVisualizar()){
               this.permissaoService.inserir(permissao);
               JOptionPane.showMessageDialog(this.controlador.getView(), "Imagem compartilhada!\n", "Compartilhar Imagem", JOptionPane.OK_OPTION);
               this.notificacaoService.novaNotificacao(n); 
            }else{
                JOptionPane.showMessageDialog(this.controlador.getView(), "Esse usuário já tinha acesso a imagem!\n", "Compartilhar Imagem", JOptionPane.OK_OPTION);
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this.controlador.getView(), "Ocorreu um erro ao compartilhar a imagem!\n"+ex, "Compartilhar Imagem", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException("Erro ao notificar: "+ex);
        }
    }
    
}
