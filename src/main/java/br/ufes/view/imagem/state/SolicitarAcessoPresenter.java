/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.view.imagem.state;

import br.ufes.models.Permissao;
import br.ufes.models.Solicitacao;
import br.ufes.models.imagem.ImagemReal;
import br.ufes.models.Usuario;
import br.ufes.service.ImagemService;
import br.ufes.service.PermissaoService;
import br.ufes.service.SolicitacaoService;
import br.ufes.service.UsuarioService;
import br.ufes.view.imagem.ManterAcessoPresenter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author gabriel
 */
public class SolicitarAcessoPresenter extends ManterAcessoState{

    private UsuarioService usuarioService;
    private SolicitacaoService solicitacaoService;
    private ImagemService imagemService;
    private PermissaoService permissaoService;
    
    public SolicitarAcessoPresenter(ManterAcessoPresenter presenter) {
        super(presenter);
        this.controlador.getView().getTxtLogin().setEnabled(false);
        this.usuarioService = new UsuarioService();
        this.solicitacaoService = new SolicitacaoService();
        this.imagemService = new ImagemService();
        this.permissaoService = new PermissaoService();
    }

    @Override
    public void executar(Usuario usuario, ImagemReal imagem) {
        
        
        Permissao nova = null;
        Usuario adm = null;
        Solicitacao solicitacao = null;
        
        try {
            nova = permissaoService.buscaPermissao(usuario, imagem);
        } catch (Exception ex) {
            Logger.getLogger(SolicitarAcessoPresenter.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        if(nova != null){
            solicitacao = new Solicitacao();
            solicitacao.setExcluir(this.controlador.getView().getRbExcluir().isSelected());
            solicitacao.setCompartilhar(this.controlador.getView().getRbCompartilhar().isSelected());
            solicitacao.setVisualizar(this.controlador.getView().getRbVisualizar().isSelected());
            solicitacao.setPermissao(nova);
            
            try {
                adm = usuarioService.buscarPorLogin(this.controlador.getView().getTxtAdmin().getText()).isEmpty() ? null : usuarioService.buscarPorLogin(this.controlador.getView().getTxtAdmin().getText()).get(0);
                if(adm.isAdimin()){
                    solicitacao.setUsuario(adm);
                    this.solicitacaoService.editar(solicitacao);
                    JOptionPane.showMessageDialog(this.controlador.getView(), "Solicitação Enviada!", "Solicitar", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                Logger.getLogger(SolicitarAcessoPresenter.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }else{
            nova = new Permissao();
            nova.setUsuario(usuario);
        
            try {
                nova.setImagem(imagemService.buscarPorPath(imagem.getPath()).get(0));
            } catch (Exception ex) {
                Logger.getLogger(SolicitarAcessoPresenter.class.getName()).log(Level.SEVERE, null, ex);
            }
        
            nova.setCompartilhar(false);
            nova.setExcluir(false);
            nova.setVisualizar(false);


            try {
                adm = usuarioService.buscarPorLogin(this.controlador.getView().getTxtAdmin().getText()).isEmpty() ? null : usuarioService.buscarPorLogin(this.controlador.getView().getTxtAdmin().getText()).get(0);
                if(adm.isAdimin()){
                    solicitacao = new Solicitacao();
                    solicitacao.setExcluir(this.controlador.getView().getRbExcluir().isSelected());
                    solicitacao.setCompartilhar(this.controlador.getView().getRbCompartilhar().isSelected());
                    solicitacao.setVisualizar(this.controlador.getView().getRbVisualizar().isSelected());
                    solicitacao.setPermissao(nova);
                    solicitacao.setUsuario(adm);

                    this.solicitacaoService.inserir(solicitacao);
                    JOptionPane.showMessageDialog(this.controlador.getView(), "Solicitação Enviada!", "Solicitar", JOptionPane.ERROR_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(this.controlador.getView(), "Usuário referênciado não é um administrador!", "Solicitar", JOptionPane.ERROR_MESSAGE);
                }


            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this.controlador.getView(), "Ocorreu um buscar o Administrador!\nVerifique se o login está correto\n"+ex, "Solicitar", JOptionPane.ERROR_MESSAGE);
                throw new RuntimeException("Erro: "+ex);
            }
        
        }
    }
    
}
