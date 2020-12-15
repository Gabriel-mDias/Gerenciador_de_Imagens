/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.view.login.cadastro;

import br.ufes.models.Usuario;
import br.ufes.service.UsuarioService;
import br.ufes.view.login.LoginPresenter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gabriel
 */
public class CadastroPresenter {

    private CadastroView view;
    private UsuarioService usuarioService;
    
    public CadastroPresenter(LoginPresenter login) {
        this.view = new CadastroView();
        this.usuarioService = new UsuarioService();
        
        this.view.getBtnLogin().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Usuario u = obterUsuarioView();
                    if(usuarioService.buscarPorLogin(u.getLogin()).size() == 0){
                        u.setAdimin(true);
                        usuarioService.inserir(u);
                        new LoginPresenter();
                        view.setVisible(false);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(CadastroPresenter.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        this.view.setVisible(true);
    }
    
    private Usuario obterUsuarioView(){
        return new Usuario(this.view.getTxtLogin().getText(), String.valueOf(this.view.getTxtSenha().getPassword()));
    } 
}
