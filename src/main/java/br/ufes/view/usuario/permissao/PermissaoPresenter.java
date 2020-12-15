/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.view.usuario.permissao;

import br.ufes.models.Notificacao;
import br.ufes.models.Permissao;
import br.ufes.models.Usuario;
import br.ufes.models.imagem.ImagemProxy;
import br.ufes.models.imagem.ImagemReal;
import br.ufes.service.NotificacaoService;
import br.ufes.service.PermissaoService;
import br.ufes.singleton.JInternalCentralizador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;

/**
 *
 * @author gabriel
 */
public class PermissaoPresenter {
    
    private PermissaoView view;
    private List<ImagemReal> imagens;
    private List<Permissao> permissoes;
    private PermissaoService permissaoService;
    private NotificacaoService notificacaoService;
    private Usuario logado;

    public PermissaoPresenter(JDesktopPane desktop, Usuario logado) {
        this.view = new PermissaoView();
        this.permissaoService = new PermissaoService();
        this.logado = logado;
        this.notificacaoService = new NotificacaoService();
        
        this.carregaTabela();
        
        this.view.getTxtLogin().setText(logado.getLogin());
        this.view.getListaImg().addListSelectionListener((e) -> {
            int posicao = this.view.getListaImg().getSelectedIndex();
            if(posicao >= 0){
                this.view.getTxtPath().setText(imagens.get(posicao).getPath());
                this.view.getRbVisualizar().setSelected(permissoes.get(posicao).isVisualizar());
                this.view.getRbExcluir().setSelected(permissoes.get(posicao).isExcluir());
                this.view.getRbCompartilhar().setSelected(permissoes.get(posicao).isCompartilhar());
            }
            
        });
        
        this.view.getBtnAlterar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePermissao(view.getListaImg().getSelectedIndex());
            }
        });
        
        
        
        JInternalCentralizador.getInstancia().centralizarView(view, desktop);
    }
    
    private void carregaTabela(){
        try {
            this.imagens = new ArrayList<>();
            this.permissoes = this.permissaoService.buscaPorUsuario(this.logado);
            for(Permissao p : this.permissoes){
                imagens.add(p.getImagem());
            }
            listagemImg();
        } catch (Exception ex) {
            Logger.getLogger(PermissaoPresenter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void listagemImg(){
        int count = 0;
        DefaultListModel listModel = new DefaultListModel();
        for(ImagemReal imagemCadastrada : this.imagens){
            if ( imagemCadastrada.getPath().endsWith("jpg")  || imagemCadastrada.getPath().endsWith("jpeg") || imagemCadastrada.getPath().endsWith("png")) {
                ImagemProxy proxy = new ImagemProxy(imagemCadastrada.getPath(), imagemCadastrada.getTitulo());
                listModel.add(count++, proxy.getMiniatura());
            }
            
        }
            
        this.view.getListaImg().setModel(listModel);
        this.view.getListaImg().setVisibleRowCount(1);
    }
    
    private void updatePermissao(int posicao){
        Permissao atualizar = permissoes.get(posicao);
        
        atualizar.setExcluir(this.view.getRbExcluir().isSelected());
        atualizar.setCompartilhar(this.view.getRbCompartilhar().isSelected());
        atualizar.setVisualizar(this.view.getRbVisualizar().isSelected());
        Notificacao n = new Notificacao(atualizar.getUsuario(), "Suas permissões foram alteradas manualmente pelo administrador: "+logado.getLogin());
        
        try {
            this.permissaoService.update(atualizar);
            this.notificacaoService.novaNotificacao(n);
            JOptionPane.showMessageDialog(view, "Permissões modificadas!\n", "Alteração", JOptionPane.OK_OPTION);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Ocorreu um erro ao atualizar a permissão!\n"+ex.getMessage(), "Alteração", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(PermissaoPresenter.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro : "+ex);
        }
    }
    
}
