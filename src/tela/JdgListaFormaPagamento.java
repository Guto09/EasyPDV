/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tela;

import DAO.FormaPagamentoDAO;
import entidade.FormaPagamento;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mileto
 */
public class JdgListaFormaPagamento extends javax.swing.JDialog {

    FormaPagamento fp;

    public JdgListaFormaPagamento(java.awt.Frame parent, boolean modal, FormaPagamento fp) throws Exception {
        super(parent, modal);
        initComponents();
        this.fp = fp;
        verificarTipoChamada();
        popularComboBox();
        listarFormasPagamento();

    }

    public JdgListaFormaPagamento(java.awt.Frame parent, boolean modal) throws Exception {
        super(parent, modal);
        initComponents();

    }
    
     private void verificarTipoChamada() {
        //System.out.println("ativo cliente na lista" + fornecedor.getAtivo());
        if (fp.getAtivo() == 'T') {
            cbxStatus.setEnabled(false);
            //System.out.println("ativo cliente na lista" + fornecedor.getAtivo());
            btnConfirmar.setText("Selecionar");
            btnConfirmar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/confirmar.png")));
        } else {
            btnConfirmar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Edit File-16.png")));
            cbxStatus.setEditable(true);
        }
    }
    

    private void popularComboBox() {

        cbxStatus.addItem("Ativos");
        cbxStatus.addItem("Inativos");
        cbxStatus.addItem("Todos");
    }

