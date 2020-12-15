/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.view.imagem;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author gabriel
 */
public class AcessoView extends javax.swing.JInternalFrame {

    
    public AcessoView() {
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblUsuario = new javax.swing.JLabel();
        txtLogin = new javax.swing.JTextField();
        rbVisualizar = new javax.swing.JRadioButton();
        rbCompartilhar = new javax.swing.JRadioButton();
        rbExcluir = new javax.swing.JRadioButton();
        btnSolicitar = new javax.swing.JButton();
        lblAdmin = new javax.swing.JLabel();
        txtAdmin = new javax.swing.JTextField();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblUsuario.setText("Login do Usuário:");

        rbVisualizar.setText("Visualizar");

        rbCompartilhar.setText("Compartilhar");

        rbExcluir.setText("Excluir");

        btnSolicitar.setText("Solicitar");

        lblAdmin.setText("Login do Administrador:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSolicitar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtLogin)
                            .addComponent(lblUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(rbVisualizar)
                                .addGap(18, 18, 18)
                                .addComponent(rbCompartilhar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rbExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))
                            .addComponent(lblAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtAdmin))
                        .addGap(0, 57, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbVisualizar)
                    .addComponent(rbCompartilhar)
                    .addComponent(rbExcluir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblAdmin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addComponent(btnSolicitar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSolicitar;
    private javax.swing.JLabel lblAdmin;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JRadioButton rbCompartilhar;
    private javax.swing.JRadioButton rbExcluir;
    private javax.swing.JRadioButton rbVisualizar;
    private javax.swing.JTextField txtAdmin;
    private javax.swing.JTextField txtLogin;
    // End of variables declaration//GEN-END:variables

    public JButton getBtnSolicitar() {
        return btnSolicitar;
    }

    public JRadioButton getRbCompartilhar() {
        return rbCompartilhar;
    }

    public JRadioButton getRbExcluir() {
        return rbExcluir;
    }

    public JRadioButton getRbVisualizar() {
        return rbVisualizar;
    }

    public JTextField getTxtLogin() {
        return txtLogin;
    }

    public JTextField getTxtAdmin() {
        return txtAdmin;
    }

    public JLabel getLblAdmin() {
        return lblAdmin;
    }

    public JLabel getLblUsuario() {
        return lblUsuario;
    }

    
}
