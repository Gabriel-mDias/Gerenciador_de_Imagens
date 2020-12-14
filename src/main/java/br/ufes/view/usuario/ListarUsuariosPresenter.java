/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.view.usuario;

import br.ufes.models.Usuario;
import br.ufes.observable.Observador;
import br.ufes.service.UsuarioService;
import br.ufes.singleton.JInternalCentralizador;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.util.List;
import java.util.ListIterator;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author gabriel
 */
public class ListarUsuariosPresenter implements Observador{
    
    private ListarUsuariosView view;
    private List<Usuario> dadosTabela;
    private UsuarioService usuarioService;
    

    public ListarUsuariosPresenter(JDesktopPane desktop) {
        this.view = new ListarUsuariosView();
        this.usuarioService = new UsuarioService();
        
        update();
        
        ListarUsuariosPresenter atual = this;
        this.view.getBtnEditar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Usuario u = usuarioSelecionado();
                if(u!=null){
                    new ManterUsuarioPresenter(u, desktop).addObservador(atual);
                }
            }
        });
        
        this.view.getBtnNovo().addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManterUsuarioPresenter(desktop).addObservador(atual);
            }
        });
        
        this.view.getBtnExcluir().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Usuario u = usuarioSelecionado();
                if(u!=null){
                    try {
                        usuarioService.deletar(u);
                        JOptionPane.showMessageDialog(view, "Usuário excluido", "Exclusão", 1);
                        update();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(view, "Ocorreu um erro ao deletar o usuário!\n"+ex, "Exclusão", JOptionPane.ERROR_MESSAGE);
                        throw new RuntimeException("Ocorreu um erro ao deletar o usuário!\n"+ex);
                    }
                }
            }
        });
        
        
        this.view.getBtnBusca().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    preencheTabela(usuarioService.buscarPorLogin(view.getTxtBusca().getText()));
                } catch (Exception ex) {
                    new RuntimeException("Erro ao realizar a consulta!");
                }
            }
        });
        
        JInternalCentralizador.getInstancia().centralizarView(view, desktop);
    }
    
    private void preencheTabela(List<Usuario> usuarios){
        this.dadosTabela = usuarios;
        var listaUsuarios = new DefaultTableModel(
                new Object[][]{},
                new String[]{"id", "Login", "Administrador"}
        );
        
        listaUsuarios.setNumRows(0);
        ListIterator<Usuario> it = usuarios.listIterator();
        
        while (it.hasNext()) {
            Usuario user = it.next();
            listaUsuarios.addRow(new Object[]{user.getId(), user.getLogin(), (user.isAdimin()? "Sim" : "Não") });
        }
        
        this.view.getTblUsuarios().setModel(listaUsuarios);
    }
    
    private Usuario usuarioSelecionado(){
        int linha = this.view.getTblUsuarios().getSelectedRow();
        if(linha < 0){
            JOptionPane.showMessageDialog(view, "Nenhum usuário foi selecionado!", "Tabela", 1);
        }else{
            return dadosTabela.get(linha);
        }
        return null;
    }

    public ListarUsuariosView getView() {
        return view;
    }

    @Override
    public void update() {
        try {
            this.preencheTabela(usuarioService.buscarTodos());
        } catch (Exception ex) {
            throw new RuntimeException("Ocorreu um erro ao buscar os usuários!\n"+ex);
        }
    }
    
    
}
