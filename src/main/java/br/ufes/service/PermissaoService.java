/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.service;

import br.ufes.models.Permissao;
import br.ufes.models.Usuario;
import br.ufes.models.imagem.ImagemReal;
import br.ufes.repository.ImagemRepository;
import br.ufes.repository.PermissaoRepository;
import java.util.List;

/**
 *
 * @author gabriel
 */
public class PermissaoService {
    
    private PermissaoRepository permissaoRepository;
    private ImagemRepository imagemRepository;

    public PermissaoService() {
        this.permissaoRepository = new PermissaoRepository();
        this.imagemRepository = new ImagemRepository();
    }
    
    public Permissao buscaPermissao(Usuario usuario, ImagemReal imagem) throws Exception{
        if(imagem.getId() == null){
            imagem = this.imagemRepository.getByPath(imagem.getPath()).get(0);
        }
        return permissaoRepository.getPermissao(usuario, imagem);
    }
    
    public Permissao buscaPermissaoId(Permissao permissao) throws Exception{
        return permissaoRepository.getPermissaoByIdsUsuarioImagem(permissao);
    }
    
    public Long inserir(Permissao permissao) throws Exception{
        return this.permissaoRepository.insert(permissao);
    }
    
    public List<Permissao> buscaPorUsuario(Usuario usuario) throws Exception{
        return this.permissaoRepository.getByUsuario(usuario);
    }
    
    public void update(Permissao permissao) throws Exception{
        this.permissaoRepository.update(permissao);
    }
}
