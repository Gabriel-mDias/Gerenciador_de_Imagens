/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.models;

import br.ufes.models.imagem.ImagemReal;

/**
 *
 * @author gabriel
 */
public class Solicitacao {
    private Long id;
    private Usuario usuario;
    private Permissao permissao;
    private boolean excluir;
    private boolean visualizar;
    private boolean compartilhar;

    public Solicitacao(Long id, Usuario usuario, Permissao permissao, boolean excluir, boolean visualizar, boolean compartilhar) {
        this.id = id;
        this.usuario = usuario;
        this.permissao = permissao;
        this.excluir = excluir;
        this.visualizar = visualizar;
        this.compartilhar = compartilhar;
    }

    

    public Solicitacao() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Permissao getPermissao() {
        return permissao;
    }

    public void setPermissao(Permissao permissao) {
        this.permissao = permissao;
    }

    

    public boolean isExcluir() {
        return excluir;
    }

    public void setExcluir(boolean excluir) {
        this.excluir = excluir;
    }

    public boolean isVisualizar() {
        return visualizar;
    }

    public void setVisualizar(boolean visualizar) {
        this.visualizar = visualizar;
    }

    public boolean isCompartilhar() {
        return compartilhar;
    }

    public void setCompartilhar(boolean compartilhar) {
        this.compartilhar = compartilhar;
    }
    
    
}
