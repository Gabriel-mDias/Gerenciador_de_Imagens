/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.view.imagem;

import br.ufes.models.imagem.ImagemReal;
import br.ufes.models.imagem.ImagemProxy;
import br.ufes.singleton.JInternalCentralizador;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;

/**
 *
 * @author gabriel
 */
public class VisualizarImgPresenter {
    
    private ImagemReal imagem;
    private VisualizarImgView view;

    public VisualizarImgPresenter(ImagemReal imagem, JDesktopPane desktop) {
        
        if(!imagem.isAtivo()){
            JOptionPane.showMessageDialog(view, "Imagem excluída previamente!\n", "Visualizar Imagem", JOptionPane.ERROR_MESSAGE);
        }else{
            this.imagem = imagem;
            this.view = new VisualizarImgView();

            this.atualizarImagem();

            JInternalCentralizador.getInstancia().centralizarView(view, desktop);
        }
    }
    
    private void atualizarImagem(){
        ImagemProxy proxy = new ImagemProxy(this.imagem);
        
        this.view.getLblImagem().setIcon(proxy.getImagemCompleta());
        this.view.getTxtTitulo().setText(proxy.getTitulo());
        this.view.getTxtPath().setText(proxy.getPath());
    }
                
    
}
