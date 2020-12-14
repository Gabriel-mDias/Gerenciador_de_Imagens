/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.singleton;

import java.awt.Dimension;
import java.beans.PropertyVetoException;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

/**
 *
 * @author gabriel
 */
public class JInternalCentralizador {
    
    private static JInternalCentralizador centralizador = null;

    private JInternalCentralizador() {
    }
    
    public static JInternalCentralizador getInstancia(){
        if(centralizador == null){
            centralizador = new JInternalCentralizador();
        }
        
        return centralizador;
    }
    
    public void centralizarView(JInternalFrame view, JDesktopPane desktop){
        view.setVisible(true);
        desktop.add(view);
        Dimension desktopSize = desktop.getSize();
        view.setLocation((desktopSize.width - view.getSize().width) / 2,(desktopSize.height - view.getSize().height) / 2);
        try {
            view.setSelected(true);
        } catch (PropertyVetoException ex) {
            new RuntimeException("Erro ao requisitar o foco para um JInternalFrame");
        }
    }
}
