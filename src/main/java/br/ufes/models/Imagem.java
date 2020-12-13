package br.ufes.models;

/**
 *
 * @author gabriel
 */
public class Imagem {
    
    private Long id;
    private String path;
    private String titulo;

    public Imagem(String path, String titulo) {
        this.path = path;
        this.titulo = titulo;
    }

    public Imagem() {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    
    
}
