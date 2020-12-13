/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.models;

/**
 *
 * @author gabriel
 */
public class Notificacao {
    
    private Long id;
    private Usuario usuario;
    private String mensagem;

    public Notificacao(Long id, Usuario usuario, String mensagem) {
        this.id = id;
        this.usuario = usuario;
        this.mensagem = mensagem;
    }

    public Notificacao(Usuario usuario, String mensagem) {
        this.usuario = usuario;
        this.mensagem = mensagem;
    }

    
    
    public Notificacao() {
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

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
    
    
}
