
package br.ufes.repository;

import br.ufes.dao.implement.UsuarioDAO;
import br.ufes.dao.interfaces.IUsuarioDAO;
import br.ufes.models.Solicitacao;
import br.ufes.models.Usuario;
import java.util.List;


public class UsuarioRepository {
    
    private IUsuarioDAO usuarioDAO;

    public UsuarioRepository() {
        this.usuarioDAO = new UsuarioDAO();
    }
    
    public Usuario findByLoginAndSenha(Usuario usuario) throws Exception{
        if(usuario == null){
            throw new RuntimeException("Consulta com usuário nulo é inválida!");
        }else if(usuario.getLogin() == null){
            throw new RuntimeException("Consulta com login do usuário nulo é inválida!");
        }else if(usuario.getSenha() == null){
            throw new RuntimeException("Consulta com senha do usuário nulo é inválida!");
        }
        
        return usuarioDAO.findByLoginAndSenha(usuario);
    }
    
    public List<Usuario> findAll() throws Exception{
        return usuarioDAO.findAll();
    }

    public void save(Usuario usuario) throws Exception{
        if(usuario == null){
            throw new RuntimeException("Inserção com usuário nulo é inválida!");
        }else if(usuario.getLogin() == null){
            throw new RuntimeException("Inserção com login do usuário nulo é inválida!");
        }else if(usuario.getSenha() == null){
            throw new RuntimeException("Inserção com senha do usuário nulo é inválida!");
        }
        usuarioDAO.save(usuario);
    }

    public void edit(Usuario usuario) throws Exception{
        if(usuario == null){
            throw new RuntimeException("Edição com usuário nulo é inválida!");
        }else if(usuario.getLogin() == null){
            throw new RuntimeException("Edição com login do usuário nulo é inválida!");
        }else if(usuario.getSenha() == null){
            throw new RuntimeException("Edição com senha do usuário nulo é inválida!");
        }else if(usuario.getId() == null){
            throw new RuntimeException("Edição com Id do usuário nulo é inválida!");
        }
        usuarioDAO.edit(usuario);
    }
    
    public void delete(Usuario usuario) throws Exception{
        if(usuario == null){
            throw new RuntimeException("Exclusão com usuário nulo é inválida!");
        }else if(usuario.getLogin() == null){
            throw new RuntimeException("Exclusão com login do usuário nulo é inválida!");
        }else if(usuario.getId() == null){
            throw new RuntimeException("Exclusão com Id do usuário nulo é inválida!");
        }
        usuarioDAO.delete(usuario);
    }
    
    public List<Usuario> findByLogin(String login) throws Exception{
        if(login==null){
            throw new RuntimeException("Consulta, por login, com o mesmo nulo é inválida!");
        }
        
        return usuarioDAO.findByLogin(login);
    }
    
}
