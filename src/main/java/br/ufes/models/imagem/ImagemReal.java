package br.ufes.models.imagem;

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

    public ImagemReal(String path, String titulo) {
        this.path = path;
        this.titulo = titulo;
    }

    public ImagemReal() {
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

    @Override
    public File loadDisk() {
        return new File(this.path);
    }

    @Override
    public ImageIcon getMiniatura() {
        BufferedImage imgIcon = ImgManipulador.getInstancia().setImagemDimensao(this.getPath(), 120, 80);
        return new ImageIcon(imgIcon);
    }

    
    
    
}
