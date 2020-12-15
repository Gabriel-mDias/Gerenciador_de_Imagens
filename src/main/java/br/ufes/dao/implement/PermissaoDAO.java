/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.dao.implement;

import br.ufes.dao.db.connection.ConectorSQLite;
import br.ufes.dao.interfaces.IPermissaoDAO;
import br.ufes.models.imagem.ImagemReal;
import br.ufes.models.Permissao;
import br.ufes.models.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author gabriel
 */
public class PermissaoDAO implements IPermissaoDAO{

    private ConectorSQLite gerenciadorConexao;

    public PermissaoDAO() {
        this.gerenciadorConexao = new ConectorSQLite();
    }

    @Override
    public Permissao getPermissao(Usuario usuario, ImagemReal imagem) throws Exception {
        try{
            
            String SQL = "SELECT p.id, p.excluir, p.compartilhar, p.visualizar FROM Permissao p "
                    +   " INNER JOIN Usuario u ON u.id = p.id_usuario INNER JOIN Imagem i ON i.id = p.id_imagem"
                    + "  WHERE u.id = ? AND i.id = ?;";
            
            Connection conn = this.gerenciadorConexao.conectar();
            this.gerenciadorConexao.abreTransacao();
            
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setLong(1, usuario.getId());
            ps.setLong(2, imagem.getId());
            ResultSet rs = ps.executeQuery();
            
            Permissao p = null;
            while(rs.next()){
                p = new Permissao();
                p.setId(rs.getLong(1));
                p.setExcluir(rs.getBoolean(2));
                p.setCompartilhar(rs.getBoolean(3));
                p.setVisualizar(rs.getBoolean(4));
                p.setUsuario(usuario);
                p.setImagem(imagem);
            }
            
            this.gerenciadorConexao.fechaTransacao();
            this.gerenciadorConexao.close();
            
            return p;
            
        }catch(Exception e){
            this.gerenciadorConexao.desfazTransacao();
            this.gerenciadorConexao.close();
            throw new Exception("Erro ao buscar");
        }
    }

    @Override
    public Permissao getPermissaoByIdsUsuarioImagem(Permissao permissao) throws Exception {
        try{
            
            String SQL = "SELECT id, excluir, compartilhar, visualizar FROM Permissao "+
                        " WHERE id_usuario=? AND id_imagem=? ;";
            
            Connection conn = this.gerenciadorConexao.conectar();
            this.gerenciadorConexao.abreTransacao();
            
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setLong(1, permissao.getUsuario().getId());
            ps.setLong(2, permissao.getImagem().getId());
            ResultSet rs = ps.executeQuery();
            
            Permissao p = null;
            while(rs.next()){
                p = new Permissao();
                p.setId(rs.getLong(1));
                p.setExcluir(rs.getBoolean(2));
                p.setCompartilhar(rs.getBoolean(3));
                p.setVisualizar(rs.getBoolean(4));
            }
            
            this.gerenciadorConexao.fechaTransacao();
            this.gerenciadorConexao.close();
            
            return p;
            
        }catch(Exception e){
            this.gerenciadorConexao.desfazTransacao();
            this.gerenciadorConexao.close();
            throw new Exception("Erro ao buscar");
        }
    }
    
    @Override
    public Permissao getPermissaoById(Long id) throws Exception {
        try{
            
            String SQL = "SELECT p.id, p.excluir, p.compartilhar, p.visualizar, u.login, i.path, u.id, i.id "+
                        " FROM Permissao p JOIN Usuario u ON u.id = p.id_usuario JOIN Imagem i ON i.id = p.id_imagem"+
                        " WHERE p.id=? ;";
            
            Connection conn = this.gerenciadorConexao.conectar();
            this.gerenciadorConexao.abreTransacao();
            
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            
            Permissao p = null;
            Usuario u = null;
            ImagemReal i = null;
            while(rs.next()){
                p = new Permissao();
                u = new Usuario();
                i = new ImagemReal();
                p.setId(rs.getLong(1));
                p.setExcluir(rs.getBoolean(2));
                p.setCompartilhar(rs.getBoolean(3));
                p.setVisualizar(rs.getBoolean(4));
                u.setLogin(rs.getString(5));
                i.setPath(rs.getString(6));
                u.setId(rs.getLong(7));
                i.setId(rs.getLong(8));
                p.setUsuario(u);
                p.setImagem(i);
            }
            
            this.gerenciadorConexao.fechaTransacao();
            this.gerenciadorConexao.close();
            
            return p;
            
        }catch(Exception e){
            this.gerenciadorConexao.desfazTransacao();
            this.gerenciadorConexao.close();
            throw new Exception("Erro ao buscar");
        }
    }
    
    @Override
    public Long insert(Permissao permissao) throws Exception {
        try{
            
            String SQL = "INSERT INTO Permissao(id_imagem, id_usuario, excluir, compartilhar, visualizar) VALUES (?, ?, ?, ?, ?); ";
            
            Connection conn = this.gerenciadorConexao.conectar();
            this.gerenciadorConexao.abreTransacao();
            
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setLong(1, permissao.getImagem().getId());
            ps.setLong(2, permissao.getUsuario().getId());
            ps.setBoolean(3, permissao.isExcluir());
            ps.setBoolean(4, permissao.isCompartilhar());
            ps.setBoolean(5, permissao.isVisualizar());
            ps.executeUpdate();

            this.gerenciadorConexao.fechaTransacao();
            this.gerenciadorConexao.close();
            
        }catch(Exception e){
            this.gerenciadorConexao.desfazTransacao();
            this.gerenciadorConexao.close();
            throw new Exception("Erro ao inserir");
        }
        
        try{
            
            String SQL = "SELECT max(id) FROM Permissao ;";
            
            Connection conn = this.gerenciadorConexao.conectar();
            this.gerenciadorConexao.abreTransacao();
            
            PreparedStatement ps = conn.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            
            Long id = null;
            while(rs.next()){
                id = rs.getLong(1);
            }
            
            this.gerenciadorConexao.fechaTransacao();
            this.gerenciadorConexao.close();
            
            return id;
            
        }catch(Exception e){
            this.gerenciadorConexao.desfazTransacao();
            this.gerenciadorConexao.close();
            throw new Exception("Erro ao buscar");
        }
    }

    @Override
    public void update(Permissao permissao) throws Exception {
        try{
            
            String SQL = "UPDATE Permissao SET compartilhar = ?, excluir = ?, visualizar = ? WHERE id = ?;";
            
            Connection conn = this.gerenciadorConexao.conectar();
            this.gerenciadorConexao.abreTransacao();
            
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setBoolean(1, permissao.isCompartilhar());
            ps.setBoolean(2, permissao.isExcluir());
            ps.setBoolean(3, permissao.isVisualizar());
            ps.setLong(4, permissao.getId());
            ps.executeUpdate();

            this.gerenciadorConexao.fechaTransacao();
            this.gerenciadorConexao.close();
            
        }catch(Exception e){
            this.gerenciadorConexao.desfazTransacao();
            this.gerenciadorConexao.close();
            throw new Exception("Erro ao atualizar");
        }
    }
    
}
