
package br.ufes.view.home;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;


public class HomeView extends javax.swing.JFrame {

    
    public HomeView() {
        initComponents();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bottomMenu = new javax.swing.JPanel();
        lblUsuario = new javax.swing.JLabel();
        lblDataLogin = new javax.swing.JLabel();
        btnNotificacoes = new javax.swing.JButton();
        Desktop = new javax.swing.JDesktopPane();
        barTopo = new javax.swing.JMenuBar();
        menuImg = new javax.swing.JMenu();
        itemVisualizarImg = new javax.swing.JMenuItem();
        itemUsuario = new javax.swing.JMenu();
        itemListaUsuario = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bottomMenu.setForeground(new java.awt.Color(87, 66, 66));

        lblUsuario.setText("Usuário");

        lblDataLogin.setText("Data-Atual");

        btnNotificacoes.setText("Notificações");

        javax.swing.GroupLayout bottomMenuLayout = new javax.swing.GroupLayout(bottomMenu);
        bottomMenu.setLayout(bottomMenuLayout);
        bottomMenuLayout.setHorizontalGroup(
            bottomMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bottomMenuLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblDataLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNotificacoes, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        bottomMenuLayout.setVerticalGroup(
            bottomMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bottomMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bottomMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsuario)
                    .addComponent(lblDataLogin)
                    .addComponent(btnNotificacoes))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        menuImg.setText("Imagens");

        itemVisualizarImg.setText("Visualizar Imagens");
        itemVisualizarImg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemVisualizarImgActionPerformed(evt);
            }
        });
        menuImg.add(itemVisualizarImg);

        barTopo.add(menuImg);

        itemUsuario.setText("Administrador");

        itemListaUsuario.setText("Controle de Usuários");
        itemUsuario.add(itemListaUsuario);

        barTopo.add(itemUsuario);

        setJMenuBar(barTopo);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Desktop, javax.swing.GroupLayout.DEFAULT_SIZE, 965, Short.MAX_VALUE)
            .addComponent(bottomMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Desktop, javax.swing.GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bottomMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void itemVisualizarImgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemVisualizarImgActionPerformed
        
    }//GEN-LAST:event_itemVisualizarImgActionPerformed

    public JDesktopPane getDesktop() {
        return Desktop;
    }

    public JMenuItem getItemListaUsuario() {
        return itemListaUsuario;
    }

    public JMenuItem getItemVisualizarImg() {
        return itemVisualizarImg;
    }

    public JMenu getMenuAdmin() {
        return itemUsuario;
    }

    public JMenu getItemUsuario() {
        return itemUsuario;
    }

    public JLabel getLblUsuario() {
        return lblUsuario;
    }

    public JButton getBtnNotificacoes() {
        return btnNotificacoes;
    }

    public JLabel getLblDataLogin() {
        return lblDataLogin;
    }
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane Desktop;
    private javax.swing.JMenuBar barTopo;
    private javax.swing.JPanel bottomMenu;
    private javax.swing.JButton btnNotificacoes;
    private javax.swing.JMenuItem itemListaUsuario;
    private javax.swing.JMenu itemUsuario;
    private javax.swing.JMenuItem itemVisualizarImg;
    private javax.swing.JLabel lblDataLogin;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JMenu menuImg;
    // End of variables declaration//GEN-END:variables
}
