/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.view.imagem;

import br.ufes.models.imagem.ImagemReal;
import br.ufes.models.Usuario;
import br.ufes.singleton.ImgManipulador;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.beans.PropertyVetoException;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;

/**
 *
 * @author gabriel
 */
public class VisualizarImgPresenter {
    
    private ImagemReal imagem;
    private VisualizarImgView view;

    public VisualizarImgPresenter(ImagemReal imagem, JDesktopPane desktop) {
        this.imagem = imagem;
        this.view = new VisualizarImgView();
        
        this.atualizarImagem();
        
        this.view.setVisible(true);
        desktop.add(this.view);
        Dimension desktopSize = desktop.getSize();
        this.view.setLocation((desktopSize.width - this.view.getSize().width) / 2,(desktopSize.height - this.view.getSize().height) / 2);
        try {
            this.view.setSelected(true);
        } catch (PropertyVetoException ex) {
            new RuntimeException("Erro ao requisitar o foco para um JInternalFrame");
        }
    }
    
    private void atualizarImagem(){
        BufferedImage imgeVisualizada;
        imgeVisualizada = ImgManipulador.getInstancia().setImagemDimensao(this.imagem.getPath(), 450, 320);
        ImageIcon imgIcon = new ImageIcon(imgeVisualizada);
        
        this.view.getLblImagem().setIcon(imgIcon);
        this.view.getTxtTitulo().setText(this.imagem.getTitulo());
        this.view.getTxtPath().setText(this.imagem.getPath());
    }
                
    
}
