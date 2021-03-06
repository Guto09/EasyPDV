/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tela;

import DAO.CidadeDAO;
import DAO.ClienteDAO;
import DAO.FaturamentoDAO;
import DAO.FinanceiroPagarDAO;
import DAO.FinanceiroReceberDAO;
import apoio.ConexaoBD;
import apoio.Formatacao;
import apoio.Validacao;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import entidade.Cidade;
import entidade.Cliente;
import entidade.Faturamento;
import entidade.FaturamentoItem;
import entidade.FinanceiroPagar;
import entidade.FormaPagamento;
import entidade.FinanceiroReceber;
import entidade.Fornecedor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
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
public class JdgListagemFinanceiroPagar extends javax.swing.JDialog {

    /**
     * Creates new form JdgListagemVendas
     */
    FinanceiroPagar formasPagas;
    Fornecedor forn;
        ArrayList<FinanceiroPagar> arrayFormasPagas;

    public JdgListagemFinanceiroPagar(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        FinanceiroPagar formasPagas = new FinanceiroPagar();
        Cidade cid = new Cidade();
        forn = new Fornecedor(cid);
        this.formasPagas = formasPagas;
        //fat.setFase('e');
        arrayFormasPagas = new ArrayList<>();
        //listarVendas();
        tfdBuscar.setText("");
        buscar();
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
        cbxTipoFiltro = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        rbtAbertos = new javax.swing.JRadioButton();
        rbtFinalizadas = new javax.swing.JRadioButton();
        btnAcessarTitulo = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        tfdBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblListaTitulos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("EasyPDV - Listagem de títulos a pagar");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Listagem de Títulos a pagar", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(0, 0, 204))); // NOI18N
        jPanel1.setToolTipText("");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Período", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 0, 204))); // NOI18N

        rbtFiltroData.setText("Filtro de data");
        rbtFiltroData.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rbtFiltroDataItemStateChanged(evt);
            }
        });
        rbtFiltroData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtFiltroDataActionPerformed(evt);
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

        cbxTipoFiltro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Data emissão", "Data vencimento", "Data pagamento" }));
        cbxTipoFiltro.setEnabled(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tffDataInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tffDataFim, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(rbtFiltroData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbxTipoFiltro, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 4, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(rbtFiltroData)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbxTipoFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tffDataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tffDataFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Situação", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 0, 204))); // NOI18N

        rbtAbertos.setSelected(true);
        rbtAbertos.setText("Aberto");
        rbtAbertos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rbtAbertosItemStateChanged(evt);
            }
        });
        rbtAbertos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtAbertosActionPerformed(evt);
            }
        });

        rbtFinalizadas.setText("Finalizadas");
        rbtFinalizadas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rbtFinalizadasItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbtAbertos)
                    .addComponent(rbtFinalizadas))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rbtAbertos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 5, Short.MAX_VALUE)
                .addComponent(rbtFinalizadas)
                .addContainerGap())
        );

        btnAcessarTitulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Edit File-16.png"))); // NOI18N
        btnAcessarTitulo.setText("Acessar Título");
        btnAcessarTitulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAcessarTituloActionPerformed(evt);
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
                    .addComponent(btnAcessarTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfdBuscar)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tfdBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAcessarTitulo)
                .addGap(37, 37, 37)
                .addComponent(btnSair)
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Vendas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 204))); // NOI18N

        tblListaTitulos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "N° Título", "Cliente", "Data Emissão", "Data Vencimento", "Data Pagamento", "Valor do Título", "Valor Pago"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblListaTitulos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblListaTitulosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblListaTitulos);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1082, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane1)
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

    private void listarTitulos() {

        if (rbtFinalizadas.isSelected() && rbtAbertos.isSelected()) {
            formasPagas.setQuitado('A');
        } else if (rbtFinalizadas.isSelected() && !rbtAbertos.isSelected()) {
            formasPagas.setQuitado('T');
        } else if (!rbtFinalizadas.isSelected() && rbtAbertos.isSelected()) {
            formasPagas.setQuitado('F');
        } else {
            formasPagas.setQuitado(' ');
        }

        try {
            //setar para tabela modelo de dados
            tblListaTitulos.setModel(this.obterDadosParaTabelaCompleto());
            tblListaTitulos.getColumnModel().getColumn(0).setPreferredWidth(10);
            tblListaTitulos.getColumnModel().getColumn(1).setPreferredWidth(80);
            tblListaTitulos.getColumnModel().getColumn(2).setPreferredWidth(80);
            tblListaTitulos.getColumnModel().getColumn(3).setPreferredWidth(50);
            tblListaTitulos.getColumnModel().getColumn(4).setPreferredWidth(50);
            tblListaTitulos.getColumnModel().getColumn(5).setPreferredWidth(40);
            tblListaTitulos.getColumnModel().getColumn(6).setPreferredWidth(20);
            tblListaTitulos.getColumnModel().getColumn(7).setPreferredWidth(10);
            tblListaTitulos.getColumnModel().getColumn(7).setPreferredWidth(0);

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

        FinanceiroPagarDAO formasDAO = new FinanceiroPagarDAO();
        ArrayList<FinanceiroPagar> formas = formasDAO.consultar(formasPagas,cbxTipoFiltro.getSelectedIndex());

//        ClienteDAO cliDAO = new ClienteDAO();
//        ArrayList<Cliente> clientes = cliDAO.consultar(cliente);
        dtm.addColumn("N° TÍTULO");
        dtm.addColumn("CLIENTE");
        dtm.addColumn("DATA EMISSÃO");
        dtm.addColumn("DATA VENCIMENTO");
        dtm.addColumn("VALOR PREVISTO");
        dtm.addColumn("VALOR TÍTULO");
        dtm.addColumn("DATA PAG");
        dtm.addColumn("VALOR PAGO");
        dtm.addColumn("SITUAÇÃO");

        for (int i = 0; i < formas.size(); i++) {
            //popular tabela
            String fase = "";
            if (String.valueOf(formas.get(i).getQuitado()).equalsIgnoreCase("T")) {
                fase = "Encerrado";
            } else {
                fase = "Aberto";
            }

            dtm.addRow(new String[]{String.valueOf(formas.get(i).getNumeroTitulo()),
                String.valueOf(formas.get(i).getFornecedor().getRazaoSocial()),
                formas.get(i).getDataEmissao(),
                formas.get(i).getVencimento(),
                String.valueOf(formas.get(i).getValorProvisorio()),
                String.valueOf(formas.get(i).getValor()),                
                String.valueOf(formas.get(i).getDataPagamento()),
                String.valueOf(formas.get(i).getValorPago()),
                fase,});
        }
//retorna o modelo
        return dtm;
    }


    private void rbtFiltroDataItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbtFiltroDataItemStateChanged
        if (rbtFiltroData.isSelected()) {

            tffDataFim.setEnabled(true);
            tffDataInicio.setEnabled(true);
            cbxTipoFiltro.setEnabled(true);

        } else {

            tffDataFim.setCalendar(null);
            tffDataInicio.setCalendar(null);
            tffDataFim.setEnabled(false);
            tffDataInicio.setEnabled(false);
            cbxTipoFiltro.setEnabled(false);
            cbxTipoFiltro.setSelectedIndex(0);
            formasPagas.setDataInicio(null);
            formasPagas.setDataFim(null);

        }

    }//GEN-LAST:event_rbtFiltroDataItemStateChanged

    private void tffDataInicioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tffDataInicioKeyReleased

    }//GEN-LAST:event_tffDataInicioKeyReleased

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed

        validarTipoDeBusca();


    }//GEN-LAST:event_btnBuscarActionPerformed
    private void buscar() {
//        if (tfdBuscar.getText().length() > 0) {
//            
//            cli.setRazaoSocial(tfdBuscar.getText());
//            formasPagas.setCliente(cli);
//            try {
//                formasPagas.setId(Integer.parseInt(tfdBuscar.getText()));
//                
//            } catch (Exception e) {
//                
//            }
//            
//        } else {
//            tfdBuscar.setText("");
//            cli.setRazaoSocial(tfdBuscar.getText());
//            formasPagas.setId(0);
//        }
//        if (tffDataInicio.getCalendar() != null && tffDataFim.getCalendar() != null) {
//            
//            String dataInicio = Formatacao.ajustaDataDMAJCalendar(tffDataInicio);
//            String dataFim = Formatacao.ajustaDataDMAJCalendar(tffDataFim);
//            fat.setDataEmissaoInicio(dataInicio);
//            fat.setDataEmissaoFim(dataFim);
//            listarTitulos();
//        } else {
//            fat.setDataEmissaoInicio(null);
//            fat.setDataEmissaoFim(null);
////            JOptionPane.showMessageDialog(rootPane, "entrou no else");
//            listarTitulos();
//        }

        forn.setRazaoSocial(tfdBuscar.getText());
        formasPagas.setNumeroTitulo(tfdBuscar.getText());
        formasPagas.setFornecedor(forn);
        listarTitulos();
    }

    private void validarTipoDeBusca() {

        if (tffDataInicio.getCalendar() != null && tffDataFim.getCalendar() != null) {
            try {
                if (Validacao.validadeFiltroDeData(tffDataInicio, tffDataFim)) {
                    formasPagas.setDataInicio(Formatacao.ajustaDataDMAJCalendar(tffDataInicio));
                    formasPagas.setDataFim(Formatacao.ajustaDataDMAJCalendar(tffDataFim));
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
    private void btnAcessarTituloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAcessarTituloActionPerformed
        int row = tblListaTitulos.getSelectedRow();
        if (row >= 0) {
            try {
                acessarTitulo();
            } catch (ParseException ex) {
                Logger.getLogger(JdgListagemFinanceiroPagar.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Erro ao acessar o título: \nNenhum título selecionado");
        }


    }//GEN-LAST:event_btnAcessarTituloActionPerformed

    private void tblListaTitulosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListaTitulosMouseClicked
        int row = tblListaTitulos.getSelectedRow();
//        if (tblListaTitulos.getValueAt(row, 6) == "Cancelado") {
//            btnCancelarVenda.setEnabled(false);
//        } else {
//            btnCancelarVenda.setEnabled(true);
//        }

        if (evt.getClickCount() > 1) {
            int linhaSelecionada = tblListaTitulos.getSelectedRow();
            try {
                acessarTitulo();
            } catch (ParseException ex) {
                Logger.getLogger(JdgListagemFinanceiroPagar.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }//GEN-LAST:event_tblListaTitulosMouseClicked

    private void rbtAbertosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbtAbertosItemStateChanged
        listarTitulos();
    }//GEN-LAST:event_rbtAbertosItemStateChanged

    private void rbtFinalizadasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbtFinalizadasItemStateChanged
        listarTitulos();
    }//GEN-LAST:event_rbtFinalizadasItemStateChanged

    private void tfdBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfdBuscarKeyReleased
        validarTipoDeBusca();
    }//GEN-LAST:event_tfdBuscarKeyReleased

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        dispose();
    }//GEN-LAST:event_btnSairActionPerformed

    private void rbtAbertosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtAbertosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbtAbertosActionPerformed

    private void rbtFiltroDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtFiltroDataActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbtFiltroDataActionPerformed

    private void acessarTitulo() throws ParseException {
        formasPagas.setId(0);
        arrayFormasPagas.removeAll(arrayFormasPagas);
        int row = tblListaTitulos.getSelectedRow();
        FinanceiroPagarDAO finDAO = new FinanceiroPagarDAO();
        FormaPagamento formaPagamento = new FormaPagamento();

        arrayFormasPagas = finDAO.consultar(formasPagas,cbxTipoFiltro.getSelectedIndex());

        formaPagamento.setDescricao(arrayFormasPagas.get(row).getFormaPagamento().getDescricao());
        formasPagas.setFormaPagamento(formaPagamento);
        formasPagas.setId(arrayFormasPagas.get(row).getId());
        forn.setRazaoSocial(arrayFormasPagas.get(row).getFornecedor().getRazaoSocial());
        formasPagas.setQuitado(arrayFormasPagas.get(row).getQuitado());
        formasPagas.setDataEmissao(arrayFormasPagas.get(row).getDataEmissao());
        formasPagas.setDataPagamento(arrayFormasPagas.get(row).getDataPagamento());
        formasPagas.setVencimento(arrayFormasPagas.get(row).getVencimento());
        Faturamento fat = new Faturamento();
        //fat.setId(arrayFormasPagas.get(row).getFaturamento().getId());
        formasPagas.setNumeroTitulo(arrayFormasPagas.get(row).getNumeroTitulo());
        formasPagas.setValorProvisorio(arrayFormasPagas.get(row).getValorProvisorio());
        formasPagas.setValor(arrayFormasPagas.get(row).getValor());
        formasPagas.setValorPago(arrayFormasPagas.get(row).getValorPago());
        formasPagas.setFornecedor(forn);
        //JdgVendaRegistrada vendaRegistrada = new JdgVendaRegistrada(null, true, fat);
        //vendaRegistrada.setVisible(true);

        JdgCadastroFinanceiroPagar financeiroPagar = new JdgCadastroFinanceiroPagar(null, true, formasPagas);
        financeiroPagar.setVisible(true);

        formasPagas.setId(0);
        forn.setRazaoSocial("");
        formasPagas.setNumeroTitulo("");
        formasPagas.setFornecedor(forn);
        listarTitulos();
    }

    private void cancelarVenda() {
//        int row = tblListaVendas.getSelectedRow();
//        
//        fat.setId(vendas.get(row).getId());
//        
//        FaturamentoDAO fatDAO = new FaturamentoDAO();
//        if (fatDAO.salvar(fat, null, null)) {
//            JOptionPane.showMessageDialog(rootPane, "Cancelamento da venda realizado com sucesso!");
//            fat.setId(0);
//            listarTitulos();
//        } else {
//            JOptionPane.showMessageDialog(rootPane, "erro retornado pelo sistema:\nErro ao cancelar venda.");
//        }
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
            java.util.logging.Logger.getLogger(JdgListagemFinanceiroPagar.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JdgListagemFinanceiroPagar.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JdgListagemFinanceiroPagar.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JdgListagemFinanceiroPagar.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JdgListagemFinanceiroPagar dialog = new JdgListagemFinanceiroPagar(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnAcessarTitulo;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnSair;
    private javax.swing.JComboBox<String> cbxTipoFiltro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rbtAbertos;
    private javax.swing.JRadioButton rbtFiltroData;
    private javax.swing.JRadioButton rbtFinalizadas;
    private javax.swing.JTable tblListaTitulos;
    private javax.swing.JTextField tfdBuscar;
    private com.toedter.calendar.JDateChooser tffDataFim;
    private com.toedter.calendar.JDateChooser tffDataInicio;
    // End of variables declaration//GEN-END:variables
}
