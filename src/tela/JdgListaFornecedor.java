/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tela;

import DAO.CidadeDAO;
import DAO.ClienteDAO;
import DAO.FornecedorDAO;
import DAO.MercadoriaDAO;
import entidade.Cidade;
import entidade.Cliente;
import entidade.Fornecedor;
import entidade.Mercadoria;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author pc05
 */
public class JdgListaFornecedor extends javax.swing.JDialog {

    /**
     * Creates new form JdgListaCliente
     */
    Fornecedor fornecedor;
    Cidade cid;

    public JdgListaFornecedor(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        cid = new Cidade();
        fornecedor = new Fornecedor(cid);
        cbxStatus.setEnabled(false);
        fornecedor.setAtivo('T');
        listarFornecedores();

    }

    public JdgListaFornecedor(java.awt.Frame parent, boolean modal, Fornecedor fornecedor, Cidade cid) {
        super(parent, modal);

        initComponents();
        this.fornecedor = fornecedor;
        this.cid = cid;
        //System.out.println("ativo cliente na listaaaa" + fornecedor.getAtivo());
        verificarTipoChamada();
        popularComboBox();
        //System.out.println("ativo cliente na listaaaa depois do popular" + fornecedor.getAtivo());
        
        listarFornecedores();

    }

    
    private void verificarTipoChamada() {
        //System.out.println("ativo cliente na lista" + fornecedor.getAtivo());
        if (fornecedor.getAtivo() == 'T') {
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
        //System.out.println("ativo cliente na listaaaa popular inicio" + cliente.getAtivo());
        cbxStatus.addItem("Ativos");
        cbxStatus.addItem("Inativos");
        cbxStatus.addItem("Todos");
        //System.out.println("ativo cliente na listaaaa popular fim" + cliente.getAtivo());
    }

    private void listarFornecedores() {
        try {
            //setar para tabela modelo de dados
            tblListaClientes.setModel(this.obterDadosParaTabelaCompleto());
            tblListaClientes.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblListaClientes.getColumnModel().getColumn(1).setPreferredWidth(170);
            tblListaClientes.getColumnModel().getColumn(2).setPreferredWidth(40);
            tblListaClientes.getColumnModel().getColumn(3).setPreferredWidth(80);
            tblListaClientes.getColumnModel().getColumn(4).setPreferredWidth(170);
            tblListaClientes.getColumnModel().getColumn(5).setPreferredWidth(20);
            tblListaClientes.getColumnModel().getColumn(6).setPreferredWidth(0);

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

        CidadeDAO cidadeDAO = new CidadeDAO();
        ArrayList<Cidade> cidades = cidadeDAO.consultar(cid);

        FornecedorDAO fornDAO = new FornecedorDAO();
        ArrayList<Fornecedor> fornecedores = fornDAO.consultar(fornecedor);

        dtm.addColumn("ID");
        dtm.addColumn("NOME");
        dtm.addColumn("CPF/CNPJ");
        dtm.addColumn("CIDADE");
        dtm.addColumn("ENDEREÇO");
        dtm.addColumn("TELEFONE");
        dtm.addColumn("SITUAÇÃO");

        for (int i = 0; i < fornecedores.size(); i++) {
            //popular tabela
            String result = "";
            if (String.valueOf(fornecedores.get(i).getAtivo()).equalsIgnoreCase("T")) {
                result = "Ativo";
            } else {
                result = "Inativo";
            }
            dtm.addRow(new String[]{String.valueOf(fornecedores.get(i).getId()),
                fornecedores.get(i).getRazaoSocial(),
                fornecedores.get(i).getCpfCnpj(),
                String.valueOf(fornecedores.get(i).getCidade().getDescricao()),
                String.valueOf(fornecedores.get(i).getEndereco()),
                String.valueOf(fornecedores.get(i).getTelefone()),
                result
            });
        }
//retorna o modelo
        return dtm;
    }

    private void selecionado() throws Exception {

        //pega a linha selecionada
        int row = tblListaClientes.getSelectedRow();

        //seta o ID do objeto da linha selecionada
//        obterDadosParaTabelaCompleto();
        this.fornecedor.setId(Integer.parseInt(tblListaClientes.getValueAt(row, 0).toString()));

        FornecedorDAO fornDAO = new FornecedorDAO();
        ArrayList<Fornecedor> fornecedores = fornDAO.consultar(fornecedor);
        fornecedor.setCidade(fornecedores.get(0).getCidade());
        fornecedor.setEndereco(fornecedores.get(0).getEndereco());
        fornecedor.setRazaoSocial(fornecedores.get(0).getRazaoSocial());
        fornecedor.setCpfCnpj(fornecedores.get(0).getCpfCnpj());
        fornecedor.setTipoCadastro(fornecedores.get(0).getTipoCadastro());
        fornecedor.setTelefone(fornecedores.get(0).getTelefone());

        //System.out.println("cidade id..." + fornecedor.getCidade().getId());

        if (tblListaClientes.getValueAt(row, 6).toString().equals("Ativo")) {
            this.fornecedor.setAtivo('T');
        } else {
            this.fornecedor.setAtivo('F');
        }

        dispose();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        tfdFiltro = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cbxStatus = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblListaClientes = new javax.swing.JTable();
        btnConfirmar = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("EasyPDV - Lista de fornecedores");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 204));
        jLabel1.setText("Lista Cadastro de Fornecedor");

        tfdFiltro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfdFiltroKeyReleased(evt);
            }
        });

        jLabel3.setText("Status:");

        cbxStatus.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxStatusItemStateChanged(evt);
            }
        });

        tblListaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblListaClientes.setFocusable(false);
        tblListaClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblListaClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblListaClientes);

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(409, 409, 409)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tfdFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(433, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfdFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(cbxStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConfirmar)
                    .addComponent(btnSair))
                .addContainerGap(52, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
        try {
            selecionado();
        } catch (Exception ex) {
            Logger.getLogger(JdgListaFornecedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnConfirmarActionPerformed

    private void cbxStatusItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxStatusItemStateChanged
        listar();
    }//GEN-LAST:event_cbxStatusItemStateChanged
    private void listar() {
        if (cbxStatus.getSelectedIndex() == 0) {
            fornecedor.setAtivo('T');
        } else if (cbxStatus.getSelectedIndex() == 1) {
            fornecedor.setAtivo('F');
        } else {
            fornecedor.setAtivo(' ');
        }
        listarFornecedores();
    }
    private void tfdFiltroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfdFiltroKeyReleased
        fornecedor.setCpfCnpj(tfdFiltro.getText());
        fornecedor.setRazaoSocial(tfdFiltro.getText());
        listarFornecedores();
    }//GEN-LAST:event_tfdFiltroKeyReleased

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        dispose();
    }//GEN-LAST:event_btnSairActionPerformed

    private void tblListaClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListaClientesMouseClicked
                if (evt.getClickCount() > 1) {
            int linhaSelecionada = tblListaClientes.getSelectedRow();
                    try {
                        selecionado();
                    } catch (Exception ex) {
                        Logger.getLogger(JdgListaFornecedor.class.getName()).log(Level.SEVERE, null, ex);
                    }
            dispose();
        }
    }//GEN-LAST:event_tblListaClientesMouseClicked

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
            java.util.logging.Logger.getLogger(JdgListaFornecedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JdgListaFornecedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JdgListaFornecedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JdgListaFornecedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JdgListaFornecedor dialog = new JdgListaFornecedor(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblListaClientes;
    private javax.swing.JTextField tfdFiltro;
    // End of variables declaration//GEN-END:variables
}
