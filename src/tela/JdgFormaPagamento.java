/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tela;

import DAO.ClienteDAO;
import DAO.FaturamentoDAO;
import DAO.FinanceiroReceberDAO;
import DAO.FormaPagamentoDAO;
import apoio.ConexaoBD;
import apoio.DocumentoLimitado;
import apoio.Formatacao;
import entidade.Faturamento;
import entidade.FaturamentoItem;
import entidade.FormaPagamento;
import entidade.FinanceiroReceber;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author pc05
 */
public class JdgFormaPagamento extends javax.swing.JDialog {

    private ArrayList<FaturamentoItem> mercs;
    private Faturamento fat;
    private FaturamentoItem fatItem;
    private FormaPagamento fp;
    private ArrayList<FormaPagamento> formas;
    private double totalPago = 0;
    private FinanceiroReceber formasPagas;
    private ArrayList<FinanceiroReceber> ArrayFormasPagas;
    private int numeroParcela = 1;
    int ultimaVenda = 0;

    /**
     * Creates new form JdgFormaPagamento
     */
    public JdgFormaPagamento(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public JdgFormaPagamento(java.awt.Frame parent, boolean modal, ArrayList<FaturamentoItem> mercs, Faturamento fat, FaturamentoItem fatItem) {
        super(parent, modal);
        initComponents();
        
        fp = new FormaPagamento();
        this.mercs = mercs;
        this.fat = fat;
        this.fatItem = fatItem;
        ArrayFormasPagas = new ArrayList<>();
        listarFormasPagamento();
        DocumentoLimitado dl = new DocumentoLimitado(3);
        DocumentoLimitado prazo = new DocumentoLimitado(2);
        tffCodigoPagamento.setDocument(dl);
        tffParcelas.setDocument(prazo);

        valoresDefault();
        atualizarValores();
        listarFormasPagamentoPagas();

    }

    

//   

    private void inserirFormaPagamento() {
        formasPagas = new FinanceiroReceber();
        formasPagas.setId(Integer.parseInt(tffCodigoPagamento.getText()));
//       formasPagas.setDescricao(descricao);

        //-----
        Date data;

        //fp.setId(Integer.parseInt(tffCodigoPagamento.getText()));
        fp = new FormaPagamento();
        fp.setId(Integer.parseInt(tffCodigoPagamento.getText()));
        fp.setAtivo('T');
        FormaPagamentoDAO formasDAO = new FormaPagamentoDAO();

        //formasDAO.consultar(fp);
        formasPagas.setFormaPagamento(fp);
        FaturamentoDAO faturamentoDAO = new FaturamentoDAO();
        ultimaVenda = (faturamentoDAO.ultimaVenda() + 1);
        if (validarParcela()) {
            double valorTotalTruncado = 0;

            for (int i = 0; i < Integer.parseInt(tffParcelas.getText()); i++) {
                data = new Date();

                data.setDate(data.getDate() + 30 * (i + 1));
                String dataFormatada = Formatacao.ajustaDataDMAJCalendar(data);

                formasPagas.setVencimento(dataFormatada);
                formasPagas.setParcelas(Integer.parseInt(tffParcelas.getText()));
                double valor = Double.parseDouble(tffValorPago.getText().replace(",", ".")) / Integer.parseInt(tffParcelas.getText());
                formasPagas.setValor((truncate(valor, 2)));
                formasPagas.setId(fp.getId());
                formasPagas.setNumeroTitulo(ultimaVenda + "-" + numeroParcela);
                numeroParcela++;
                formasPagas.setQuitado('F');
                formasPagas.setCliente(fat.getCliente());
                formasPagas.setAtivo('T');
                formasPagas.setValorPago(0);
                formasPagas.setDataEmissao(Formatacao.getDataAtual());
                formasPagas.setFormaPagamento(fp);
                valorTotalTruncado = valorTotalTruncado + formasPagas.getValor();
                if (i + 1 == Integer.parseInt(tffParcelas.getText())) {

                    double diferenca = valor * Integer.parseInt(tffParcelas.getText()) - valorTotalTruncado;
                    if (valor != valorTotalTruncado) {
                        formasPagas.setValor(truncate(diferenca + valor, 2));
                    } else {
                        formasPagas.setValor(valor);
                    }

                }
                for (int j = 0; j < formas.size(); j++) {
                    if (formas.get(j).getId() == Integer.parseInt(tffCodigoPagamento.getText())) {
                        formasPagas.setDescricao(formas.get(j).getDescricao());
                        fp.setDescricao(formas.get(j).getDescricao());
                        formasPagas.setFormaPagamento(fp);
                    }
                }
             
                ArrayFormasPagas.add(formasPagas);
                formasPagas = new FinanceiroReceber();
            }

            
            listarFormasPagamentoPagas();
        } else {

            data = new Date();
            data.setDate(data.getDate());
            String dataFormatada = Formatacao.ajustaDataDMAJCalendar(data);
            formasPagas.setVencimento(dataFormatada);
            formasPagas.setParcelas(Integer.parseInt(tffParcelas.getText()));
            formasPagas.setQuitado('T');
            formasPagas.setValorPago(Double.parseDouble(tffValorPago.getText().replace(",", ".")));
            formasPagas.setDataEmissao(Formatacao.getDataAtual());
            formasPagas.setAtivo('T');
            formasPagas.setCliente(fat.getCliente());
            formasPagas.setDataPagamento(dataFormatada);
            double valor = Double.parseDouble(tffValorPago.getText().replace(",", "."));
            formasPagas.setValor(Double.parseDouble(tffValorPago.getText().replace(",", ".")));
          //  JOptionPane.showMessageDialog(rootPane, tffValorPago.getText().replace(",", "."));
            formasPagas.setId(fp.getId());
            formasPagas.setNumeroTitulo(ultimaVenda + "-" + numeroParcela);
            formasPagas.setFormaPagamento(fp);
            numeroParcela++;

            for (int j = 0; j < formas.size(); j++) {
                if (formas.get(j).getId() == Integer.parseInt(tffCodigoPagamento.getText())) {
                    formasPagas.setDescricao(formas.get(j).getDescricao());
                    fp.setDescricao(formas.get(j).getDescricao());
                    formasPagas.setFormaPagamento(fp);
                }
            }
            //            if (formas.get(i).getId() == Integer.parseInt(tffCodigoPagamento.getText())) {
            //              formasPagas.setDescricao(formas.get(i).getDescricao());
//                }
//                formasPagas.setDescricao(fp.getDescricao());

            ArrayFormasPagas.add(formasPagas);
            formasPagas = new FinanceiroReceber();
        }

        //----
        //formasPagas.setParcelas(Integer.parseInt(tffParcelas.getText()));
        //formasPagas.setValor(Double.parseDouble(tffValorPago.getText().replace(",", ".")));
        //ArrayFormasPagas.add(formasPagas);
        listarFormasPagamentoPagas();
        limparCampos();
    }

    public double truncate(Double valor, int precisao) {
        BigDecimal bd = BigDecimal.valueOf(valor);
        bd = bd.setScale(precisao, BigDecimal.ROUND_DOWN);
        return bd.doubleValue();
    }

    private void limparCampos() {
        tffCodigoPagamento.setText("");
        tffParcelas.setText("1");
        tffValorPago.setText(lblSaldoPagar.getText());
        tffParcelas.setEnabled(false);

    }

    private void valoresDefault() {
        tffParcelas.setEnabled(false);
        tffParcelas.setText("1");
        tffDesconto.setText("0,00");
        lblTotalPago.setText("0,00");
    }

    private void atualizarValores() {

        lblTotalBruto.setText(String.valueOf(fat.getValorTotal()));
        lblTotalLiquido.setText(String.valueOf(fat.getValorTotal() - Double.parseDouble(tffDesconto.getText().replace(",", "."))));
        tffValorPago.setText(lblTotalLiquido.getText().replace(".", ","));
        lblTotalPago.setText(String.valueOf(totalPago));
        double saldoAPagar = Double.parseDouble(lblTotalLiquido.getText().replace(",", ".")) - totalPago;
        if (saldoAPagar >= 0) {
            lblSaldo.setText("Saldo a Pagar");
            lblSaldoPagar.setText(String.valueOf(saldoAPagar));
            tffValorPago.setText(lblSaldoPagar.getText());
        } else {
            saldoAPagar = saldoAPagar * (-1);
            lblSaldo.setText("Troco");
            lblSaldoPagar.setText(String.valueOf(saldoAPagar));
        }

    }

    private void listarFormasPagamento() {
        try {
            //setar para tabela modelo de dados
            tblFormaPagamento.setModel(this.obterDadosParaJTable());
            tblFormaPagamento.getColumnModel().getColumn(0).setPreferredWidth(5);
            tblFormaPagamento.getColumnModel().getColumn(1).setPreferredWidth(100);
            tblFormaPagamento.getColumnModel().getColumn(2).setPreferredWidth(10);
//            tblFormaPagamento.getColumnModel().getColumn(3).setPreferredWidth(1);

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
        fp.setAtivo('T');

        formas = fpDAO.consultar(fp);

        dtm.addColumn("ID");
        dtm.addColumn("DESCRIÇÃO");
        dtm.addColumn("FORMA");
//        dtm.addColumn("SITUAÇÃO");

        for (int i = 0; i < formas.size(); i++) {
            //popular tabela

            String resultPrazo = "";
            if (String.valueOf(formas.get(i).getFormaAvista()).equalsIgnoreCase("T")) {
                resultPrazo = "Avista";
            } else {
                resultPrazo = "Prazo";
            }
            dtm.addRow(new String[]{String.valueOf(formas.get(i).getId()),
                formas.get(i).getDescricao(), resultPrazo});
        }
//retorna o modelo
        return dtm;
    }

    private void listarFormasPagamentoPagas() {
        try {
            //setar para tabela modelo de dados
            tblFormasPagas.setModel(this.obterDadosParaJTablePagas());
            tblFormasPagas.getColumnModel().getColumn(0).setPreferredWidth(5);
            tblFormasPagas.getColumnModel().getColumn(1).setPreferredWidth(100);
            tblFormasPagas.getColumnModel().getColumn(2).setPreferredWidth(10);
            tblFormasPagas.getColumnModel().getColumn(3).setPreferredWidth(1);

        } catch (Exception ex) {
            Logger.getLogger(JdgListaFormaPagamento.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public DefaultTableModel obterDadosParaJTablePagas() throws Exception {
        DefaultTableModel dtm = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
//adiciona titulo para as colunas
        dtm.addColumn("N° TÍTULO");
        dtm.addColumn("ID PAG");
        dtm.addColumn("DESCRIÇÃO");
        dtm.addColumn("VALOR");
        dtm.addColumn("VENCIMENTO");

        for (int i = 0; i < ArrayFormasPagas.size(); i++) {
            //popular tabela

            dtm.addRow(new String[]{String.valueOf(ArrayFormasPagas.get(i).getNumeroTitulo()),
                String.valueOf(ArrayFormasPagas.get(i).getId()),
                ArrayFormasPagas.get(i).getDescricao(),
                String.valueOf(ArrayFormasPagas.get(i).getValor()),
                String.valueOf(ArrayFormasPagas.get(i).getVencimento())});

        }

//retorna o modelo
        return dtm;
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tblFormasPagas = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        tffCodigoPagamento = new javax.swing.JFormattedTextField();
        tffValorPago = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblFormaPagamento = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        lblTotalBruto = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tffDesconto = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        lblTotalLiquido = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tffParcelas = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        lblTotalPago = new javax.swing.JLabel();
        lblSaldo = new javax.swing.JLabel();
        lblSaldoPagar = new javax.swing.JLabel();
        btnFinalizar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("EasyPDV - Forma de Pagamento");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Forma de Pagamento", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(51, 0, 255))); // NOI18N

        tblFormasPagas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id", "Descrição", "Valor", "Parcelas", "Vencimento"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblFormasPagas);

        jLabel1.setText("Código:");

        tffCodigoPagamento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tffCodigoPagamentoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tffCodigoPagamentoKeyReleased(evt);
            }
        });

        tffValorPago.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        tffValorPago.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tffValorPagoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tffValorPagoKeyReleased(evt);
            }
        });

