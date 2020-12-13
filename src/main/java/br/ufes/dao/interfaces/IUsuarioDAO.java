
package br.ufes.dao.interfaces;

import br.ufes.models.Usuario;
import java.util.List;


public interface IUsuarioDAO {
    
    public Usuario findByLoginAndSenha(Usuario usuario) throws Exception;
    public List<Usuario> findAll() throws Exception;
    public List<Usuario> findByLogin(String login) throws Exception;
    public void save(Usuario usuario) throws Exception;
    public void edit(Usuario usuario) throws Exception;
    public void delete(Usuario usuario) throws Exception;
}
