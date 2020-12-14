
package br.ufes.dao.interfaces;

import br.ufes.models.imagem.ImagemReal;
import br.ufes.models.Permissao;
import br.ufes.models.Usuario;
import java.util.List;


public interface IPermissaoDAO {
    
    public List<Permissao> getByIdUsuario(Long id) throws Exception;
    public Permissao getPermissao(Usuario usuario, ImagemReal imagem) throws Exception;
}
