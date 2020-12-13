
package br.ufes.models;


public class Permissao {
    
    private Long id;
    private Usuario usuario;
    private Imagem imagem;
    private boolean visualizar;
    private boolean excluir;
    private boolean editar;

    public Permissao(Long id, Usuario usuario, Imagem imagem, boolean visualizar, boolean excluir, boolean editar) {
        this.id = id;
        this.usuario = usuario;
        this.imagem = imagem;
        this.visualizar = visualizar;
        this.excluir = excluir;
        this.editar = editar;
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

    public Imagem getImagem() {
        return imagem;
    }

    public void setImagem(Imagem imagem) {
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

    public boolean isEditar() {
        return editar;
    }

    public void setEditar(boolean editar) {
        this.editar = editar;
    }
    
    
}
