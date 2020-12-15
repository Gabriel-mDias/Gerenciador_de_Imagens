
package br.ufes.dao.interfaces;

import br.ufes.models.imagem.ImagemReal;
import br.ufes.models.Permissao;
import br.ufes.models.Usuario;


public interface IPermissaoDAO {
    
    public Long insert(Permissao permissao) throws Exception;
    public void update(Permissao permissao) throws Exception;
    public Permissao getPermissao(Usuario usuario, ImagemReal imagem) throws Exception;
    public Permissao getPermissaoByIdsUsuarioImagem(Permissao permissao) throws Exception;
    public Permissao getPermissaoById(Long id) throws Exception;
}
