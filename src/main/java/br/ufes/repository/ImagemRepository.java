/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.repository;

import br.ufes.dao.implement.ImagemDAO;
import br.ufes.dao.interfaces.IImagemDAO;
import br.ufes.models.imagem.ImagemReal;
import java.util.List;

/**
 *
 * @author gabriel
 */
public class ImagemRepository {
    
    private IImagemDAO imagemDAO;

    public ImagemRepository() {
        this.imagemDAO = new ImagemDAO();
    }
    
    public void insert(ImagemReal imagem) throws Exception{
        if(imagem == null){
            throw new RuntimeException("Inserção com imagem nula é inválida!");
        }else if(imagem.getPath() == null){
            throw new RuntimeException("Inserção de imagem com path nula é inválida!");
        }
        
        imagemDAO.insert(imagem);
    }
    public List<ImagemReal> getAllByIdUsuario(Long idUsuario) throws Exception{
        if(idUsuario == null){
            throw new RuntimeException("Busca com id nula é inválida!");
        }
        
        return imagemDAO.getAllByIdUsuario(idUsuario);
    }
    public List<ImagemReal> getByPath(String path) throws Exception{
        if(path == null){
            throw new RuntimeException("Busca com path nula é inválida!");
        }
        
        return imagemDAO.getByPath(path);
    }
    public void update(ImagemReal imagem) throws Exception{
        if(imagem == null){
            throw new RuntimeException("Atualização com imagem nula é inválida!");
        }else if(imagem.getPath() == null){
            throw new RuntimeException("Atualização de imagem com path nula é inválida!");
        }else if(imagem.getId() == null){
            throw new RuntimeException("Atualização de imagem com id nula é inválida!");
        }
        
        this.imagemDAO.update(imagem);
    }
}
