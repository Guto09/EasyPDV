/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tela;

import DAO.CidadeDAO;
import DAO.ClienteDAO;
import DAO.FaturamentoDAO;
import apoio.ConexaoBD;
import apoio.Formatacao;
import apoio.Validacao;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import entidade.Cidade;
import entidade.Cliente;
import entidade.Faturamento;
import entidade.FaturamentoItem;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Mileto
 */
public class JdgListagemVendas extends javax.swing.JDialog {

    /**
     * Creates new form JdgListagemVendas
     */
    Faturamento fat;
    Cliente cli;
    ArrayList<Faturamento> vendas;
    
    public JdgListagemVendas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        Faturamento fat = new Faturamento();
        Cidade cid = new Cidade();
        cli = new Cliente(cid);
        this.fat = fat;
        fat.setFase('e');
        vendas = new ArrayList<>();
        listarVendas();
        tfdBuscar.setText("");
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        rbtFiltroData = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tffDataInicio = new com.toedter.calendar.JDateChooser();
        tffDataFim = new com.toedter.calendar.JDateChooser();
        jPanel4 = new javax.swing.JPanel();
        rbtFinalizadas = new javax.swing.JRadioButton();
        rbtCanceladas = new javax.swing.JRadioButton();
        btnRelatorios = new javax.swing.JButton();
        btnAcessarVenda = new javax.swing.JButton();
        btnCancelarVenda = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        tfdBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblListaVendas = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("EasyPDV - Listagem de vendas");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Listagem de vendas", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(0, 0, 204))); // NOI18N
        jPanel1.setToolTipText("");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Período", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 0, 204))); // NOI18N