        jLabel2.setText("Valor Pago:");

        tblFormaPagamento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblFormaPagamento.setAutoscrolls(false);
        tblFormaPagamento.setEnabled(false);
        tblFormaPagamento.setFocusable(false);
        tblFormaPagamento.setRequestFocusEnabled(false);
        jScrollPane2.setViewportView(tblFormaPagamento);

        jLabel3.setText("Total Bruto:");

        lblTotalBruto.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lblTotalBruto.setText("jLabel4");

        jLabel4.setText("Desconto:");

        tffDesconto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        tffDesconto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tffDescontoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tffDescontoKeyReleased(evt);
            }
        });

        jLabel5.setText("Total Líquido:");

        lblTotalLiquido.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lblTotalLiquido.setText("jLabel4");

        jLabel6.setText("Parcelas:");

        tffParcelas.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("##"))));
        tffParcelas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tffParcelasKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tffParcelasKeyReleased(evt);
            }
        });

        jLabel7.setText("Total Pago:");

        lblTotalPago.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lblTotalPago.setText("jLabel4");

        lblSaldo.setText("Saldo a pagar:");

        lblSaldoPagar.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lblSaldoPagar.setText("jLabel4");

        btnFinalizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Icon_money.png"))); // NOI18N
        btnFinalizar.setText("FINALIZAR");
        btnFinalizar.setEnabled(false);
        btnFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizarActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icon_Schutdown16.png"))); // NOI18N
        jButton2.setText("Sair");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel8.setText("*Use a tecla ENTER para avançar");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(136, 136, 136)
                            .addComponent(btnFinalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(39, 39, 39)
                            .addComponent(jButton2)
                            .addGap(114, 114, 114))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap()))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addComponent(tffCodigoPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel4)
                                        .addComponent(tffDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addComponent(tffValorPago, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(tffParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(lblTotalBruto)
                            .addComponent(jLabel7)
                            .addComponent(lblTotalPago))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTotalLiquido)
                            .addComponent(lblSaldoPagar)
                            .addComponent(lblSaldo)
                            .addComponent(jLabel5))
                        .addGap(63, 63, 63))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tffCodigoPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tffDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tffValorPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tffParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lblTotalLiquido)
                                    .addGap(18, 18, 18)
                                    .addComponent(lblSaldo)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lblSaldoPagar))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lblTotalBruto)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel7)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lblTotalPago))))
                        .addGap(16, 16, 16)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFinalizar)
                    .addComponent(jButton2)))
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
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tffValorPagoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tffValorPagoKeyPressed

    }//GEN-LAST:event_tffValorPagoKeyPressed

    private void tffValorPagoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tffValorPagoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            tffValorPago.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###.00"))));
            tffValorPago.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
            totalPago = Double.parseDouble(lblTotalPago.getText().replace(",", ".")) + Double.parseDouble(tffValorPago.getText().replace(",", "."));
            
            if (validarParcela()) {
                tffParcelas.requestFocus();

            } else {
                inserirFormaPagamento();
                atualizarValores();
                if ((Double.parseDouble(lblTotalPago.getText()) >= (Double.parseDouble(lblTotalLiquido.getText())))) {
                    btnFinalizar.setEnabled(true);
                    
                    finalizarVenda();
                }
                tffCodigoPagamento.requestFocus();

            }

        }
    }//GEN-LAST:event_tffValorPagoKeyReleased

    private void tffDescontoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tffDescontoKeyPressed
        if (evt.getKeyCode() != KeyEvent.VK_ENTER && evt.getKeyCode() != KeyEvent.VK_TAB
                && tffDesconto.getText().equals("0,00")) {
            tffDesconto.setText("");
        }
    }//GEN-LAST:event_tffDescontoKeyPressed

    private void tffDescontoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tffDescontoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            if (!tffDesconto.getText().equals("0,00") && !tffDesconto.getText().equals("0.00") && !tffDesconto.getText().endsWith("0")) {
                tffDesconto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###.00"))));
                tffDesconto.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
            }
            atualizarValores();
            if ((Double.parseDouble(lblTotalPago.getText()) >= (Double.parseDouble(lblTotalLiquido.getText())))) {
                btnFinalizar.setEnabled(true);
                finalizarVenda();
            }
            tffValorPago.requestFocus();

        }
    }//GEN-LAST:event_tffDescontoKeyReleased

    private void tffParcelasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tffParcelasKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tffParcelasKeyPressed

    private void tffParcelasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tffParcelasKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {

            inserirFormaPagamento();
            atualizarValores();
            
            if ((Double.parseDouble(lblTotalPago.getText()) >= (Double.parseDouble(lblTotalLiquido.getText())))) {
                    btnFinalizar.setEnabled(true);
                    try {
                    //Thread.sleep(4000);
                   // TimeUnit.SECONDS.sleep(4);
                } catch (Exception e) {
                }
                    
                    
                    finalizarVenda();
                }
            
            tffCodigoPagamento.requestFocus();
        }
    }//GEN-LAST:event_tffParcelasKeyReleased

    private void btnFinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizarActionPerformed
        finalizarVenda();
    }//GEN-LAST:event_btnFinalizarActionPerformed
    private void finalizarVenda() {
        
        
        FaturamentoDAO faturamentoDAO = new FaturamentoDAO();
        FormaPagamento formaPagamento = new FormaPagamento();

        FinanceiroReceberDAO financeiroReceber = new FinanceiroReceberDAO();

        fat.setValorTotalLiquido(Double.parseDouble(lblTotalLiquido.getText()));
        String dataAtual = Formatacao.getDataAtual();
        fat.setDesconto(Double.parseDouble(tffDesconto.getText().replace(",", ".")));
        fat.setDataEmissao(dataAtual);

        if (faturamentoDAO.salvar(fat, mercs, fatItem)) {

            financeiroReceber.salvar(ArrayFormasPagas, ultimaVenda, formasPagas);
//            try {
//                Thread.sleep(3000);
//            } catch (Exception e) {
//            }
                        

            JOptionPane.showMessageDialog(rootPane, "Venda registrada com sucesso!");
            dispose();
            
        } else {
            JOptionPane.showMessageDialog(rootPane, "Erro retornado pelo sistema: \nFalha ao finalizar a venda");
        }
    }
    private void tffCodigoPagamentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tffCodigoPagamentoKeyReleased

        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {

            try {

                FormaPagamentoDAO formaDAO = new FormaPagamentoDAO();
                ArrayList<FormaPagamento> formas = new ArrayList<FormaPagamento>();
                formas = formaDAO.consultar(Integer.parseInt(tffCodigoPagamento.getText()));
                if (formas.size() > 0) {
                    validarParcela();

                    tffDesconto.requestFocus();

                } else {
                    JOptionPane.showMessageDialog(rootPane, "Erro ao inserir forma de pagamento: \n Forma de pagamento não localizada.");

                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, "Erro ao inserir forma de pagamento: \n Forma de pagamento não localizada.");
            }
        }
    }//GEN-LAST:event_tffCodigoPagamentoKeyReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tffCodigoPagamentoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tffCodigoPagamentoKeyPressed
        atualizarValores();


    }//GEN-LAST:event_tffCodigoPagamentoKeyPressed

    private boolean validarParcela() {
        boolean ok = false;
        for (int i = 0; i < formas.size(); i++) {
            if (formas.get(i).getId() == Integer.parseInt(tffCodigoPagamento.getText()) && formas.get(i).getFormaAvista() == 'F') {
                ok = true;
            }

        }
        if (ok) {
            tffParcelas.setEnabled(true);
        } else {
            tffParcelas.setEnabled(false);
        }
        return ok;
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
            java.util.logging.Logger.getLogger(JdgFormaPagamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JdgFormaPagamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JdgFormaPagamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JdgFormaPagamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JdgFormaPagamento dialog = new JdgFormaPagamento(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnFinalizar;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblSaldo;
    private javax.swing.JLabel lblSaldoPagar;
    private javax.swing.JLabel lblTotalBruto;
    private javax.swing.JLabel lblTotalLiquido;
    private javax.swing.JLabel lblTotalPago;
    private javax.swing.JTable tblFormaPagamento;
    private javax.swing.JTable tblFormasPagas;
    private javax.swing.JFormattedTextField tffCodigoPagamento;
    private javax.swing.JFormattedTextField tffDesconto;
    private javax.swing.JFormattedTextField tffParcelas;
    private javax.swing.JFormattedTextField tffValorPago;
    // End of variables declaration//GEN-END:variables
}
