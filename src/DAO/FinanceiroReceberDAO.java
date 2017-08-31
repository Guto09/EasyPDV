/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import apoio.ConexaoBD;
import apoio.Formatacao;
import entidade.Cidade;
import entidade.Cliente;
import entidade.Faturamento;
import entidade.FaturamentoItem;
import entidade.FormaPagamento;
import entidade.FinanceiroReceber;
import entidade.Mercadoria;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import tela.JdgPedidoVenda;

/**
 *
 * @author Mileto
 */
public class FinanceiroReceberDAO {

    ArrayList<FinanceiroReceber> formasPagas;
    FinanceiroReceber formaPagamentoPagas;

    public boolean salvar(ArrayList<FinanceiroReceber> formasPagas, int idFaturamento, FinanceiroReceber formaPagamentoPagas) {
        this.formaPagamentoPagas = formaPagamentoPagas;
        if (this.formaPagamentoPagas.getId() == 0) {
            try {

                Statement st = ConexaoBD.getInstance().getConnection().createStatement();

                for (int i = 0; i < formasPagas.size(); i++) {
                    String sql = "";
                    if (formasPagas.get(i).getDataPagamento() == null) {

                        sql = "INSERT INTO financeiro_receber VALUES ("
                                + "DEFAULT," + "" + idFaturamento + ","
                                + "" + formasPagas.get(i).getCliente().getId() + ","
                                + "" + formasPagas.get(i).getFormaPagamento().getId() + ","
                                + "'" + formasPagas.get(i).getNumeroTitulo() + "',"
                                + "'" + formasPagas.get(i).getDataEmissao() + "',"
                                + "'" + formasPagas.get(i).getVencimento() + "',"
                                + "null,"
                                + formasPagas.get(i).getValor() + ","
                                + formasPagas.get(i).getValorPago() + ","
                                + "'" + formasPagas.get(i).getQuitado() + "',"
                                + "'" + formasPagas.get(i).getAtivo() + "'"
                                + ")";
                    } else {

                        sql = "INSERT INTO financeiro_receber VALUES ("
                                + "DEFAULT," + "" + idFaturamento + ","
                                + "" + formasPagas.get(i).getCliente().getId() + ","
                                + "" + formasPagas.get(i).getFormaPagamento().getId() + ","
                                + "'" + formasPagas.get(i).getNumeroTitulo() + "',"
                                + "'" + formasPagas.get(i).getDataEmissao() + "',"
                                + "'" + formasPagas.get(i).getVencimento() + "',"
                                + "'" + formasPagas.get(i).getDataPagamento() + "',"
                                + formasPagas.get(i).getValor() + ","
                                + formasPagas.get(i).getValorPago() + ","
                                + "'" + formasPagas.get(i).getQuitado() + "',"
                                + "'" + formasPagas.get(i).getAtivo() + "'"
                                + ")";
                    }
                  //  System.out.println(sql);
                    int resultado = st.executeUpdate(sql);
                }

                return true;
            } catch (Exception e) {
                Logger.getLogger(JdgPedidoVenda.class.getName()).log(Level.SEVERE, null, e);

            }
            return true;
        } else {
            try {

                Statement st = ConexaoBD.getInstance().getConnection().createStatement();
                String sql = "update financeiro_receber set valor_pago = " + formaPagamentoPagas.getValorPago() + ","
                        + " quitado ='" + formaPagamentoPagas.getQuitado() + "',"
                        + " data_pagamento = '" + formaPagamentoPagas.getDataPagamento() + "',"
                        + " ativo = '" + formaPagamentoPagas.getAtivo()+"'"
                        + " where id = " + formaPagamentoPagas.getId();

                //System.out.println(sql);
                int resultado = st.executeUpdate(sql);
                return true;
            } catch (Exception e) {
                Logger.getLogger(JdgPedidoVenda.class.getName()).log(Level.SEVERE, null, e);
                return false;
            }

        }
    }

