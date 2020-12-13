/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.view.home.state;

import br.ufes.view.home.HomePresenter;

/**
 *
 * @author gabriel
 */
public class UsuarioHomePresenter extends HomeState{
    
    public UsuarioHomePresenter(HomePresenter controlador) {
        super(controlador);
        
        this.controlador.getView().getMenuAdmin().setEnabled(false);
        this.controlador.getView().getLblUsuario().setText("Usuário logado: "+this.controlador.getLogadoAtual().getLogin()+" (Usuário)");
    }
    
}
