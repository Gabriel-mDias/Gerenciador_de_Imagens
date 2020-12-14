/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.dao.interfaces;

import br.ufes.models.imagem.ImagemReal;
import java.util.List;

/**
 *
 * @author gabriel
 */
public interface IImagemDAO {
    
    public void insert(ImagemReal imagem) throws Exception;
    public List<ImagemReal> getAllByIdUsuario(Long idUsuario) throws Exception;
    public List<ImagemReal> getByPath(String path) throws Exception;
    
}
