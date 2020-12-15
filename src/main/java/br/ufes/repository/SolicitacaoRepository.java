/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.repository;

import br.ufes.dao.implement.SolicitacaoDAO;
import br.ufes.dao.interfaces.ISolicitacaoDAO;
import br.ufes.models.Solicitacao;
import br.ufes.models.Usuario;
import java.util.List;

/**
 *
 * @author gabriel
 */
public class SolicitacaoRepository {
    private ISolicitacaoDAO solicitacaoDAO;

    public SolicitacaoRepository() {
        this.solicitacaoDAO = new SolicitacaoDAO();
    }
    
    public void insert(Solicitacao solicitacao) throws Exception{
        if(solicitacao == null){
            throw new RuntimeException("Solicitação nula é inválida!");
        }
        this.solicitacaoDAO.insert(solicitacao);
    }
    public void delete(Solicitacao solicitacao) throws Exception{
        if(solicitacao == null){
            throw new RuntimeException("Solicitação nula é inválida!");
        }
        this.solicitacaoDAO.delete(solicitacao);
    }
    
    public List<Solicitacao> getAllByAdmin(Usuario administrador) throws Exception{
        if(administrador == null){
            throw new RuntimeException("Solicitação com adiministrador nulo é inválida!");
        }
        
        return solicitacaoDAO.getAllByAdmin(administrador);
    }
}
