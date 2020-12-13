/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.view.usuario.state;

import br.ufes.models.Usuario;
import br.ufes.view.usuario.ManterUsuarioPresenter;
import javax.swing.JOptionPane;

/**
 *
 * @author gabriel
 */
public class EditarUsuarioState extends ManterUsuarioState{

    public EditarUsuarioState(ManterUsuarioPresenter contolador, Long idUsuario) {
        super(contolador, idUsuario);
    }


    @Override
    public void executar(Usuario usuario) {
        usuario.setId(idUsuario);
        try {
            usuarioService.editar(usuario);
            JOptionPane.showMessageDialog(contolador.getView(), "Usuário atualizado!", "Editado", 1);
        } catch (Exception ex) {
            new RuntimeException("Erro ao atualizar um usuário");
        }
    }
    
    
}
