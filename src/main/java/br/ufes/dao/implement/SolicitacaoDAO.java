/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.dao.implement;

import br.ufes.dao.db.connection.ConectorSQLite;
import br.ufes.dao.interfaces.ISolicitacaoDAO;
import br.ufes.models.Permissao;
import br.ufes.models.Solicitacao;
import br.ufes.models.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gabriel
 */
public class SolicitacaoDAO implements ISolicitacaoDAO{
    
    private ConectorSQLite gerenciadorConexao;

    public SolicitacaoDAO() {
        this.gerenciadorConexao = new ConectorSQLite();
    }
    
    @Override
    public void insert(Solicitacao solicitacao) throws Exception {
        try{
            
            String SQL = "INSERT INTO Solicitacao(excluir, compartilhar, visualizar, id_permissao, id_admin) VALUES (?, ?, ?, ?, ?); ";
            
            Connection conn = this.gerenciadorConexao.conectar();
            this.gerenciadorConexao.abreTransacao();
            
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setBoolean(1, solicitacao.isExcluir());
            ps.setBoolean(2, solicitacao.isCompartilhar());
            ps.setBoolean(3, solicitacao.isVisualizar());
            ps.setLong(4, solicitacao.getPermissao().getId());
            ps.setLong(5, solicitacao.getUsuario().getId());
            ps.executeUpdate();

            this.gerenciadorConexao.fechaTransacao();
            this.gerenciadorConexao.close();
            
        }catch(Exception e){
            this.gerenciadorConexao.desfazTransacao();
            this.gerenciadorConexao.close();
            throw new Exception("Erro ao inserir");
        }
    }

    @Override
    public void delete(Solicitacao solicitacao) throws Exception {
        try{
            
            String SQL = "DELETE FROM Solicitacao WHERE id_permissao = ? AND id_admin = ?;";
            
            Connection conn = this.gerenciadorConexao.conectar();
            this.gerenciadorConexao.abreTransacao();
            
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setLong(1, solicitacao.getPermissao().getId());
            ps.setLong(2, solicitacao.getUsuario().getId());
            ps.executeUpdate();

            this.gerenciadorConexao.fechaTransacao();
            this.gerenciadorConexao.close();
            
        }catch(Exception e){
            this.gerenciadorConexao.desfazTransacao();
            this.gerenciadorConexao.close();
            throw new Exception("Erro ao excluir");
        }
    }
    
    @Override
    public List<Solicitacao> getAllByAdmin(Usuario usuario) throws Exception{
        List<Solicitacao> solicitacoes = new ArrayList<>();
        try{
            
            String SQL = "SELECT s.id, s.excluir, s.compartilhar, s.visualizar, s.id_permissao, u.id, u.login "+
                        " FROM Solicitacao s INNER JOIN Permissao p ON p.id = s.id_permissao INNER JOIN Usuario u ON u.id = p.id_usuario"+
                        " WHERE s.id_admin = ? ;";
            
            Connection conn = this.gerenciadorConexao.conectar();
            this.gerenciadorConexao.abreTransacao();
            
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setLong(1, usuario.getId());
            ResultSet rs = ps.executeQuery();
            
            
            while(rs.next()){
                Solicitacao s = new Solicitacao();
                Permissao p = new Permissao();
                Usuario u = new Usuario();
                s.setId(rs.getLong(1));
                s.setExcluir(rs.getBoolean(2));
                s.setCompartilhar(rs.getBoolean(3));
                s.setVisualizar(rs.getBoolean(4));
                p.setId(rs.getLong(5));
                s.setUsuario(usuario);
                u.setId(rs.getLong(6));
                u.setLogin(rs.getString(7));
                p.setUsuario(u);
                s.setPermissao(p);
                solicitacoes.add(s);
            }
            
            this.gerenciadorConexao.fechaTransacao();
            this.gerenciadorConexao.close();
            
            return solicitacoes;
            
        }catch(Exception e){
            this.gerenciadorConexao.desfazTransacao();
            this.gerenciadorConexao.close();
            throw new Exception("Erro ao buscar");
        }
    }
}
