/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tela;

import DAO.CidadeDAO;
import DAO.ClienteDAO;
import DAO.FaturamentoDAO;
import DAO.FaturamentoItemDAO;
import apoio.Formatacao;
import entidade.Cidade;
import entidade.Cliente;
import entidade.Faturamento;
import entidade.FaturamentoItem;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Mileto
 */
public class JdgVendaRegistrada extends javax.swing.JDialog {

    /**
     * Creates new form JdgVendaRealizada
     */
    ArrayList<FaturamentoItem> mercs;
    Faturamento fat;
    Cliente cli;

    private MaskFormatter mascaraCpf;
    private MaskFormatter mascaraCnpj;

    public JdgVendaRegistrada(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

    }

    public JdgVendaRegistrada(java.awt.Frame parent, boolean modal, Faturamento fat) {
        super(parent, modal);
        initComponents();
        this.fat = fat;
//         mercs = new ArrayList<>();
        mascaraCpfCnpj();
        listarMercadorias();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tfdNomeCliente = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tfdDataEmissao = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tfdValorBruto = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tfdValorLiquido = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tfdDesconto = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tfdNumero = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        tffCpfCnpj = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        tfdSituação = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMercadorias = new javax.swing.JTable();
        btnCancelarVenda = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("EasyPDV - Venda Registrada");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Registro Venda", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(0, 0, 204))); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setText("Cliente:");

        tfdNomeCliente.setEditable(false);

        jLabel2.setText("Emissão:");

        tfdDataEmissao.setEditable(false);

        jLabel4.setText("Valor Bruto:");

        tfdValorBruto.setEditable(false);

        jLabel3.setText("Valor Líquido:");

        tfdValorLiquido.setEditable(false);

        jLabel5.setText("Desconto:");

        tfdDesconto.setEditable(false);

        jLabel6.setText("Número:");

        tfdNumero.setEditable(false);

        jLabel7.setText("CPF/CNPJ:");

        tffCpfCnpj.setEditable(false);
        try {
            tffCpfCnpj.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tffCpfCnpj.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tffCpfCnpjFocusLost(evt);
            }
        });
        tffCpfCnpj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tffCpfCnpjActionPerformed(evt);
            }
        });

        jLabel8.setText("Situação:");

        tfdSituação.setEditable(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(tffCpfCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfdSituação))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(tfdNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfdDataEmissao, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tfdNomeCliente))
                .addGap(37, 37, 37)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tfdValorBruto, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(tfdValorLiquido)
                    .addComponent(tfdDesconto))
                .addContainerGap(146, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tfdValorBruto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(tfdNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfdDataEmissao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(tfdNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(tffCpfCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(tfdSituação, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(tfdValorLiquido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(tfdDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        tblMercadorias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Referência", "Descrição", "Quantidade", "Valor Bruto", "Desconto", "Valor Líquido"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblMercadorias);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 676, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(164, 164, 164))
        );

        btnCancelarVenda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/iconcancel3.png"))); // NOI18N
        btnCancelarVenda.setText("Cancelar venda");
        btnCancelarVenda.setEnabled(false);
        btnCancelarVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarVendaActionPerformed(evt);
            }
        });

        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icon_Schutdown16.png"))); // NOI18N
        btnSair.setText("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCancelarVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 305, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelarVenda)
                    .addComponent(btnSair)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        dispose();
    }//GEN-LAST:event_btnSairActionPerformed

    private void tffCpfCnpjFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tffCpfCnpjFocusLost

    }//GEN-LAST:event_tffCpfCnpjFocusLost

    private void tffCpfCnpjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tffCpfCnpjActionPerformed

    }//GEN-LAST:event_tffCpfCnpjActionPerformed

    private void btnCancelarVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarVendaActionPerformed

        try {
            int op = JOptionPane.showConfirmDialog(rootPane, "Tem certeza que deseja cancelar a venda? \n"
                    + "ID: " + tfdNumero.getText() + "\n"
                    + "Cliente: " + tfdNomeCliente.getText()
            );

            if (op == 0) {
                try {

                    cancelarVenda();
                    listarMercadorias();
                } catch (Exception ex) {
                    Logger.getLogger(JdgPedidoVenda.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Erro retornado pelo sistema: \nProblema ao cancelar venda,\n entre em contato com o suporte");
        }

    }//GEN-LAST:event_btnCancelarVendaActionPerformed

     private void cancelarVenda() {


        FaturamentoDAO fatDAO = new FaturamentoDAO();
        fat.setFase('c');
        fat.setId(Integer.parseInt(tfdNumero.getText()));
        if (fatDAO.salvar(fat, null, null)) {
            JOptionPane.showMessageDialog(rootPane, "Cancelamento da venda realizado com sucesso!");

        } else {
            JOptionPane.showMessageDialog(rootPane, "erro retornado pelo sistema:\nErro ao cancelar venda.");
        }
    }
    
    
    /**
     * @param args the command line arguments
     */
    private void mascaraCpfCnpj() {
        try {
            mascaraCpf = new MaskFormatter("###.###.###-##");
            mascaraCnpj = new MaskFormatter("##.###.###/####-##");

        } catch (Exception e) {
        }

    }

    private void listarMercadorias() {

        try {
            //setar para tabela modelo de dados

            tblMercadorias.setModel(this.obterDadosParaTabelaCompleto());
            tblMercadorias.getColumnModel().getColumn(0).setPreferredWidth(100);
            tblMercadorias.getColumnModel().getColumn(1).setPreferredWidth(100);
            tblMercadorias.getColumnModel().getColumn(2).setPreferredWidth(0);
            tblMercadorias.getColumnModel().getColumn(3).setPreferredWidth(0);
            tblMercadorias.getColumnModel().getColumn(4).setPreferredWidth(0);
            tblMercadorias.getColumnModel().getColumn(5).setPreferredWidth(0);
            

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

        dtm.addColumn("REFERÊNCIA");
        dtm.addColumn("DESCRIÇÃO");
        dtm.addColumn("QNT");
        dtm.addColumn("$UNITÁRIO");
        dtm.addColumn("$DESCONTO");
        dtm.addColumn("$TOTAL");

//        mercs.add(faturamentoItem);
        FaturamentoDAO fatDAO = new FaturamentoDAO();
        cli = new Cliente(null);

        ArrayList<Faturamento> faturamento = new ArrayList<>();
        faturamento = fatDAO.consultar(fat, cli);
        tfdNomeCliente.setText(faturamento.get(0).getCliente().getRazaoSocial());
        tfdDataEmissao.setText(faturamento.get(0).getDataEmissao());
        tfdValorBruto.setText(String.valueOf(faturamento.get(0).getValorTotal()).replace(".", ","));
        tfdValorLiquido.setText(String.valueOf(faturamento.get(0).getValorTotalLiquido()).replace(".", ","));
        tfdNumero.setText(String.valueOf(faturamento.get(0).getId()));
        tfdDesconto.setText(String.valueOf(faturamento.get(0).getDesconto()).replace(".", ","));
        if (faturamento.get(0).getCliente().getCpfCnpj().length() > 11) {


            Formatacao.reformatarCnpj(tffCpfCnpj);
            tffCpfCnpj.setText(faturamento.get(0).getCliente().getCpfCnpj());
        } else if (faturamento.get(0).getCliente().getCpfCnpj().length() <= 11) {
            tffCpfCnpj.setText(faturamento.get(0).getCliente().getCpfCnpj());


            Formatacao.reformatarCpf(tffCpfCnpj);
            tffCpfCnpj.setText(faturamento.get(0).getCliente().getCpfCnpj());
        }
        
        if (faturamento.get(0).getFase()=='e') {
            tfdSituação.setText("Encerrado");
            btnCancelarVenda.setEnabled(true);
        }else{
            tfdSituação.setText("Cancelado");
            btnCancelarVenda.setEnabled(false);
        }
        
        
         FaturamentoItem fatItem = new FaturamentoItem();
         FaturamentoItemDAO fatItemDAO = new FaturamentoItemDAO();
         fatItem.setIdFaturamento(fat.getId());
        ArrayList<FaturamentoItem> itensVendidos = fatItemDAO.consultar(fatItem);

        

        for (int i = 0; i < itensVendidos.size(); i++) {
            //popular tabela
            

            dtm.addRow(new String[]{String.valueOf(itensVendidos.get(i).getMercadoria().getReferencia()),
                itensVendidos.get(i).getMercadoria().getDescricao(),
                String.valueOf(itensVendidos.get(i).getQuantidade()),
                String.valueOf(itensVendidos.get(i).getValorUnitario()),
                String.valueOf(itensVendidos.get(i).getDesconto()),
                String.valueOf(itensVendidos.get(i).getValorTotal())
                
            });
        }
//retorna o modelo
        return dtm;
    }
        

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
            java.util.logging.Logger.getLogger(JdgVendaRegistrada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JdgVendaRegistrada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JdgVendaRegistrada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JdgVendaRegistrada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JdgVendaRegistrada dialog = new JdgVendaRegistrada(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnCancelarVenda;
    private javax.swing.JButton btnSair;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblMercadorias;
    private javax.swing.JTextField tfdDataEmissao;
    private javax.swing.JTextField tfdDesconto;
    private javax.swing.JTextField tfdNomeCliente;
    private javax.swing.JTextField tfdNumero;
    private javax.swing.JTextField tfdSituação;
    private javax.swing.JTextField tfdValorBruto;
    private javax.swing.JTextField tfdValorLiquido;
    private javax.swing.JFormattedTextField tffCpfCnpj;
    // End of variables declaration//GEN-END:variables
}
