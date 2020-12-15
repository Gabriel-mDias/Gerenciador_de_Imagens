/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.view.solicitacoes;

import br.ufes.models.Solicitacao;
import br.ufes.models.Usuario;
import br.ufes.service.SolicitacaoService;
import br.ufes.singleton.JInternalCentralizador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author gabriel
 */
public class ListarSolicitacoesPresenter{
    
    private ListarSolicitacoesView view;
    private List<Solicitacao> dadosTabela;
    private SolicitacaoService solicitacoesService;
    

    public ListarSolicitacoesPresenter(JDesktopPane desktop, Usuario usuario) {
        this.view = new ListarSolicitacoesView();
        this.solicitacoesService = new SolicitacaoService();
        
        
        try {
            preencheTabela(solicitacoesService.buscarPorAdmin(usuario));
        } catch (Exception ex) {
            Logger.getLogger(ListarSolicitacoesPresenter.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
        
        this.view.getBtnAceitar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    solicitacoesService.aceitar(solicitacaoSelecionada());
                    JOptionPane.showMessageDialog(view, "Permissão concedida!\n", "Aceitar Solicitação", JOptionPane.ERROR_MESSAGE);
                    preencheTabela(solicitacoesService.buscarPorAdmin(usuario));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, "Erro ao conceder essa permissão!\n"+ex, "Aceitar Solicitação", JOptionPane.ERROR_MESSAGE);
                    throw new RuntimeException("Erro ao conceder a permissão: "+ex);
                }
            }
        });
        
        
        this.view.getBtnExcluir().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    solicitacoesService.deletar(solicitacaoSelecionada());
                    JOptionPane.showMessageDialog(view, "Solicitação excluida!\n", "Excluir Solicitação", JOptionPane.OK_OPTION);
                    preencheTabela(solicitacoesService.buscarPorAdmin(usuario));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, "Erro ao excluir essa solicitação!\n"+ex, "Excluir Solicitação", JOptionPane.ERROR_MESSAGE);
                    throw new RuntimeException("Erro ao excluir a solicitação: "+ex);
                }
            }
        });
        
        
        JInternalCentralizador.getInstancia().centralizarView(view, desktop);
    }
    
    private void preencheTabela(List<Solicitacao> solicitacoes){
        this.dadosTabela = solicitacoes;
        var listaSolicitacoes = new DefaultTableModel(
                new Object[][]{},
                new String[]{"id", "Usuario", "Excluir" , "Compartilhar", "Visualizar", "Título da Img" }
        );
        
        listaSolicitacoes.setNumRows(0);
        ListIterator<Solicitacao> it = solicitacoes.listIterator();
        
        while (it.hasNext()) {
            Solicitacao s = it.next();
            listaSolicitacoes.addRow(new Object[]{s.getId(), s.getPermissao().getUsuario().getLogin(), (s.isExcluir() ? "Sim" : "Não") , (s.isCompartilhar()? "Sim" : "Não") , (s.isVisualizar()? "Sim" : "Não"), s.getPermissao().getImagem().getTitulo() });
        }
        
        this.view.getTblSolicitacao().setModel(listaSolicitacoes);
    }
    
    private Solicitacao solicitacaoSelecionada(){
        int linha = this.view.getTblSolicitacao().getSelectedRow();
        if(linha < 0){
            JOptionPane.showMessageDialog(view, "Nenhuma solicitação foi selecionado!", "Tabela", 1);
        }else{
            return dadosTabela.get(linha);
        }
        return null;
    }

    public ListarSolicitacoesView getView() {
        return view;
    }

    
    
    
}
