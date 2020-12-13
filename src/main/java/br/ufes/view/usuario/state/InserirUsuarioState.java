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
public class InserirUsuarioState extends ManterUsuarioState{
    
    
    public InserirUsuarioState(ManterUsuarioPresenter contolador) {
        super(contolador);
    }

    @Override
    public void executar(Usuario usuario) {
        try {
            usuarioService.inserir(usuario);
            JOptionPane.showMessageDialog(contolador.getView(), "Usuário cadastrado!", "Cadastrado", 1);
        } catch (Exception ex) {
            new RuntimeException("Erro ao inserir um usuário");
        }
    }
    
}
