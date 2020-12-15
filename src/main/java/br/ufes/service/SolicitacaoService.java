/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.service;

import br.ufes.models.Notificacao;
import br.ufes.models.Permissao;
import br.ufes.models.Solicitacao;
import br.ufes.models.Usuario;
import br.ufes.models.imagem.ImagemReal;
import br.ufes.repository.ImagemRepository;
import br.ufes.repository.NotificacaoRepository;
import br.ufes.repository.PermissaoRepository;
import br.ufes.repository.SolicitacaoRepository;
import java.util.List;

/**
 *
 * @author gabriel
 */
public class SolicitacaoService {
    
    private SolicitacaoRepository solicitacaoRepository;
    private PermissaoRepository permissaoRepository;
    private NotificacaoRepository notificacaoRepository;
    private ImagemRepository imagemRepository;

    public SolicitacaoService() {
        this.solicitacaoRepository = new SolicitacaoRepository();
        this.permissaoRepository = new PermissaoRepository();
        this.notificacaoRepository = new NotificacaoRepository();
        this.imagemRepository = new ImagemRepository();
    }
    
    public void inserir(Solicitacao solicitacao) throws Exception{
        Long id = this.permissaoRepository.insert(solicitacao.getPermissao());
        solicitacao.getPermissao().setId(id);
               
        this.solicitacaoRepository.insert(solicitacao);
        
        this.notificacaoRepository.insert(new Notificacao(solicitacao.getUsuario(), "Existe uma nova solicitação para permissões do: "+solicitacao.getPermissao().getUsuario().getLogin()));
    }
    
    public void editar(Solicitacao solicitacao) throws Exception{
        this.solicitacaoRepository.insert(solicitacao);
        
        this.notificacaoRepository.insert(new Notificacao(solicitacao.getUsuario(), "Existe uma nova solicitação para permissões do: "+solicitacao.getPermissao().getUsuario().getLogin()));
    }
    
    public void aceitar(Solicitacao solicitacao) throws Exception{
        Permissao aceita = solicitacao.getPermissao();
        aceita.setCompartilhar(solicitacao.isCompartilhar());
        aceita.setExcluir(solicitacao.isExcluir());
        aceita.setVisualizar(solicitacao.isVisualizar());
        
        this.permissaoRepository.update(aceita);
        this.solicitacaoRepository.delete(solicitacao);
        
        this.notificacaoRepository.insert(new Notificacao(solicitacao.getPermissao().getUsuario(),"Uma solicitação sua foi aceita pelo administrador: "+solicitacao.getUsuario().getLogin()));
    }
    
    public void deletar(Solicitacao solicitacao) throws Exception{
        this.solicitacaoRepository.delete(solicitacao);
        
        this.notificacaoRepository.insert(new Notificacao(solicitacao.getPermissao().getUsuario(), "Uma solicitação sua foi recusada pelo administrador: "+solicitacao.getUsuario().getLogin()));
    }
    
    public List<Solicitacao> buscarPorAdmin(Usuario admin) throws Exception{
        List<Solicitacao> solicitacoes =  this.solicitacaoRepository.getAllByAdmin(admin);
        Permissao p;
        for(Solicitacao s : solicitacoes){
            p = permissaoRepository.getPermissaoById(s.getPermissao().getId());
            p.setImagem(imagemRepository.getByPath(p.getImagem().getPath()).get(0));
            s.setPermissao(p);
        }
        return solicitacoes;
    }
    
    
}
