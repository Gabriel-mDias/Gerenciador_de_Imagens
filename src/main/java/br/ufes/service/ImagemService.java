/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.service;

import br.ufes.memento.MementoImagem;
import br.ufes.models.imagem.ImagemReal;
import br.ufes.repository.ImagemRepository;
import java.util.List;

/**
 *
 * @author gabriel
 */
public class ImagemService {
    
    private ImagemRepository imagemRepository;

    public ImagemService() {
        this.imagemRepository = new ImagemRepository();
    }
    
    public List<ImagemReal> buscarPorPath(String path) throws Exception{
        return imagemRepository.getByPath(path);
    }
    
    public List<ImagemReal> buscarPorIdUsuario(Long idUsuario) throws Exception{
        return imagemRepository.getAllByIdUsuario(idUsuario);
    }
    
    public void inserir(ImagemReal imagem) throws Exception{
        if(this.imagemRepository.getByPath(imagem.getPath()).isEmpty()){
            this.imagemRepository.insert(imagem);
        }
        
    }
    
    public void inserirTodas(List<ImagemReal> imagens) throws Exception{
        for(ImagemReal imagem : imagens){
            this.inserir(imagem);
        }
    }
    
    public List<ImagemReal> getBySubPath(String path) throws Exception{
        return imagemRepository.getByPath(path+"%");
    }
    
    public void update(ImagemReal imagem) throws Exception{
        this.imagemRepository.update(imagem);
    }
    
    public MementoImagem delete(ImagemReal imagem) throws Exception{
        imagem = this.buscarPorPath(imagem.getPath()).get(0);
        MementoImagem preDelete = imagem.getMemento();
        imagem.setAtivo(false);
        this.imagemRepository.update(imagem);
        return preDelete;
    }
    
    public void desfazer(ImagemReal imagem, MementoImagem memento) throws Exception{
        if(imagem.getId() == memento.getId()){
            imagem.restaurar(memento);
            this.imagemRepository.update(imagem);
        }
    }
}

