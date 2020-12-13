
package br.ufes.view.login;

import br.ufes.models.Usuario;
import br.ufes.service.UsuarioService;
import br.ufes.view.home.HomePresenter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;


public class LoginPresenter {
    
    private LoginView view;
    private UsuarioService usuarioService;

    public LoginPresenter() {
        this.view = new LoginView();
        usuarioService = new UsuarioService();
        
        this.view.getBtnLogin().addActionListener( new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Usuario u = obterUsuarioView();
                    u = usuarioService.efetuarLogin(u);
                    if(u != null){
                        new HomePresenter(u);
                        view.setVisible(false);
                    }else{
                        JOptionPane.showMessageDialog(view, "Erro ao logar, verifique se os dados informados estão corretos!", "Usuário não encontrado", 1);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, ex);
                }
            }
        
        });
        
        this.view.setVisible(true);
    }
    
    private Usuario obterUsuarioView(){
        return new Usuario(this.view.getTxtLogin().getText(), String.valueOf(this.view.getTxtSenha().getPassword()));
    } 
}
