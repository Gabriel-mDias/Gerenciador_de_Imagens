/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.dao.implement;

import br.ufes.dao.db.connection.ConectorSQLite;
import br.ufes.dao.interfaces.IImagemDAO;
import br.ufes.models.Imagem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gabriel
 */
public class ImagemDAO implements IImagemDAO{
    
    private ConectorSQLite gerenciadorConexao;

    public ImagemDAO() {
        this.gerenciadorConexao = new ConectorSQLite();
    }

    @Override
    public void insert(Imagem imagem) throws Exception {
        try{
            
            String SQL = "INSERT INTO Imagem (path, titulo) VALUES (?, ?) ";

            Connection conn = this.gerenciadorConexao.conectar();
            this.gerenciadorConexao.abreTransacao();

            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setString(1, imagem.getPath());
            ps.setString(2, imagem.getTitulo());
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
    public List<Imagem> getAllByIdUsuario(Long idUsuario) throws Exception {
        List<Imagem> imagens = new ArrayList<>();
        try{
            
            String SQL = "SELECT i.id, i.path, i.titulo FROM Imagem i INNER JOIN Permissao p ON p.id_imagem = i.id"+
                        " WHERE p.id_usuario = ?;";
            
            Connection conn = this.gerenciadorConexao.conectar();
            this.gerenciadorConexao.abreTransacao();
            
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setLong(1, idUsuario);
            ResultSet rs = ps.executeQuery();
            
            
            while(rs.next()){
                Imagem i = new Imagem();
                i.setId(rs.getLong(1));
                i.setPath(rs.getString(2));
                i.setTitulo(rs.getString(3));
                imagens.add(i);
            }
            
            this.gerenciadorConexao.fechaTransacao();
            this.gerenciadorConexao.close();
            
            return imagens;
            
        }catch(Exception e){
            this.gerenciadorConexao.desfazTransacao();
            this.gerenciadorConexao.close();
            throw new Exception("Erro ao buscar");
        }
    }

    @Override
    public List<Imagem> getByPath(String path) throws Exception {
        List<Imagem> imagens = new ArrayList<>();
        try{
            
            String SQL = "SELECT i.id, i.path, i.titulo FROM Imagem i WHERE path LIKE ? ;";
            
            Connection conn = this.gerenciadorConexao.conectar();
            this.gerenciadorConexao.abreTransacao();
            
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setString(1, path);
            ResultSet rs = ps.executeQuery();
            
            
            while(rs.next()){
                Imagem i = new Imagem();
                i.setId(rs.getLong(1));
                i.setPath(rs.getString(2));
                i.setTitulo(rs.getString(3));
                imagens.add(i);
            }
            
            this.gerenciadorConexao.fechaTransacao();
            this.gerenciadorConexao.close();
            
            return imagens;
            
        }catch(Exception e){
            this.gerenciadorConexao.desfazTransacao();
            this.gerenciadorConexao.close();
            throw new Exception("Erro ao buscar");
        }
    }
    
}