        rbtFiltroData.setText("Filtro de data");
        rbtFiltroData.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rbtFiltroDataItemStateChanged(evt);
            }
        });

        jLabel1.setText("Início:");

        jLabel2.setText("Fim:");

        tffDataInicio.setToolTipText("");
        tffDataInicio.setEnabled(false);
        tffDataInicio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tffDataInicioKeyReleased(evt);
            }
        });

        tffDataFim.setEnabled(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tffDataInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(rbtFiltroData, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(0, 27, Short.MAX_VALUE))
                    .addComponent(tffDataFim, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rbtFiltroData)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tffDataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tffDataFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Fase", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 0, 204))); // NOI18N

        rbtFinalizadas.setSelected(true);
        rbtFinalizadas.setText("Finalizadas");
        rbtFinalizadas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rbtFinalizadasItemStateChanged(evt);
            }
        });

        rbtCanceladas.setText("Canceladas");
        rbtCanceladas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rbtCanceladasItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbtFinalizadas)
                    .addComponent(rbtCanceladas))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rbtFinalizadas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbtCanceladas)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnRelatorios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/print.png"))); // NOI18N
        btnRelatorios.setText("Relatórios");
        btnRelatorios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRelatoriosActionPerformed(evt);
            }
        });

        btnAcessarVenda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Edit File-16.png"))); // NOI18N
        btnAcessarVenda.setText("Acessar Venda");
        btnAcessarVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAcessarVendaActionPerformed(evt);
            }
        });

        btnCancelarVenda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/iconcancel3.png"))); // NOI18N
        btnCancelarVenda.setText("Cancelar Venda");
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

        tfdBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfdBuscarKeyReleased(evt);
            }
        });

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Lupa3.png"))); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRelatorios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAcessarVenda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCancelarVenda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfdBuscar)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfdBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAcessarVenda)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelarVenda)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRelatorios)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSair)
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Vendas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 204))); // NOI18N

        tblListaVendas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "N° Venda", "Cliente", "Data Emissão", "Valor Bruto", "Desconto", "Valor Líquido", "Fase"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblListaVendas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblListaVendasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblListaVendas);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 993, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
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

    private void listarVendas() {
        
        if (rbtFinalizadas.isSelected() && rbtCanceladas.isSelected()) {
            fat.setFase('t');
        } else if (rbtFinalizadas.isSelected() && !rbtCanceladas.isSelected()) {
            fat.setFase('e');
        } else if (!rbtFinalizadas.isSelected() && rbtCanceladas.isSelected()) {
            fat.setFase('c');
        } else {
            fat.setFase(' ');
        }
        
        try {
            //setar para tabela modelo de dados
            tblListaVendas.setModel(this.obterDadosParaTabelaCompleto());
            tblListaVendas.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblListaVendas.getColumnModel().getColumn(1).setPreferredWidth(170);
            tblListaVendas.getColumnModel().getColumn(2).setPreferredWidth(40);
            tblListaVendas.getColumnModel().getColumn(3).setPreferredWidth(40);
            tblListaVendas.getColumnModel().getColumn(4).setPreferredWidth(40);
            tblListaVendas.getColumnModel().getColumn(5).setPreferredWidth(40);
            tblListaVendas.getColumnModel().getColumn(6).setPreferredWidth(0);
            
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

        FaturamentoDAO fatDAO = new FaturamentoDAO();
        ArrayList<Faturamento> vendas = fatDAO.consultar(fat, cli);

//        ClienteDAO cliDAO = new ClienteDAO();
//        ArrayList<Cliente> clientes = cliDAO.consultar(cliente);
        dtm.addColumn("N° VENDA");
        dtm.addColumn("CLIENTE");
        dtm.addColumn("DATA EMISSÃO");
        dtm.addColumn("VALOR BRUTO");
        dtm.addColumn("DESCONTO");
        dtm.addColumn("VALOR LÍQUIDO");
        dtm.addColumn("FASE");
        
        for (int i = 0; i < vendas.size(); i++) {
            //popular tabela
            String fase = "";
            if (String.valueOf(vendas.get(i).getFase()).equalsIgnoreCase("e")) {
                fase = "Encerrado";
            } else {
                fase = "Cancelado";
            }
            dtm.addRow(new String[]{String.valueOf(vendas.get(i).getId()),
                vendas.get(i).getCliente().getRazaoSocial(),
                vendas.get(i).getDataEmissao(),
                String.valueOf(vendas.get(i).getValorTotal()),
                String.valueOf(vendas.get(i).getDesconto()),
                String.valueOf(vendas.get(i).getValorTotalLiquido()),
                fase
            });
        }
//retorna o modelo
        return dtm;
    }
    

    private void rbtFiltroDataItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbtFiltroDataItemStateChanged
        if (rbtFiltroData.isSelected()) {
            
            tffDataFim.setEnabled(true);
            tffDataInicio.setEnabled(true);
            
        } else {
            
            tffDataFim.setCalendar(null);
            tffDataInicio.setCalendar(null);
            tffDataFim.setEnabled(false);
            tffDataInicio.setEnabled(false);
            
        }

    }//GEN-LAST:event_rbtFiltroDataItemStateChanged

    private void tffDataInicioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tffDataInicioKeyReleased

    }//GEN-LAST:event_tffDataInicioKeyReleased

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        
        validarTipoDeBusca();
        

    }//GEN-LAST:event_btnBuscarActionPerformed
    private void buscar() {
        if (tfdBuscar.getText().length() > 0) {
            
            cli.setRazaoSocial(tfdBuscar.getText());
            fat.setCliente(cli);
            try {
                fat.setId(Integer.parseInt(tfdBuscar.getText()));
                
            } catch (Exception e) {
                
            }
            
        } else {
            tfdBuscar.setText("");
            cli.setRazaoSocial(tfdBuscar.getText());
            fat.setId(0);
        }
        if (tffDataInicio.getCalendar() != null && tffDataFim.getCalendar() != null) {
            
            String dataInicio = Formatacao.ajustaDataDMAJCalendar(tffDataInicio);
            String dataFim = Formatacao.ajustaDataDMAJCalendar(tffDataFim);
            fat.setDataEmissaoInicio(dataInicio);
            fat.setDataEmissaoFim(dataFim);
            listarVendas();
        } else {
            fat.setDataEmissaoInicio(null);
            fat.setDataEmissaoFim(null);
//            JOptionPane.showMessageDialog(rootPane, "entrou no else");
            listarVendas();
        }
    }
    
    private void validarTipoDeBusca() {
        
        if (tffDataInicio.getCalendar() != null && tffDataFim.getCalendar() != null) {
            try {
                if (Validacao.validadeFiltroDeData(tffDataInicio, tffDataFim)) {
                    buscar();
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Filtro de data Incorreta");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, "Filtro de data Incorreta");
            }
            
        } else {
            buscar();
        }
        
    }
    private void btnCancelarVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarVendaActionPerformed
        FaturamentoDAO fatDAO = new FaturamentoDAO();
        vendas = fatDAO.consultar(fat, cli);
        try {
            int op = JOptionPane.showConfirmDialog(rootPane, "Tem certeza que deseja cancelar a venda? \n"
                    + "ID: " + vendas.get(tblListaVendas.getSelectedRow()).getId() + "\n"
                    + "Cliente: " + vendas.get(tblListaVendas.getSelectedRow()).getCliente().getRazaoSocial()
            );
            
            if (op == 0) {
                try {
                    
                    cancelarVenda();
                    
                } catch (Exception ex) {
                    Logger.getLogger(JdgPedidoVenda.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Nenhuma Linha da tabela Selecionada");
        }

    }//GEN-LAST:event_btnCancelarVendaActionPerformed

    private void btnAcessarVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAcessarVendaActionPerformed
        acessarVenda();

    }//GEN-LAST:event_btnAcessarVendaActionPerformed

    private void tblListaVendasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListaVendasMouseClicked
        int row = tblListaVendas.getSelectedRow();
        if (tblListaVendas.getValueAt(row, 6) == "Cancelado") {
            btnCancelarVenda.setEnabled(false);
        } else {
            btnCancelarVenda.setEnabled(true);
        }
        
        if (evt.getClickCount() > 1) {
            int linhaSelecionada = tblListaVendas.getSelectedRow();
            acessarVenda();
            
        }

    }//GEN-LAST:event_tblListaVendasMouseClicked

    private void rbtCanceladasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbtCanceladasItemStateChanged
        listarVendas();
    }//GEN-LAST:event_rbtCanceladasItemStateChanged

    private void rbtFinalizadasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbtFinalizadasItemStateChanged
        listarVendas();
    }//GEN-LAST:event_rbtFinalizadasItemStateChanged

    private void tfdBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfdBuscarKeyReleased
        validarTipoDeBusca();
    }//GEN-LAST:event_tfdBuscarKeyReleased

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        dispose();
    }//GEN-LAST:event_btnSairActionPerformed

    private void btnRelatoriosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRelatoriosActionPerformed
        try {
            if (Validacao.validadeFiltroDeData(tffDataInicio, tffDataFim)) {
                if (rbtCanceladas.isSelected() || rbtFinalizadas.isSelected()) {
                    
                    buscar();


                    JdgRelatoriosVendas relatorioVendas = new JdgRelatoriosVendas(null, false, fat);
                    
                    relatorioVendas.setVisible(true);
                    
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Nenhuma fase do pedido selecionada!\n para gerar o relatório precisa selecionar\numa fase ou mais");                    
                }
                
            } else {
                JOptionPane.showMessageDialog(rootPane, "Filtro de data Incorreta!\n para gerar relatório precisa informar um período");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Filtro de data Incorreta!\n para gerar relatório precisa informar um período");
        }
        
        try {
            // Compila o relatorio//

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao gerar relatório: " + e);
            System.out.println(e);
        }
        

    }//GEN-LAST:event_btnRelatoriosActionPerformed
    
    private void acessarVenda() {
        fat.setId(0);
        vendas.removeAll(vendas);
        int row = tblListaVendas.getSelectedRow();
        FaturamentoDAO fatDAO = new FaturamentoDAO();
        
        vendas = fatDAO.consultar(fat, cli);
        fat.setId(vendas.get(row).getId());
        
        JdgVendaRegistrada vendaRegistrada = new JdgVendaRegistrada(null, true, fat);
        vendaRegistrada.setVisible(true);
        fat.setId(0);
        listarVendas();
    }
    
    private void cancelarVenda() {
        int row = tblListaVendas.getSelectedRow();
        
        fat.setId(vendas.get(row).getId());
        
        FaturamentoDAO fatDAO = new FaturamentoDAO();
        if (fatDAO.salvar(fat, null, null)) {
            JOptionPane.showMessageDialog(rootPane, "Cancelamento da venda realizado com sucesso!");
            fat.setId(0);
            listarVendas();
        } else {
            JOptionPane.showMessageDialog(rootPane, "erro retornado pelo sistema:\nErro ao cancelar venda.");
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
            java.util.logging.Logger.getLogger(JdgListagemVendas.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
            
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JdgListagemVendas.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
            
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JdgListagemVendas.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
            
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JdgListagemVendas.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JdgListagemVendas dialog = new JdgListagemVendas(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnAcessarVenda;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelarVenda;
    private javax.swing.JButton btnRelatorios;
    private javax.swing.JButton btnSair;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rbtCanceladas;
    private javax.swing.JRadioButton rbtFiltroData;
    private javax.swing.JRadioButton rbtFinalizadas;
    private javax.swing.JTable tblListaVendas;
    private javax.swing.JTextField tfdBuscar;
    private com.toedter.calendar.JDateChooser tffDataFim;
    private com.toedter.calendar.JDateChooser tffDataInicio;
    // End of variables declaration//GEN-END:variables
}
