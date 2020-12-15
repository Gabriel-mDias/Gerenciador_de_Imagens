
package br.ufes.view.home;

import br.ufes.models.Usuario;
import br.ufes.observable.Observador;
import br.ufes.service.NotificacaoService;
import br.ufes.view.home.state.AdministradorHomePresenter;
import br.ufes.view.home.state.HomeState;
import br.ufes.view.home.state.UsuarioHomePresenter;
import br.ufes.view.imagem.ListarImgPresenter;
import br.ufes.view.notificacao.NotificacaoPresenter;
import br.ufes.view.solicitacoes.ListarSolicitacoesPresenter;
import br.ufes.view.usuario.ListarUsuariosPresenter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class HomePresenter implements Observador {
    
    private HomeView view;
    private Usuario logadoAtual;
    private HomeState estado;
    private NotificacaoService notificacaoService;

    public HomePresenter(Usuario logadoAtual) {
        this.logadoAtual = logadoAtual;
        this.view = new HomeView();
        this.notificacaoService = new NotificacaoService();
        
        atualizarValorNotificacoes();
        
        this.view.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                atualizarValorNotificacoes();
            }

            @Override
            public void focusLost(FocusEvent e) {
                atualizarValorNotificacoes();
            }
        });
        
        
        if(this.logadoAtual.isAdimin()){
            this.estado = new AdministradorHomePresenter(this);
        }else{
            this.estado = new UsuarioHomePresenter(this);
        }
        
        this.view.getItemListarImg().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ListarImgPresenter(getLogadoAtual(), view.getDesktop());
            }
        });
        
        this.view.getItemListaUsuario().addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ListarUsuariosPresenter(view.getDesktop());
            }
        });
        
        HomePresenter esse = this;  //Auxiliar para armazenar essa instância, a fim que o ActionListener veja
        this.view.getBtnNotificacoes().addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new NotificacaoPresenter(getLogadoAtual(), view.getDesktop()).addObservador(esse);
                atualizarValorNotificacoes();
            }
        });
        
        this.view.getItemSolicitacoes().addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ListarSolicitacoesPresenter(view.getDesktop(), getLogadoAtual());
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
    
    public void atualizarValorNotificacoes(){
        int totalNotificacoes = 0;
        try {
            totalNotificacoes = this.notificacaoService.listarTodas(this.logadoAtual.getId()).size();
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao calcular o total de notificações: "+ex);
        }
        this.view.getBtnNotificacoes().setText("Notificações("+totalNotificacoes+")");
    }

    @Override
    public void update() {
        atualizarValorNotificacoes();
    }
    
}

