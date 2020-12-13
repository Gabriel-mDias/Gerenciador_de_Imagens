
package br.ufes.view.home;

import br.ufes.models.Usuario;
import br.ufes.view.home.state.AdministradorHomePresenter;
import br.ufes.view.home.state.HomeState;
import br.ufes.view.home.state.UsuarioHomePresenter;
import br.ufes.view.imagem.ListarImgPresenter;
import br.ufes.view.notificacao.NotificacaoPresenter;
import br.ufes.view.usuario.ListarUsuariosPresenter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePresenter {
    
    private HomeView view;
    private Usuario logadoAtual;
    private HomeState estado;

    public HomePresenter(Usuario logadoAtual) {
        this.logadoAtual = logadoAtual;
        this.view = new HomeView();
        
        if(this.logadoAtual.isAdimin()){
            this.estado = new AdministradorHomePresenter(this);
        }else{
            this.estado = new UsuarioHomePresenter(this);
        }
        
        this.view.getItemVisualizarImg().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ListarImgPresenter(view.getDesktop());
            }
        });
        
        this.view.getItemListaUsuario().addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ListarUsuariosPresenter(view.getDesktop());
            }
        });
        
        this.view.getBtnNotificacoes().addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new NotificacaoPresenter(getLogadoAtual(), view.getDesktop());
            }
        });
        
        
        this.view.setLocationRelativeTo(null);
        this.view.setVisible(true);
    }
    
    
    public HomeView getView() {
        return view;
    }

    public Usuario getLogadoAtual() {
        return logadoAtual;
    }
    
    
}

