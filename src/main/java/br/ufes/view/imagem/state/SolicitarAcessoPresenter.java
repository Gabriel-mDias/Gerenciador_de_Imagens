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
public class SolicitarAcessoPresenter extends ManterAcessoState{

    public SolicitarAcessoPresenter(ManterAcessoPresenter presenter) {
        super(presenter);
        this.controlador.getView().getTxtLogin().setEnabled(false);
    }

    @Override
    public void executar(Usuario usuario, ImagemReal imagem) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
