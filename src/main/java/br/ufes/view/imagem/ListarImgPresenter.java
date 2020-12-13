
package br.ufes.view.imagem;

import br.ufes.models.Imagem;
import br.ufes.service.ImagemService;
import br.ufes.singleton.ImgManipulador;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;


public class ListarImgPresenter {
    
    private ListarImgView view;
    private List<File> listOfFiles;
    List<Imagem> imagens;
    private ImagemService imagemService;
    

    public ListarImgPresenter(JDesktopPane desktop) {
        this.view = new ListarImgView();
        imagemService = new ImagemService();
        
        this.reloadImagensBanco();
        
        this.view.getListaImg().addListSelectionListener((e) -> {
            this.view.getTxtPath().setText(imagens.get(this.view.getListaImg().getSelectedIndex()).getPath());
        });
        
        this.view.getBtnBuscar().addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscaImagensBanco(view.getTxtPath().getText());
            }
        });
        
        
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
    
    private void listagemImg(){
        int count = 0;
        DefaultListModel listModel = new DefaultListModel();
        for(Imagem imagemCadastrada : this.imagens){
            if ( imagemCadastrada.getPath().endsWith("jpg")  || imagemCadastrada.getPath().endsWith("jpeg") || imagemCadastrada.getPath().endsWith("png")) {
                BufferedImage imagem;
                imagem = ImgManipulador.getInstancia().setImagemDimensao(imagemCadastrada.getPath(), 120, 80);
                ImageIcon ii = new ImageIcon(imagem);
                listModel.add(count++, ii);
            }
            
        }
            
        this.view.getListaImg().setModel(listModel);
        this.view.getListaImg().setVisibleRowCount(1);
    }
    
    private void reloadImagensBanco(){  //Inserir todas as imagens da pasta no banco
        String path = "./img" ; //código do banco para pegar todas imagens do diretório

        this.imagens = new ArrayList<>();
        File folder = new File(path);
        listOfFiles = new ArrayList<>();
        try {
            Files.walk(Paths.get(path)).forEach( arquivo -> {
                listOfFiles.add(arquivo.toFile());
            } );
        
        } catch (IOException ex) {
            Logger.getLogger(ListarImgPresenter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for(File arquivo : listOfFiles){
            String name = arquivo.toString();

            if ( name.endsWith("jpg")  || name.endsWith("jpeg") || name.endsWith("png")) {         
                imagens.add(new Imagem(arquivo.getPath(), arquivo.getName()));
            }
            
        }
        try {
            this.imagemService.inserirTodas(imagens);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Ocorreu um erro ao inserir novas imagens no banco!\n"+ex, "Inserção", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException("Erro ao inserir: "+ex);
        }
        
        this.listagemImg();
    }
    
    private void buscaImagensBanco(String path){
        try {
            this.imagens = imagemService.getBySubPath(path);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Ocorreu um erro ao buscar as imagens no banco!\n"+ex, "Busca", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException("Erro ao buscar: "+ex);
        }
        
        this.listagemImg();
    }
}

