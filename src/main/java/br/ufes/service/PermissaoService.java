/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.service;

import br.ufes.models.Permissao;
import br.ufes.repository.PermissaoRepository;
import java.util.List;

/**
 *
 * @author gabriel
 */
public class PermissaoService {
    
    private PermissaoRepository permissaoRepository;

    public PermissaoService() {
        this.permissaoRepository = new PermissaoRepository();
    }
    
    public List<Permissao> buscaPermissao(Long idUsuario) throws Exception{
        return permissaoRepository.getByIdUsuario(idUsuario);
    }
}
