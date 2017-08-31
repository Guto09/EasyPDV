/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tela;

import DAO.FormaPagamentoDAO;
import DAO.MercadoriaDAO;
import entidade.FormaPagamento;
import entidade.Mercadoria;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mileto
 */
public class JdgListaMercadorias extends javax.swing.JDialog {

    Mercadoria merc;

    public JdgListaMercadorias(java.awt.Frame parent, boolean modal, Mercadoria merc) {
        super(parent, modal);
        initComponents();
        this.merc = merc;
        verificarTipoChamada();
        popularComboBox();

        listarMercadorias();
    }

    private void verificarTipoChamada() {

        if (merc.getAtivo() == 'T') {
            cbxStatus.setEnabled(false);

            btnConfirmar.setText("Selecionar");
            btnConfirmar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/confirmar.png")));
        } else {
            btnConfirmar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Edit File-16.png")));
            cbxStatus.setEditable(true);
        }
    }

    public JdgListaMercadorias(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

    }
//
//    private void verificarTipoChamada() {
//        if (merc.getAtivo() == 'T') {
//            cbxStatus.setEnabled(false);
//        } else {
//            cbxStatus.setEditable(true);
//        }
//    }

    private void popularComboBox() {

        cbxStatus.addItem("Ativos");
        cbxStatus.addItem("Inativos");
        cbxStatus.addItem("Todos");
    }

    private void listarMercadorias() {
        try {
            //setar para tabela modelo de dados
            tblMercadorias.setModel(this.obterDadosParaTabelaCompleto());
            tblMercadorias.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblMercadorias.getColumnModel().getColumn(1).setPreferredWidth(20);
            tblMercadorias.getColumnModel().getColumn(2).setPreferredWidth(170);
            tblMercadorias.getColumnModel().getColumn(3).setPreferredWidth(0);
            tblMercadorias.getColumnModel().getColumn(4).setPreferredWidth(0);
            tblMercadorias.getColumnModel().getColumn(5).setPreferredWidth(0);
            tblMercadorias.getColumnModel().getColumn(6).setPreferredWidth(0);

        } catch (Exception ex) {
            Logger.getLogger(JdgListaFormaPagamento.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private DefaultTableModel obterDadosParaTabelaCompleto() throws Exception {
        DefaultTableModel dtm = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
//adiciona titulo para as colunas
        MercadoriaDAO mercDAO = new MercadoriaDAO();
        ArrayList<Mercadoria> mercs = mercDAO.consultar(merc);
        dtm.addColumn("ID");
        dtm.addColumn("REFERÊNCIA");
        dtm.addColumn("DESCRIÇÃO");
        dtm.addColumn("ESTOQUE");
        dtm.addColumn("CUSTO");
        dtm.addColumn("VENDA");
        dtm.addColumn("SITUAÇÃO");

        for (int i = 0; i < mercs.size(); i++) {
            //popular tabela
            String result = "";
            if (String.valueOf(mercs.get(i).getAtivo()).equalsIgnoreCase("T")) {
                result = "Ativo";
            } else {
                result = "Inativo";
            }
            dtm.addRow(new String[]{String.valueOf(mercs.get(i).getId()),
                mercs.get(i).getReferencia(),
                mercs.get(i).getDescricao(),
                String.valueOf(mercs.get(i).getEstoque()),
                String.valueOf(mercs.get(i).getPrecoCusto()),
                String.valueOf(mercs.get(i).getPrecoVenda()),
                result
            });
        }
//retorna o modelo
        return dtm;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblMercadorias = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnConfirmar = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        cbxStatus = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tfdReferencia = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("EasyPDV - Lista de mercadorias");

        tblMercadorias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Referência", "Descrição", "Estoque", "Preço de Custo", "Preço de Venda", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblMercadorias.setFocusable(false);
        tblMercadorias.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tblMercadoriasAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        tblMercadorias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMercadoriasMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tblMercadoriasMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblMercadoriasMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblMercadorias);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 204));
        jLabel1.setText("Lista de mercadorias");

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

        cbxStatus.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxStatusItemStateChanged(evt);
            }
        });
        cbxStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxStatusActionPerformed(evt);
            }
        });

        jLabel2.setText("Situação:");

        jLabel3.setText("Referência:");

        tfdReferencia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfdReferenciaKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 737, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(288, 288, 288)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfdReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbxStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(tfdReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
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

    private void tblMercadoriasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMercadoriasMouseClicked
//Se clicar mais de 2x ele ira passar a escolha para a variável e fechara o programa
        if (evt.getClickCount() > 1) {
            int linhaSelecionada = tblMercadorias.getSelectedRow();
            selecionado();
            dispose();
        }
    }//GEN-LAST:event_tblMercadoriasMouseClicked

    private void tblMercadoriasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMercadoriasMousePressed

    }//GEN-LAST:event_tblMercadoriasMousePressed

    private void tblMercadoriasAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tblMercadoriasAncestorAdded

    }//GEN-LAST:event_tblMercadoriasAncestorAdded

    private void tblMercadoriasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMercadoriasMouseEntered

    }//GEN-LAST:event_tblMercadoriasMouseEntered

    private void cbxStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxStatusActionPerformed

    private void listar() {
        merc.setReferencia(tfdReferencia.getText());
        if (cbxStatus.getSelectedItem().equals("Todos")) {
            merc.setAtivo(' ');
        } else if (cbxStatus.getSelectedItem().equals("Ativos")) {
            merc.setAtivo('T');

        } else if (cbxStatus.getSelectedItem().equals("Inativos")) {
            merc.setAtivo('F');

        }
        listarMercadorias();
    }

    private void tfdReferenciaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfdReferenciaKeyReleased
        listar();
    }//GEN-LAST:event_tfdReferenciaKeyReleased

    private void cbxStatusItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxStatusItemStateChanged
        listar();
    }//GEN-LAST:event_cbxStatusItemStateChanged

    private void selecionado() {

        //pega a linha selecionada
        int row = tblMercadorias.getSelectedRow();

        //seta o ID do objeto da linha selecionada
        this.merc.setId(Integer.parseInt(tblMercadorias.getValueAt(row, 0).toString()));
        this.merc.setReferencia(tblMercadorias.getValueAt(row, 1).toString());
        this.merc.setDescricao(tblMercadorias.getValueAt(row, 2).toString());
        this.merc.setEstoque(Double.parseDouble(tblMercadorias.getValueAt(row, 3).toString()));
        this.merc.setPrecoCusto(Double.parseDouble(tblMercadorias.getValueAt(row, 4).toString()));
        this.merc.setPrecoVenda(Double.parseDouble(tblMercadorias.getValueAt(row, 5).toString()));
        if (tblMercadorias.getValueAt(row, 6).toString().equals("Ativo")) {
            this.merc.setAtivo('T');
        } else {
            this.merc.setAtivo('F');
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
            java.util.logging.Logger.getLogger(JdgListaMercadorias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JdgListaMercadorias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JdgListaMercadorias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JdgListaMercadorias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JdgListaMercadorias dialog = new JdgListaMercadorias(new javax.swing.JFrame(), true);
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
    private javax.swing.JTable tblMercadorias;
    private javax.swing.JTextField tfdReferencia;
    // End of variables declaration//GEN-END:variables
}
