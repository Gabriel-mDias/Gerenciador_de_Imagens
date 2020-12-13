
package br.ufes.dao.implement;

import br.ufes.dao.db.connection.ConectorSQLite;
import br.ufes.dao.interfaces.IUsuarioDAO;
import br.ufes.models.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class UsuarioDAO implements IUsuarioDAO{

    private ConectorSQLite gerenciadorConexao;

    public UsuarioDAO() {
        this.gerenciadorConexao = new ConectorSQLite();
    }
    
    @Override
    public Usuario findByLoginAndSenha(Usuario usuario) throws Exception{
        Usuario retorno = null;
        try{
            
            String SQL = "SELECT u.id, u.login, u.isAdmin FROM Usuario u"+
                        " WHERE u.login = ? AND u.senha = ? ;";
            
            Connection conn = this.gerenciadorConexao.conectar();
            this.gerenciadorConexao.abreTransacao();
            
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setString(1, usuario.getLogin());
            ps.setString(2, usuario.getSenha());
            ResultSet rs = ps.executeQuery();
            
            
            while(rs.next()){
                retorno = new Usuario();
                retorno.setId(rs.getLong(1));
                retorno.setLogin(rs.getString(2));
                retorno.setAdimin(rs.getBoolean(3));
            }
            
            this.gerenciadorConexao.fechaTransacao();
            this.gerenciadorConexao.close();
            
            return retorno;
            
        }catch(Exception e){
            this.gerenciadorConexao.desfazTransacao();
            this.gerenciadorConexao.close();
            throw new Exception("Erro ao buscar");
        }
    }
    
    @Override
    public List<Usuario> findAll() throws Exception{
        List<Usuario> retorno = new ArrayList<>();
        try{
            
            String SQL = "SELECT u.id, u.login, u.isAdmin FROM Usuario u ;";
            
            Connection conn = this.gerenciadorConexao.conectar();
            this.gerenciadorConexao.abreTransacao();
            
            PreparedStatement ps = conn.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            
            
            while(rs.next()){
                retorno.add(new Usuario(rs.getLong(1), rs.getString(2), rs.getBoolean(3)));
            }
            
            this.gerenciadorConexao.fechaTransacao();
            this.gerenciadorConexao.close();
            
            return retorno;
            
        }catch(Exception e){
            this.gerenciadorConexao.desfazTransacao();
            this.gerenciadorConexao.close();
            throw new Exception("Erro ao buscar");
        }
    }
    
    @Override
    public List<Usuario> findByLogin(String login) throws Exception {
       List<Usuario> retorno = new ArrayList<>();
        try{
            
            String SQL = "SELECT u.id, u.login, u.isAdmin FROM Usuario u WHERE u.login LIKE ?;";
            
            Connection conn = this.gerenciadorConexao.conectar();
            this.gerenciadorConexao.abreTransacao();
            
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setString(1, "%"+login+"%");
            ResultSet rs = ps.executeQuery();
            
            
            while(rs.next()){
                retorno.add(new Usuario(rs.getLong(1), rs.getString(2), rs.getBoolean(3)));
            }
            
            this.gerenciadorConexao.fechaTransacao();
            this.gerenciadorConexao.close();
            
            return retorno;
            
        }catch(Exception e){
            this.gerenciadorConexao.desfazTransacao();
            this.gerenciadorConexao.close();
            throw new Exception("Erro ao buscar");
        }
    }

    @Override
    public void save(Usuario usuario) throws Exception {
        try{
            
            String SQL = "INSERT INTO Usuario(login, senha, isAdmin) "+
                        " VALUES(?, ?, ?); ";
            
            Connection conn = this.gerenciadorConexao.conectar();
            this.gerenciadorConexao.abreTransacao();
            
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setString(1, usuario.getLogin());
            ps.setString(2, usuario.getSenha());
            ps.setBoolean(3, usuario.isAdimin());
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
    public void edit(Usuario usuario) throws Exception {
        try{
            
            String SQL = "UPDATE Usuario SET login = ? , senha = ?, isAdmin = ? "+
                        " WHERE id = ?;";
            
            Connection conn = this.gerenciadorConexao.conectar();
            this.gerenciadorConexao.abreTransacao();
            
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setString(1, usuario.getLogin());
            ps.setString(2, usuario.getSenha());
            ps.setBoolean(3, usuario.isAdimin());
            ps.setLong(4, usuario.getId());
            ps.executeUpdate();

            this.gerenciadorConexao.fechaTransacao();
            this.gerenciadorConexao.close();
            
        }catch(Exception e){
            this.gerenciadorConexao.desfazTransacao();
            this.gerenciadorConexao.close();
            throw new Exception("Erro ao atualizar");
        }
    }

    @Override
    public void delete(Usuario usuario) throws Exception {
        try{
            
            String SQL = "DELETE FROM Usuario WHERE id = ?;";
            
            Connection conn = this.gerenciadorConexao.conectar();
            this.gerenciadorConexao.abreTransacao();
            
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setLong(1, usuario.getId());
            ps.executeUpdate();

            this.gerenciadorConexao.fechaTransacao();
            this.gerenciadorConexao.close();
            
        }catch(Exception e){
            this.gerenciadorConexao.desfazTransacao();
            this.gerenciadorConexao.close();
            throw new Exception("Erro ao excluir");
        }
    }

    
    
}
