/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tela;

import DAO.CidadeDAO;
import DAO.ClienteDAO;
import DAO.FormaPagamentoDAO;
import entidade.Cidade;
import entidade.Cliente;
import entidade.FormaPagamento;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Mileto
 */
public class JdgCadastroCidade extends javax.swing.JDialog {

    Cidade cidade = new Cidade();

    public JdgCadastroCidade(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        verificarCadastroSelecionado();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        rbtAtivo = new javax.swing.JRadioButton();
        tfdDescricao = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        tfdCodigo = new javax.swing.JTextField();
        btnLocalizar = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("EasyPDV - Cadastro Cidade");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cadastro de Cidade", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(0, 0, 255))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rbtAtivo.setSelected(true);
        rbtAtivo.setText("Ativo");
        rbtAtivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtAtivoActionPerformed(evt);
            }
        });
        jPanel1.add(rbtAtivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        tfdDescricao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfdDescricaoActionPerformed(evt);
            }
        });
        jPanel1.add(tfdDescricao, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, 241, -1));

        jLabel2.setText("Descrição*");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 53, -1, -1));

        jLabel1.setText("Código");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 22, -1, -1));

        tfdCodigo.setEnabled(false);
        jPanel1.add(tfdCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 51, -1));

        btnLocalizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Lupa3.png"))); // NOI18N
        btnLocalizar.setText("Localizar");
        btnLocalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocalizarActionPerformed(evt);
            }
        });
        jPanel1.add(btnLocalizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/confirmar.png"))); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });
        jPanel1.add(btnSalvar, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 110, -1, -1));

        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icon_Schutdown16.png"))); // NOI18N
        btnSair.setText("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });
        jPanel1.add(btnSair, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 110, 79, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        
        try {
            if (verificarCadastrado()) {
                
                if (tfdDescricao.getText().length() <= 150 && tfdDescricao.getText().length() > 0) {
                    
                    cidade.setDescricao(tfdDescricao.getText());
                    
                    if (rbtAtivo.isSelected()) {
                        cidade.setAtivo('T');
                    } else {
                        cidade.setAtivo('F');
                    }
                    
                    CidadeDAO cidadeDAO = new CidadeDAO();
                    
                    if (cidadeDAO.salvar(cidade)) {
                        limparcampos();
                        JOptionPane.showMessageDialog(null, "Cidade Salva com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Erro ao salvar o registro");
                    }
                } else {
                    tfdDescricao.setText("");
                    tfdDescricao.requestFocus();
                     JOptionPane.showMessageDialog(null, "Erro ao salvar Cidade \n"
                            + "Descrição da cidade em branco ou maior que 150 caracteres");
                    
                }
            }else{
                tfdDescricao.requestFocus();
                JOptionPane.showMessageDialog(null, "Erro ao salvar Cidade: \n"
                            + "Cidade já cadastrada");
            }
        } catch (SQLException ex) {
            Logger.getLogger(JdgCadastroCidade.class.getName()).log(Level.SEVERE, null, ex);
        }

       
            
        

    }//GEN-LAST:event_btnSalvarActionPerformed

    private boolean verificarCadastrado() throws SQLException {

        CidadeDAO cidDAO = new CidadeDAO();
        cidade.setDescricao(tfdDescricao.getText());

        ArrayList<Cidade> cidades = cidDAO.consultar(cidade);
        if (cidade.getId() == 0) {

            if (cidades.size() > 0) {

                if (tfdDescricao.getText().equalsIgnoreCase(cidades.get(0).getDescricao())) {
                   // System.out.println("false");
                    return false;
                } else {
                  //  System.out.println("true");
                    return true;
                }
            }
        }
        return true;
    }

    private void limparcampos() {
        cidade.setAtivo(' ');
        rbtAtivo.setSelected(true);
        tfdCodigo.setText("");
        tfdDescricao.setText("");
        tfdDescricao.requestFocus();
        cidade.setId(0);
        cidade.setDescricao("");
    }


    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        dispose();
    }//GEN-LAST:event_btnSairActionPerformed

    private void tfdDescricaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfdDescricaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfdDescricaoActionPerformed

    private void btnLocalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocalizarActionPerformed
        String auxDescricao = tfdDescricao.getText();
        JdgListaCidade listaCidade = new JdgListaCidade(null, true, cidade);
        listaCidade.setVisible(true);
        
        verificarCadastroSelecionado();
        if (tfdDescricao.getText().length()<1) {
            tfdDescricao.setText(auxDescricao);
        }
        tfdDescricao.requestFocus();
    }//GEN-LAST:event_btnLocalizarActionPerformed

    private void rbtAtivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtAtivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbtAtivoActionPerformed

    private void verificarCadastroSelecionado() {

        if (cidade.getId() > 0) {
            if (cidade.getAtivo() == 'T') {
                rbtAtivo.setSelected(true);

            } else {
                rbtAtivo.setSelected(false);
            }

            tfdCodigo.setText(String.valueOf(cidade.getId()));
            tfdDescricao.setText(cidade.getDescricao());

        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JdgCadastroCidade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JdgCadastroCidade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JdgCadastroCidade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JdgCadastroCidade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JdgCadastroCidade dialog = new JdgCadastroCidade(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLocalizar;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSalvar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton rbtAtivo;
    private javax.swing.JTextField tfdCodigo;
    private javax.swing.JTextField tfdDescricao;
    // End of variables declaration//GEN-END:variables
}
