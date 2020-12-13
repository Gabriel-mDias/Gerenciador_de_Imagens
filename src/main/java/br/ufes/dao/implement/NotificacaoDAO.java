/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.dao.implement;

import br.ufes.dao.db.connection.ConectorSQLite;
import br.ufes.dao.interfaces.INotificacaoDAO;
import br.ufes.models.Notificacao;
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
public class NotificacaoDAO implements INotificacaoDAO{

    private ConectorSQLite gerenciadorConexao;

    public NotificacaoDAO() {
        this.gerenciadorConexao = new ConectorSQLite();
    }
    
    @Override
    public void insert(Notificacao notificacao) throws Exception {
        try{
            
            String SQL = "INSERT INTO Notificacao(mensagem, id_usuario) VALUES (?, ?) ";
            
            Connection conn = this.gerenciadorConexao.conectar();
            this.gerenciadorConexao.abreTransacao();
            
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setString(1, notificacao.getMensagem());
            ps.setLong(2, notificacao.getUsuario().getId());
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
    public void delete(Notificacao notificacao) throws Exception {
        try{
            
            String SQL = "DELETE FROM Notificacao WHERE id = ? AND id_usuario = ?;";
            
            Connection conn = this.gerenciadorConexao.conectar();
            this.gerenciadorConexao.abreTransacao();
            
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setLong(1, notificacao.getId());
            ps.setLong(1, notificacao.getUsuario().getId());
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
    public void deleteAllByIdUsuario(Long idUsuario) throws Exception {
        try{
            
            String SQL = "DELETE FROM Notificacao WHERE id_usuario = ?;";
            
            Connection conn = this.gerenciadorConexao.conectar();
            this.gerenciadorConexao.abreTransacao();
            
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setLong(1, idUsuario);
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
    public List<Notificacao> getAllByIdUsuario(Long idUsuario) throws Exception {
        List<Notificacao> notificacoes = new ArrayList<>();
        try{
            
            String SQL = "SELECT n.id, n.mensagem, n.id_usuario FROM Notificacao n "+
                        " WHERE n.id_usuario = ?;";
            
            Connection conn = this.gerenciadorConexao.conectar();
            this.gerenciadorConexao.abreTransacao();
            
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setLong(1, idUsuario);
            ResultSet rs = ps.executeQuery();
            
            
            while(rs.next()){
                Usuario u = new Usuario();
                Notificacao n = new Notificacao();
                n.setId(rs.getLong(1));
                n.setMensagem(rs.getString(2));
                n.setUsuario(u.comId(rs.getLong(3)));
                notificacoes.add(n);
            }
            
            this.gerenciadorConexao.fechaTransacao();
            this.gerenciadorConexao.close();
            
            return notificacoes;
            
        }catch(Exception e){
            this.gerenciadorConexao.desfazTransacao();
            this.gerenciadorConexao.close();
            throw new Exception("Erro ao buscar");
        }
    }
    
    
    
}
