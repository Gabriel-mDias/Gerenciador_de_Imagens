/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.memento;

/**
 *
 * @author gabriel
 */
public class MementoImagem {
    
    private Long id;
    private String path;
    private String titulo;
    private boolean ativo;

    public MementoImagem(Long id, String path, String titulo, boolean ativo) {
        this.id = id;
        this.path = path;
        this.titulo = titulo;
        this.ativo = ativo;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    
    
}