    private void listarFormasPagamento() {
        try {
            //setar para tabela modelo de dados
            tblFormaPagamento.setModel(this.obterDadosParaJTable());
            tblFormaPagamento.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblFormaPagamento.getColumnModel().getColumn(1).setPreferredWidth(270);
            tblFormaPagamento.getColumnModel().getColumn(2).setPreferredWidth(1);
            tblFormaPagamento.getColumnModel().getColumn(3).setPreferredWidth(1);

        } catch (Exception ex) {
            Logger.getLogger(JdgListaFormaPagamento.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public DefaultTableModel obterDadosParaJTable() throws Exception {
        DefaultTableModel dtm = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
//adiciona titulo para as colunas
        FormaPagamentoDAO fpDAO = new FormaPagamentoDAO();
        ArrayList<FormaPagamento> formas = fpDAO.consultar(fp);
        dtm.addColumn("ID");
        dtm.addColumn("DESCRIÇÃO");
        dtm.addColumn("FORMA");
        dtm.addColumn("SITUAÇÃO");

        for (int i = 0; i < formas.size(); i++) {
            //popular tabela
            String resultAtivo = "";
            String resultPrazo = "";
            if (String.valueOf(formas.get(i).getAtivo()).equalsIgnoreCase("T")) {
                resultAtivo = "Ativo";
            } else {
                resultAtivo = "Inativo";
            }
            if (String.valueOf(formas.get(i).getFormaAvista()).equalsIgnoreCase("T")) {
                resultPrazo = "Avista";
            } else {
                resultPrazo = "Prazo";
            }
            dtm.addRow(new String[]{String.valueOf(formas.get(i).getId()),
                formas.get(i).getDescricao(), resultPrazo, resultAtivo});
        }
//retorna o modelo
        return dtm;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblFormaPagamento = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnConfirmar = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        tfdDescricao = new javax.swing.JTextField();
        cbxStatus = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("EasyPDV - Lista formas de pagamento");

        tblFormaPagamento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Id", "Descrição", "Ativo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblFormaPagamento.setFocusable(false);
        tblFormaPagamento.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tblFormaPagamentoAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        tblFormaPagamento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblFormaPagamentoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tblFormaPagamentoMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblFormaPagamentoMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblFormaPagamento);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 204));
        jLabel1.setText("Lista Formas de pagamento");

        btnConfirmar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Edit File-16.png"))); // NOI18N
        btnConfirmar.setText("Editar");
        btnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarActionPerformed(evt);
            }
        });

        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icon_Schutdown16.png"))); // NOI18N
        btnSair.setText("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        jLabel2.setText("Descrição:");

        tfdDescricao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfdDescricaoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfdDescricaoKeyReleased(evt);
            }
        });

        cbxStatus.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxStatusItemStateChanged(evt);
            }
        });

        jLabel3.setText("Status:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(56, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfdDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67))
            .addGroup(layout.createSequentialGroup()
                .addGap(173, 173, 173)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(36, 36, 36))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tfdDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConfirmar)
                    .addComponent(btnSair))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
        selecionado();

    }//GEN-LAST:event_btnConfirmarActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        dispose();
    }//GEN-LAST:event_btnSairActionPerformed

    private void tblFormaPagamentoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblFormaPagamentoMouseClicked
        if (evt.getClickCount() > 1) {
            int linhaSelecionada = tblFormaPagamento.getSelectedRow();
            selecionado();
            dispose();
        }
    }//GEN-LAST:event_tblFormaPagamentoMouseClicked

    private void tblFormaPagamentoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblFormaPagamentoMousePressed

    }//GEN-LAST:event_tblFormaPagamentoMousePressed

    private void tblFormaPagamentoAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tblFormaPagamentoAncestorAdded

    }//GEN-LAST:event_tblFormaPagamentoAncestorAdded

    private void tblFormaPagamentoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblFormaPagamentoMouseEntered

    }//GEN-LAST:event_tblFormaPagamentoMouseEntered

    private void tfdDescricaoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfdDescricaoKeyPressed


    }//GEN-LAST:event_tfdDescricaoKeyPressed

    private void cbxStatusItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxStatusItemStateChanged
        listar();
    }//GEN-LAST:event_cbxStatusItemStateChanged

    private void tfdDescricaoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfdDescricaoKeyReleased
        listar();
    }//GEN-LAST:event_tfdDescricaoKeyReleased
    private void listar() {
        fp.setDescricao(tfdDescricao.getText().toLowerCase());
        if (cbxStatus.getSelectedItem().equals("Todos")) {
            fp.setAtivo(' ');
        } else if (cbxStatus.getSelectedItem().equals("Ativos")) {
            fp.setAtivo('T');

        } else if (cbxStatus.getSelectedItem().equals("Inativos")) {
            fp.setAtivo('F');

        }
        listarFormasPagamento();

    }

    private void selecionado() {
        FormaPagamento fp = new FormaPagamento();
        //pega a linha selecionada
        int row = tblFormaPagamento.getSelectedRow();

        //seta o ID do objeto da linha selecionada
        this.fp.setId(Integer.parseInt(tblFormaPagamento.getValueAt(row, 0).toString()));
        this.fp.setDescricao(tblFormaPagamento.getValueAt(row, 1).toString());
        if (tblFormaPagamento.getValueAt(row, 2).toString().equals("Avista")) {
            this.fp.setFormaAvista('T');
            System.out.println("entrou no if do avista");
        } else if (tblFormaPagamento.getValueAt(row, 2).toString().equals("Prazo")) {
            this.fp.setFormaAvista('F');
            System.out.println("entrou no if do prazo");
        }
        if (tblFormaPagamento.getValueAt(row, 3).toString().equals("Ativo")) {
            this.fp.setAtivo('T');
        } else {
            this.fp.setAtivo('F');
        }
        dispose();
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
            java.util.logging.Logger.getLogger(JdgListaFormaPagamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JdgListaFormaPagamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JdgListaFormaPagamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JdgListaFormaPagamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JdgListaFormaPagamento dialog = null;
                try {
                    dialog = new JdgListaFormaPagamento(new javax.swing.JFrame(), true);
                } catch (Exception ex) {
                    Logger.getLogger(JdgListaFormaPagamento.class.getName()).log(Level.SEVERE, null, ex);
                }
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
    private javax.swing.JButton btnConfirmar;
    private javax.swing.JButton btnSair;
    private javax.swing.JComboBox<String> cbxStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblFormaPagamento;
    private javax.swing.JTextField tfdDescricao;
    // End of variables declaration//GEN-END:variables
}
