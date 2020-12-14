package br.ufes.models.imagem;

import br.ufes.memento.MementoImagem;
import br.ufes.singleton.ImgManipulador;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.ImageIcon;

/**
 *
 * @author gabriel
 */
public class ImagemReal implements Imagem{
    
    private Long id;
    private String path;
    private String titulo;
    private boolean ativo;

    public ImagemReal(String path, String titulo) {
        this.path = path;
        this.titulo = titulo;
    }

    public ImagemReal() {
    }
    
    public void restaurar(MementoImagem memento){
        this.id = memento.getId();
        this.path = memento.getPath();
        this.titulo = memento.getTitulo();
        this.ativo = memento.isAtivo();
    }
    
    public MementoImagem getMemento(){
        return new MementoImagem(this.id, this.path, this.titulo, this.ativo);
    }
    
    @Override
    public File loadDisk() {
        return new File(this.path);
    }

    @Override
    public ImageIcon getMiniatura() {
        BufferedImage imgIcon = ImgManipulador.getInstancia().setImagemDimensao(this.getPath(), 120, 80);
        return new ImageIcon(imgIcon);
    }
    
    @Override
    public ImageIcon getImagemCompleta() {
        BufferedImage imgIcon = ImgManipulador.getInstancia().setImagemDimensao(this.getPath(),  450, 320);
        return new ImageIcon(imgIcon);
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

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

  
    
}
