/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.models.imagem;

import java.io.File;
import javax.swing.ImageIcon;

/**
 *
 * @author gabriel
 */
public class ImagemProxy implements Imagem{

    private ImagemReal imagem;
    
    private Long id;
    private String path;
    private String titulo;
    private boolean ativo;

    public ImagemProxy(String path, String titulo) {
        this.path = path;
        this.titulo = titulo;
    }

    public ImagemProxy(ImagemReal imagem) {
        this.imagem = imagem;
        this.path = imagem.getPath();
        this.titulo = imagem.getTitulo();
    }
    
    @Override
    public File loadDisk() {
        if(this.imagem == null){
            this.imagem = new ImagemReal(path,titulo);
        }
            
        return this.imagem.loadDisk();
    }

    @Override
    public ImageIcon getMiniatura() {
        if(this.imagem == null){
            this.imagem = new ImagemReal(path,titulo);
        }
            
        return this.imagem.getMiniatura();
    }

    @Override
    public ImageIcon getImagemCompleta() {
        if(this.imagem == null){
            this.imagem = new ImagemReal(path,titulo);
        }
            
        return this.imagem.getImagemCompleta();
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

    public ImagemReal getImagem() {
        return imagem = new ImagemReal(path, titulo);
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    
    
}
