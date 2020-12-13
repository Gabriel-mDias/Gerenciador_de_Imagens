/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.view.usuario.state;

import br.ufes.models.Usuario;
import br.ufes.service.UsuarioService;
import br.ufes.view.usuario.ManterUsuarioPresenter;

/**
 *
 * @author gabriel
 */
public abstract class ManterUsuarioState {
    
    protected ManterUsuarioPresenter contolador;
    protected Long idUsuario;
    protected UsuarioService usuarioService;

    public ManterUsuarioState(ManterUsuarioPresenter contolador) {
        this.contolador = contolador;
        this.usuarioService = new UsuarioService();
        idUsuario = null;
    }

    public ManterUsuarioState(ManterUsuarioPresenter contolador, Long idUsuario) {
        this.contolador = contolador;
        this.idUsuario = idUsuario;
        this.usuarioService = new UsuarioService();
    }
    
    public abstract void executar(Usuario usuario);
}
