/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tela;

import DAO.CidadeDAO;
import DAO.FormaPagamentoDAO;
import entidade.Cidade;
import entidade.FormaPagamento;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mileto
 */
public class JdgListaCidade extends javax.swing.JDialog {

    Cidade cidade;

    public JdgListaCidade(java.awt.Frame parent, boolean modal, Cidade cidade) {
        super(parent, modal);
        initComponents();

        this.cidade = cidade;
        verificarTipoChamada();
        popularComboBox();

        listarCidades();
    }

    public JdgListaCidade(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        listarCidades();
    }

    private void listarCidades() {
        try {
            //setar para tabela modelo de dados
            tblCidades.setModel(this.obterDadosParaJTable());
            tblCidades.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblCidades.getColumnModel().getColumn(1).setPreferredWidth(270);
            tblCidades.getColumnModel().getColumn(2).setPreferredWidth(40);

        } catch (Exception ex) {
            Logger.getLogger(JdgListaFormaPagamento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void popularComboBox() {

        cbxStatus.addItem("Ativos");
        cbxStatus.addItem("Inativos");
        cbxStatus.addItem("Todos");
    }

    private void verificarTipoChamada() {
        if (cidade.getAtivo() == 'T') {
            cbxStatus.setEnabled(false);
            btnConfirmar.setText("Selecionar");
            btnConfirmar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/confirmar.png")));
        } else {
            cbxStatus.setEditable(true);
        }
    }

    public DefaultTableModel obterDadosParaJTable() throws Exception {
        DefaultTableModel dtm = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
//adiciona titulo para as colunas
        CidadeDAO cidadeDAO = new CidadeDAO();
        ArrayList<Cidade> cidades = cidadeDAO.consultar(cidade);
        dtm.addColumn("ID");
        dtm.addColumn("DESCRIÇÃO");
        dtm.addColumn("SITUAÇÃO");

        for (int i = 0; i < cidades.size(); i++) {
            //popular tabela
            String result = "";
            if (String.valueOf(cidades.get(i).getAtivo()).equalsIgnoreCase("T")) {
                result = "Ativo";
            } else {
                result = "Inativo";
            }
            dtm.addRow(new String[]{String.valueOf(cidades.get(i).getId()),
                cidades.get(i).getDescricao(), result});
        }
//retorna o modelo
        return dtm;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblCidades = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnConfirmar = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        tfdDescricao = new javax.swing.JTextField();
        lblSatus = new javax.swing.JLabel();
        cbxStatus = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("EasyPDV - Lista de cidades");

        tblCidades.setModel(new javax.swing.table.DefaultTableModel(
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
        tblCidades.setFocusable(false);
        tblCidades.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tblCidadesAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        tblCidades.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCidadesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tblCidadesMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblCidadesMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblCidades);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 204));
        jLabel1.setText("Lista de Cidades");

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

        lblSatus.setText("Status:");

        cbxStatus.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxStatusItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(28, 28, 28))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfdDescricao)
                        .addGap(18, 18, 18)
                        .addComponent(lblSatus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(68, 68, 68))))
            .addGroup(layout.createSequentialGroup()
                .addGap(188, 188, 188)
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tfdDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSatus)
                    .addComponent(cbxStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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

    private void tblCidadesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCidadesMouseClicked
        if (evt.getClickCount() > 1) {
            int linhaSelecionada = tblCidades.getSelectedRow();
            selecionado();
            dispose();
        }
    }//GEN-LAST:event_tblCidadesMouseClicked

    private void tblCidadesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCidadesMousePressed

    }//GEN-LAST:event_tblCidadesMousePressed

    private void tblCidadesAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tblCidadesAncestorAdded

    }//GEN-LAST:event_tblCidadesAncestorAdded

    private void tblCidadesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCidadesMouseEntered

    }//GEN-LAST:event_tblCidadesMouseEntered
    private void listar() {
        cidade.setDescricao(tfdDescricao.getText().toLowerCase());
        System.out.println("descricao == " + cidade.getDescricao());
        if (cbxStatus.getSelectedItem().equals("Todos")) {
            cidade.setAtivo(' ');
        } else if (cbxStatus.getSelectedItem().equals("Ativos")) {
            cidade.setAtivo('T');

        } else if (cbxStatus.getSelectedItem().equals("Inativos")) {
            cidade.setAtivo('F');

        }
        listarCidades();
    }
    private void tfdDescricaoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfdDescricaoKeyPressed


    }//GEN-LAST:event_tfdDescricaoKeyPressed

    private void cbxStatusItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxStatusItemStateChanged

        listar();

    }//GEN-LAST:event_cbxStatusItemStateChanged

    private void tfdDescricaoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfdDescricaoKeyReleased
        listar();
    }//GEN-LAST:event_tfdDescricaoKeyReleased

    //retorna item selecionado na taleba
    private void selecionado() {

        //pega a linha selecionada
        int row = tblCidades.getSelectedRow();

        //seta o ID do objeto da linha selecionada
        this.cidade.setId(Integer.parseInt(tblCidades.getValueAt(row, 0).toString()));
        this.cidade.setDescricao(tblCidades.getValueAt(row, 1).toString());
        if (tblCidades.getValueAt(row, 2).toString().equals("Ativo")) {
            this.cidade.setAtivo('T');
        } else {
            this.cidade.setAtivo('F');
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
            java.util.logging.Logger.getLogger(JdgListaCidade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JdgListaCidade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JdgListaCidade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JdgListaCidade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JdgListaCidade dialog = new JdgListaCidade(new javax.swing.JFrame(), true);
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblSatus;
    private javax.swing.JTable tblCidades;
    private javax.swing.JTextField tfdDescricao;
    // End of variables declaration//GEN-END:variables
}
