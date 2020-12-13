
package br.ufes.service;

import br.ufes.models.Notificacao;
import br.ufes.models.Usuario;
import br.ufes.repository.NotificacaoRepository;
import br.ufes.repository.UsuarioRepository;
import java.time.LocalDate;
import java.util.List;


public class UsuarioService {
    
    private UsuarioRepository usuarioRepository;
    private NotificacaoRepository notificacaoRepository;

    public UsuarioService() {
        this.usuarioRepository = new UsuarioRepository();
        this.notificacaoRepository = new NotificacaoRepository();
    }
    
    public Usuario efetuarLogin(Usuario usuario) throws Exception{
        return usuarioRepository.findByLoginAndSenha(usuario);
    }
    
    public List<Usuario> buscarTodos() throws Exception{
        return usuarioRepository.findAll();
    }
    
    public List<Usuario> buscarPorLogin(String login) throws Exception{
        return usuarioRepository.findByLogin(login);
    }
    
    public void inserir(Usuario usuario) throws Exception{
        usuarioRepository.save(usuario);
    }
    
    public void editar(Usuario usuario) throws Exception{
        usuarioRepository.edit(usuario);
        
        notificacaoRepository.insert(new Notificacao(usuario, "Uma alteração em seu registro foi efetuada às "+LocalDate.now()+"! Entre em contato com um administrador"));
    }
    
    public void deletar(Usuario usuario) throws Exception{
        usuarioRepository.delete(usuario);
    }
}
   
