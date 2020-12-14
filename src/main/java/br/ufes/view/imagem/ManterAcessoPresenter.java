/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.view.imagem;

import br.ufes.models.imagem.ImagemReal;
import br.ufes.models.Usuario;
import br.ufes.service.UsuarioService;
import br.ufes.singleton.JInternalCentralizador;
import br.ufes.view.imagem.state.CompartilharAcessoPresenter;
import br.ufes.view.imagem.state.ManterAcessoState;
import br.ufes.view.imagem.state.SolicitarAcessoPresenter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;

/**
 *
 * @author gabriel
 */
public class ManterAcessoPresenter {
    
    private AcessoView view;
    private Usuario logado;
    private ManterAcessoState estado;
    private UsuarioService usuarioService;
    private ImagemReal imgSelecionada;

    public ManterAcessoPresenter(Usuario logado, ImagemReal imagem, JDesktopPane desktop) {
        this.logado = logado;
        this.view = new AcessoView();
        this.view.getTxtLogin().setText(logado.getLogin());
        this.estado = new SolicitarAcessoPresenter(this);
        this.usuarioService = new UsuarioService();
        this.imgSelecionada = imagem;
        
        this.view.getBtnSolicitar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                estado.executar(logado, imgSelecionada);
            }
        });
        
        
        JInternalCentralizador.getInstancia().centralizarView(view, desktop);
    }

    public ManterAcessoPresenter(ImagemReal imagem, JDesktopPane desktop) {
        this.view = new AcessoView();
        this.usuarioService = new UsuarioService();
        this.estado = new CompartilharAcessoPresenter(this);
        this.imgSelecionada = imagem;
        
        this.view.getBtnSolicitar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    estado.executar(usuarioService.buscarPorLogin(view.getTxtLogin().getText()).get(0), imgSelecionada);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, "Ocorreu um erro ao buscar o usuário em questão no banco!\n"+ex, "Busca", JOptionPane.ERROR_MESSAGE);
                    throw new RuntimeException("Erro ao buscar: "+ex);
                }
            }
        });
        
        
        JInternalCentralizador.getInstancia().centralizarView(view, desktop);
    }

    public AcessoView getView() {
        return view;
    }

    public Usuario getLogado() {
        return logado;
    }
    
    
}