    public ArrayList<FinanceiroReceber> consultar(FinanceiroReceber formaPagamentoPagas, int tipoFiltro) {
        this.formaPagamentoPagas = formaPagamentoPagas;
        String sql = "";
        if ((formaPagamentoPagas.getDataInicio() == null && formaPagamentoPagas.getDataFim() == null)
                && (formaPagamentoPagas.getQuitado() == 'T' || formaPagamentoPagas.getQuitado() == 'F')) {

            sql = "select f.id idfinanceiro,f.id_faturamento,f.id_cliente,f.numero_titulo,f.data_emissao,"
                    + "f.data_vencimento,f.data_pagamento,f.valor_titulo,"
                    + "f.valor_pago,f.quitado, f.ativo ativofinanceiro,c.id idcliente,"
                    + "c.razao_social,c.cpf_cnpj,c.endereco,c.telefone, c.ativo ativocliente,"
                    + "fp.descricao "
                    + " from financeiro_receber f, cliente c,forma_pagamento fp "
                    + "where c.id = f.id_cliente"
                    + " and fp.id = f.id_forma_pagamento "
                    + " and quitado ='" + formaPagamentoPagas.getQuitado() + "' "
                    + "and f.ativo = 'T' "
                    + "and (c.razao_social ilike '" + formaPagamentoPagas.getCliente().getRazaoSocial() + "%' "
                    + "or f.numero_titulo ilike '" + formaPagamentoPagas.getNumeroTitulo() + "%')";
        } else if ((formaPagamentoPagas.getDataInicio() != null && formaPagamentoPagas.getDataFim() != null)
                && (formaPagamentoPagas.getQuitado() == 'T' || formaPagamentoPagas.getQuitado() == 'F')) {
            if (tipoFiltro == 0) {
                sql = "select f.id idfinanceiro,f.id_faturamento,f.id_cliente,f.numero_titulo,f.data_emissao,"
                        + "f.data_vencimento,f.data_pagamento,f.valor_titulo,"
                        + "f.valor_pago,f.quitado, f.ativo ativofinanceiro,c.id idcliente,"
                        + "c.razao_social,c.cpf_cnpj,c.endereco,c.telefone, c.ativo ativocliente,"
                        + "fp.descricao "
                        + " from financeiro_receber f, cliente c,forma_pagamento fp "
                        + "where c.id = f.id_cliente"
                        + " and fp.id = f.id_forma_pagamento "
                        + " and quitado ='" + formaPagamentoPagas.getQuitado() + "' "
                        + "and f.data_emissao >='" + formaPagamentoPagas.getDataInicio() + "' "
                        + "and f.data_emissao <= '" + formaPagamentoPagas.getDataFim() + " 23:59:59' "
                        + "and f.ativo = 'T' "
                        + "and (c.razao_social ilike '" + formaPagamentoPagas.getCliente().getRazaoSocial() + "%' "
                        + "or f.numero_titulo ilike '" + formaPagamentoPagas.getNumeroTitulo() + "%')";
            } else if (tipoFiltro == 1) {
                sql = "select f.id idfinanceiro,f.id_faturamento,f.id_cliente,f.numero_titulo,f.data_emissao,"
                        + "f.data_vencimento,f.data_pagamento,f.valor_titulo,"
                        + "f.valor_pago,f.quitado, f.ativo ativofinanceiro,c.id idcliente,"
                        + "c.razao_social,c.cpf_cnpj,c.endereco,c.telefone, c.ativo ativocliente,"
                        + "fp.descricao "
                        + " from financeiro_receber f, cliente c,forma_pagamento fp "
                        + "where c.id = f.id_cliente"
                        + " and fp.id = f.id_forma_pagamento "
                        + " and quitado ='" + formaPagamentoPagas.getQuitado() + "' "
                        + "and f.data_vencimento >='" + formaPagamentoPagas.getDataInicio() + "' "
                        + "and f.data_vencimento <= '" + formaPagamentoPagas.getDataFim() + " 23:59:59' "
                        + "and f.ativo = 'T' "
                        + "and (c.razao_social ilike '" + formaPagamentoPagas.getCliente().getRazaoSocial() + "%' "
                        + "or f.numero_titulo ilike '" + formaPagamentoPagas.getNumeroTitulo() + "%')";
            } else if (tipoFiltro == 2) {
                sql = "select f.id idfinanceiro,f.id_faturamento,f.id_cliente,f.numero_titulo,f.data_emissao,"
                        + "f.data_vencimento,f.data_pagamento,f.valor_titulo,"
                        + "f.valor_pago,f.quitado, f.ativo ativofinanceiro,c.id idcliente,"
                        + "c.razao_social,c.cpf_cnpj,c.endereco,c.telefone, c.ativo ativocliente,"
                        + "fp.descricao "
                        + " from financeiro_receber f, cliente c,forma_pagamento fp "
                        + "where c.id = f.id_cliente"
                        + " and fp.id = f.id_forma_pagamento "
                        + " and quitado ='" + formaPagamentoPagas.getQuitado() + "' "
                        + "and f.data_pagamento >='" + formaPagamentoPagas.getDataInicio() + "' "
                        + "and f.data_pagamento <= '" + formaPagamentoPagas.getDataFim() + " 23:59:59' "
                        + "and f.ativo = 'T' "
                        + "and (c.razao_social ilike '" + formaPagamentoPagas.getCliente().getRazaoSocial() + "%' "
                        + "or f.numero_titulo ilike '" + formaPagamentoPagas.getNumeroTitulo() + "%')";
            }
        } else if ((formaPagamentoPagas.getDataInicio() == null && formaPagamentoPagas.getDataFim() == null)
                && (formaPagamentoPagas.getQuitado() == 'A')) {

            sql = "select f.id idfinanceiro,f.id_faturamento,f.id_cliente,f.numero_titulo,f.data_emissao,"
                    + "f.data_vencimento,f.data_pagamento,f.valor_titulo,"
                    + "f.valor_pago,f.quitado, f.ativo ativofinanceiro,c.id idcliente,"
                    + "c.razao_social,c.cpf_cnpj,c.endereco,c.telefone, c.ativo ativocliente,"
                    + "fp.descricao "
                    + " from financeiro_receber f, cliente c,forma_pagamento fp "
                    + "where c.id = f.id_cliente"
                    + " and fp.id = f.id_forma_pagamento "
                    + "and f.ativo = 'T' "
                    + "and (c.razao_social ilike '" + formaPagamentoPagas.getCliente().getRazaoSocial() + "%' "
                    + "or f.numero_titulo ilike '" + formaPagamentoPagas.getNumeroTitulo() + "%')";

        } else if ((formaPagamentoPagas.getDataInicio() != null && formaPagamentoPagas.getDataFim() != null)
                && (formaPagamentoPagas.getQuitado() == 'A')) {
            if (tipoFiltro == 0) {
                sql = "select f.id idfinanceiro,f.id_faturamento,f.id_cliente,f.numero_titulo,f.data_emissao,"
                        + "f.data_vencimento,f.data_pagamento,f.valor_titulo,"
                        + "f.valor_pago,f.quitado, f.ativo ativofinanceiro,c.id idcliente,"
                        + "c.razao_social,c.cpf_cnpj,c.endereco,c.telefone, c.ativo ativocliente,"
                        + "fp.descricao "
                        + " from financeiro_receber f, cliente c,forma_pagamento fp "
                        + "where c.id = f.id_cliente"
                        + " and fp.id = f.id_forma_pagamento "
                        + "and f.data_emissao >='" + formaPagamentoPagas.getDataInicio() + "' "
                        + "and f.data_emissao <= '" + formaPagamentoPagas.getDataFim() + " 23:59:59' "
                        + "and f.ativo = 'T' "
                        + "and (c.razao_social ilike '" + formaPagamentoPagas.getCliente().getRazaoSocial() + "%' "
                        + "or f.numero_titulo ilike '" + formaPagamentoPagas.getNumeroTitulo() + "%')";
            } else if (tipoFiltro == 1) {
                sql = "select f.id idfinanceiro,f.id_faturamento,f.id_cliente,f.numero_titulo,f.data_emissao,"
                        + "f.data_vencimento,f.data_pagamento,f.valor_titulo,"
                        + "f.valor_pago,f.quitado, f.ativo ativofinanceiro,c.id idcliente,"
                        + "c.razao_social,c.cpf_cnpj,c.endereco,c.telefone, c.ativo ativocliente,"
                        + "fp.descricao "
                        + " from financeiro_receber f, cliente c,forma_pagamento fp "
                        + "where c.id = f.id_cliente"
                        + " and fp.id = f.id_forma_pagamento "
                        + "and f.data_vencimento >='" + formaPagamentoPagas.getDataInicio() + "' "
                        + "and f.data_vencimento <= '" + formaPagamentoPagas.getDataFim() + " 23:59:59' "
                        + "and f.ativo = 'T' "
                        + "and (c.razao_social ilike '" + formaPagamentoPagas.getCliente().getRazaoSocial() + "%' "
                        + "or f.numero_titulo ilike '" + formaPagamentoPagas.getNumeroTitulo() + "%')";
            } else if (tipoFiltro == 2) {
                sql = "select f.id idfinanceiro,f.id_faturamento,f.id_cliente,f.numero_titulo,f.data_emissao,"
                        + "f.data_vencimento,f.data_pagamento,f.valor_titulo,"
                        + "f.valor_pago,f.quitado, f.ativo ativofinanceiro,c.id idcliente,"
                        + "c.razao_social,c.cpf_cnpj,c.endereco,c.telefone, c.ativo ativocliente,"
                        + "fp.descricao "
                        + " from financeiro_receber f, cliente c,forma_pagamento fp "
                        + "where c.id = f.id_cliente"
                        + " and fp.id = f.id_forma_pagamento "
                        + "and f.data_pagamento >='" + formaPagamentoPagas.getDataInicio() + "' "
                        + "and f.data_pagamento <= '" + formaPagamentoPagas.getDataFim() + " 23:59:59' "
                        + "and f.ativo = 'T' "
                        + "and (c.razao_social ilike '" + formaPagamentoPagas.getCliente().getRazaoSocial() + "%' "
                        + "or f.numero_titulo ilike '" + formaPagamentoPagas.getNumeroTitulo() + "%')";
            }
            
        }
        ArrayList<FinanceiroReceber> formas = new ArrayList<>();
        //faz consulta e adiciona os valores para o array...
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
           // System.out.println(sql);
            ResultSet resultado = st.executeQuery(sql);
            while (resultado.next()) {
                FinanceiroReceber forma = new FinanceiroReceber();
                Cidade cid = new Cidade();
                Cliente cli = new Cliente(cid);
                FormaPagamento formaPagamento = new FormaPagamento();
                formaPagamento.setDescricao(resultado.getString("descricao"));
                forma.setFormaPagamento(formaPagamento);
                forma.setId(resultado.getInt("idfinanceiro"));
                forma.setNumeroTitulo(resultado.getString("numero_titulo"));
                forma.setDataEmissao(Formatacao.ajustaDataDMAHora(resultado.getString("data_emissao")));
                forma.setVencimento(Formatacao.ajustaDataDMA(resultado.getString("data_vencimento")));
                forma.setValor(resultado.getDouble("valor_titulo"));
                if (resultado.getString("data_pagamento") == null) {
                    forma.setDataPagamento("");
                } else {

                    forma.setDataPagamento(Formatacao.ajustaDataDMA(resultado.getString("data_pagamento")));
                }
                forma.setValorPago(resultado.getDouble("valor_pago"));
                forma.setQuitado(resultado.getString("quitado").charAt(0));
                forma.setAtivo(resultado.getString("ativofinanceiro").charAt(0));
                cli.setId(resultado.getInt("id_cliente"));
                cli.setRazaoSocial(resultado.getString("razao_social"));
                cli.setCpfCnpj(resultado.getString("cpf_cnpj"));
                cli.setEndereco(resultado.getString("endereco"));
                forma.setCliente(cli);

                formas.add(forma);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar título " + e);
        }

        return formas;
    }
}
