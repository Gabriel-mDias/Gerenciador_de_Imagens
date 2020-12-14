/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.view.imagem.state;

import br.ufes.models.imagem.ImagemReal;
import br.ufes.models.Usuario;
import br.ufes.view.imagem.ManterAcessoPresenter;

/**
 *
 * @author gabriel
 */
public abstract class ManterAcessoState {

    protected ManterAcessoPresenter controlador;
    
    public ManterAcessoState(ManterAcessoPresenter presenter) {
        this.controlador = presenter;
    }
    
    public abstract void executar(Usuario usuario, ImagemReal imagem);
}
