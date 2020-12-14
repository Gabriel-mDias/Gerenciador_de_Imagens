
package br.ufes.models;

import br.ufes.models.imagem.ImagemReal;


public class Permissao {
    
    private Long id;
    private Usuario usuario;
    private ImagemReal imagem;
    private boolean visualizar;
    private boolean excluir;
    private boolean compartilhar;

    public Permissao(Long id, Usuario usuario, ImagemReal imagem, boolean visualizar, boolean excluir, boolean compartilhar) {
        this.id = id;
        this.usuario = usuario;
        this.imagem = imagem;
        this.visualizar = visualizar;
        this.excluir = excluir;
        this.compartilhar = compartilhar;
    }

    public Permissao() {
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

    public ImagemReal getImagem() {
        return imagem;
    }

    public void setImagem(ImagemReal imagem) {
        this.imagem = imagem;
    }

    public boolean isVisualizar() {
        return visualizar;
    }

    public void setVisualizar(boolean visualizar) {
        this.visualizar = visualizar;
    }

    public boolean isExcluir() {
        return excluir;
    }

    public void setExcluir(boolean excluir) {
        this.excluir = excluir;
    }

    public boolean isCompartilhar() {
        return compartilhar;
    }

    public void setCompartilhar(boolean compartilhar) {
        this.compartilhar = compartilhar;
    }
    
    
}
