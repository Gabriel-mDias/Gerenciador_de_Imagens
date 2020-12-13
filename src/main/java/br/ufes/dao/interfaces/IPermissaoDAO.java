
package br.ufes.dao.interfaces;

import br.ufes.models.Permissao;
import java.util.List;


public interface IPermissaoDAO {
    
    public List<Permissao> getByIdUsuario(Long id) throws Exception;
}
