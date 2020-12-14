
package br.ufes.view.imagem;

import br.ufes.memento.ZeladorImagem;
import br.ufes.models.imagem.ImagemReal;
import br.ufes.models.Usuario;
import br.ufes.models.imagem.ImagemProxy;
import br.ufes.service.ImagemService;
import br.ufes.singleton.JInternalCentralizador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;


public class ListarImgPresenter {
    
    private ListarImgView view;
    private List<File> listOfFiles;
    private List<ImagemReal> imagens;
    private ImagemService imagemService;
    private Usuario logado;
    private ZeladorImagem zelador;
    

    public ListarImgPresenter(Usuario usuario, JDesktopPane desktop) {
        this.view = new ListarImgView();
        imagemService = new ImagemService();
        zelador = new ZeladorImagem();
        
        this.reloadImagensBanco();
        
        this.view.getListaImg().addListSelectionListener((e) -> {
            int posicao = this.view.getListaImg().getSelectedIndex();
            if(posicao >= 0){
                this.view.getTxtPath().setText(imagens.get(this.view.getListaImg().getSelectedIndex()).getPath());
            }
            
        });
        
        this.view.getBtnBuscar().addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscaImagensBanco(view.getTxtPath().getText());
            }
        });
        
        this.view.getBtnExcluir().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    zelador.addMemento(imagemService.delete(getImgSelecionada()));                    
                    JOptionPane.showMessageDialog(view, "Imagem excluída!\n", "Excluir Imagem", JOptionPane.OK_OPTION);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, "Erro ao excluir essa imagem!\n"+ex, "Excluir Imagem", JOptionPane.ERROR_MESSAGE);
                    throw new RuntimeException("Erro ao excluir a imagem: "+ex);
                }
            }
        });
        
        this.view.getBtnDesfazer().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ImagemReal i = new ImagemReal();
                    if(zelador.historicoDesfazer()){
                        i.restaurar(zelador.getUltimo());
                        imagemService.update(i);
                        JOptionPane.showMessageDialog(view, "Imagem recuperada!\n", "Desfazer", JOptionPane.OK_OPTION);
                    }else{
                        JOptionPane.showMessageDialog(view, "Não há imagens para recuperar!\n", "Desfazer", JOptionPane.OK_OPTION);
                    }
                    
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, "Erro ao recuperar essa imagem!\n"+ex, "Desfazer", JOptionPane.ERROR_MESSAGE);
                    throw new RuntimeException("Erro ao atualizar a imagem: "+ex);
                }
            }
        });
        
        
        this.view.getBtnVisualizar().addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    new VisualizarImgPresenter(imagemService.buscarPorPath(getImgSelecionada().getPath()).get(0), desktop);
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(view, "Erro ao visualizar essa imagem!\n"+ex, "Visualizar Imagem", JOptionPane.ERROR_MESSAGE);
                    throw new RuntimeException("Erro ao visualizar a imagem: "+ex);
                }
            }
        });
        
        this.view.getBtnSolicitar().addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManterAcessoPresenter(usuario, getImgSelecionada(), desktop);
            }
        });
        
        this.view.getBtnCompartilhar().addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManterAcessoPresenter(getImgSelecionada(), desktop);
            }
        });
        
        JInternalCentralizador.getInstancia().centralizarView(view, desktop);
    }
    
    private void listagemImg(){
        int count = 0;
        DefaultListModel listModel = new DefaultListModel();
        for(ImagemReal imagemCadastrada : this.imagens){
            if ( imagemCadastrada.getPath().endsWith("jpg")  || imagemCadastrada.getPath().endsWith("jpeg") || imagemCadastrada.getPath().endsWith("png")) {
                ImagemProxy proxy = new ImagemProxy(imagemCadastrada.getPath(), imagemCadastrada.getTitulo());
                listModel.add(count++, proxy.getMiniatura());
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
                imagens.add(new ImagemReal(arquivo.getPath(), arquivo.getName()));
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
    
    private ImagemReal getImgSelecionada(){
        return imagens.get(this.view.getListaImg().getSelectedIndex());
    }
    
}

